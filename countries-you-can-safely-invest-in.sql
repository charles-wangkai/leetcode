SELECT t1.country
FROM (
    SELECT
        MIN(t.name) AS country,
        AVG(c.duration) AS avg_duration_per_country
    FROM Calls c
    JOIN Person p
    ON p.id in (c.caller_id, c.callee_id)
    JOIN Country t
    ON t.country_code = SUBSTR(p.phone_number, 1, 3)
    GROUP BY t.country_code
) t1
WHERE avg_duration_per_country > (
    SELECT AVG(duration)
    FROM Calls c
)