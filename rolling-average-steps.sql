SELECT
  s1.user_id,
  s1.steps_date,
  round(
    (s1.steps_count + s2.steps_count + s3.steps_count) / 3,
    2
  ) AS rolling_average
FROM
  Steps s1
  JOIN Steps s2 ON s2.user_id = s1.user_id
  AND datediff(s1.steps_date, s2.steps_date) = 1
  JOIN Steps s3 ON s3.user_id = s2.user_id
  AND datediff(s2.steps_date, s3.steps_date) = 1
ORDER BY
  s1.user_id,
  s1.steps_date