SELECT transaction_id
FROM Transactions t
WHERE NOT EXISTS (
  SELECT 1
  FROM Transactions t1
  WHERE DATE(t1.day) = DATE(t.day)
  AND t1.amount > t.amount
)
ORDER BY transaction_id