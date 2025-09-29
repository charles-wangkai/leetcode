select
  book_id,
  title,
  author,
  published_year
from
  books
where
  rating is null
order by
  book_id