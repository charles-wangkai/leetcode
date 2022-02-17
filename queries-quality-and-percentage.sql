SELECT
    query_name,
    round(
        avg(cast(rating AS decimal(10, 6)) / position),
        2
    ) AS quality,
    round(sum(IF(rating < 3, 1, 0)) / count(*) * 100, 2) AS poor_query_percentage
FROM
    Queries
GROUP BY
    query_name