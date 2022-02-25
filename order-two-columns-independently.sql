SELECT
  t1.first_col,
  t2.second_col
FROM
  (
    SELECT
      first_col,
      @first_rank := @first_rank + 1 AS first_rank
    FROM
      Data,
      (
        SELECT
          @first_rank := 0
      ) t
    ORDER BY
      first_col
  ) t1
  JOIN (
    SELECT
      second_col,
      @second_rank := @second_rank + 1 AS second_rank
    FROM
      Data,
      (
        SELECT
          @second_rank := 0
      ) t
    ORDER BY
      second_col DESC
  ) t2 ON t2.second_rank = t1.first_rank