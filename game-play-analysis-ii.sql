SELECT a.player_id,
       a.device_id
  FROM (
        SELECT player_id,
               MIN(event_date) AS min_event_date
          FROM Activity
        GROUP BY player_id
       ) t,
       Activity a
 WHERE a.player_id = t.player_id
   AND a.event_date = t.min_event_date