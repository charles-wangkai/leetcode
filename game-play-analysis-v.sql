SELECT
    install_dt,
    count(*) AS installs,
    round(sum(is_retain) / count(*), 2) AS Day1_retention
FROM
    (
        SELECT
            player_id,
            install_dt,
            CASE
                WHEN EXISTS (
                    SELECT
                        1
                    FROM
                        Activity a
                    WHERE
                        a.player_id = t.player_id
                        AND a.event_date = date_add(t.install_dt, INTERVAL 1 DAY)
                ) THEN 1
                ELSE 0
            END AS is_retain
        FROM
            (
                SELECT
                    player_id,
                    min(event_date) AS install_dt
                FROM
                    Activity
                GROUP BY
                    player_id
            ) t
    ) t1
GROUP BY
    install_dt