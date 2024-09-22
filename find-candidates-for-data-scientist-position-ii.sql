WITH t AS (
  SELECT
    p.project_id,
    c.candidate_id,
    count(*) AS cnt,
    100 + sum(
      CASE
        WHEN c.proficiency > p.importance THEN 10
        WHEN c.proficiency < p.importance THEN -5
        ELSE 0
      END
    ) AS score
  FROM
    Projects p
    JOIN Candidates c ON c.skill = p.skill
  GROUP BY
    p.project_id,
    c.candidate_id
),
t1 AS (
  SELECT
    t.project_id,
    t.candidate_id,
    t.score
  FROM
    t
  WHERE
    t.cnt = (
      SELECT
        count(*)
      FROM
        Projects p
      WHERE
        p.project_id = t.project_id
    )
)
SELECT
  project_id,
  candidate_id,
  score
FROM
  t1
WHERE
  NOT EXISTS (
    SELECT
      1
    FROM
      t1 t11
    WHERE
      t11.project_id = t1.project_id
      AND (
        t11.score > t1.score
        OR (
          t11.score = t1.score
          AND t11.candidate_id < t1.candidate_id
        )
      )
  )
ORDER BY
  t1.project_id