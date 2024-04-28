-- https://leetcode.com/problems/server-utilization-time/solutions/5080541/rank/
WITH t AS (
  SELECT
    *,
    RANK() OVER (
      PARTITION BY server_id
      ORDER BY
        status_time
    ) AS rk
  FROM
    Servers
)
SELECT
  SUM(duration) DIV 86400 AS total_uptime_days
FROM
  (
    SELECT
      TIMESTAMPDIFF(SECOND, t1.status_time, t2.status_time) AS duration
    FROM
      t t1,
      t t2
    WHERE
      t1.server_id = t2.server_id
      AND t1.session_status = 'start'
      AND t1.rk + 1 = t2.rk
  ) t