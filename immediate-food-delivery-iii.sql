SELECT
  order_date,
  round(
    (
      SELECT
        count(*)
      FROM
        Delivery d
      WHERE
        d.order_date = t1.order_date
        AND d.customer_pref_delivery_date = t1.order_date
    ) / (
      SELECT
        count(*)
      FROM
        Delivery d
      WHERE
        d.order_date = t1.order_date
    ) * 100,
    2
  ) AS immediate_percentage
FROM
  (
    SELECT
      DISTINCT order_date
    FROM
      Delivery
  ) t1
ORDER BY
  order_date