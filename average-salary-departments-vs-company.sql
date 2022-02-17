SELECT
     pay_month,
     department_id,
     CASE
          WHEN avg(amount) > (
               SELECT
                    avg(amount)
               FROM
                    salary s1
               WHERE
                    date_format(s1.pay_date, '%Y-%m') = s.pay_month
          ) + 0.000000001 THEN 'higher'
          WHEN avg(amount) < (
               SELECT
                    avg(amount)
               FROM
                    salary s1
               WHERE
                    date_format(s1.pay_date, '%Y-%m') = s.pay_month
          ) - 0.000000001 THEN 'lower'
          ELSE 'same'
     END AS comparison
FROM
     (
          SELECT
               id,
               employee_id,
               amount,
               date_format(pay_date, '%Y-%m') AS pay_month
          FROM
               salary
     ) s
     JOIN employee e ON s.employee_id = e.employee_id
GROUP BY
     pay_month,
     department_id