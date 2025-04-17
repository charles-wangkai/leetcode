select
  i1.product_id as product1_id,
  i2.product_id as product2_id,
  i1.category as product1_category,
  i2.category as product2_category,
  count(*) as customer_count
from
  ProductInfo i1
  join ProductInfo i2 on i2.product_id > i1.product_id
  join (
    select
      distinct user_id
    from
      ProductPurchases
  ) u on exists(
    select
      1
    from
      ProductPurchases p
    where
      p.user_id = u.user_id
      and p.product_id = i1.product_id
  )
  and exists(
    select
      1
    from
      ProductPurchases p
    where
      p.user_id = u.user_id
      and p.product_id = i2.product_id
  )
group by
  i1.product_id,
  i1.category,
  i2.product_id,
  i2.category
having
  count(*) >= 3
order by
  customer_count desc,
  product1_id,
  product2_id