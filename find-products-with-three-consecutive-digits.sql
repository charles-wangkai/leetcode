SELECT
  product_id,
  name
FROM
  Products
WHERE
  name RLIKE '\\d{3}'
  AND name NOT RLIKE '\\d{4}'
ORDER BY
  product_id