SELECT DISTINCT L1.Num
  FROM Logs L1, Logs L2, Logs L3
 WHERE L2.Id = L1.Id + 1
   AND L2.Num = L1.Num
   AND L3.Id = L1.Id + 2
   AND L3.Num = L1.Num