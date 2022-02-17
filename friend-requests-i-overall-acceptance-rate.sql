SELECT
     round(
          CASE
               WHEN (
                    SELECT
                         count(*)
                    FROM
                         FriendRequest
               ) = 0 THEN 0.0
               ELSE (
                    SELECT
                         count(DISTINCT requester_id, accepter_id)
                    FROM
                         RequestAccepted
               ) / (
                    SELECT
                         count(DISTINCT sender_id, send_to_id)
                    FROM
                         FriendRequest
               )
          END,
          2
     ) AS accept_rate