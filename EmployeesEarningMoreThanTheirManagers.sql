SELECT Name
  FROM Employee e
 WHERE EXISTS (
        SELECT 1
          FROM Employee m
         WHERE m.Id = e.ManagerId
           AND e.Salary > m.Salary
       )