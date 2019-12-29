SELECT
    e.employee_id,
    t.team_size
FROM Employee e
JOIN (
    SELECT
        team_id,
        COUNT(*) AS team_size
    FROM Employee
    GROUP BY team_id
) t
ON t.team_id = e.team_id