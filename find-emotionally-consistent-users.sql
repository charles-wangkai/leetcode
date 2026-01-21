SELECT
  t1.user_id,
  t2.reaction AS dominant_reaction,
  round(t2.reaction_cnt / t1.total_cnt, 2) AS reaction_ratio
FROM
  (
    SELECT
      user_id,
      count(*) AS total_cnt
    FROM
      reactions
    GROUP BY
      user_id
  ) t1
  JOIN (
    SELECT
      user_id,
      reaction,
      reaction_cnt,
      row_number() over (
        PARTITION by user_id
        ORDER BY
          reaction_cnt DESC
      ) AS row_num
    FROM
      (
        SELECT
          user_id,
          reaction,
          count(*) AS reaction_cnt
        FROM
          reactions
        GROUP BY
          user_id,
          reaction
      ) t
  ) t2 ON t2.user_id = t1.user_id
  AND t2.row_num = 1
WHERE
  t1.total_cnt >= 5
  AND t2.reaction_cnt * 5 >= t1.total_cnt * 3
ORDER BY
  reaction_ratio DESC,
  t1.user_id