SELECT IF(COUNT(*) = 0, NULL, MAX(num)) AS num
  FROM (SELECT num, COUNT(*) AS cnt
          FROM number
        GROUP BY num
        HAVING COUNT(*) = 1
       ) t