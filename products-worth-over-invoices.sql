SELECT
  p.name,
  coalesce(sum(rest), 0) AS rest,
  coalesce(sum(paid), 0) AS paid,
  coalesce(sum(canceled), 0) AS canceled,
  coalesce(sum(refunded), 0) AS refunded
FROM
  Product p
  LEFT OUTER JOIN Invoice i ON i.product_id = p.product_id
GROUP BY
  p.product_id
ORDER BY
  p.name