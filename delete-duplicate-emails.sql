DELETE
  FROM Person
 WHERE Id NOT IN (
        SELECT MinId
          FROM (
            SELECT MIN(Id) MinId
              FROM Person
             GROUP BY Email
          ) t
       )