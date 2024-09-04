SELECT
  datediff(purchase_date, '2023-11-01') DIV 7 + 1 AS week_of_month,
  purchase_date,
  sum(amount_spend) AS total_amount
FROM
  Purchases
WHERE
  weekday(purchase_date) = 4
GROUP BY
  purchase_date
ORDER BY
  purchase_date