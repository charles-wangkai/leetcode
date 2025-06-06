select
  l.book_id,
  l.title,
  l.author,
  l.genre,
  l.publication_year,
  l.total_copies as current_borrowers
from
  library_books l
where
  l.total_copies = (
    select
      count(*)
    from
      borrowing_records b
    where
      b.book_id = l.book_id
      and b.return_date is null
  )
order by
  current_borrowers desc,
  title