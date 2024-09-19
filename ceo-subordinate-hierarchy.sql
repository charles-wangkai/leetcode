# https://leetcode.com/problems/ceo-subordinate-hierarchy/solutions/5556013/mysql-beat-100-recursive-cte/
WITH RECURSIVE t AS (
  SELECT
    employee_id,
    0 AS hierarchy_level
  FROM
    Employees
  WHERE
    manager_id IS NULL
  UNION
  ALL
  SELECT
    e.employee_id,
    t.hierarchy_level + 1
  FROM
    Employees e
    JOIN t ON t.employee_id = e.manager_id
)
SELECT
  e.employee_id AS subordinate_id,
  e.employee_name AS subordinate_name,
  t.hierarchy_level,
  e.salary - (
    SELECT
      salary
    FROM
      Employees
    WHERE
      manager_id IS NULL
  ) AS salary_difference
FROM
  Employees e
  JOIN t ON t.employee_id = e.employee_id
WHERE
  e.manager_id IS NOT NULL
ORDER BY
  t.hierarchy_level,
  e.employee_id