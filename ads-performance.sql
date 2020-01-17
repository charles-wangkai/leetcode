SELECT
    ad_id,
    ROUND(IF(click_cnt + view_cnt = 0, 0, click_cnt / (click_cnt + view_cnt) * 100), 2) AS ctr
FROM (
    SELECT
        ad_id,
        SUM(IF(action = 'Clicked', 1, 0)) AS click_cnt,
        SUM(IF(action = 'Viewed', 1, 0)) AS view_cnt
    FROM Ads
    GROUP BY ad_id
) t
ORDER BY 2 DESC, 1