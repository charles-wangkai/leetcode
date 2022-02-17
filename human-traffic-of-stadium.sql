SELECT
  id,
  visit_date,
  people
FROM
  stadium s1
WHERE
  people >= 100
  AND (
    (
      EXISTS (
        SELECT
          1
        FROM
          stadium s2
        WHERE
          s2.people >= 100
          AND s2.id = s1.id - 2
      )
      AND EXISTS (
        SELECT
          1
        FROM
          stadium s2
        WHERE
          s2.people >= 100
          AND s2.id = s1.id - 1
      )
    )
    OR (
      EXISTS (
        SELECT
          1
        FROM
          stadium s2
        WHERE
          s2.people >= 100
          AND s2.id = s1.id - 1
      )
      AND EXISTS (
        SELECT
          1
        FROM
          stadium s2
        WHERE
          s2.people >= 100
          AND s2.id = s1.id + 1
      )
    )
    OR (
      EXISTS (
        SELECT
          1
        FROM
          stadium s2
        WHERE
          s2.people >= 100
          AND s2.id = s1.id + 1
      )
      AND EXISTS (
        SELECT
          1
        FROM
          stadium s2
        WHERE
          s2.people >= 100
          AND s2.id = s1.id + 2
      )
    )
  )