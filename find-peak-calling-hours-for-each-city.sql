WITH t1 AS (
  SELECT
    city,
    calling_hour,
    count(*) AS number_of_calls
  FROM
    (
      SELECT
        HOUR(call_time) AS calling_hour,
        city
      FROM
        Calls
    ) t
  GROUP BY
    city,
    calling_hour
)
SELECT
  city,
  calling_hour AS peak_calling_hour,
  number_of_calls
FROM
  t1 AS t11
WHERE
  NOT EXISTS (
    SELECT
      1
    FROM
      t1 AS t12
    WHERE
      t12.city = t11.city
      AND t12.number_of_calls > t11.number_of_calls
  )
ORDER BY
  peak_calling_hour DESC,
  city DESC