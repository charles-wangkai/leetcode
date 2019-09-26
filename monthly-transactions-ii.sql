SELECT
    month,
    country,
    COALESCE(approved_count, 0) AS approved_count,
    COALESCE(approved_amount, 0) AS approved_amount,
    COALESCE(chargeback_count, 0) AS chargeback_count,
    COALESCE(chargeback_amount, 0) AS chargeback_amount
FROM (
    SELECT
        t1.month,
        t1.country,
        t1.approved_count,
        t1.approved_amount,
        t2.chargeback_count,
        t2.chargeback_amount
    FROM (
        SELECT
            DATE_FORMAT(trans_date, '%Y-%m') AS month,
            country,
            COUNT(*) AS approved_count,
            SUM(amount) AS approved_amount
        FROM Transactions
        WHERE state = 'approved'
        GROUP BY DATE_FORMAT(trans_date, '%Y-%m'), country
    ) t1
    LEFT OUTER JOIN (
        SELECT
            DATE_FORMAT(c.trans_date, '%Y-%m') AS month,
            t.country,
            COUNT(*) AS chargeback_count,
            SUM(t.amount) AS chargeback_amount
        FROM Chargebacks c
        JOIN Transactions t
        ON t.id = c.trans_id
        GROUP BY DATE_FORMAT(c.trans_date, '%Y-%m'), t.country
    ) t2
    ON t1.month = t2.month
    AND t1.country = t2.country

    UNION

    SELECT
        t2.month,
        t2.country,
        t1.approved_count,
        t1.approved_amount,
        t2.chargeback_count,
        t2.chargeback_amount
    FROM (
        SELECT
            DATE_FORMAT(trans_date, '%Y-%m') AS month,
            country,
            COUNT(*) AS approved_count,
            SUM(amount) AS approved_amount
        FROM Transactions
        WHERE state = 'approved'
        GROUP BY DATE_FORMAT(trans_date, '%Y-%m'), country
    ) t1
    RIGHT OUTER JOIN (
        SELECT
            DATE_FORMAT(c.trans_date, '%Y-%m') AS month,
            t.country,
            COUNT(*) AS chargeback_count,
            SUM(t.amount) AS chargeback_amount
        FROM Chargebacks c
        JOIN Transactions t
        ON t.id = c.trans_id
        GROUP BY DATE_FORMAT(c.trans_date, '%Y-%m'), t.country
    ) t2
    ON t1.month = t2.month
    AND t1.country = t2.country
) t