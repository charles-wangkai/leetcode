SELECT
    u.unique_id,
    e.name
FROM
    Employees e
    LEFT OUTER JOIN EmployeeUNI u ON u.id = e.id