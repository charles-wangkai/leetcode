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
      AND c.mandatory = 'Yes'
    WHERE
      e.student_id = s.student_id
  ) = (
    SELECT
      count(*)
    FROM
      courses c
    WHERE
      c.major = s.major
      AND c.mandatory = 'Yes'
  )
  AND NOT EXISTS (
    SELECT
      1
    FROM
      enrollments e
      JOIN courses c ON c.course_id = e.course_id
      AND c.major = s.major
      AND c.mandatory = 'Yes'
    WHERE
      e.student_id = s.student_id
      AND e.grade <> 'A'
  )
  AND (
    SELECT
      count(DISTINCT e.course_id)
    FROM
      enrollments e
      JOIN courses c ON c.course_id = e.course_id
      AND c.major = s.major
      AND c.mandatory = 'No'
    WHERE
      e.student_id = s.student_id
  ) >= 2
  AND NOT EXISTS (
    SELECT
      1
    FROM
      enrollments e
      JOIN courses c ON c.course_id = e.course_id
      AND c.major = s.major
      AND c.mandatory = 'No'
    WHERE
      e.student_id = s.student_id
      AND e.grade NOT IN ('A', 'B')
  )
  AND (
    SELECT
      avg(e.GPA)
    FROM
      enrollments e
    WHERE
      e.student_id = s.student_id
  ) >= 2.5
ORDER BY
  student_id