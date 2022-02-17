SELECT
  DISTINCT l3.employee_id
FROM
  Employees l1
  JOIN Employees l2 ON l2.manager_id = l1.employee_id
  JOIN Employees l3 ON l3.manager_id = l2.employee_id
WHERE
  l1.manager_id = 1
  AND l3.employee_id <> 1