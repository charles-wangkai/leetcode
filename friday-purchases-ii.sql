SELECT
  datediff(t.dt, '2023-11-01') DIV 7 + 1 AS week_of_month,
  t.dt AS purchase_date,
  coalesce(t1.total_amount, 0) AS total_amount
FROM
  (
    SELECT
      '2023-11-03' AS dt
    UNION
    SELECT
      '2023-11-10' AS dt
    UNION
    SELECT
      '2023-11-17' AS dt
    UNION
    SELECT
      '2023-11-24' AS dt
  ) t
  LEFT OUTER JOIN (
    SELECT
      purchase_date,
      sum(amount_spend) AS total_amount
    FROM
      Purchases
    WHERE
      weekday(purchase_date) = 4
    GROUP BY
      purchase_date
  ) t1 ON t1.purchase_date = t.dt
ORDER BY
  t.dt