SELECT
  d.name AS Department,
  e.name AS Employee,
  e.salary
FROM
  Employee e,
  Department d,
  (
    SELECT
      departmentId,
      max(salary) highestSalary
    FROM
      Employee
    GROUP BY
      DepartmentId
  ) s
WHERE
  e.departmentId = s.departmentId
  AND e.salary = s.highestSalary
  AND e.departmentId = d.id