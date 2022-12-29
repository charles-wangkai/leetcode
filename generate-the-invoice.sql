WITH t AS (
  SELECT
    c.invoice_id,
    sum(p.price * c.quantity) AS price
  FROM
    Products p
    JOIN Purchases c ON c.product_id = p.product_id
  GROUP BY
    c.invoice_id
)
SELECT
  c.product_id,
  c.quantity,
  p.price * c.quantity AS price
FROM
  (
    SELECT
      invoice_id
    FROM
      t
    WHERE
      NOT EXISTS (
        SELECT
          1
        FROM
          t t1
        WHERE
          t1.price > t.price
          OR (
            t1.price = t.price
            AND t1.invoice_id < t.invoice_id
          )
      )
  ) t2
  JOIN Purchases c ON c.invoice_id = t2.invoice_id
  JOIN Products p ON p.product_id = c.product_id