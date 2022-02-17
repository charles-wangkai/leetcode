SELECT
  order_id
FROM
  (
    SELECT
      order_id,
      max(quantity) AS max_quantity
    FROM
      OrdersDetails
    GROUP BY
      order_id
  ) t
WHERE
  t.max_quantity > (
    SELECT
      max(avg_quantity)
    FROM
      (
        SELECT
          order_id,
          avg(quantity) AS avg_quantity
        FROM
          OrdersDetails
        GROUP BY
          order_id
      ) t1
  )