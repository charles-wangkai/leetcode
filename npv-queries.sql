SELECT
    q.id,
    q.year,
    COALESCE(v.npv, 0) AS npv
FROM Queries q
LEFT OUTER JOIN NPV v
ON v.id = q.id
AND v.year = q.year