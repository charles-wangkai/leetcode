SELECT
  product_id,
  (SELECT price FROM Products WHERE product_id = t.product_id AND store = 'store1') AS store1,
  (SELECT price FROM Products WHERE product_id = t.product_id AND store = 'store2') AS store2,
  (SELECT price FROM Products WHERE product_id = t.product_id AND store = 'store3') AS store3
FROM (
  SELECT DISTINCT product_id
  FROM Products
) t