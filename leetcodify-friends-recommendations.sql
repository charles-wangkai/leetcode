SELECT
  t1.user_id,
  t1.recommended_id
FROM
  (
    SELECT
      DISTINCT l1.user_id,
      l2.user_id AS recommended_id
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
  LEFT OUTER JOIN (
    SELECT
      user1_id,
      user2_id
    FROM
      Friendship
    UNION
    SELECT
      user2_id,
      user1_id
    FROM
      Friendship
  ) t2 ON t2.user1_id = t1.user_id
  AND t2.user2_id = t1.recommended_id
WHERE
  t2.user1_id IS NULL