SELECT
    round(
        sum(
            IF(
                d.order_date = d.customer_pref_delivery_date,
                1,
                0
            )
        ) / count(DISTINCT d.customer_id) * 100,
        2
    ) AS immediate_percentage
FROM
    Delivery d
    JOIN (
        SELECT
            customer_id,
            min(order_date) AS min_order_date
        FROM
            Delivery
        GROUP BY
            customer_id
    ) t ON t.customer_id = d.customer_id
    AND t.min_order_date = d.order_date