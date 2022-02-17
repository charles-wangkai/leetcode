SELECT
    t1.sub_id AS post_id,
    coalesce(t2.number_of_comments, 0) AS number_of_comments
FROM
    (
        SELECT
            DISTINCT sub_id
        FROM
            Submissions
        WHERE
            parent_id IS NULL
    ) t1
    LEFT OUTER JOIN (
        SELECT
            parent_id,
            count(DISTINCT sub_id) AS number_of_comments
        FROM
            Submissions
        WHERE
            parent_id IS NOT NULL
        GROUP BY
            parent_id
    ) t2 ON t2.parent_id = t1.sub_id
ORDER BY
    post_id