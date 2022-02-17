SELECT
    t2.customer_id,
    t2.product_id,
    p.product_name
FROM
    (
        SELECT
            customer_id,
            max(cnt) AS max_cnt
        FROM
            (
                SELECT
                    customer_id,
                    count(*) AS cnt
                FROM
                    Orders
                GROUP BY
                    customer_id,
                    product_id
            ) t
        GROUP BY
            customer_id
    ) t1
    JOIN (
        SELECT
            customer_id,
            product_id,
            count(*) AS cnt
        FROM
            Orders
        GROUP BY
            customer_id,
            product_id
    ) t2 ON t2.customer_id = t1.customer_id
    AND t2.cnt = t1.max_cnt
    JOIN Products p ON p.product_id = t2.product_id