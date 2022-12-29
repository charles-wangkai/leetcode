SELECT
  s.student_id,
  s.department_id,
  IF (
    t.student_num_in_department = 1,
    0,
    round(
      (
        rank() OVER (
          PARTITION by s.department_id
          ORDER BY
            s.mark DESC
        ) - 1
      ) * 100 / (t.student_num_in_department - 1),
      2
    )
  ) AS percentage
FROM
  Students s
  JOIN (
    SELECT
      department_id,
      count(*) AS student_num_in_department
    FROM
      Students
    GROUP BY
      department_id
  ) t ON t.department_id = s.department_id