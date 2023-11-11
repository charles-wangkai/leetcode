WITH t1 AS (
  SELECT
    f.flight_id,
    f.capacity,
    coalesce(t.passenger_cnt, 0) AS passenger_cnt
  FROM
    Flights f
    LEFT OUTER JOIN (
      SELECT
        flight_id,
        count(*) AS passenger_cnt
      FROM
        Passengers
      GROUP BY
        flight_id
    ) t ON t.flight_id = f.flight_id
)
SELECT
  flight_id,
  least(capacity, passenger_cnt) AS booked_cnt,
  passenger_cnt - least(capacity, passenger_cnt) AS waitlist_cnt
FROM
  t1
ORDER BY
  flight_id