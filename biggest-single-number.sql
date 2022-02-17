SELECT
  IF(count(*) = 0, NULL, max(num)) AS num
FROM
  (
    SELECT
      num,
      count(*) AS cnt
    FROM
      MyNumbers
    GROUP BY
      num
    HAVING
      count(*) = 1
  ) t