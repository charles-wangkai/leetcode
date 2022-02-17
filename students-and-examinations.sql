SELECT
    stud.student_id,
    stud.student_name,
    subj.subject_name,
    count(exam.student_id) AS attended_exams
FROM
    Students stud
    JOIN Subjects subj
    LEFT OUTER JOIN Examinations exam ON exam.student_id = stud.student_id
    AND exam.subject_name = subj.subject_name
GROUP BY
    stud.student_id,
    stud.student_name,
    subj.subject_name
ORDER BY
    stud.student_id,
    subj.subject_name