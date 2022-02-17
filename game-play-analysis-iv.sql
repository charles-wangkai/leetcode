SELECT
       round(
              count(*) / (
                     SELECT
                            count(DISTINCT player_id)
                     FROM
                            Activity
              ),
              2
       ) AS fraction
FROM
       (
              SELECT
                     1
              FROM
                     (
                            SELECT
                                   player_id,
                                   date_add(min(event_date), INTERVAL 1 DAY) AS second_date
                            FROM
                                   Activity
                            GROUP BY
                                   player_id
                     ) t,
                     Activity a
              WHERE
                     a.player_id = t.player_id
                     AND a.event_date = t.second_date
       ) t1