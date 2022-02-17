SELECT
    *
FROM
    (
        SELECT
            'failed' AS period_state,
            sd.fail_date AS start_date,
            min(ed.fail_date) AS end_date
        FROM
            (
                SELECT
                    *
                FROM
                    Failed
                WHERE
                    fail_date BETWEEN '2019-01-01'
                    AND '2019-12-31'
                    AND (
                        fail_date = '2019-01-01'
                        OR date_sub(fail_date, INTERVAL 1 DAY) NOT IN (
                            SELECT
                                fail_date
                            FROM
                                Failed
                        )
                    )
            ) sd
            JOIN (
                SELECT
                    *
                FROM
                    Failed
                WHERE
                    fail_date BETWEEN '2019-01-01'
                    AND '2019-12-31'
                    AND (
                        fail_date = '2019-12-31'
                        OR date_add(fail_date, INTERVAL 1 DAY) NOT IN (
                            SELECT
                                fail_date
                            FROM
                                Failed
                        )
                    )
            ) ed ON sd.fail_date <= ed.fail_date
        GROUP BY
            sd.fail_date
        UNION
        SELECT
            'succeeded' AS period_state,
            sd.success_date AS start_date,
            min(ed.success_date) AS end_date
        FROM
            (
                SELECT
                    *
                FROM
                    Succeeded
                WHERE
                    success_date BETWEEN '2019-01-01'
                    AND '2019-12-31'
                    AND (
                        success_date = '2019-01-01'
                        OR date_sub(success_date, INTERVAL 1 DAY) NOT IN (
                            SELECT
                                success_date
                            FROM
                                Succeeded
                        )
                    )
            ) sd
            JOIN (
                SELECT
                    *
                FROM
                    Succeeded
                WHERE
                    success_date BETWEEN '2019-01-01'
                    AND '2019-12-31'
                    AND (
                        success_date = '2019-12-31'
                        OR date_add(success_date, INTERVAL 1 DAY) NOT IN (
                            SELECT
                                success_date
                            FROM
                                Succeeded
                        )
                    )
            ) ed ON sd.success_date <= ed.success_date
        GROUP BY
            sd.success_date
    ) t
ORDER BY
    start_date