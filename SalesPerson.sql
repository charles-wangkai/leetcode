SELECT name
  FROM salesperson p
 WHERE NOT EXISTS (
        SELECT 1
        FROM orders o
        JOIN company c
          ON o.com_id = c.com_id
         AND c.name = 'RED'
        WHERE o.sales_id = p.sales_id
       )