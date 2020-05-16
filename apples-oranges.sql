SELECT
    sale_date,
    SUM(IF(fruit = 'apples', sold_num, 0)) - SUM(IF(fruit = 'oranges', sold_num, 0)) AS diff
FROM Sales
GROUP BY sale_date
ORDER BY sale_date