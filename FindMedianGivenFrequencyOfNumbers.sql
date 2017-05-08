SELECT (lt.Number + gt.Number) / 2 median
FROM (
    SELECT *
    FROM (
        SELECT n1.Number, SUM(n2.Frequency) AS lte_num
          FROM Numbers n1
          JOIN Numbers n2
            ON n2.Number <= n1.Number
        GROUP BY n1.Number
    ) t
    WHERE lte_num >= (SELECT SUM(Frequency) / 2 FROM Numbers)
    ORDER BY t.Number
    LIMIT 1
) lt
JOIN (
    SELECT *
    FROM (
        SELECT n1.Number, SUM(n2.Frequency) AS gte_num
          FROM Numbers n1
          JOIN Numbers n2
            ON n2.Number >= n1.Number
        GROUP BY n1.Number
    ) t
    WHERE gte_num >= (SELECT SUM(Frequency) / 2 FROM Numbers)
    ORDER BY t.Number DESC
    LIMIT 1
) gt
ON 1 = 1