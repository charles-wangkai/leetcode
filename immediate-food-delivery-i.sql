SELECT
  round(
    sum(
      IF(order_date = customer_pref_delivery_date, 1, 0)
    ) / count(*) * 100,
    2
  ) AS immediate_percentage
FROM
  Delivery