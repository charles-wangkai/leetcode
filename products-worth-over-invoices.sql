SELECT
  p.name,
  COALESCE(SUM(rest), 0) AS rest,
  COALESCE(SUM(paid), 0) AS paid,
  COALESCE(SUM(canceled), 0) AS canceled,
  COALESCE(SUM(refunded), 0) AS refunded
FROM Product p
LEFT OUTER JOIN Invoice i
ON i.product_id = p.product_id
GROUP BY p.product_id
ORDER BY p.name