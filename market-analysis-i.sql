SELECT
    u.user_id AS buyer_id,
    u.join_date,
    COALESCE(t.orders_in_2019, 0) AS orders_in_2019
FROM Users u
LEFT OUTER JOIN (
    SELECT
        buyer_id,
        COUNT(*) AS orders_in_2019
    FROM Orders
    WHERE YEAR(order_date) = 2019
    GROUP BY buyer_id
) t
ON u.user_id = t.buyer_id