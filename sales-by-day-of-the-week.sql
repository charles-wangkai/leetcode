SELECT
    i.item_category as Category,
    SUM(IF(DAYOFWEEK(o.order_date) = 2, o.quantity, 0)) AS Monday,
    SUM(IF(DAYOFWEEK(o.order_date) = 3, o.quantity, 0)) AS Tuesday,
    SUM(IF(DAYOFWEEK(o.order_date) = 4, o.quantity, 0)) AS Wednesday,
    SUM(IF(DAYOFWEEK(o.order_date) = 5, o.quantity, 0)) AS Thursday,
    SUM(IF(DAYOFWEEK(o.order_date) = 6, o.quantity, 0)) AS Friday,
    SUM(IF(DAYOFWEEK(o.order_date) = 7, o.quantity, 0)) AS Saturday,
    SUM(IF(DAYOFWEEK(o.order_date) = 1, o.quantity, 0)) AS Sunday
FROM Items i
LEFT OUTER JOIN Orders o
ON i.item_id = o.item_id
GROUP BY i.item_category
ORDER BY i.item_category