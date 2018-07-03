SELECT CASE COUNT(*)
         WHEN 0 THEN NULL
         ELSE Salary
       END
  FROM (
    SELECT DISTINCT Salary
      FROM Employee
    ORDER BY Salary DESC
    LIMIT 1, 1
  ) SecondHighestSalary