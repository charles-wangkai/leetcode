SELECT
    customer_id,
    customer_name
FROM Customers
WHERE customer_id IN (
    SELECT customer_id
    FROM Orders
    WHERE product_name = 'A'
)
AND customer_id IN (
    SELECT customer_id
    FROM Orders
    WHERE product_name = 'B'
)
AND customer_id NOT IN (
    SELECT customer_id
    FROM Orders
    WHERE product_name = 'C'
)
ORDER BY customer_id