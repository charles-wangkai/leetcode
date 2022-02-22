SELECT
  problem_id
FROM
  Problems
WHERE
  5 * likes < 3 * (likes + dislikes)
ORDER BY
  problem_id