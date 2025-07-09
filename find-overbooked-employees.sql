select
  e.employee_id,
  e.employee_name,
  e.department,
  t1.meeting_heavy_weeks
from
  (
    select
      employee_id,
      count(*) as meeting_heavy_weeks
    from
      (
        select
          employee_id
        from
          meetings
        group by
          employee_id,
          yearweek(meeting_date, 1)
        having
          sum(duration_hours) > 20
      ) t
    group by
      employee_id
    having
      count(*) >= 2
  ) t1
  join employees e on e.employee_id = t1.employee_id
order by
  t1.meeting_heavy_weeks desc,
  e.employee_name