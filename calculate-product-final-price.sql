SELECT
  p.product_id,
  p.price * (100 - coalesce(d.discount, 0)) / 100 AS final_price,
  p.category
FROM
  Products p
  LEFT OUTER JOIN Discounts d ON d.category = p.category
ORDER BY
  p.product_id