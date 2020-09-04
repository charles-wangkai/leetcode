SELECT
    w.name AS warehouse_name,
    SUM(w.units * p.width * p.length * p.height) AS volume
FROM Warehouse w
JOIN Products p
ON p.product_id = w.product_id
GROUP BY w.name