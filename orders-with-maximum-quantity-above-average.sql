SELECT order_id
FROM (
  SELECT
    order_id,
    MAX(quantity) AS max_quantity
  FROM OrdersDetails
  GROUP BY order_id
) t
WHERE t.max_quantity > (
  SELECT MAX(avg_quantity)
  FROM (
    SELECT
      order_id,
      AVG(quantity) AS avg_quantity
    FROM OrdersDetails
    GROUP BY order_id
  ) t1
)