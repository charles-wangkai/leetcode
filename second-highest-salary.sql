SELECT
  CASE
    count(*)
    WHEN 0 THEN NULL
    ELSE Salary
  END AS SecondHighestSalary
FROM
  (
    SELECT
      DISTINCT Salary
    FROM
      Employee
    ORDER BY
      Salary DESC
    LIMIT
      1, 1
  ) SecondHighestSalary