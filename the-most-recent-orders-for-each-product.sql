SELECT
    p.product_name,
    p.product_id,
    o.order_id,
    o.order_date
FROM (
    SELECT
        product_id,
        MAX(order_date) AS max_order_date
    FROM Orders
    GROUP BY product_id
) t
JOIN Orders o
ON o.product_id = t.product_id
AND o.order_date = t.max_order_date
JOIN Products p
ON p.product_id = o.product_id
ORDER BY p.product_name, p.product_id, o.order_id