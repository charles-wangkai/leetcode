SELECT
  m.employee_id,
  m.name,
  count(*) AS reports_count,
  round(avg(r.age)) AS average_age
FROM
  Employees r
  JOIN Employees m ON m.employee_id = r.reports_to
GROUP BY
  m.employee_id
ORDER BY
  m.employee_id