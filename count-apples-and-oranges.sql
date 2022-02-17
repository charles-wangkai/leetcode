SELECT
  sum(apple_count) AS apple_count,
  sum(orange_count) AS orange_count
FROM
  (
    SELECT
      coalesce(c.apple_count, 0) + b.apple_count AS apple_count,
      coalesce(c.orange_count, 0) + b.orange_count AS orange_count
    FROM
      Boxes b
      LEFT OUTER JOIN Chests c ON c.chest_id = b.chest_id
  ) t