SELECT
  t1.user1_id,
  t1.user2_id
FROM
  (
    SELECT
      DISTINCT l1.user_id AS user1_id,
      l2.user_id AS user2_id
    FROM
      Listens l1
      JOIN Listens l2 ON l2.user_id <> l1.user_id
      AND l2.song_id = l1.song_id
      AND l2.day = l1.day
    GROUP BY
      l1.user_id,
      l2.user_id,
      l1.day
    HAVING
      count(DISTINCT l1.song_id) >= 3
  ) t1
  LEFT OUTER JOIN Friendship f ON f.user1_id = t1.user1_id
  AND f.user2_id = t1.user2_id
WHERE
  f.user1_id IS NOT NULL