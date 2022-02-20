SELECT
  employee_id
FROM
  (
    SELECT
      e.employee_id
    FROM
      Employees e
      LEFT JOIN Salaries s ON e.employee_id = s.employee_id
    WHERE
      s.employee_id IS NULL
    UNION
    SELECT
      s.employee_id
    FROM
      Employees e
      RIGHT JOIN Salaries s ON e.employee_id = s.employee_id
    WHERE
      e.employee_id IS NULL
  ) t
ORDER BY
  employee_id