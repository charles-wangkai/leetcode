WITH t AS (
  SELECT
    product_id,
    year(purchase_date) AS year
  FROM
    Orders
  GROUP BY
    product_id,
    year(purchase_date)
  HAVING
    count(*) >= 3
)
SELECT
  DISTINCT t1.product_id
FROM
  t t1
  JOIN t t2 ON t2.product_id = t1.product_id
  AND t2.year = t1.year + 1