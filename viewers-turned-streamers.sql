SELECT
  s.user_id,
  t.sessions_count
FROM
  Sessions s
  JOIN (
    SELECT
      user_id,
      count(*) AS sessions_count
    FROM
      Sessions
    WHERE
      session_type = 'Streamer'
    GROUP BY
      user_id
  ) t ON t.user_id = s.user_id
WHERE
  NOT EXISTS (
    SELECT
      1
    FROM
      Sessions s1
    WHERE
      s1.user_id = s.user_id
      AND s1.session_start < s.session_start
  )
  AND session_type = 'Viewer'
ORDER BY
  t.sessions_count DESC,
  s.user_id DESC