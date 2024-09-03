WITH t AS (
  SELECT
    dep_id,
    count(*) AS cnt
  FROM
    Employees
  GROUP BY
    dep_id
),
t1 AS (
  SELECT
    *
  FROM
    t
  WHERE
    NOT EXISTS(
      SELECT
        1
      FROM
        t t2
      WHERE
        t2.cnt > t.cnt
    )
)
SELECT
  e.emp_name AS manager_name,
  e.dep_id
FROM
  t1
  JOIN Employees e ON e.dep_id = t1.dep_id
WHERE
  e.position = 'Manager'
ORDER BY
  e.dep_id