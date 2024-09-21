# https://leetcode.com/problems/find-overlapping-shifts-ii/solutions/5699754/100-mysql-ctes-for-shift-overlap-analysis/
WITH shift_changes AS (
  SELECT
    employee_id,
    DATE(start_time) AS shift_date,
    start_time AS change_time,
    1 AS shift_change
  FROM
    EmployeeShifts
  UNION
  ALL
  SELECT
    employee_id,
    DATE(end_time) AS shift_date,
    end_time AS change_time,
    -1 AS shift_change
  FROM
    EmployeeShifts
),
concurrent_shifts AS (
  SELECT
    employee_id,
    shift_date,
    change_time,
    SUM(shift_change) OVER (
      PARTITION BY employee_id,
      shift_date
      ORDER BY
        change_time
    ) AS concurrent_count
  FROM
    shift_changes
),
max_overlaps AS (
  SELECT
    employee_id,
    MAX(concurrent_count) AS max_overlapping_shifts
  FROM
    concurrent_shifts
  GROUP BY
    employee_id
),
overlap_durations AS (
  SELECT
    e1.employee_id,
    SUM(
      TIMESTAMPDIFF(
        MINUTE,
        GREATEST(e1.start_time, e2.start_time),
        LEAST(e1.end_time, e2.end_time)
      )
    ) AS total_overlap_duration
  FROM
    EmployeeShifts e1
    JOIN EmployeeShifts e2 ON e1.employee_id = e2.employee_id
    AND e1.start_time < e2.start_time
    AND e1.end_time > e2.start_time
  GROUP BY
    e1.employee_id
)
SELECT
  e.employee_id,
  COALESCE(m.max_overlapping_shifts, 1) AS max_overlapping_shifts,
  COALESCE(o.total_overlap_duration, 0) AS total_overlap_duration
FROM
  (
    SELECT
      DISTINCT employee_id
    FROM
      EmployeeShifts
  ) e
  LEFT JOIN max_overlaps m ON e.employee_id = m.employee_id
  LEFT JOIN overlap_durations o ON e.employee_id = o.employee_id
ORDER BY
  e.employee_id;