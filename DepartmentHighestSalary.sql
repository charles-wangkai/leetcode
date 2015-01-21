SELECT d.Name,
       e.Name,
       e.Salary
  FROM Employee e,
       Department d,
       (
        SELECT DepartmentId,
               MAX(Salary) HighestSalary
          FROM Employee
        GROUP BY DepartmentId
       ) s
 WHERE e.DepartmentId = s.DepartmentId
   AND e.Salary = s.HighestSalary
   AND e.DepartmentId = d.Id