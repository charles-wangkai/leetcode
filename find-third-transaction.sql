SELECT
  user_id,
  spend AS third_transaction_spend,
  transaction_date AS third_transaction_date
FROM
  Transactions t
WHERE
  (
    SELECT
      count(*)
    FROM
      Transactions t1
    WHERE
      t1.user_id = t.user_id
      AND t1.transaction_date < t.transaction_date
  ) = 2
  AND NOT EXISTS (
    SELECT
      1
    FROM
      Transactions t1
    WHERE
      t1.user_id = t.user_id
      AND t1.transaction_date < t.transaction_date
      AND t1.spend >= t.spend
  )
ORDER BY
  user_id