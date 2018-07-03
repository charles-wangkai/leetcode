SELECT customer_number
  FROM orders
GROUP BY customer_number
HAVING COUNT(*) = (
    SELECT MAX(ORDER_CNT)
    FROM (
        SELECT COUNT(*) AS ORDER_CNT
          FROM orders
        GROUP BY customer_number
    ) t
)