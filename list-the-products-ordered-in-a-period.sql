SELECT
    p.product_name,
    t.unit
FROM
    (
        SELECT
            product_id,
            sum(unit) AS unit
        FROM
            Orders
        WHERE
            year(order_date) = 2020
            AND MONTH(order_date) = 2
        GROUP BY
            product_id
    ) t
    JOIN Products p ON p.product_id = t.product_id
WHERE
    t.unit >= 100