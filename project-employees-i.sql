SELECT
  p.project_id,
  round(avg(e.experience_years), 2) AS average_years
FROM
  Project p,
  Employee e
WHERE
  p.employee_id = e.employee_id
GROUP BY
  project_id