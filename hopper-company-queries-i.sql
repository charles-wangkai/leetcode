SELECT
  t1.month,
  t1.active_drivers,
  t2.accepted_rides
FROM
  (
    SELECT
      m.month,
      count(d.join_date) AS active_drivers
    FROM
      (
        SELECT
          1 AS MONTH
        UNION
        SELECT
          2
        UNION
        SELECT
          3
        UNION
        SELECT
          4
        UNION
        SELECT
          5
        UNION
        SELECT
          6
        UNION
        SELECT
          7
        UNION
        SELECT
          8
        UNION
        SELECT
          9
        UNION
        SELECT
          10
        UNION
        SELECT
          11
        UNION
        SELECT
          12
      ) m
      LEFT OUTER JOIN Drivers d ON d.join_date < date_add(
        concat('2020-', m.month, '-01'),
        INTERVAL 1 MONTH
      )
    GROUP BY
      m.month
  ) t1
  JOIN (
    SELECT
      m.month,
      count(ar.requested_at) AS accepted_rides
    FROM
      (
        SELECT
          1 AS MONTH
        UNION
        SELECT
          2
        UNION
        SELECT
          3
        UNION
        SELECT
          4
        UNION
        SELECT
          5
        UNION
        SELECT
          6
        UNION
        SELECT
          7
        UNION
        SELECT
          8
        UNION
        SELECT
          9
        UNION
        SELECT
          10
        UNION
        SELECT
          11
        UNION
        SELECT
          12
      ) m
      LEFT OUTER JOIN (
        SELECT
          r.requested_at
        FROM
          Rides r
          JOIN AcceptedRides a ON a.ride_id = r.ride_id
        WHERE
          year(r.requested_at) = 2020
      ) ar ON MONTH(ar.requested_at) = m.month
    GROUP BY
      m.month
  ) t2 ON t2.month = t1.month
ORDER BY
  t1.month