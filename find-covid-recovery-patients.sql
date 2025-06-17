SELECT
  t.patient_id,
  p.patient_name,
  p.age,
  t.recovery_time
FROM
  (
    SELECT
      pos.patient_id,
      datediff(min(neg.test_date), pos.positive_date) AS recovery_time
    FROM
      (
        SELECT
          patient_id,
          min(test_date) AS positive_date
        FROM
          covid_tests
        WHERE
          result = 'Positive'
        GROUP BY
          patient_id
      ) pos
      JOIN covid_tests neg ON neg.patient_id = pos.patient_id
      AND neg.result = 'Negative'
      AND neg.test_date > pos.positive_date
    GROUP BY
      pos.patient_id
  ) t
  JOIN patients p ON p.patient_id = t.patient_id
ORDER BY
  t.recovery_time,
  p.patient_name