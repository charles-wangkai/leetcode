SELECT
  DISTINCT s1.user_id
FROM
  Sessions s1
  JOIN Sessions s2 ON s2.user_id = s1.user_id
  AND s2.session_type = s1.session_type
  AND s2.session_start > s1.session_start
  AND s2.session_start <= timestampadd(HOUR, 12, s1.session_end)
  AND NOT EXISTS (
    SELECT
      1
    FROM
      Sessions s3
    WHERE
      s3.user_id = s1.user_id
      AND s3.session_type = s1.session_type
      AND s3.session_start > s1.session_start
      AND s3.session_start < s2.session_start
  )
ORDER BY
  s1.user_id