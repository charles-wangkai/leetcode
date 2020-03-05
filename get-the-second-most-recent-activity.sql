SELECT
    COALESCE(t2.username, t1.username) AS username,
    COALESCE(t2.activity, t1.activity) AS activity,
    COALESCE(t2.startDate, t1.startDate) AS startDate,
    COALESCE(t2.endDate, t1.endDate) AS endDate
FROM (
    SELECT *
    FROM UserActivity u
    WHERE (
        SELECT COUNT(*)
        FROM UserActivity u1
        WHERE u1.username = u.username
        AND u1.startDate > u.startDate
    ) = 0
) t1
LEFT OUTER JOIN (
    SELECT *
    FROM UserActivity u
    WHERE (
        SELECT COUNT(*)
        FROM UserActivity u1
        WHERE u1.username = u.username
        AND u1.startDate > u.startDate
    ) = 1
) t2
ON t2.username = t1.username