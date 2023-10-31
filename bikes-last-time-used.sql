SELECT
  bike_number,
  max(end_time) AS end_time
FROM
  Bikes
GROUP BY
  bike_number
ORDER BY
  end_time DESC