WITH t AS (
  SELECT
    country,
    winery,
    sum(points) point_sum
  FROM
    Wineries
  GROUP BY
    country,
    winery
)
SELECT
  t1.country,
  concat(t1.winery, ' (', t1.point_sum, ')') AS top_winery,
  coalesce(
    concat(r2.winery, ' (', r2.point_sum, ')'),
    'No second winery'
  ) AS second_winery,
  coalesce(
    concat(r3.winery, ' (', r3.point_sum, ')'),
    'No third winery'
  ) AS third_winery
FROM
  t t1
  LEFT OUTER JOIN (
    SELECT
      *
    FROM
      t t2
    WHERE
      (
        SELECT
          count(*)
        FROM
          t t21
        WHERE
          t21.country = t2.country
          AND (
            t21.point_sum > t2.point_sum
            OR (
              t21.point_sum = t2.point_sum
              AND t21.winery < t2.winery
            )
          )
      ) = 1
  ) r2 ON r2.country = t1.country
  LEFT OUTER JOIN (
    SELECT
      *
    FROM
      t t3
    WHERE
      (
        SELECT
          count(*)
        FROM
          t t31
        WHERE
          t31.country = t3.country
          AND (
            t31.point_sum > t3.point_sum
            OR (
              t31.point_sum = t3.point_sum
              AND t31.winery < t3.winery
            )
          )
      ) = 2
  ) r3 ON r3.country = t1.country
WHERE
  NOT EXISTS (
    SELECT
      1
    FROM
      t t11
    WHERE
      t11.country = t1.country
      AND (
        t11.point_sum > t1.point_sum
        OR (
          t11.point_sum = t1.point_sum
          AND t11.winery < t1.winery
        )
      )
  )
ORDER BY
  t1.country