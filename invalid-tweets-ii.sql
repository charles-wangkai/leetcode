SELECT
  tweet_id
FROM
  Tweets
WHERE
  length(content) > 140
  OR regexp_like(content, '.*@.+@.+@.+@.+.*') = 1
  OR regexp_like(content, '.*#.+#.+#.+#.+.*') = 1
ORDER BY
  tweet_id