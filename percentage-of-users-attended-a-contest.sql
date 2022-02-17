SELECT
  contest_id,
  round(
    count(*) / (
      SELECT
        count(*)
      FROM
        Users
    ) * 100,
    2
  ) AS percentage
FROM
  Register
GROUP BY
  contest_id
ORDER BY
  percentage DESC,
  contest_id