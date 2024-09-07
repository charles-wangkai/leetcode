SELECT
  regexp_substr(tweet, '#[a-zA-Z]+') AS hashtag,
  count(*) AS hashtag_count
FROM
  Tweets
WHERE
  MONTH(tweet_date) = 2
GROUP BY
  hashtag
ORDER BY
  hashtag_count DESC,
  hashtag DESC
LIMIT
  3