SELECT
  DISTINCT t1.user1_id AS user_id
FROM
  (
    SELECT
      user1_id,
      min(call_time) AS min_call_time,
      max(call_time) AS max_call_time
    FROM
      (
        SELECT
          caller_id AS user1_id,
          recipient_id AS user2_id,
          call_time
        FROM
          Calls
        UNION
        SELECT
          recipient_id AS user1_id,
          caller_id AS user2_id,
          call_time
        FROM
          Calls
      ) t
    GROUP BY
      user1_id,
      date(call_time)
  ) t1
  JOIN (
    SELECT
      caller_id AS user1_id,
      recipient_id AS user2_id,
      call_time
    FROM
      Calls
    UNION
    SELECT
      recipient_id AS user1_id,
      caller_id AS user2_id,
      call_time
    FROM
      Calls
  ) t2 ON t2.user1_id = t1.user1_id
  AND t2.call_time = t1.min_call_time
  JOIN (
    SELECT
      caller_id AS user1_id,
      recipient_id AS user2_id,
      call_time
    FROM
      Calls
    UNION
    SELECT
      recipient_id AS user1_id,
      caller_id AS user2_id,
      call_time
    FROM
      Calls
  ) t3 ON t3.user1_id = t1.user1_id
  AND t3.call_time = t1.max_call_time
  AND t3.user2_id = t2.user2_id