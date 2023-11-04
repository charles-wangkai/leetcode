# https://leetcode.com/problems/consecutive-transactions-with-increasing-amounts/solutions/3576917/best-explaination-step-by-step-guide/
WITH t1 AS (
  SELECT
    tr1.customer_id,
    tr1.transaction_date
  FROM
    Transactions tr1
    JOIN Transactions tr2 ON tr2.customer_id = tr1.customer_id
    AND datediff(tr2.transaction_date, tr1.transaction_date) = 1
    AND tr2.amount > tr1.amount
),
t2 AS (
  SELECT
    customer_id,
    transaction_date,
    row_number() over (
      PARTITION by customer_id
      ORDER BY
        transaction_date
    ) AS row_num
  FROM
    t1
),
t3 AS (
  SELECT
    customer_id,
    transaction_date,
    date_sub(transaction_date, INTERVAL row_num DAY) AS date_group
  FROM
    t2
)
SELECT
  customer_id,
  min(transaction_date) AS consecutive_start,
  date_add(min(transaction_date), INTERVAL count(*) DAY) AS consecutive_end
FROM
  t3
GROUP BY
  customer_id,
  date_group
HAVING
  count(*) > 1
ORDER BY
  customer_id