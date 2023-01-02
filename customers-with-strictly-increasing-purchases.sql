WITH t AS (
  SELECT
    customer_id,
    year(order_date) AS year,
    sum(price) AS price_in_year
  FROM
    Orders
  GROUP BY
    customer_id,
    year(order_date)
)
SELECT
  customer_id
FROM
  t
WHERE
  customer_id NOT IN (
    SELECT
      customer_id
    FROM
      t
    WHERE
      EXISTS (
        SELECT
          1
        FROM
          t t1
        WHERE
          t1.customer_id = t.customer_id
          AND t1.year < t.year
          AND t1.price_in_year >= t.price_in_year
      )
  )
GROUP BY
  customer_id
HAVING
  max(year) - min(year) + 1 = count(*)