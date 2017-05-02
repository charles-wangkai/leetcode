SELECT e.Id, e.Company, e.Salary
  FROM Employee e
  JOIN (
    SELECT e.Id, COUNT(*) AS lower_cnt
      FROM Employee e, Employee el
     WHERE el.Company = e.Company
       AND (el.Salary < e.Salary OR (el.Salary = e.Salary AND el.Id <= e.Id))
    GROUP BY e.id
  ) lower
    ON e.Id = lower.Id
  JOIN (
    SELECT e.Id, COUNT(*) AS upper_cnt
      FROM Employee e, Employee eu
     WHERE eu.Company = e.Company
       AND (eu.Salary > e.Salary OR (eu.Salary = e.Salary AND eu.Id >= e.Id))
    GROUP BY e.id
  ) upper
    ON e.Id = upper.Id
 WHERE lower_cnt - upper_cnt BETWEEN -1 AND 1