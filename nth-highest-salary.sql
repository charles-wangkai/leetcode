CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  RETURN (
    SELECT CASE
             WHEN COUNT(*) < N THEN NULL
             ELSE MIN(Salary)
           END
      FROM (
        SELECT DISTINCT Salary
          FROM Employee
        ORDER BY Salary DESC
        LIMIT N
      ) HighestSalary
  );
END