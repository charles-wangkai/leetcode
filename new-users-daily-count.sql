SELECT login_date,
       COUNT(*) AS user_count
FROM (
    SELECT MIN(activity_date) AS login_date
    FROM Traffic
    WHERE activity = 'login'
    GROUP BY user_id
) t
WHERE login_date >= DATE_SUB('2019-06-30', INTERVAL 90 DAY)
GROUP BY login_date