SELECT
  p.post_id,
  coalesce(
    GROUP_CONCAT(
      DISTINCT k.topic_id
      ORDER BY
        CONVERT(k.topic_id, char)
    ),
    'Ambiguous!'
  ) AS topic
FROM
  Posts p
  LEFT OUTER JOIN Keywords k ON p.content RLIKE concat('\\b', k.word, '\\b')
GROUP BY
  p.post_id