SELECT d.Name Department,
       e.Name Employee,
       e.Salary
  FROM Employee e,
       Department d
 WHERE e.DepartmentId = d.Id
   AND (
        SELECT COUNT(*)
          FROM (
                SELECT DISTINCT DepartmentId,
                                Salary
                  FROM Employee
               ) e1
         WHERE e1.DepartmentId = e.DepartmentId
           AND e1.Salary >= e.Salary
       ) <= 3