SELECT
    date_format(order_date, "%Y-%m") AS MONTH,
    count(*) AS order_count,
    count(DISTINCT customer_id) AS customer_count
FROM
    Orders
WHERE
    invoice > 20
GROUP BY
    date_format(order_date, "%Y-%m")