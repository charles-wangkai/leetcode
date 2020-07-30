SELECT
    c.name AS customer_name,
    c.customer_id,
    o.order_id,
    o.order_date
FROM Orders o
JOIN Customers c
ON c.customer_id = o.customer_id
WHERE (
    SELECT COUNT(*)
    FROM Orders o1
    WHERE o1.customer_id = o.customer_id
    AND o1.order_date >= o.order_date
) <= 3
ORDER BY c.name, c.customer_id, o.order_date DESC