SELECT
  p.product_id,
  p.product_name
FROM
  Product p,
  Sales s
WHERE
  p.product_id = s.product_id
GROUP BY
  p.product_id,
  p.product_name
HAVING
  min(s.sale_date) >= '2019-01-01'
  AND max(s.sale_date) <= '2019-03-31'