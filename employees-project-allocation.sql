SELECT
  p.employee_id,
  p.project_id,
  e.name AS employee_name,
  p.workload AS project_workload
FROM
  Project p
  JOIN Employees e ON e.employee_id = p.employee_id
WHERE
  p.workload > (
    SELECT
      avg(p1.workload)
    FROM
      Project p1
      JOIN Employees e1 ON e1.employee_id = p1.employee_id
    WHERE
      e1.team = e.team
  )
ORDER BY
  p.employee_id,
  p.project_id