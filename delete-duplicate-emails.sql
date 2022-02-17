DELETE FROM
  Person
WHERE
  Id NOT IN (
    SELECT
      MinId
    FROM
      (
        SELECT
          min(Id) MinId
        FROM
          Person
        GROUP BY
          Email
      ) t
  )