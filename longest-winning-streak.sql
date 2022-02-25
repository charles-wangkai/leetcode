SELECT
  m1.player_id,
  coalesce(t1.longest_streak, 0) AS longest_streak
FROM
  (
    SELECT
      DISTINCT player_id
    FROM
      Matches
  ) m1
  LEFT OUTER JOIN (
    SELECT
      player_id,
      max(streak) AS longest_streak
    FROM
      (
        SELECT
          m1.player_id,
          (
            SELECT
              count(*)
            FROM
              Matches m4
            WHERE
              m4.player_id = m1.player_id
              AND m4.match_day BETWEEN m2.match_day
              AND m1.match_day
          ) AS streak
        FROM
          Matches m1
          JOIN Matches m2 ON m1.result = 'Win'
          AND m2.result = 'Win'
          AND m2.player_id = m1.player_id
          AND m2.match_day <= m1.match_day
          AND NOT EXISTS (
            SELECT
              1
            FROM
              Matches m3
            WHERE
              m3.player_id = m1.player_id
              AND m3.match_day BETWEEN m2.match_day
              AND m1.match_day
              AND m3.result <> 'Win'
          )
      ) t
    GROUP BY
      player_id
  ) t1 ON t1.player_id = m1.player_id