SELECT e.Name
  FROM (
    SELECT ManagerId, COUNT(*) as cnt
      FROM Employee
    GROUP BY ManagerId
    HAVING cnt >= 5
  ) m
  JOIN Employee e
    ON m.ManagerId = e.Id