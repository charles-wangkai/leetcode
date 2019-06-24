SELECT s.buyer_id
FROM (SELECT DISTINCT buyer_id FROM Sales) s
WHERE EXISTS (
        SELECT 1
        FROM Sales s1, Product p
        WHERE p.product_id = s1.product_id
        AND p.product_name = 'S8'
        AND s1.buyer_id = s.buyer_id
      )
      AND NOT EXISTS (
        SELECT 1
        FROM Sales s1, Product p
        WHERE p.product_id = s1.product_id
        AND p.product_name = 'iPhone'
        AND s1.buyer_id = s.buyer_id
      )