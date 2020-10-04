SELECT s.seller_name
FROM Seller s
LEFT OUTER JOIN (
  SELECT seller_id
  FROM Orders
  WHERE YEAR(sale_date) = 2020
) t
ON t.seller_id = s.seller_id
WHERE t.seller_id IS NULL
ORDER BY s.seller_name