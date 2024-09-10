# https://leetcode.com/problems/find-trending-hashtags-ii/solutions/4959507/mysql-recursive-cte/
WITH recursive tags AS (
  SELECT
    regexp_substr(tweet, "#[a-zA-Z]+") AS hashtag,
    regexp_replace(tweet, "#[a-zA-Z]+", "", 1, 1) AS tweet
  FROM
    Tweets
  UNION
  ALL
  SELECT
    regexp_substr(tweet, "#[a-zA-Z]+") AS hashtag,
    regexp_replace(tweet, "#[a-zA-Z]+", "", 1, 1) AS tweet
  FROM
    tags
  WHERE
    hashtag IS NOT NULL
)
SELECT
  hashtag,
  count(*) AS count
FROM
  tags
WHERE
  hashtag IS NOT NULL
GROUP BY
  hashtag
ORDER BY
  count DESC,
  hashtag DESC
LIMIT
  3