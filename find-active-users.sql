SELECT
  u1.user_id
FROM
  Users u1
  JOIN Users u2 ON u2.user_id = u1.user_id
  AND u2.created_at > u1.created_at
  AND u2.created_at <= date_add(u1.created_at, INTERVAL 7 DAY)
UNION
SELECT
  user_id
FROM
  Users
GROUP BY
  user_id,
  created_at
HAVING
  count(*) >= 2