SELECT
  t.n AS ids
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
      ) t1,
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
      ) t2
  ) t
  LEFT OUTER JOIN Customers c ON c.customer_id = t.n
WHERE
  c.customer_id IS NULL
  AND t.n < (
    SELECT
      max(customer_id)
    FROM
      Customers
  )
ORDER BY
  ids