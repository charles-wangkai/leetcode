with t as (
  select
    r.employee_id,
    r.rating,
    (
      select
        count(*)
      from
        performance_reviews r1
      where
        r1.employee_id = r.employee_id
        and r1.review_date > r.review_date
    ) as seq
  from
    performance_reviews r
),
t1 as (
  select
    distinct employee_id
  from
    t
  where
    seq < 3
  group by
    employee_id
  having
    count(*) = 3
)
select
  t1.employee_id,
  e.name,
  (
    select
      rating
    from
      t
    where
      t.employee_id = t1.employee_id
      and t.seq = 0
  ) - (
    select
      rating
    from
      t
    where
      t.employee_id = t1.employee_id
      and t.seq = 2
  ) as improvement_score
from
  t1
  join employees e on e.employee_id = t1.employee_id
where
  (
    select
      rating
    from
      t
    where
      t.employee_id = t1.employee_id
      and t.seq = 0
  ) > (
    select
      rating
    from
      t
    where
      t.employee_id = t1.employee_id
      and t.seq = 1
  )
  and (
    select
      rating
    from
      t
    where
      t.employee_id = t1.employee_id
      and t.seq = 1
  ) > (
    select
      rating
    from
      t
    where
      t.employee_id = t1.employee_id
      and t.seq = 2
  )
order by
  improvement_score desc,
  name