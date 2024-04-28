-- https://leetcode.com/problems/server-utilization-time/solutions/5080541/rank/
WITH t AS (
  SELECT
    *,
    RANK() OVER (
      PARTITION BY server_id
      ORDER BY
        status_time,
        session_status
    ) AS r
  FROM
    Servers
)
SELECT
  SUM(duration) DIV 86400 AS total_uptime_days
FROM
  (
    SELECT
      TIMESTAMPDIFF(SECOND, a.status_time, b.status_time) AS duration
    FROM
      t a,
      t b
    WHERE
      a.server_id = b.server_id
      AND a.session_status = 'start'
      AND a.r = b.r - 1
  ) t