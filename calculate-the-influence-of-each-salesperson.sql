SELECT
  p.salesperson_id,
  p.name,
  coalesce(sum(s.price), 0) AS total
FROM
  Sales s
  JOIN Customer c ON c.customer_id = s.customer_id
  RIGHT OUTER JOIN Salesperson p ON p.salesperson_id = c.salesperson_id
GROUP BY
  p.salesperson_id,
  p.name