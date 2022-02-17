SELECT
    p.group_id,
    min(p.player_id) AS player_id
FROM
    Players p
    JOIN (
        SELECT
            player_id,
            sum(score) AS score_sum
        FROM
            (
                SELECT
                    first_player AS player_id,
                    first_score AS score
                FROM
                    Matches
                UNION
                ALL
                SELECT
                    second_player AS player_id,
                    second_score AS score
                FROM
                    Matches
            ) t
        GROUP BY
            player_id
    ) t1 ON p.player_id = t1.player_id
    JOIN (
        SELECT
            p.group_id,
            max(t1.score_sum) AS max_score_sum
        FROM
            Players p
            JOIN (
                SELECT
                    player_id,
                    sum(score) AS score_sum
                FROM
                    (
                        SELECT
                            first_player AS player_id,
                            first_score AS score
                        FROM
                            Matches
                        UNION
                        ALL
                        SELECT
                            second_player AS player_id,
                            second_score AS score
                        FROM
                            Matches
                    ) t
                GROUP BY
                    player_id
            ) t1 ON p.player_id = t1.player_id
        GROUP BY
            p.group_id
    ) t2 ON p.group_id = t2.group_id
    AND t1.score_sum = t2.max_score_sum
GROUP BY
    p.group_id