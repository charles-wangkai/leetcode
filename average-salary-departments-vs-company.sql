SELECT pay_month,
       department_id,
       CASE WHEN AVG(amount) > (SELECT AVG(amount) FROM salary s1 WHERE DATE_FORMAT(s1.pay_date, '%Y-%m') = s.pay_month) THEN 'higher'
            WHEN AVG(amount) < (SELECT AVG(amount) FROM salary s1 WHERE DATE_FORMAT(s1.pay_date, '%Y-%m') = s.pay_month) THEN 'lower'
            ELSE 'same'
       END AS comparison
  FROM (SELECT id, employee_id, amount, DATE_FORMAT(pay_date, '%Y-%m') AS pay_month FROM salary) s
  JOIN employee e
    ON s.employee_id = e.employee_id
GROUP BY pay_month, department_id