SELECT
    p.project_id
FROM
    Project p,
    (
        SELECT
            max(employee_cnt) AS max_employee_cnt
        FROM
            (
                SELECT
                    count(*) AS employee_cnt
                FROM
                    Project
                GROUP BY
                    project_id
            ) t
    ) t1
GROUP BY
    p.project_id,
    t1.max_employee_cnt
HAVING
    count(*) = t1.max_employee_cnt