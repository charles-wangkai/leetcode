WITH t1 AS (
  SELECT
    user_id,
    action_date,
    max(ACTION) AS ACTION
  FROM
    activity
  GROUP BY
    user_id,
    action_date
  HAVING
    count(*) = 1
),
t2 AS (
  SELECT
    t1.*,
    datediff(
      action_date,
      lag(action_date) over(
        PARTITION by user_id,
        ACTION
        ORDER BY
          action_date
      )
    ) AS gap
  FROM
    t1
),
t3 AS (
  SELECT
    t2.*,
    sum(IF(gap = 1, 0, 1)) over(
      PARTITION by user_id,
      ACTION
      ORDER BY
        action_date
    ) AS streak_id
  FROM
    t2
),
t4 AS (
  SELECT
    user_id,
    ACTION,
    datediff(max(action_date), min(action_date)) + 1 AS streak_length,
    min(action_date) AS start_date,
    max(action_date) AS end_date
  FROM
    t3
  GROUP BY
    user_id,
    ACTION,
    streak_id
)
SELECT
  *
FROM
  t4
WHERE
  streak_length >= 5
ORDER BY
  streak_length DESC,
  user_id