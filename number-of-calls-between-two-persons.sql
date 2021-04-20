SELECT
  person1,
  person2,
  COUNT(*) AS call_count,
  SUM(duration) AS total_duration
FROM (
  SELECT
    LEAST(from_id, to_id) AS person1,
    GREATEST(from_id, to_id) AS person2,
    duration
  FROM Calls
) t
GROUP BY person1, person2