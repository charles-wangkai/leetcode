SELECT
  m.month,
  ROUND(COALESCE(t.average_ride_distance, 0), 2) AS average_ride_distance,
  ROUND(COALESCE(t.average_ride_duration, 0), 2) AS average_ride_duration
FROM (
  SELECT 1 AS month
  UNION
  SELECT 2
  UNION
  SELECT 3
  UNION
  SELECT 4
  UNION
  SELECT 5
  UNION
  SELECT 6
  UNION
  SELECT 7
  UNION
  SELECT 8
  UNION
  SELECT 9
  UNION
  SELECT 10
) m
LEFT OUTER JOIN (
  SELECT
    MONTH(r.requested_at) - d.delta AS month,
    SUM(a.ride_distance) / 3 AS average_ride_distance,
    SUM(a.ride_duration) / 3 AS average_ride_duration
  FROM Rides r
  JOIN AcceptedRides a
  ON a.ride_id = r.ride_id
  JOIN (
    SELECT 0 AS delta
    UNION
    SELECT 1
    UNION
    SELECT 2
  ) d
  WHERE YEAR(r.requested_at) = 2020
  GROUP BY month
) t
ON t.month = m.month
ORDER BY m.month