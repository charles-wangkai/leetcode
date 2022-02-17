SELECT
  id,
  count(*) AS num
FROM
  (
    SELECT
      requester_id AS id,
      accepter_id
    FROM
      RequestAccepted
    UNION
    ALL
    SELECT
      accepter_id AS id,
      requester_id
    FROM
      RequestAccepted
  ) t
GROUP BY
  id
ORDER BY
  num DESC
LIMIT
  1