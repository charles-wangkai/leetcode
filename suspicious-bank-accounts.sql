SELECT
  DISTINCT t1.account_id
FROM
  (
    SELECT
      t.account_id,
      year(t.day) AS year,
      MONTH(t.day) AS MONTH
    FROM
      Transactions t
      JOIN Accounts a ON a.account_id = t.account_id
    WHERE
      t.type = 'Creditor'
    GROUP BY
      a.account_id,
      a.max_income,
      year(t.day),
      MONTH(t.day)
    HAVING
      sum(t.amount) > a.max_income
  ) t1
  JOIN (
    SELECT
      t.account_id,
      year(date_add(t.day, INTERVAL 1 MONTH)) AS year,
      MONTH(date_add(t.day, INTERVAL 1 MONTH)) AS MONTH
    FROM
      Transactions t
      JOIN Accounts a ON a.account_id = t.account_id
    WHERE
      t.type = 'Creditor'
    GROUP BY
      a.account_id,
      a.max_income,
      year(date_add(t.day, INTERVAL 1 MONTH)),
      MONTH(date_add(t.day, INTERVAL 1 MONTH))
    HAVING
      sum(t.amount) > a.max_income
  ) t2 ON t2.account_id = t1.account_id
  AND t2.year = t1.year
  AND t2.month = t1.month