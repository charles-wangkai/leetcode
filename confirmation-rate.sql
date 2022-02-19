SELECT
  s.user_id,
  round(coalesce(t.confirmation_rate, 0), 2) AS confirmation_rate
FROM
  Signups s
  LEFT OUTER JOIN (
    SELECT
      user_id,
      sum(IF(ACTION = 'confirmed', 1, 0)) / count(*) AS confirmation_rate
    FROM
      Confirmations
    GROUP BY
      user_id
  ) t ON t.user_id = s.user_id