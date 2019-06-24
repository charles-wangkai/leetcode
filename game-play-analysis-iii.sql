SELECT a.player_id,
       a.event_date,
       SUM(before_a.games_played) AS games_played_so_far
  FROM Activity a, Activity before_a
 WHERE a.player_id = before_a.player_id
   AND a.event_date >= before_a.event_date
GROUP BY a.player_id, a.event_date