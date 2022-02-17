SELECT
    s.company_id,
    s.employee_id,
    s.employee_name,
    round(s.salary * (100 - c.tax_percentage) / 100) AS salary
FROM
    Salaries s
    JOIN (
        SELECT
            company_id,
            CASE
                WHEN max(salary) < 1000 THEN 0
                WHEN max(salary) <= 10000 THEN 24
                ELSE 49
            END AS tax_percentage
        FROM
            Salaries
        GROUP BY
            company_id
    ) c ON c.company_id = s.company_id