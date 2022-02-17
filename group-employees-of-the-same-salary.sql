SELECT
  e.employee_id,
  e.name,
  e.salary,
  t.team_id
FROM
  (
    SELECT
      t1.salary,
      count(*) AS team_id
    FROM
      (
        SELECT
          salary
        FROM
          Employees
        GROUP BY
          salary
        HAVING
          count(*) <> 1
      ) t1
      JOIN (
        SELECT
          salary
        FROM
          Employees
        GROUP BY
          salary
        HAVING
          count(*) <> 1
      ) t2 ON t2.salary <= t1.salary
    GROUP BY
      t1.salary
  ) t
  JOIN Employees e ON e.salary = t.salary
ORDER BY
  t.team_id,
  e.employee_id