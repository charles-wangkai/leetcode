SELECT
  t1.account_id,
  t1.day,
  sum(IF(t2.type = 'Deposit', t2.amount, - t2.amount)) AS balance
FROM
  Transactions t1
  JOIN Transactions t2 ON t2.account_id = t1.account_id
  AND t2.day <= t1.day
GROUP BY
  t1.account_id,
  t1.day
ORDER BY
  t1.account_id,
  t1.day