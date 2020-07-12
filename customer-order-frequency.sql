SELECT
    customer_id,
    name
FROM Customers
WHERE customer_id in (
    SELECT o.customer_id
    FROM Orders o
    JOIN Product p
    ON p.product_id = o.product_id
    WHERE YEAR(order_date) = 2020
    AND MONTH(order_date) = 6
    GROUP BY o.customer_id
    HAVING SUM(p.price * o.quantity) >= 100
)
AND customer_id in (
    SELECT o.customer_id
    FROM Orders o
    JOIN Product p
    ON p.product_id = o.product_id
    WHERE YEAR(order_date) = 2020
    AND MONTH(order_date) = 7
    GROUP BY o.customer_id
    HAVING SUM(p.price * o.quantity) >= 100
)