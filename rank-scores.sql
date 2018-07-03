SELECT Score,
       (
        SELECT COUNT(DISTINCT Score) + 1
          FROM Scores S2
         WHERE S2.Score > S1.Score
       ) Rank
  FROM Scores S1
ORDER BY Score DESC