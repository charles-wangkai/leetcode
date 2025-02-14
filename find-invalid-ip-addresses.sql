select
  ip,
  count(*) as invalid_count
from
  logs
where
  ip not rlike '^((0|[1-9][0-9]{0,1}|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}(0|[1-9][0-9]{0,1}|1[0-9]{2}|2[0-4][0-9]|25[0-5])$'
group by
  ip
order by
  invalid_count desc,
  ip desc