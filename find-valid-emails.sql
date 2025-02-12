select
  *
from
  Users
where
  email rlike '^[a-zA-Z0-9_]+@[[:alpha:]]+\.com$'
order by
  user_id