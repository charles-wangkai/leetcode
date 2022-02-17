SELECT
    login_date,
    count(*) AS user_count
FROM
    (
        SELECT
            min(activity_date) AS login_date
        FROM
            Traffic
        WHERE
            activity = 'login'
        GROUP BY
            user_id
    ) t
WHERE
    login_date >= date_sub('2019-06-30', INTERVAL 90 DAY)
GROUP BY
    login_date