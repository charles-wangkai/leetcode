WITH t AS (
  SELECT
    u.seller_id,
    count(DISTINCT i.item_id) AS num_items
  FROM
    Users u
    JOIN Orders o ON o.seller_id = u.seller_id
    JOIN Items i ON i.item_id = o.item_id
  WHERE
    i.item_brand <> u.favorite_brand
  GROUP BY
    u.seller_id
)
SELECT
  seller_id,
  num_items
FROM
  t
WHERE
  num_items = (
    SELECT
      max(num_items)
    FROM
      t
  )
ORDER BY
  seller_id