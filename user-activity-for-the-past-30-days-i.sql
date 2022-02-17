SELECT
    activity_date AS DAY,
    count(DISTINCT user_id) AS active_users
FROM
    Activity
WHERE
    activity_date > date_sub('2019-07-27', INTERVAL 30 DAY)
GROUP BY
    activity_date