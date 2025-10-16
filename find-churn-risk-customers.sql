WITH t1 AS (
  SELECT
    user_id,
    event_type,
    plan_name,
    monthly_amount,
    row_number() over (
      PARTITION by user_id
      ORDER BY
        event_date DESC
    ) AS row_num
  FROM
    subscription_events
),
t2 AS (
  SELECT
    user_id,
    event_type AS current_event_type,
    plan_name AS current_plan,
    monthly_amount AS current_monthly_amount
  FROM
    t1
  WHERE
    row_num = 1
),
t3 AS (
  SELECT
    user_id,
    max(monthly_amount) AS max_historical_amount,
    datediff(max(event_date), min(event_date)) AS days_as_subscriber
  FROM
    subscription_events
  GROUP BY
    user_id
  HAVING
    sum(IF(event_type = 'downgrade', 1, 0)) >= 1
)
SELECT
  t2.user_id,
  t2.current_plan,
  t2.current_monthly_amount,
  t3.max_historical_amount,
  t3.days_as_subscriber
FROM
  t2
  JOIN t3 ON t3.user_id = t2.user_id
WHERE
  t2.current_event_type <> 'cancel'
  AND t2.current_monthly_amount < 0.5 * t3.max_historical_amount
  AND t3.days_as_subscriber >= 60
ORDER BY
  t3.days_as_subscriber DESC,
  t2.user_id