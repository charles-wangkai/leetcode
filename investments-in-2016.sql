SELECT
  round(sum(TIV_2016), 2) AS TIV_2016
FROM
  insurance
WHERE
  TIV_2015 IN (
    SELECT
      TIV_2015
    FROM
      insurance
    GROUP BY
      TIV_2015
    HAVING
      count(*) > 1
  )
  AND (LAT, LON) IN (
    SELECT
      LAT,
      LON
    FROM
      insurance
    GROUP BY
      LAT,
      LON
    HAVING
      count(*) = 1
  )