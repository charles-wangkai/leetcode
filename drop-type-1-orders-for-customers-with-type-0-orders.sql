SELECT
  *
FROM
  Orders o
WHERE
  NOT EXISTS (
    SELECT
      1
    FROM
      Orders o1
    WHERE
      o1.customer_id = o.customer_id
      AND o.order_type = 1
      AND o1.order_type = 0
  )