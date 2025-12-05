SELECT
  first_course,
  second_course,
  count(*) AS transition_count
FROM
  (
    SELECT
      t1.user_id,
      t1.course_name AS first_course,
      lead(t1.course_name) OVER (
        PARTITION BY t1.user_id
        ORDER BY
          t1.completion_date
      ) AS second_course
    FROM
      course_completions t1
      JOIN (
        SELECT
          user_id
        FROM
          course_completions
        GROUP BY
          user_id
        HAVING
          count(*) >= 5
          AND avg(course_rating) >= 4
      ) t2 ON t2.user_id = t1.user_id
  ) t
WHERE
  second_course IS NOT NULL
GROUP BY
  first_course,
  second_course
ORDER BY
  transition_count DESC,
  first_course,
  second_course