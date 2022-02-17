SELECT
    *
FROM
    Accounts
WHERE
    id IN (
        SELECT
            id
        FROM
            Logins l
        WHERE
            (
                SELECT
                    count(DISTINCT login_date)
                FROM
                    Logins l1
                WHERE
                    l1.id = l.id
                    AND l1.login_date BETWEEN l.login_date
                    AND date_add(l.login_date, INTERVAL 4 DAY)
            ) = 5
    )
ORDER BY
    id