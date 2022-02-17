SELECT
  person1,
  person2,
  count(*) AS call_count,
  sum(duration) AS total_duration
FROM
  (
    SELECT
      least(from_id, to_id) AS person1,
      greatest(from_id, to_id) AS person2,
      duration
    FROM
      Calls
  ) t
GROUP BY
  person1,
  person2