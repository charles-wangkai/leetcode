SELECT
  u.user_id,
  u.name,
  coalesce(t.distance_sum, 0) AS 'traveled distance'
FROM
  Users u
  LEFT OUTER JOIN (
    SELECT
      user_id,
      sum(distance) AS distance_sum
    FROM
      Rides
    GROUP BY
      user_id
  ) t ON t.user_id = u.user_id
ORDER BY
  u.user_id