SELECT
    t1.visited_on,
    SUM(t2.amount) AS amount,
    ROUND(SUM(t2.amount) / 7, 2) AS average_amount
FROM (
    SELECT DISTINCT visited_on
    FROM Customer t
    WHERE EXISTS (
        SELECT 1
        FROM Customer
        WHERE visited_on = DATE_SUB(t.visited_on, INTERVAL 6 DAY)
    )
) t1
JOIN Customer t2
ON DATEDIFF(t1.visited_on, t2.visited_on) BETWEEN 0 AND 6
GROUP BY t1.visited_on
ORDER BY 1