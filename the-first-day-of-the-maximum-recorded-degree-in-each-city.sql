SELECT
  *
FROM
  Weather w1
WHERE
  NOT EXISTS (
    SELECT
      1
    FROM
      Weather w2
    WHERE
      w2.city_id = w1.city_id
      AND (
        w2.degree > w1.degree
        OR (
          w2.degree = w1.degree
          AND w2.day < w1.day
        )
      )
  )
ORDER BY
  city_id