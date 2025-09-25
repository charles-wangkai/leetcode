SELECT
  emp_id,
  dept
FROM
  (
    SELECT
      emp_id,
      dept,
      dense_rank() over (
        PARTITION by dept
        ORDER BY
          salary DESC
      ) AS rk
    FROM
      employees
  ) t
WHERE
  rk = 2
ORDER BY
  emp_id