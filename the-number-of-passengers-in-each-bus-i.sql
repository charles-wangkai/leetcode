SELECT
  b.bus_id,
  count(t.passenger_id) AS passengers_cnt
FROM
  Buses b
  LEFT OUTER JOIN (
    SELECT
      p.passenger_id,
      min(b.arrival_time) AS pickup_time
    FROM
      Passengers p
      JOIN Buses b ON b.arrival_time >= p.arrival_time
    GROUP BY
      p.passenger_id
  ) t ON t.pickup_time = b.arrival_time
GROUP BY
  b.bus_id
ORDER BY
  b.bus_id