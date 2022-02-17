SELECT
    u.user_id AS buyer_id,
    u.join_date,
    coalesce(t.orders_in_2019, 0) AS orders_in_2019
FROM
    Users u
    LEFT OUTER JOIN (
        SELECT
            buyer_id,
            count(*) AS orders_in_2019
        FROM
            Orders
        WHERE
            year(order_date) = 2019
        GROUP BY
            buyer_id
    ) t ON u.user_id = t.buyer_id