SELECT
  name AS Employee
FROM
  Employee e
WHERE
  EXISTS (
    SELECT
      1
    FROM
      Employee m
    WHERE
      m.id = e.managerId
      AND e.salary > m.salary
  )