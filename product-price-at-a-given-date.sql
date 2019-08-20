SELECT
    t1.product_id,
    COALESCE(t2.new_price, 10) AS price
FROM (
    SELECT DISTINCT product_id
    FROM Products
) t1
LEFT OUTER JOIN (
    SELECT
        t.product_id,
        p.new_price
    FROM (
        SELECT
            product_id,
            MAX(change_date) AS last_change_date
        FROM Products
        WHERE change_date <= '2019-08-16'
        GROUP BY product_id
    ) t
    JOIN Products p
    ON t.product_id = p.product_id
    AND t.last_change_date = p.change_date
) t2
ON t1.product_id = t2.product_id