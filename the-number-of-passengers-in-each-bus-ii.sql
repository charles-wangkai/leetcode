-- https://leetcode.com/problems/the-number-of-passengers-in-each-bus-ii/discuss/1731123/variable-instead-of-recursion-faster-than-100
SELECT
  b.bus_id,
  coalesce(t2.passengers_cnt, 0) AS passengers_cnt
FROM
  Buses b
  LEFT OUTER JOIN (
    SELECT
      t.bus_id,
      least(t.capacity, t.all_passenger_cnt - @pickup_cnt) AS passengers_cnt,
      @pickup_cnt := @pickup_cnt + least(t.capacity, t.all_passenger_cnt - @pickup_cnt)
    FROM
      (
        SELECT
          b.bus_id,
          b.arrival_time,
          b.capacity,
          count(*) AS all_passenger_cnt
        FROM
          Buses b
          JOIN Passengers p ON p.arrival_time <= b.arrival_time
        GROUP BY
          b.bus_id,
          b.arrival_time,
          b.capacity
        ORDER BY
          b.arrival_time
      ) t,
      (
        SELECT
          @pickup_cnt := 0
      ) t1
  ) t2 ON t2.bus_id = b.bus_id
ORDER BY
  b.bus_id