SELECT
  'Senior' AS experience,
  coalesce(max(employee_cnt), 0) AS accepted_candidates
FROM
  (
    SELECT
      c1.employee_id,
      count(*) AS employee_cnt
    FROM
      Candidates c1
      JOIN Candidates c2 ON c1.experience = 'Senior'
      AND c2.experience = 'Senior'
      AND (
        c2.salary < c1.salary
        OR (
          c2.salary = c1.salary
          AND c2.employee_id <= c1.employee_id
        )
      )
    GROUP BY
      c1.employee_id
    HAVING
      sum(c2.salary) <= 70000
  ) t1
UNION
SELECT
  'Junior' AS experience,
  coalesce(max(employee_cnt), 0) AS accepted_candidates
FROM
  (
    SELECT
      c1.employee_id,
      count(*) AS employee_cnt,
      sum(c2.salary) AS salary_sum
    FROM
      Candidates c1
      JOIN Candidates c2 ON c1.experience = 'Junior'
      AND c2.experience = 'Junior'
      AND (
        c2.salary < c1.salary
        OR (
          c2.salary = c1.salary
          AND c2.employee_id <= c1.employee_id
        )
      )
    GROUP BY
      c1.employee_id
    HAVING
      sum(c2.salary) <= 70000 - (
        SELECT
          coalesce(max(salary_sum), 0) AS salary_max_sum
        FROM
          (
            SELECT
              c1.employee_id,
              sum(c2.salary) AS salary_sum
            FROM
              Candidates c1
              JOIN Candidates c2 ON c1.experience = 'Senior'
              AND c2.experience = 'Senior'
              AND (
                c2.salary < c1.salary
                OR (
                  c2.salary = c1.salary
                  AND c2.employee_id <= c1.employee_id
                )
              )
            GROUP BY
              c1.employee_id
            HAVING
              sum(c2.salary) <= 70000
          ) t3
      )
  ) t2