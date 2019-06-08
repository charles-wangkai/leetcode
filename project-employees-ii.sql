SELECT p.project_id
  FROM Project p,
       (
           SELECT MAX(employee_cnt) AS max_employee_cnt
           FROM (
               SELECT COUNT(*) AS employee_cnt
               FROM Project
               GROUP BY project_id
           ) t
       ) t1
GROUP BY p.project_id, t1.max_employee_cnt
HAVING COUNT(*) = t1.max_employee_cnt