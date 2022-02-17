SELECT
  event_day AS DAY,
  emp_id,
  sum(out_time) - sum(in_time) AS total_time
FROM
  Employees
GROUP BY
  event_day,
  emp_id