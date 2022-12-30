SELECT
  e.employee_id
FROM
  Employees e
  LEFT OUTER JOIN (
    SELECT
      employee_id,
      sum(
        (time_to_sec(timediff(out_time, in_time)) + 59) DIV 60
      ) AS minutes
    FROM
      LOGS
    GROUP BY
      employee_id
  ) t ON t.employee_id = e.employee_id
WHERE
  coalesce(t.minutes, 0) < e.needed_hours * 60