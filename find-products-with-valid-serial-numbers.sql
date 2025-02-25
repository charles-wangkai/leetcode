select
  *
from
  products
where
  description rlike 'SN[0-9]{4}-[0-9]{4}($|[^0-9])'
order by
  product_id