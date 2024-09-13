SELECT
  student_id
FROM
  students s
WHERE
  (
    SELECT
      count(DISTINCT e.course_id)
    FROM
      enrollments e
      JOIN courses c ON c.course_id = e.course_id
      AND c.major = s.major
    WHERE
      e.student_id = s.student_id
  ) = (
    SELECT
      count(*)
    FROM
      courses c
    WHERE
      c.major = s.major
  )
  AND NOT EXISTS (
    SELECT
      1
    FROM
      enrollments e
      JOIN courses c ON c.course_id = e.course_id
      AND c.major = s.major
    WHERE
      e.student_id = s.student_id
      AND e.grade <> 'A'
  )
ORDER BY
  student_id