SELECT
  N,
  CASE
    WHEN P IS NULL THEN 'Root'
    WHEN N IN (
      SELECT
        P
      FROM
        Tree
    ) THEN 'Inner'
    ELSE 'Leaf'
  END AS TYPE
FROM
  Tree
ORDER BY
  N