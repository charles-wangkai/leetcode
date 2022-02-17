SELECT
  transaction_id
FROM
  Transactions t
WHERE
  NOT EXISTS (
    SELECT
      1
    FROM
      Transactions t1
    WHERE
      date(t1.day) = date(t.day)
      AND t1.amount > t.amount
  )
ORDER BY
  transaction_id