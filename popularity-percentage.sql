WITH t2 AS (
  SELECT
    user1,
    count(*) AS friend_cnt
  FROM
    (
      SELECT
        user1,
        user2
      FROM
        Friends
      UNION
      SELECT
        user2 AS user1,
        user1 AS user2
      FROM
        Friends
    ) t1
  GROUP BY
    user1
)
SELECT
  user1,
  round(
    friend_cnt / (
      SELECT
        count(*)
      FROM
        t2
    ) * 100,
    2
  ) AS percentage_popularity
FROM
  t2
ORDER BY
  user1