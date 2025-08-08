select
  b.book_id,
  b.title,
  b.author,
  b.genre,
  b.pages,
  r.rating_spread,
  round(r.polarization_score, 2) as polarization_score
from
  (
    select
      book_id,
      max(session_rating) - min(session_rating) as rating_spread,
      sum(
        if(
          session_rating <= 2
          or session_rating >= 4,
          1,
          0
        )
      ) / count(*) as polarization_score
    from
      reading_sessions
    group by
      book_id
    having
      max(session_rating) >= 4
      and min(session_rating) <= 2
      and count(*) >= 5
      and polarization_score >= 0.6
  ) r
  join books b on b.book_id = r.book_id
order by
  r.polarization_score desc,
  b.title desc