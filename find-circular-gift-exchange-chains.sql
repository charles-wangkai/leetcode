-- https://leetcode.com/problems/find-circular-gift-exchange-chains/solutions/6202389/recursive-cte-to-find-cycles/
WITH recursive e AS (
  SELECT
    giver_id,
    receiver_id,
    gift_value AS total_gift_value,
    1 AS chain_length
  FROM
    SecretSanta
  UNION
  ALL
  SELECT
    e.giver_id,
    s.receiver_id,
    e.total_gift_value + s.gift_value AS total_gift_value,
    e.chain_length + 1 AS chain_length
  FROM
    e
    JOIN SecretSanta s ON s.giver_id = e.receiver_id
  WHERE
    e.giver_id <> e.receiver_id
)
SELECT
  row_number() over (
    ORDER BY
      chain_length DESC,
      total_gift_value DESC
  ) AS chain_id,
  chain_length,
  total_gift_value
FROM
  (
    SELECT
      DISTINCT chain_length,
      total_gift_value
    FROM
      e
    WHERE
      giver_id = receiver_id
  ) t