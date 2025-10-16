SELECT
  customer_id,
  total_orders,
  round(peak_hour_percentage) AS peak_hour_percentage,
  round(average_rating, 2) AS average_rating
FROM
  (
    SELECT
      customer_id,
      count(*) AS total_orders,
      sum(
        IF(
          time(order_timestamp) BETWEEN '11:00:00'
          AND '14:00:00'
          OR time(order_timestamp) BETWEEN '18:00:00'
          AND '21:00:00',
          1,
          0
        )
      ) / count(*) * 100 AS peak_hour_percentage,
      avg(order_rating) AS average_rating,
      count(order_rating) / count(*) AS rating_ratio
    FROM
      restaurant_orders
    GROUP BY
      customer_id
  ) t
WHERE
  total_orders >= 3
  AND peak_hour_percentage >= 60
  AND average_rating >= 4
  AND rating_ratio >= 0.5
ORDER BY
  average_rating DESC,
  customer_id DESC