SELECT
  DISTINCT p1.user_id
FROM
  Purchases p1
  JOIN Purchases p2 ON p2.user_id = p1.user_id
  AND p2.purchase_id <> p1.purchase_id
  AND DATEDIFF(p2.purchase_date, p1.purchase_date) BETWEEN 0
  AND 7
ORDER BY
  p1.user_id