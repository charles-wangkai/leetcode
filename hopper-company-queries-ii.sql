SELECT
  t1.month,
  IF(t2.working_drivers = 0, 0, ROUND(t2.working_drivers / t1.active_drivers * 100, 2)) AS working_percentage
FROM (
  SELECT
    m.month,
    COUNT(d.join_date) AS active_drivers
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
    UNION
    SELECT 11
    UNION
    SELECT 12
  ) m
  LEFT OUTER JOIN Drivers d
  ON d.join_date < DATE_ADD(CONCAT('2020-', m.month, '-01'), INTERVAL 1 MONTH)
  GROUP BY m.month
) t1
JOIN (
  SELECT
    m.month,
    COUNT(DISTINCT ar.driver_id) AS working_drivers
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
    UNION
    SELECT 11
    UNION
    SELECT 12
  ) m
  LEFT OUTER JOIN (
    SELECT
      r.requested_at,
      a.driver_id
    FROM Rides r
    JOIN AcceptedRides a
    ON a.ride_id = r.ride_id
    WHERE YEAR(r.requested_at) = 2020
  ) ar
  ON MONTH(ar.requested_at) = m.month
  GROUP BY m.month
) t2
ON t2.month = t1.month
ORDER BY t1.month