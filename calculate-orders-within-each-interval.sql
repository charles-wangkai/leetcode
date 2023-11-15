SELECT
  interval_no,
  sum(order_count) AS total_orders
FROM
  (
    SELECT
      (MINUTE - 1) DIV 6 + 1 AS interval_no,
      order_count
    FROM
      Orders
  ) t
GROUP BY
  interval_no