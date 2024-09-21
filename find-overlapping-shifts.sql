SELECT
  s1.employee_id,
  count(*) AS overlapping_shifts
FROM
  EmployeeShifts s1
  JOIN EmployeeShifts s2 ON s2.employee_id = s1.employee_id
  AND s2.start_time > s1.start_time
  AND s2.start_time < s1.end_time
GROUP BY
  s1.employee_id
ORDER BY
  s1.employee_id