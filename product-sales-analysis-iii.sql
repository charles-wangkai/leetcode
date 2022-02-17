SELECT
  s.product_id,
  s.year AS first_year,
  s.quantity,
  s.price
FROM
  Sales s
WHERE
  NOT EXISTS (
    SELECT
      1
    FROM
      Sales s1
    WHERE
      s1.product_id = s.product_id
      AND s1.year < s.year
  )