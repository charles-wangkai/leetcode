with t as (
  select
    t1.season,
    t1.category,
    sum(quantity) as total_quantity,
    sum(revenue) as total_revenue
  from
    (
      select
        case
          when month(s.sale_date) in (12, 1, 2) then 'Winter'
          when month(s.sale_date) in (3, 4, 5) then 'Spring'
          when month(s.sale_date) in (6, 7, 8) then 'Summer'
          when month(s.sale_date) in (9, 10, 11) then 'Fall'
        end as season,
        p.category,
        s.quantity,
        s.quantity * s.price as revenue
      from
        sales s
        join products p on p.product_id = s.product_id
    ) t1
  group by
    t1.season,
    t1.category
)
select
  *
from
  t
where
  not exists (
    select
      1
    from
      t t2
    where
      t2.season = t.season
      and (
        t2.total_quantity > t.total_quantity
        or (
          t2.total_quantity = t.total_quantity
          and t2.total_revenue > t.total_revenue
        )
      )
  )
order by
  season