SELECT DISTINCT t1.account_id
FROM (
  SELECT
    t.account_id,
    YEAR(t.day) AS year,
    MONTH(t.day) AS month
  FROM Transactions t
  JOIN Accounts a
  ON a.account_id = t.account_id
  WHERE t.type = 'Creditor'
  GROUP BY a.account_id, a.max_income, YEAR(t.day), MONTH(t.day)
  HAVING SUM(t.amount) > a.max_income
) t1
JOIN (
  SELECT
    t.account_id,
    YEAR(DATE_ADD(t.day, INTERVAL 1 MONTH)) AS year,
    MONTH(DATE_ADD(t.day, INTERVAL 1 MONTH)) AS month
  FROM Transactions t
  JOIN Accounts a
  ON a.account_id = t.account_id
  WHERE t.type = 'Creditor'
  GROUP BY a.account_id, a.max_income, YEAR(DATE_ADD(t.day, INTERVAL 1 MONTH)), MONTH(DATE_ADD(t.day, INTERVAL 1 MONTH))
  HAVING SUM(t.amount) > a.max_income
) t2
ON t2.account_id = t1.account_id
AND t2.year = t1.year
AND t2.month = t1.month