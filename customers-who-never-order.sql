SELECT Name
  FROM Customers c
 WHERE NOT EXISTS (
        SELECT 1
          FROM Orders o
         WHERE o.CustomerId = c.Id
       )