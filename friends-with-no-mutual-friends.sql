SELECT
  *
FROM
  Friends f
WHERE
  NOT EXISTS (
    SELECT
      user_id
    FROM
      (
        SELECT
          user_id1 AS user_id
        FROM
          Friends
        UNION
        SELECT
          user_id2 AS user_id
        FROM
          Friends
      ) t
    WHERE
      t.user_id <> f.user_id1
      AND t.user_id <> f.user_id2
      AND (
        EXISTS (
          SELECT
            1
          FROM
            Friends f1
          WHERE
            f1.user_id1 = f.user_id1
            AND f1.user_id2 = t.user_id
        )
        OR EXISTS (
          SELECT
            1
          FROM
            Friends f1
          WHERE
            f1.user_id1 = t.user_id
            AND f1.user_id2 = f.user_id1
        )
      )
      AND (
        EXISTS (
          SELECT
            1
          FROM
            Friends f1
          WHERE
            f1.user_id1 = f.user_id2
            AND f1.user_id2 = t.user_id
        )
        OR EXISTS (
          SELECT
            1
          FROM
            Friends f1
          WHERE
            f1.user_id1 = t.user_id
            AND f1.user_id2 = f.user_id2
        )
      )
  )
ORDER BY
  user_id1,
  user_id2