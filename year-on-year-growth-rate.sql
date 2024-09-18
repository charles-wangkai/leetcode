WITH t AS (
  SELECT
    product_id,
    year(transaction_date) AS year,
    sum(spend) AS year_spend
  FROM
    user_transactions
  GROUP BY
    product_id,
    year(transaction_date)
)
SELECT
  t.year,
  t.product_id,
  t.year_spend AS curr_year_spend,
  t1.year_spend AS prev_year_spend,
  round(t.year_spend / t1.year_spend * 100 - 100, 2) AS yoy_rate
FROM
  t
  LEFT OUTER JOIN t t1 ON t1.product_id = t.product_id
  AND t1.year + 1 = t.year
ORDER BY
  t.product_id,
  t.year