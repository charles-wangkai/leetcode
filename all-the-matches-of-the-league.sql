SELECT
  t1.team_name AS home_team,
  t2.team_name AS away_team
FROM
  Teams t1
  JOIN Teams t2 ON t2.team_name <> t1.team_name