CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT BEGIN RETURN (
  SELECT
    CASE
      WHEN count(*) < N THEN NULL
      ELSE min(Salary)
    END
  FROM
    (
      SELECT
        DISTINCT Salary
      FROM
        Employee
      ORDER BY
        Salary DESC
      LIMIT
        N
    ) HighestSalary
);

END