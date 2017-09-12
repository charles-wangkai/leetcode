SELECT CASE WHEN id % 2 = 0 THEN id - 1
            ELSE LEAST(id + 1, (SELECT MAX(id) from seat))
       END AS id,
       student
FROM seat
ORDER BY id