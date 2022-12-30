WITH t AS (
  SELECT
    id,
    drink,
    row_number() over() AS row_num
  FROM
    CoffeeShop
)
SELECT
  t1.id,
  t2.drink
FROM
  t t1
  JOIN t t2 ON t2.row_num <= t1.row_num
WHERE
  t2.drink IS NOT NULL
  AND NOT EXISTS (
    SELECT
      1
    FROM
      t
    WHERE
      t.row_num <= t1.row_num
      AND t.row_num > t2.row_num
      AND t.drink IS NOT NULL
  )
ORDER BY
  t1.row_num