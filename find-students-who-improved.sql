select
  t1.student_id,
  t1.subject,
  t1.score as first_score,
  t2.score as latest_score
from
  (
    select
      student_id,
      subject,
      score
    from
      Scores s1
    where
      not exists (
        select
          1
        from
          Scores s2
        where
          s2.student_id = s1.student_id
          and s2.subject = s1.subject
          and s2.exam_date < s1.exam_date
      )
  ) t1
  join (
    select
      student_id,
      subject,
      score
    from
      Scores s1
    where
      not exists (
        select
          1
        from
          Scores s2
        where
          s2.student_id = s1.student_id
          and s2.subject = s1.subject
          and s2.exam_date > s1.exam_date
      )
  ) t2 on t2.student_id = t1.student_id
  and t2.subject = t1.subject
  and t2.score > t1.score
order by
  student_id,
  subject