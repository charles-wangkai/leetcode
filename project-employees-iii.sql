SELECT p.project_id,
       p.employee_id
  FROM Project p,
       Employee e,
       (
        SELECT p.project_id,
               MAX(e.experience_years) AS max_experience_years
          FROM Project p, Employee e
         WHERE p.employee_id = e.employee_id
        GROUP BY p.project_id
       ) t
 WHERE p.employee_id = e.employee_id
   AND p.project_id = t.project_id
   AND e.experience_years = t.max_experience_years