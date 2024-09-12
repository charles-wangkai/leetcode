# https://leetcode.com/problems/employee-task-duration-and-concurrent-tasks/solutions/5182774/mysql-variables/
WITH EVENTS AS (
  SELECT
    employee_id,
    start_time AS event_time,
    1 AS event_status
  FROM
    Tasks
  UNION
  ALL
  SELECT
    employee_id,
    end_time AS event_time,
    -1 AS event_status
  FROM
    Tasks
  ORDER BY
    employee_id,
    event_time,
    event_status
)
SELECT
  employee_id,
  FLOOR(
    SUM(TIMESTAMPDIFF(MICROSECOND, start_time, end_time)) / 3600000000
  ) AS total_task_hours,
  MAX(event_count) AS max_concurrent_tasks
FROM
  (
    SELECT
      employee_id,
      CASE
        WHEN event_status = 1
        AND @event_count = 0 THEN @start_time := event_time
        ELSE @start_time
      END AS start_time,
      @event_count := @event_count + event_status AS event_count,
      CASE
        WHEN event_status = -1
        AND @event_count = 0 THEN event_time
        ELSE @start_time
      END AS end_time
    FROM
      EVENTS
      JOIN (
        SELECT
          @event_count := 0,
          @start_time := 0
      ) e
  ) e1
GROUP BY
  employee_id
ORDER BY
  employee_id