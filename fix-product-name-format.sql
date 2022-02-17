SELECT
    lower(trim(product_name)) AS product_name,
    substr(sale_date, 1, 7) AS sale_date,
    count(*) AS total
FROM
    Sales
GROUP BY
    1,
    2
ORDER BY
    1,
    2