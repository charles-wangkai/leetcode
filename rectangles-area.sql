SELECT
    point1.id AS p1,
    point2.id AS p2,
    ABS(point1.x_value - point2.x_value) * ABS(point1.y_value - point2.y_value) AS area
FROM Points point1
JOIN Points point2
ON point1.id < point2.id
AND point1.x_value <> point2.x_value
AND point1.y_value <> point2.y_value
ORDER BY area DESC, p1, p2