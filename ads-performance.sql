SELECT
    ad_id,
    round(
        IF(
            click_cnt + view_cnt = 0,
            0,
            click_cnt / (click_cnt + view_cnt) * 100
        ),
        2
    ) AS ctr
FROM
    (
        SELECT
            ad_id,
            sum(IF(ACTION = 'Clicked', 1, 0)) AS click_cnt,
            sum(IF(ACTION = 'Viewed', 1, 0)) AS view_cnt
        FROM
            Ads
        GROUP BY
            ad_id
    ) t
ORDER BY
    2 DESC,
    1