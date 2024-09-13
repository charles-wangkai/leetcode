SELECT
  e.user_id
FROM
  emails e
  JOIN texts t ON t.email_id = e.email_id
  AND t.signup_action = 'Verified'
  AND datediff(t.action_date, e.signup_date) = 1
ORDER BY
  e.user_id