SELECT
    e1.student_id,
    min(e1.course_id) AS course_id,
    e1.grade
FROM
    Enrollments e1,
    (
        SELECT
            student_id,
            max(grade) AS max_grade
        FROM
            Enrollments
        GROUP BY
            student_id
    ) e2
WHERE
    e1.student_id = e2.student_id
    AND e1.grade = e2.max_grade
GROUP BY
    e1.student_id,
    e1.grade
ORDER BY
    e1.student_id