-- https://leetcode.com/problems/longest-team-pass-streak/solutions/6941074/mysql-good-enough
SELECT
  team_name,
  max(streak) AS longest_streak
FROM
  (
    SELECT
      from_team_name AS team_name,
      batch,
      count(*) AS streak
    FROM
      (
        SELECT
          t1.team_name AS from_team_name,
          t2.team_name AS to_team_name,
          row_number() over (
            PARTITION by t1.team_name
            ORDER BY
              time_stamp
          ) - row_number() over (
            PARTITION by t1.team_name,
            t1.team_name = t2.team_name
            ORDER BY
              time_stamp
          ) AS batch
        FROM
          Passes p
          JOIN Teams t1 ON t1.player_id = p.pass_from
          JOIN Teams t2 ON t2.player_id = p.pass_to
      ) t
    WHERE
      from_team_name = to_team_name
    GROUP BY
      team_name,
      batch
  ) tt
GROUP BY
  team_name
ORDER BY
  team_name