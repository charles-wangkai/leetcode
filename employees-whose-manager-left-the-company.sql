SELECT
  e1.employee_id
FROM
  Employees e1
  LEFT OUTER JOIN Employees e2 ON e2.employee_id = e1.manager_id
WHERE
  e1.salary < 30000
  AND e1.manager_id IS NOT NULL
  AND e2.employee_id IS NULL
ORDER BY
  e1.employee_id