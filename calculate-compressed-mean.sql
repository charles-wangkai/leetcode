SELECT
  round(
    sum(item_count * order_occurrences) / sum(order_occurrences),
    2
  ) AS average_items_per_order
FROM
  Orders