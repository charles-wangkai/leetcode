SELECT
  t.task_id,
  v.n AS subtask_id
FROM
  (
    SELECT
      x * 10 + y + 1 AS n
    FROM
      (
        SELECT
          0 AS x
        UNION
        SELECT
          1
      ) v1,
      (
        SELECT
          0 AS y
        UNION
        SELECT
          1
        UNION
        SELECT
          2
        UNION
        SELECT
          3
        UNION
        SELECT
          4
        UNION
        SELECT
          5
        UNION
        SELECT
          6
        UNION
        SELECT
          7
        UNION
        SELECT
          8
        UNION
        SELECT
          9
      ) v2
  ) v
  JOIN Tasks t ON t.subtasks_count >= v.n
  LEFT OUTER JOIN Executed e ON e.task_id = t.task_id
  AND e.subtask_id = v.n
WHERE
  e.task_id IS NULL