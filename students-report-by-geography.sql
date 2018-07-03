SET @rank_america = 0;
SET @rank_asia = 0;
SET @rank_europe = 0;

SELECT t_america.name AS America, t_asia.name AS Asia, t_europe.name AS Europe
  FROM (
        SELECT name, @rank_america := @rank_america + 1 AS rank
          FROM student
         WHERE continent = 'America'
        ORDER BY name
       ) t_america
  LEFT OUTER JOIN (
        SELECT name, @rank_asia := @rank_asia + 1 AS rank
          FROM student
         WHERE continent = 'Asia'
        ORDER BY name
       ) t_asia
    ON t_asia.rank = t_america.rank
  LEFT OUTER JOIN (
        SELECT name, @rank_europe := @rank_europe + 1 AS rank
          FROM student
         WHERE continent = 'Europe'
        ORDER BY name
       ) t_europe
    ON t_europe.rank = t_america.rank