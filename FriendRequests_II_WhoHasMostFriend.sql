SELECT id, COUNT(*) AS num
  FROM (
    SELECT requester_id AS id, accepter_id
      FROM request_accepted

    UNION ALL

    SELECT accepter_id AS id, requester_id
      FROM request_accepted
  ) t
GROUP BY id
ORDER BY num desc
LIMIT 1