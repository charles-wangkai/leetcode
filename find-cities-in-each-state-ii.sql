select
  state,
  cities,
  matching_letter_count
from
  (
    select
      state,
      group_concat(
        city
        order by
          city separator ', '
      ) as cities,
      sum(if(left(city, 1) = left(state, 1), 1, 0)) as matching_letter_count
    from
      cities
    group by
      state
    having
      count(*) >= 3
  ) t
where
  matching_letter_count >= 1
order by
  matching_letter_count desc,
  state