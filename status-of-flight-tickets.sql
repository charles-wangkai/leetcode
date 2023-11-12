SELECT
  p.passenger_id,
  IF(
    (
      SELECT
        count(*)
      FROM
        Passengers p1
      WHERE
        p1.flight_id = p.flight_id
        AND p1.booking_time <= p.booking_time
    ) <= f.capacity,
    'Confirmed',
    'Waitlist'
  ) AS STATUS
FROM
  Passengers p
  JOIN Flights f ON f.flight_id = p.flight_id
ORDER BY
  p.passenger_id