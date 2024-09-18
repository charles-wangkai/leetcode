WITH t AS (
  SELECT
    t.customer_id,
    p.category,
    count(*) AS cnt,
    max(transaction_date) AS max_transaction_date
  FROM
    Transactions t
    JOIN Products p ON p.product_id = t.product_id
  GROUP BY
    t.customer_id,
    p.category
),
t1 AS (
  SELECT
    customer_id,
    category
  FROM
    t
  WHERE
    NOT EXISTS (
      SELECT
        1
      FROM
        t t1
      WHERE
        t1.customer_id = t.customer_id
        AND (
          t1.cnt > t.cnt
          OR (
            t1.cnt = t.cnt
            AND max_transaction_date > t.max_transaction_date
          )
        )
    )
)
SELECT
  t2.customer_id,
  t2.total_amount,
  t2.transaction_count,
  t2.unique_categories,
  t2.avg_transaction_amount,
  t1.category AS top_category,
  t2.loyalty_score
FROM
  (
    SELECT
      s.customer_id,
      round(sum(s.amount), 2) AS total_amount,
      count(*) AS transaction_count,
      count(DISTINCT p.category) AS unique_categories,
      round(avg(s.amount), 2) AS avg_transaction_amount,
      round(count(*) * 10 + sum(s.amount) / 100, 2) AS loyalty_score
    FROM
      Transactions s
      JOIN Products p ON p.product_id = s.product_id
    GROUP BY
      s.customer_id
  ) t2
  JOIN t1 ON t1.customer_id = t2.customer_id
ORDER BY
  t2.loyalty_score DESC,
  t2.customer_id