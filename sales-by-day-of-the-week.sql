SELECT
    i.item_category AS Category,
    sum(IF(dayofweek(o.order_date) = 2, o.quantity, 0)) AS Monday,
    sum(IF(dayofweek(o.order_date) = 3, o.quantity, 0)) AS Tuesday,
    sum(IF(dayofweek(o.order_date) = 4, o.quantity, 0)) AS Wednesday,
    sum(IF(dayofweek(o.order_date) = 5, o.quantity, 0)) AS Thursday,
    sum(IF(dayofweek(o.order_date) = 6, o.quantity, 0)) AS Friday,
    sum(IF(dayofweek(o.order_date) = 7, o.quantity, 0)) AS Saturday,
    sum(IF(dayofweek(o.order_date) = 1, o.quantity, 0)) AS Sunday
FROM
    Items i
    LEFT OUTER JOIN Orders o ON i.item_id = o.item_id
GROUP BY
    i.item_category
ORDER BY
    i.item_category