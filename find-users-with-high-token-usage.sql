SELECT
  user_id,
  prompt_count,
  round(avg_tokens, 2) AS avg_tokens
FROM
  (
    SELECT
      user_id,
      count(*) AS prompt_count,
      avg(tokens) AS avg_tokens
    FROM
      prompts
    GROUP BY
      user_id
  ) t
WHERE
  prompt_count >= 3
  AND EXISTS(
    SELECT
      1
    FROM
      prompts p
    WHERE
      p.user_id = t.user_id
      AND p.tokens > t.avg_tokens
  )
ORDER BY
  avg_tokens DESC,
  user_id