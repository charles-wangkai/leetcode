with t as (
  select
    season_id,
    team_id,
    team_name,
    wins * 3 + draws as points,
    goals_for - goals_against as goal_difference
  from
    SeasonStats
)
select
  season_id,
  team_id,
  team_name,
  points,
  goal_difference,
  row_number() over (
    PARTITION by season_id
    ORDER BY
      points desc,
      goal_difference desc,
      team_name
  ) AS position
from
  t
order by
  season_id,
  position