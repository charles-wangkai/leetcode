SELECT
  hall_id,
  min(start_day) AS start_day,
  max(end_day) AS end_day
FROM
  (
    SELECT
      SUM(is_new_event_start) OVER (
        PARTITION BY hall_id
        ORDER BY
          start_day
      ) AS event_id,
      hall_id,
      start_day,
      end_day
    FROM
      (
        SELECT
          hall_id,
          start_day,
          end_day,
          IFNULL(
            start_day > MAX(end_day) OVER (
              PARTITION BY hall_id
              ORDER BY
                start_day ROWS BETWEEN UNBOUNDED PRECEDING
                AND 1 PRECEDING
            ),
            1
          ) AS is_new_event_start
        FROM
          HallEvents
      ) t
  ) t1
GROUP BY
  hall_id,
  event_id