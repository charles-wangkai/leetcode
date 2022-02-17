SELECT
  name,
  mail
FROM
  Users
WHERE
  user_id IN (
    SELECT
      u.user_id
    FROM
      Users u
      JOIN Contests c1 ON c1.gold_medal = u.user_id
      OR c1.silver_medal = u.user_id
      OR c1.bronze_medal = u.user_id
      JOIN Contests c2 ON c2.contest_id = c1.contest_id + 1
      AND (
        c2.gold_medal = u.user_id
        OR c2.silver_medal = u.user_id
        OR c2.bronze_medal = u.user_id
      )
      JOIN Contests c3 ON c3.contest_id = c2.contest_id + 1
      AND (
        c3.gold_medal = u.user_id
        OR c3.silver_medal = u.user_id
        OR c3.bronze_medal = u.user_id
      )
    UNION
    SELECT
      gold_medal
    FROM
      Contests
    GROUP BY
      gold_medal
    HAVING
      count(*) >= 3
  )