SELECT
  datediff(t1.dt, '2023-11-01') DIV 7 + 1 AS week_of_month,
  t2.membership,
  coalesce(
    (
      SELECT
        sum(p.amount_spend)
      FROM
        Purchases p
        JOIN Users u ON u.user_id = p.user_id
      WHERE
        p.purchase_date = t1.dt
        AND u.membership = t2.membership
    ),
    0
  ) AS total_amount
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
  ) t1,
  (
    SELECT
      'Premium' AS membership
    UNION
    SELECT
      'VIP' AS membership
  ) t2
ORDER BY
  week_of_month,
  t2.membership