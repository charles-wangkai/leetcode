# https://leetcode.com/problems/customers-with-maximum-number-of-transactions-on-consecutive-days/solutions/3802885/short-answer-using-to-days-rank/
WITH t1 AS (
  SELECT
    customer_id,
    date_sub(
      transaction_date,
      INTERVAL (
        rank() over (
          PARTITION by customer_id
          ORDER BY
            transaction_date
        )
      ) DAY
    ) AS gp
  FROM
    Transactions
),
t2 AS (
  SELECT
    customer_id,
    count(gp) AS consecutive_cnt
  FROM
    t1
  GROUP BY
    customer_id,
    gp
)
SELECT
  customer_id
FROM
  t2
WHERE
  consecutive_cnt = (
    SELECT
      max(consecutive_cnt)
    FROM
      t2
  )
ORDER BY
  customer_id