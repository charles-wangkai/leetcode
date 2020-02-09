SELECT MIN(u.name) AS results
FROM (
    SELECT
        user_id,
        COUNT(*) AS rating_cnt
    FROM Movie_Rating
    GROUP BY user_id
) t
JOIN Users u
ON u.user_id = t.user_id
WHERE t.rating_cnt = (
    SELECT MAX(rating_cnt)
    FROM (
        SELECT COUNT(*) AS rating_cnt
        FROM Movie_Rating
        GROUP BY user_id
    ) t1
)

UNION ALL

SELECT MIN(m.title) AS results
FROM (
    SELECT
        movie_id,
        AVG(rating) AS rating
    FROM Movie_Rating
    WHERE created_at BETWEEN '2020-02-01' AND '2020-02-29'
    GROUP BY movie_id
) t
JOIN Movies m
ON m.movie_id = t.movie_id
WHERE t.rating = (
    SELECT MAX(rating) AS max_rating
    FROM (
        SELECT AVG(rating) AS rating
        FROM Movie_Rating
        WHERE created_at BETWEEN '2020-02-01' AND '2020-02-29'
        GROUP BY movie_id
    ) t1
)