SELECT
    u.user_id,
    u.user_name,
    u.credit + coalesce(t2.income, 0) - coalesce(t1.expense, 0) AS credit,
    IF(
        u.credit + coalesce(t2.income, 0) - coalesce(t1.expense, 0) < 0,
        'Yes',
        'No'
    ) AS credit_limit_breached
FROM
    Users u
    LEFT OUTER JOIN (
        SELECT
            u.user_id,
            sum(t.amount) AS expense
        FROM
            Users u
            JOIN Transactions t ON t.paid_by = u.user_id
        GROUP BY
            u.user_id
    ) t1 ON t1.user_id = u.user_id
    LEFT OUTER JOIN (
        SELECT
            u.user_id,
            sum(t.amount) AS income
        FROM
            Users u
            JOIN Transactions t ON t.paid_to = u.user_id
        GROUP BY
            u.user_id
    ) t2 ON t2.user_id = u.user_id