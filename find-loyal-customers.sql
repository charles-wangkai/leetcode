SELECT
  customer_id
FROM
  customer_transactions
GROUP BY
  customer_id
HAVING
  sum(IF(transaction_type = 'purchase', 1, 0)) >= 3
  AND datediff(max(transaction_date), min(transaction_date)) >= 30
  AND sum(IF(transaction_type = 'refund', 1, 0)) / count(*) < 0.2
ORDER BY
  customer_id