SELECT
  t_america.name AS America,
  t_asia.name AS Asia,
  t_europe.name AS Europe
FROM
  (
    SELECT
      @rank_america := 0,
      @rank_asia := 0,
      @rank_europe := 0
  ) t,
  (
    SELECT
      name,
      @rank_america := @rank_america + 1 AS rnk
    FROM
      student
    WHERE
      continent = 'America'
    ORDER BY
      name
  ) t_america
  LEFT OUTER JOIN (
    SELECT
      name,
      @rank_asia := @rank_asia + 1 AS rnk
    FROM
      student
    WHERE
      continent = 'Asia'
    ORDER BY
      name
  ) t_asia ON t_asia.rnk = t_america.rnk
  LEFT OUTER JOIN (
    SELECT
      name,
      @rank_europe := @rank_europe + 1 AS rnk
    FROM
      student
    WHERE
      continent = 'Europe'
    ORDER BY
      name
  ) t_europe ON t_europe.rnk = t_america.rnk