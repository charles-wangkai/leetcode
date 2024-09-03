SELECT
  city
FROM
  Listings
GROUP BY
  city
HAVING
  avg(price) > (
    SELECT
      avg(price)
    FROM
      Listings
  )
ORDER BY
  city