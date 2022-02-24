SELECT
  airport_id
FROM
  (
    SELECT
      departure_airport AS airport_id,
      flights_count
    FROM
      Flights
    UNION
    ALL
    SELECT
      arrival_airport AS airport_id,
      flights_count
    FROM
      Flights
  ) t
GROUP BY
  airport_id
HAVING
  sum(flights_count) = (
    SELECT
      max(traffic)
    FROM
      (
        SELECT
          sum(flights_count) AS traffic
        FROM
          (
            SELECT
              departure_airport AS airport_id,
              flights_count
            FROM
              Flights
            UNION
            ALL
            SELECT
              arrival_airport AS airport_id,
              flights_count
            FROM
              Flights
          ) t
        GROUP BY
          airport_id
      ) t1
  )