SELECT
    min(u.name) AS results
FROM
    (
        SELECT
            user_id,
            count(*) AS rating_cnt
        FROM
            MovieRating
        GROUP BY
            user_id
    ) t
    JOIN Users u ON u.user_id = t.user_id
WHERE
    t.rating_cnt = (
        SELECT
            max(rating_cnt)
        FROM
            (
                SELECT
                    count(*) AS rating_cnt
                FROM
                    MovieRating
                GROUP BY
                    user_id
            ) t1
    )
UNION
ALL
SELECT
    min(m.title) AS results
FROM
    (
        SELECT
            movie_id,
            avg(rating) AS rating
        FROM
            MovieRating
        WHERE
            created_at BETWEEN '2020-02-01'
            AND '2020-02-29'
        GROUP BY
            movie_id
    ) t
    JOIN Movies m ON m.movie_id = t.movie_id
WHERE
    t.rating = (
        SELECT
            max(rating) AS max_rating
        FROM
            (
                SELECT
                    avg(rating) AS rating
                FROM
                    MovieRating
                WHERE
                    created_at BETWEEN '2020-02-01'
                    AND '2020-02-29'
                GROUP BY
                    movie_id
            ) t1
    )