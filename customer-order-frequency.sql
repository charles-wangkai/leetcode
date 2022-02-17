SELECT
    customer_id,
    name
FROM
    Customers
WHERE
    customer_id IN (
        SELECT
            o.customer_id
        FROM
            Orders o
            JOIN Product p ON p.product_id = o.product_id
        WHERE
            year(order_date) = 2020
            AND MONTH(order_date) = 6
        GROUP BY
            o.customer_id
        HAVING
            sum(p.price * o.quantity) >= 100
    )
    AND customer_id IN (
        SELECT
            o.customer_id
        FROM
            Orders o
            JOIN Product p ON p.product_id = o.product_id
        WHERE
            year(order_date) = 2020
            AND MONTH(order_date) = 7
        GROUP BY
            o.customer_id
        HAVING
            sum(p.price * o.quantity) >= 100
    )