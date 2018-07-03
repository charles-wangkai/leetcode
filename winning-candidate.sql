SELECT c.Name
FROM (
    SELECT CandidateId, COUNT(*) AS cnt
      FROM Vote
    GROUP BY CandidateId
    ORDER BY cnt DESC
    LIMIT 1
) v
JOIN Candidate c
on v.CandidateId = c.id