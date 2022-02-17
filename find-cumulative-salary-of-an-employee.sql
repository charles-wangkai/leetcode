SELECT
  e1.Id,
  e1.Month,
  sum(e2.Salary) AS Salary
FROM
  Employee e1
  JOIN Employee e2 ON e2.Id = e1.Id
  AND e2.Month BETWEEN e1.Month - 2
  AND e1.Month
WHERE
  e1.Month <> (
    SELECT
      max(e3.Month)
    FROM
      Employee e3
    WHERE
      e3.Id = e1.Id
  )
GROUP BY
  e1.Id,
  e1.Month
ORDER BY
  Id,
  MONTH DESC