SELECT
  X AS x,
  Y AS y
FROM
  (
    SELECT
      c1.X,
      c1.y,
      count(*) AS cnt
    FROM
      Coordinates c1
      JOIN Coordinates c2 ON c2.X = c1.Y
      AND c2.Y = c1.X
    WHERE
      c1.X <= c1.Y
    GROUP BY
      c1.X,
      c1.Y
  ) t
WHERE
  x <> y
  OR cnt <> 1
ORDER BY
  X,
  Y