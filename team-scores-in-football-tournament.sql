SELECT
    team_id,
    team_name,
    sum(
        CASE
            WHEN self_goals > other_goals THEN 3
            WHEN self_goals = other_goals THEN 1
            ELSE 0
        END
    ) AS num_points
FROM
    (
        SELECT
            t.team_id,
            t.team_name,
            m.host_goals AS self_goals,
            m.guest_goals AS other_goals
        FROM
            Teams t
            LEFT OUTER JOIN Matches m ON m.host_team = t.team_id
        UNION
        ALL
        SELECT
            t.team_id,
            t.team_name,
            m.guest_goals AS self_goals,
            m.host_goals AS other_goals
        FROM
            Teams t
            LEFT OUTER JOIN Matches m ON m.guest_team = t.team_id
    ) t1
GROUP BY
    team_id,
    team_name
ORDER BY
    num_points DESC,
    team_id