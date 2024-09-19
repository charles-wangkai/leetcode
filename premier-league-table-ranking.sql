WITH t AS (
  SELECT
    team_id,
    team_name,
    wins * 3 + draws AS points
  FROM
    TeamStats
)
SELECT
  team_id,
  team_name,
  points,
  (
    SELECT
      count(*)
    FROM
      t t1
    WHERE
      t1.points > t.points
  ) + 1 AS position
FROM
  t
ORDER BY
  points DESC,
  team_name