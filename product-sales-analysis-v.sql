SELECT
  s.user_id,
  sum(s.quantity * p.price) AS spending
FROM
  Sales s
  JOIN Product p ON p.product_id = s.product_id
GROUP BY
  s.user_id
ORDER BY
  spending DESC,
  s.user_id