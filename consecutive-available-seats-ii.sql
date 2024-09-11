WITH t AS (
  SELECT
    c1.seat_id AS first_seat_id,
    c2.seat_id AS last_seat_id,
    (
      SELECT
        count(*)
      FROM
        Cinema c4
      WHERE
        c4.seat_id BETWEEN c1.seat_id
        AND c2.seat_id
    ) AS consecutive_seats_len
  FROM
    Cinema c1
    JOIN Cinema c2 ON c2.seat_id >= c1.seat_id
    AND c2.free = 1
    AND NOT EXISTS (
      SELECT
        1
      FROM
        Cinema c3
      WHERE
        c3.seat_id BETWEEN c1.seat_id
        AND c2.seat_id
        AND c3.free = 0
    )
  WHERE
    c1.free = 1
)
SELECT
  first_seat_id,
  last_seat_id,
  consecutive_seats_len
FROM
  t
WHERE
  consecutive_seats_len = (
    SELECT
      max(consecutive_seats_len)
    FROM
      t
  )
ORDER BY
  first_seat_id