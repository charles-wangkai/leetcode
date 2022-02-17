SELECT
    book_id,
    name
FROM
    Books b
WHERE
    available_from <= date_sub('2019-06-23', INTERVAL 1 MONTH)
    AND NOT EXISTS (
        SELECT
            1
        FROM
            Orders o
        WHERE
            o.book_id = b.book_id
            AND dispatch_date > date_sub('2019-06-23', INTERVAL 1 YEAR)
        GROUP BY
            o.book_id
        HAVING
            sum(o.quantity) >= 10
    )