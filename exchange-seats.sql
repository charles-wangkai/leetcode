SELECT
     CASE
          WHEN id % 2 = 0 THEN id - 1
          ELSE least(
               id + 1,
               (
                    SELECT
                         max(id)
                    FROM
                         seat
               )
          )
     END AS id,
     student
FROM
     seat
ORDER BY
     id