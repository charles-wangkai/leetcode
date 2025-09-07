SELECT
  session_id,
  user_id,
  session_duration_minutes,
  scroll_count
FROM
  (
    SELECT
      session_id,
      max(user_id) AS user_id,
      TIMESTAMPDIFF(
        MINUTE,
        min(event_timestamp),
        max(event_timestamp)
      ) AS session_duration_minutes,
      sum(IF(event_type = 'scroll', 1, 0)) AS scroll_count,
      sum(IF(event_type = 'click', 1, 0)) AS click_count,
      sum(IF(event_type = 'purchase', 1, 0)) AS purchase_count
    FROM
      app_events
    GROUP BY
      session_id
  ) t
WHERE
  session_duration_minutes > 30
  AND scroll_count >= 5
  AND click_count / scroll_count < 0.2
  AND purchase_count = 0
ORDER BY
  scroll_count DESC,
  session_id