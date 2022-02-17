SELECT
    sale_date,
    sum(IF(fruit = 'apples', sold_num, 0)) - sum(IF(fruit = 'oranges', sold_num, 0)) AS diff
FROM
    Sales
GROUP BY
    sale_date
ORDER BY
    sale_date