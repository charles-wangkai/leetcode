SELECT
  concat_ws(
    ',',
    t1.topping_name,
    t2.topping_name,
    t3.topping_name
  ) AS pizza,
  t1.cost + t2.cost + t3.cost AS total_cost
FROM
  Toppings t1
  JOIN Toppings t2 ON t2.topping_name > t1.topping_name
  JOIN Toppings t3 ON t3.topping_name > t2.topping_name
ORDER BY
  total_cost DESC,
  pizza