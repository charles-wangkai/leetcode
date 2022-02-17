SELECT
  c.Name
FROM
  (
    SELECT
      CandidateId,
      count(*) AS cnt
    FROM
      Vote
    GROUP BY
      CandidateId
    ORDER BY
      cnt DESC
    LIMIT
      1
  ) v
  JOIN Candidate c ON v.CandidateId = c.id