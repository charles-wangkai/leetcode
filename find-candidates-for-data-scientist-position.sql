SELECT
  t1.candidate_id
FROM
  (
    SELECT
      candidate_id
    FROM
      Candidates c1
    WHERE
      skill = 'Python'
  ) t1
  JOIN (
    SELECT
      candidate_id
    FROM
      Candidates c1
    WHERE
      skill = 'Tableau'
  ) t2 ON t2.candidate_id = t1.candidate_id
  JOIN (
    SELECT
      candidate_id
    FROM
      Candidates c1
    WHERE
      skill = 'PostgreSQL'
  ) t3 ON t3.candidate_id = t2.candidate_id
ORDER BY
  t1.candidate_id