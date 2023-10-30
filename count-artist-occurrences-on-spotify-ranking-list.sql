SELECT
  artist,
  count(*) AS occurrences
FROM
  Spotify
GROUP BY
  artist
ORDER BY
  occurrences DESC,
  artist