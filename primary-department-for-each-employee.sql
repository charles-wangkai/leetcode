SELECT
  employee_id,
  department_id
FROM
  Employee
WHERE
  primary_flag = 'Y'
UNION
SELECT
  e.employee_id,
  e.department_id
FROM
  (
    SELECT
      employee_id
    FROM
      Employee
    GROUP BY
      employee_id
    HAVING
      count(*) = 1
  ) t
  JOIN Employee e ON e.employee_id = t.employee_id