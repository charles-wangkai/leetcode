SELECT
  DISTINCT c1.user_id
FROM
  Confirmations c1
  JOIN Confirmations c2 ON c2.user_id = c1.user_id
  AND c2.time_stamp > c1.time_stamp
  AND c2.time_stamp <= date_add(c1.time_stamp, INTERVAL 1 DAY)