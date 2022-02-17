SELECT
    u.user_id AS seller_id,
    IF(t.seller_id IS NULL, 'no', 'yes') AS 2nd_item_fav_brand
FROM
    Users u
    LEFT OUTER JOIN (
        SELECT
            o.seller_id,
            i.item_brand
        FROM
            Orders o,
            Items i
        WHERE
            o.item_id = i.item_id
            AND (
                SELECT
                    count(*)
                FROM
                    Orders o1
                WHERE
                    o1.seller_id = o.seller_id
                    AND o1.order_date < o.order_date
            ) = 1
    ) t ON u.user_id = t.seller_id
    AND u.favorite_brand = t.item_brand