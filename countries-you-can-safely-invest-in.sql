SELECT
    t1.country
FROM
    (
        SELECT
            min(t.name) AS country,
            avg(c.duration) AS avg_duration_per_country
        FROM
            Calls c
            JOIN Person p ON p.id IN (c.caller_id, c.callee_id)
            JOIN Country t ON t.country_code = substr(p.phone_number, 1, 3)
        GROUP BY
            t.country_code
    ) t1
WHERE
    avg_duration_per_country > (
        SELECT
            avg(duration)
        FROM
            Calls c
    )