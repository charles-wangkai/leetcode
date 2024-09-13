WITH t AS (
  SELECT
    car_id,
    lot_id,
    sum(
      TIMESTAMPDIFF(MICROSECOND, entry_time, exit_time)
    ) AS time_sum,
    sum(fee_paid) fee_sum
  FROM
    ParkingTransactions
  GROUP BY
    car_id,
    lot_id
)
SELECT
  t.car_id,
  sum(t.fee_sum) AS total_fee_paid,
  round(
    sum(t.fee_sum) / (sum(t.time_sum) / 3600000000),
    2
  ) AS avg_hourly_fee,
  t1.lot_id AS most_time_lot
FROM
  t
  JOIN t t1 ON t1.car_id = t.car_id
  AND NOT EXISTS (
    SELECT
      1
    FROM
      t t2
    WHERE
      t2.car_id = t1.car_id
      AND t2.time_sum > t1.time_sum
  )
GROUP BY
  t.car_id,
  t1.lot_id
ORDER BY
  t.car_id