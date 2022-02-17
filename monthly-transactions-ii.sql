SELECT
    MONTH,
    country,
    coalesce(approved_count, 0) AS approved_count,
    coalesce(approved_amount, 0) AS approved_amount,
    coalesce(chargeback_count, 0) AS chargeback_count,
    coalesce(chargeback_amount, 0) AS chargeback_amount
FROM
    (
        SELECT
            t1.month,
            t1.country,
            t1.approved_count,
            t1.approved_amount,
            t2.chargeback_count,
            t2.chargeback_amount
        FROM
            (
                SELECT
                    date_format(trans_date, '%Y-%m') AS MONTH,
                    country,
                    count(*) AS approved_count,
                    sum(amount) AS approved_amount
                FROM
                    Transactions
                WHERE
                    state = 'approved'
                GROUP BY
                    date_format(trans_date, '%Y-%m'),
                    country
            ) t1
            LEFT OUTER JOIN (
                SELECT
                    date_format(c.trans_date, '%Y-%m') AS MONTH,
                    t.country,
                    count(*) AS chargeback_count,
                    sum(t.amount) AS chargeback_amount
                FROM
                    Chargebacks c
                    JOIN Transactions t ON t.id = c.trans_id
                GROUP BY
                    date_format(c.trans_date, '%Y-%m'),
                    t.country
            ) t2 ON t1.month = t2.month
            AND t1.country = t2.country
        UNION
        SELECT
            t2.month,
            t2.country,
            t1.approved_count,
            t1.approved_amount,
            t2.chargeback_count,
            t2.chargeback_amount
        FROM
            (
                SELECT
                    date_format(trans_date, '%Y-%m') AS MONTH,
                    country,
                    count(*) AS approved_count,
                    sum(amount) AS approved_amount
                FROM
                    Transactions
                WHERE
                    state = 'approved'
                GROUP BY
                    date_format(trans_date, '%Y-%m'),
                    country
            ) t1
            RIGHT OUTER JOIN (
                SELECT
                    date_format(c.trans_date, '%Y-%m') AS MONTH,
                    t.country,
                    count(*) AS chargeback_count,
                    sum(t.amount) AS chargeback_amount
                FROM
                    Chargebacks c
                    JOIN Transactions t ON t.id = c.trans_id
                GROUP BY
                    date_format(c.trans_date, '%Y-%m'),
                    t.country
            ) t2 ON t1.month = t2.month
            AND t1.country = t2.country
    ) t