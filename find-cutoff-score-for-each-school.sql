SELECT
  s.school_id,
  coalesce(min(e.score), -1) AS score
FROM
  Schools s
  LEFT OUTER JOIN Exam e ON e.student_count <= s.capacity
GROUP BY
  s.school_id