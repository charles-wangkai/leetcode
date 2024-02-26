SELECT
  a.age_bucket,
  round(
    sum(IF(t.activity_type = 'send', t.time_spent, 0)) / sum(t.time_spent) * 100,
    2
  ) AS send_perc,
  round(
    sum(IF(t.activity_type = 'open', t.time_spent, 0)) / sum(t.time_spent) * 100,
    2
  ) AS open_perc
FROM
  Activities t
  JOIN Age a ON a.user_id = t.user_id
GROUP BY
  a.age_bucket