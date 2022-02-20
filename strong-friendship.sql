SELECT
  t.user1_id,
  t.user2_id,
  t.common_friend
FROM
  (
    SELECT
      t1.friend_id AS user1_id,
      t2.friend_id AS user2_id,
      count(*) AS common_friend
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
      ) t1
      JOIN (
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
      ) t2 ON t2.user_id = t1.user_id
    GROUP BY
      t1.friend_id,
      t2.friend_id
    HAVING
      count(*) >= 3
  ) t
  JOIN Friendship f ON f.user1_id = t.user1_id
  AND f.user2_id = t.user2_id