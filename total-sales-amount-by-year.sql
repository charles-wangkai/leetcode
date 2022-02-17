SELECT
    CONVERT(product_id, char) AS PRODUCT_ID,
    product_name AS PRODUCT_NAME,
    report_year AS REPORT_YEAR,
    average_daily_sales * day_num AS TOTAL_AMOUNT
FROM
    (
        SELECT
            p.product_id,
            p.product_name,
            y.report_year,
            s.average_daily_sales,
            datediff(
                least(s.period_end, y.end_date),
                greatest(s.period_start, y.begin_date)
            ) + 1 AS day_num
        FROM
            Sales s
            JOIN Product p ON p.product_id = s.product_id
            JOIN (
                SELECT
                    "2018" AS report_year,
                    '2018-01-01' AS begin_date,
                    '2018-12-31' AS end_date
                UNION
                SELECT
                    "2019",
                    '2019-01-01',
                    '2019-12-31'
                UNION
                SELECT
                    "2020",
                    '2020-01-01',
                    '2020-12-31'
            ) y
    ) t
WHERE
    day_num > 0
ORDER BY
    product_id,
    report_year