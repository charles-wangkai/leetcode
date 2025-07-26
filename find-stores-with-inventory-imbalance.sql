SELECT
  s.store_id,
  s.store_name,
  s.location,
  te.product_name AS most_exp_product,
  tc.product_name AS cheapest_product,
  round(tc.quantity / te.quantity, 2) AS imbalance_ratio
FROM
  (
    SELECT
      store_id
    FROM
      inventory i
    GROUP BY
      store_id
    HAVING
      count(DISTINCT product_name) >= 3
  ) t
  JOIN (
    SELECT
      store_id,
      product_name,
      quantity
    FROM
      inventory ie
    WHERE
      NOT EXISTS (
        SELECT
          1
        FROM
          inventory i
        WHERE
          i.store_id = ie.store_id
          AND i.price > ie.price
      )
  ) te ON te.store_id = t.store_id
  JOIN (
    SELECT
      store_id,
      product_name,
      quantity
    FROM
      inventory ic
    WHERE
      NOT EXISTS (
        SELECT
          1
        FROM
          inventory i
        WHERE
          i.store_id = ic.store_id
          AND i.price < ic.price
      )
  ) tc ON tc.store_id = t.store_id
  JOIN stores s ON s.store_id = t.store_id
WHERE
  te.quantity < tc.quantity
ORDER BY
  imbalance_ratio DESC,
  s.store_name