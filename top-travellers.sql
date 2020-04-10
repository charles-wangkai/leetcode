SELECT
    MIN(u.name) AS name,
    SUM(COALESCE(r.distance, 0)) AS travelled_distance
FROM Users u
LEFT OUTER JOIN Rides r
ON r.user_id = u.id
GROUP BY u.id
ORDER BY travelled_distance DESC, u.name