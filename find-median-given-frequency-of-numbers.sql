SELECT
    (lt.num + gt.num) / 2 AS median
FROM
    (
        SELECT
            *
        FROM
            (
                SELECT
                    n1.num,
                    sum(n2.frequency) AS lte_num
                FROM
                    Numbers n1
                    JOIN Numbers n2 ON n2.num <= n1.num
                GROUP BY
                    n1.num
            ) t
        WHERE
            lte_num >= (
                SELECT
                    sum(frequency) / 2
                FROM
                    Numbers
            )
        ORDER BY
            t.num
        LIMIT
            1
    ) lt, (
        SELECT
            *
        FROM
            (
                SELECT
                    n1.num,
                    sum(n2.frequency) AS gte_num
                FROM
                    Numbers n1
                    JOIN Numbers n2 ON n2.num >= n1.num
                GROUP BY
                    n1.num
            ) t
        WHERE
            gte_num >= (
                SELECT
                    sum(frequency) / 2
                FROM
                    Numbers
            )
        ORDER BY
            t.num DESC
        LIMIT
            1
    ) gt