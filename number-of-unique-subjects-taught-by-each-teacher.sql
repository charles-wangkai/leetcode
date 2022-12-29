SELECT
  teacher_id,
  count(DISTINCT subject_id) AS cnt
FROM
  Teacher
GROUP BY
  teacher_id