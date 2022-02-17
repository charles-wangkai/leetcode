SELECT
  t.user_id,
  l1.page_id,
  count(*) AS friends_likes
FROM
  (
    SELECT
      user1_id AS user_id,
      user2_id AS friend_id
    FROM
      Friendship
    UNION
    SELECT
      user2_id AS user_id,
      user1_id AS friend_id
    FROM
      Friendship
  ) t
  JOIN Likes l1 ON l1.user_id = t.friend_id
  LEFT OUTER JOIN Likes l2 ON l2.user_id = t.user_id
  AND l2.page_id = l1.page_id
WHERE
  l2.user_id IS NULL
GROUP BY
  t.user_id,
  l1.page_id