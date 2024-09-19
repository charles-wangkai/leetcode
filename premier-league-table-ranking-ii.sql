WITH t AS (
  SELECT
    team_id,
    team_name,
    wins * 3 + draws AS points
  FROM
    TeamStats
),
t1 AS (
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
),
t2 AS (
  SELECT
    count(*) AS cnt
  FROM
    TeamStats
)
SELECT
  t1.team_name,
  t1.points,
  t1.position,
  CASE
    WHEN (t1.position - 1) / t2.cnt < 0.33 THEN 'Tier 1'
    WHEN (t1.position - 1) / t2.cnt < 0.66 THEN 'Tier 2'
    ELSE 'Tier 3'
  END AS tier
FROM
  t1,
  t2
ORDER BY
  t1.points DESC,
  t1.team_name