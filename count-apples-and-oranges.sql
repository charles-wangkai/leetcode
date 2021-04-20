SELECT
  SUM(apple_count) AS apple_count,
  SUM(orange_count) AS orange_count
FROM (
  SELECT
    COALESCE(c.apple_count, 0) + b.apple_count AS apple_count,
    COALESCE(c.orange_count, 0) + b.orange_count AS orange_count
  FROM Boxes b
  LEFT OUTER JOIN Chests c
  ON c.chest_id = b.chest_id
) t