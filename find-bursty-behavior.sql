SELECT
  t1.user_id,
  t1.max_7day_posts,
  t2.cnt / 4 AS avg_weekly_posts
FROM
  (
    SELECT
      user_id,
      max(cnt) AS max_7day_posts
    FROM
      (
        SELECT
          p1.user_id,
          count(*) AS cnt
        FROM
          Posts p1
          JOIN Posts p2 ON p2.user_id = p1.user_id
          AND datediff(p1.post_date, p2.post_date) BETWEEN 0
          AND 6
        WHERE
          p1.post_date BETWEEN '2024-02-01'
          AND '2024-02-28'
        GROUP BY
          p1.user_id,
          p1.post_id
      ) t
    GROUP BY
      user_id
  ) t1
  JOIN (
    SELECT
      user_id,
      count(*) AS cnt
    FROM
      Posts
    WHERE
      post_date BETWEEN '2024-02-01'
      AND '2024-02-28'
    GROUP BY
      user_id
  ) t2 ON t2.user_id = t1.user_id
WHERE
  t1.max_7day_posts * 2 >= t2.cnt
ORDER BY
  t1.user_id