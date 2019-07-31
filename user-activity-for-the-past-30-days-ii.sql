SELECT COALESCE(ROUND(AVG(t.session_cnt), 2), 0) AS average_sessions_per_user
FROM (
    SELECT COUNT(DISTINCT session_id) AS session_cnt
    FROM Activity
    WHERE activity_date > DATE_SUB('2019-07-27', INTERVAL 30 DAY)
    GROUP BY user_id
) t