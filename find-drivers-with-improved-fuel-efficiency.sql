select
  driver_id,
  driver_name,
  round(first_half_avg, 2) as first_half_avg,
  round(second_half_avg, 2) as second_half_avg,
  round(second_half_avg - first_half_avg, 2) as efficiency_improvement
from
  (
    select
      d.driver_id,
      d.driver_name,
      (
        select
          avg(t.distance_km / t.fuel_consumed)
        from
          trips t
        where
          t.driver_id = d.driver_id
          and month(t.trip_date) <= 6
      ) as first_half_avg,
      (
        select
          avg(t.distance_km / t.fuel_consumed)
        from
          trips t
        where
          t.driver_id = d.driver_id
          and month(t.trip_date) >= 7
      ) as second_half_avg
    from
      drivers d
  ) t1
where
  second_half_avg > first_half_avg
order by
  efficiency_improvement desc,
  driver_name