SELECT
    u.name,
    t.balance
FROM
    (
        SELECT
            account,
            sum(amount) AS balance
        FROM
            Transactions
        GROUP BY
            account
    ) t
    JOIN Users u ON u.account = t.account
WHERE
    t.balance > 10000