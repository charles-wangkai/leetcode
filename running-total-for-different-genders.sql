SELECT
    cs.gender,
    cs.day,
    SUM(bs.score_points) AS total
FROM Scores cs
JOIN Scores bs
ON bs.gender = cs.gender
AND bs.day <= cs.day
GROUP BY cs.gender, cs.day
ORDER BY cs.gender, cs.day