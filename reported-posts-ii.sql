SELECT
    round(avg(removed_spam_ratio) * 100, 2) AS average_daily_percent
FROM
    (
        SELECT
            coalesce(t2.removed_spam_post_cnt, 0) / t1.spam_post_cnt AS removed_spam_ratio
        FROM
            (
                SELECT
                    action_date,
                    count(DISTINCT post_id) AS spam_post_cnt
                FROM
                    Actions
                WHERE
                    extra = 'spam'
                GROUP BY
                    action_date
            ) t1
            LEFT OUTER JOIN (
                SELECT
                    a.action_date,
                    count(DISTINCT a.post_id) AS removed_spam_post_cnt
                FROM
                    Actions a,
                    Removals r
                WHERE
                    a.post_id = r.post_id
                    AND a.extra = 'spam'
                GROUP BY
                    a.action_date
            ) t2 ON t2.action_date = t1.action_date
    ) t