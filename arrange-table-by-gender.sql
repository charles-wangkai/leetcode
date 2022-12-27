SELECT
  user_id,
  gender
FROM
  Genders g1
ORDER BY
  3 * (
    SELECT
      count(*)
    FROM
      Genders g2
    WHERE
      g2.gender = g1.gender
      AND g2.user_id < g1.user_id
  ) + CASE
    gender
    WHEN 'female' THEN 1
    WHEN 'other' THEN 2
    ELSE 3
  END