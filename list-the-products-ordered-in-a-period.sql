SELECT
    p.product_name,
    t.unit
FROM (
    SELECT
        product_id,
        SUM(unit) AS unit
    FROM Orders
    WHERE YEAR(order_date) = 2020
    AND MONTH(order_date) = 2
    GROUP BY product_id
) t
JOIN Products p
ON p.product_id = t.product_id
WHERE t.unit >= 100