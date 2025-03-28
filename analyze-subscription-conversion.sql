select
  user_id,
  (
    select
      round(avg(activity_duration), 2)
    from
      UserActivity u
    where
      u.user_id = t.user_id
      and u.activity_type = 'free_trial'
  ) as trial_avg_duration,
  (
    select
      round(avg(activity_duration), 2)
    from
      UserActivity u
    where
      u.user_id = t.user_id
      and u.activity_type = 'paid'
  ) as paid_avg_duration
from
  (
    select
      distinct user_id
    from
      UserActivity u1
    where
      activity_type = 'free_trial'
      and exists (
        select
          1
        from
          UserActivity u2
        where
          u2.user_id = u1.user_id
          and u2.activity_type = 'paid'
          and u2.activity_date > u1.activity_date
      )
  ) t
order by
  user_id