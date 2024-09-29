WITH t AS (
  SELECT
    v.fuel_type,
    d.driver_id,
    avg(r.rating) AS rating,
    sum(distance) AS distance,
    max(d.accidents) AS accidents
  FROM
    Trips r
    JOIN Vehicles v ON v.vehicle_id = r.vehicle_id
    JOIN Drivers d ON d.driver_id = v.driver_id
  GROUP BY
    v.fuel_type,
    d.driver_id
)
SELECT
  fuel_type,
  driver_id,
  round(rating, 2) AS rating,
  distance
FROM
  t
WHERE
  NOT EXISTS (
    SELECT
      1
    FROM
      t t1
    WHERE
      t1.fuel_type = t.fuel_type
      AND (
        t1.rating > t.rating
        OR (
          t1.rating = t.rating
          AND (
            t1.distance > t.distance
            OR (
              t1.distance = t.distance
              AND t1.accidents < t.accidents
            )
          )
        )
      )
  )
ORDER BY
  fuel_type