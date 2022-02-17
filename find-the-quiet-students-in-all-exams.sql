SELECT
    student_id,
    student_name
FROM
    Student
WHERE
    student_id IN (
        SELECT
            student_id
        FROM
            Exam
        WHERE
            student_id NOT IN (
                SELECT
                    e.student_id
                FROM
                    Exam e
                    JOIN (
                        SELECT
                            exam_id,
                            min(score) AS extreme_score
                        FROM
                            Exam
                        GROUP BY
                            exam_id
                        UNION
                        SELECT
                            exam_id,
                            max(score) AS extreme_score
                        FROM
                            Exam
                        GROUP BY
                            exam_id
                    ) s ON s.exam_id = e.exam_id
                    AND s.extreme_score = e.score
            )
    )
ORDER BY
    student_id