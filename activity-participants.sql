SELECT
    t1.activity
FROM
    (
        SELECT
            activity,
            count(*) AS activity_cnt
        FROM
            Friends
        GROUP BY
            activity
    ) t1
    LEFT OUTER JOIN (
        SELECT
            min(activity_cnt) AS min_activity_cnt,
            max(activity_cnt) AS max_activity_cnt
        FROM
            (
                SELECT
                    count(*) AS activity_cnt
                FROM
                    Friends
                GROUP BY
                    activity
            ) t
    ) t2 ON t1.activity_cnt = t2.min_activity_cnt
    OR t1.activity_cnt = t2.max_activity_cnt
WHERE
    t2.min_activity_cnt IS NULL