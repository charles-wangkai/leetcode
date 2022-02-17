SELECT
  customer_number
FROM
  orders
GROUP BY
  customer_number
HAVING
  count(*) = (
    SELECT
      max(ORDER_CNT)
    FROM
      (
        SELECT
          count(*) AS ORDER_CNT
        FROM
          orders
        GROUP BY
          customer_number
      ) t
  )