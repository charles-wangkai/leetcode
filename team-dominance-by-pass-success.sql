SELECT
  from_team_name AS team_name,
  half_number,
  sum(IF (from_team_name = to_team_name, 1, -1)) AS dominance
FROM
  (
    SELECT
      t1.team_name AS from_team_name,
      t2.team_name AS to_team_name,
      IF(p.time_stamp <= '45:00', 1, 2) AS half_number
    FROM
      Passes p
      JOIN Teams t1 ON t1.player_id = p.pass_from
      JOIN Teams t2 ON t2.player_id = p.pass_to
  ) t
GROUP BY
  from_team_name,
  half_number
ORDER BY
  team_name,
  half_number