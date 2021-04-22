SELECT
  player_id,
  player_name,
  COUNT(*) AS grand_slams_count
FROM (
  SELECT
    p.player_id,
    p.player_name
  FROM Players p
  JOIN Championships c
  ON c.Wimbledon = p.player_id

  UNION ALL

  SELECT
    p.player_id,
    p.player_name
  FROM Players p
  JOIN Championships c
  ON c.Fr_open = p.player_id

  UNION ALL

  SELECT
    p.player_id,
    p.player_name
  FROM Players p
  JOIN Championships c
  ON c.US_open = p.player_id

  UNION ALL

  SELECT
    p.player_id,
    p.player_name
  FROM Players p
  JOIN Championships c
  ON c.Au_open = p.player_id
) t
GROUP BY player_id, player_name