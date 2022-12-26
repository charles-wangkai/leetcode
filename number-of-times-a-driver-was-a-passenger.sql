SELECT
  r1.driver_id,
  count(DISTINCT r2.ride_id) AS cnt
FROM
  Rides r1
  LEFT OUTER JOIN Rides r2 ON r2.passenger_id = r1.driver_id
GROUP BY
  r1.driver_id