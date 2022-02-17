SELECT
    t.spend_date,
    p.platform,
    sum(coalesce(s.amount, 0)) AS total_amount,
    count(DISTINCT s.user_id) AS total_users
FROM
    (
        SELECT
            DISTINCT spend_date AS spend_date
        FROM
            Spending
    ) t
    JOIN (
        SELECT
            'both' AS platform
        UNION
        SELECT
            'desktop' AS platform
        UNION
        SELECT
            'mobile' AS platform
    ) p
    LEFT OUTER JOIN (
        SELECT
            spend_date,
            user_id,
            CASE
                WHEN count(DISTINCT platform) = 2 THEN 'both'
                WHEN min(platform) = 'desktop' THEN 'desktop'
                ELSE 'mobile'
            END AS category
        FROM
            Spending
        GROUP BY
            spend_date,
            user_id
    ) u ON u.spend_date = t.spend_date
    AND u.category = p.platform
    LEFT OUTER JOIN Spending s ON s.user_id = u.user_id
    AND s.spend_date = t.spend_date
GROUP BY
    t.spend_date,
    p.platform