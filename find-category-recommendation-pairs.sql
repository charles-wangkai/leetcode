select
  i1.category as category1,
  i2.category as category2,
  count(distinct p1.user_id) as customer_count
from
  ProductInfo i1
  join ProductInfo i2 on i2.category > i1.category
  join ProductPurchases p1 on p1.product_id = i1.product_id
  join ProductPurchases p2 on p2.product_id = i2.product_id
  and p2.user_id = p1.user_id
group by
  i1.category,
  i2.category
having
  count(distinct p1.user_id) >= 3
order by
  customer_count desc,
  category1,
  category2