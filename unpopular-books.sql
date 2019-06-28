SELECT book_id,
       name
FROM Books b
WHERE available_from <= DATE_SUB('2019-06-23', INTERVAL 1 MONTH)
AND NOT EXISTS (
    SELECT 1
    FROM Orders o
    WHERE o.book_id = b.book_id
    AND dispatch_date > DATE_SUB('2019-06-23', INTERVAL 1 YEAR)
    GROUP BY o.book_id
    HAVING SUM(o.quantity) >= 10
)