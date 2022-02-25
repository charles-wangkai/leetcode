SELECT
  t5.team_id,
  t5.name,
  t5.prev_rank - t6.current_rank AS rank_diff
FROM
  (
    SELECT
      p.team_id,
      p.name,
      @prev_rank := @prev_rank + 1 AS prev_rank
    FROM
      TeamPoints p,
      (
        SELECT
          @prev_rank := 0
      ) t4
    ORDER BY
      points DESC,
      name
  ) t5
  JOIN(
    SELECT
      t2.team_id,
      @current_rank := @current_rank + 1 AS current_rank
    FROM
      (
        SELECT
          p.team_id
        FROM
          TeamPoints p
          JOIN (
            SELECT
              team_id,
              sum(points_change) AS points
            FROM
              (
                SELECT
                  team_id,
                  points AS points_change
                FROM
                  TeamPoints
                UNION
                SELECT
                  team_id,
                  points_change
                FROM
                  PointsChange
              ) t
            GROUP BY
              team_id
          ) t1 ON t1.team_id = p.team_id
        ORDER BY
          t1.points DESC,
          p.name
      ) t2,
      (
        SELECT
          @current_rank := 0
      ) t3
  ) t6 ON t6.team_id = t5.team_id