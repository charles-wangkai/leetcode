SELECT
  t.machine_id,
  round(avg(processing_time), 3) AS processing_time
FROM
  (
    SELECT
      machine_id,
      max(timestamp) - min(timestamp) AS processing_time
    FROM
      Activity
    GROUP BY
      machine_id,
      process_id
  ) t
GROUP BY
  machine_id