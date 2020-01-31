SELECT
    t1.transactions_count,
    COALESCE(t2.visits_count, 0) AS visits_count
FROM (
    SELECT transactions_count
    FROM (
        SELECT 0 AS transactions_count
        UNION
        SELECT @row := @row + 1 AS transactions_count
        FROM
            Transactions x,
            (SELECT @row := 0) n
    ) t11
    WHERE transactions_count <= (
        SELECT MAX(transactions_count)
        FROM (
            SELECT
                v.user_id,
                v.visit_date,
                COUNT(x.user_id) AS transactions_count
            FROM Visits v
            LEFT OUTER JOIN Transactions x
            ON x.user_id = v.user_id
            AND x.transaction_date = v.visit_date
            GROUP BY v.user_id, v.visit_date
        ) t12
    )
) t1
LEFT OUTER JOIN (
    SELECT
        transactions_count,
        COUNT(*) AS visits_count
    FROM (
        SELECT
            v.user_id,
            v.visit_date,
            COUNT(x.user_id) AS transactions_count
        FROM Visits v
        LEFT OUTER JOIN Transactions x
        ON x.user_id = v.user_id
        AND x.transaction_date = v.visit_date
        GROUP BY v.user_id, v.visit_date
    ) t21
    GROUP BY transactions_count
) t2
ON t2.transactions_count = t1.transactions_count
ORDER BY 1