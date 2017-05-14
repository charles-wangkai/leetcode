SELECT d.dept_name, COALESCE(cnt, 0) AS student_number
  FROM department d
LEFT OUTER JOIN (
    SELECT dept_id, COUNT(*) AS cnt
      FROM student
    GROUP BY dept_id
) s
ON d.dept_id = s.dept_id
ORDER BY student_number DESC, dept_name