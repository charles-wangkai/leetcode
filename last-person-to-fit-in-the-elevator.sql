SELECT
    person_name
FROM
    Queue q
WHERE
    (
        SELECT
            sum(weight)
        FROM
            Queue
        WHERE
            turn <= q.turn
    ) <= 1000
ORDER BY
    turn DESC
LIMIT
    1