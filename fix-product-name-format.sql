SELECT
    LOWER(TRIM(product_name)) AS product_name,
    SUBSTR(sale_date, 1, 7) AS sale_date,
    COUNT(*) AS total
FROM Sales
GROUP BY 1, 2
ORDER BY 1, 2