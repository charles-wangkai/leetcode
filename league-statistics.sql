SELECT
  t.team_name,
  count(*) AS matches_played,
  sum(
    IF(
      m.goal_for > m.goal_against,
      3,
      IF(m.goal_for = m.goal_against, 1, 0)
    )
  ) AS points,
  sum(m.goal_for) AS goal_for,
  sum(m.goal_against) AS goal_against,
  sum(m.goal_for) - sum(m.goal_against) AS goal_diff
FROM
  (
    SELECT
      home_team_id AS team_id,
      home_team_goals AS goal_for,
      away_team_goals AS goal_against
    FROM
      Matches
    UNION
    ALL
    SELECT
      away_team_id,
      away_team_goals,
      home_team_goals
    FROM
      Matches
  ) m
  JOIN Teams t ON t.team_id = m.team_id
GROUP BY
  t.team_id,
  t.team_name
ORDER BY
  3 DESC,
  6 DESC,
  1