WITH t AS (
  SELECT
    s.user_id,
    s.product_id,
    sum(s.quantity * p.price) AS spent
  FROM
    Sales s
    JOIN Product p ON p.product_id = s.product_id
  GROUP BY
    s.user_id,
    s.product_id
)
SELECT
  user_id,
  product_id
FROM
  t t1
WHERE
  NOT EXISTS (
    SELECT
      1
    FROM
      t t2
    WHERE
      t2.user_id = t1.user_id
      AND t2.spent > t1.spent
  )