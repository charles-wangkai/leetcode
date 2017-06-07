SELECT ROUND(CASE WHEN (SELECT COUNT(*) FROM friend_request) = 0
                  THEN 0.0
                  ELSE (SELECT COUNT(DISTINCT requester_id, accepter_id) FROM request_accepted) / (SELECT COUNT(DISTINCT sender_id, send_to_id) FROM friend_request)
             END
             , 2) AS accept_rate