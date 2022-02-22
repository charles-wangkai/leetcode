SELECT
  count(*) AS accounts_count
FROM
  Subscriptions
WHERE
  year(start_date) <= 2021
  AND year(end_date) >= 2021
  AND account_id NOT IN (
    SELECT
      account_id
    FROM
      Streams
    WHERE
      year(stream_date) = 2021
  )