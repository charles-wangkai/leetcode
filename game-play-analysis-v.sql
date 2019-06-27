SELECT install_dt,
       COUNT(*) AS installs,
       ROUND(SUM(is_retain) / COUNT(*), 2) AS Day1_retention
FROM (
    SELECT player_id,
           install_dt,
           CASE
               WHEN EXISTS (
                   SELECT 1
                   FROM Activity a
                   WHERE a.player_id = t.player_id
                   AND a.event_date = DATE_ADD(t.install_dt, INTERVAL 1 DAY)
               )
               THEN 1
               ELSE 0
           END AS is_retain
    FROM (
        SELECT player_id,
               MIN(event_date) AS install_dt
        FROM Activity
        GROUP BY player_id
    ) t
) t1
GROUP BY install_dt