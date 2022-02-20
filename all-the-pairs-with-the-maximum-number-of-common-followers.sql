SELECT
  r1.user_id AS user1_id,
  r2.user_id AS user2_id
FROM
  Relations r1
  JOIN Relations r2 ON r2.follower_id = r1.follower_id
  AND r2.user_id > r1.user_id
GROUP BY
  r1.user_id,
  r2.user_id
HAVING
  count(*) = (
    SELECT
      max(common)
    FROM
      (
        SELECT
          count(*) AS common
        FROM
          Relations r1
          JOIN Relations r2 ON r2.follower_id = r1.follower_id
          AND r2.user_id > r1.user_id
        GROUP BY
          r1.user_id,
          r2.user_id
      ) t
  )