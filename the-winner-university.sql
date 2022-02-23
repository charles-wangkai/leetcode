SELECT
  CASE
    WHEN newyork_excellent_cnt > california_excellent_cnt THEN 'New York University'
    WHEN newyork_excellent_cnt < california_excellent_cnt THEN 'California University'
    ELSE 'No Winner'
  END AS winner
FROM
  (
    SELECT
      (
        SELECT
          count(*)
        FROM
          NewYork
        WHERE
          score >= 90
      ) AS newyork_excellent_cnt,
      (
        SELECT
          count(*)
        FROM
          California
        WHERE
          score >= 90
      ) AS california_excellent_cnt
  ) t