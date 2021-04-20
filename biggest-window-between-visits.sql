SELECT
  user_id,
  MAX(DATEDIFF(next_date, visit_date)) AS biggest_window
FROM (
  SELECT
    v1.user_id,
    v1.visit_date,
    COALESCE(MIN(v2.visit_date), '2021-1-1') AS next_date
  FROM UserVisits v1
  LEFT OUTER JOIN UserVisits v2
  ON v2.user_id = v1.user_id
  AND v2.visit_date > v1.visit_date
  GROUP BY v1.user_id, v1.visit_date
) t
GROUP BY user_id