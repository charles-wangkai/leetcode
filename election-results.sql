WITH t AS (
  SELECT
    candidate,
    sum(
      1 / (
        SELECT
          count(*)
        FROM
          Votes v1
        WHERE
          v1.voter = v.voter
      )
    ) AS vote_num
  FROM
    Votes v
  WHERE
    candidate IS NOT NULL
  GROUP BY
    candidate
)
SELECT
  candidate
FROM
  t
WHERE
  t.vote_num = (
    SELECT
      max(vote_num)
    FROM
      t
  )
ORDER BY
  candidate