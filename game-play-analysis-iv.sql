SELECT ROUND(COUNT(*) / (SELECT COUNT(DISTINCT player_id) FROM Activity), 2) AS fraction
  FROM (
        SELECT 1
        FROM (
                SELECT player_id,
                       DATE_ADD(MIN(event_date), INTERVAL 1 DAY) AS second_date
                FROM Activity
                GROUP BY player_id
             ) t,
             Activity a
        WHERE a.player_id = t.player_id
        AND a.event_date = t.second_date
       ) t1