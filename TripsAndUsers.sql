SELECT t1.Request_at Day,
       ROUND((
                SELECT COUNT(*)
                  FROM Trips t2,
                       Users u2
                 WHERE t2.Request_at = t1.Request_at
                   AND t2.Client_Id = u2.Users_Id
                   AND u2.Banned = 'No'
                   AND t2.Status LIKE 'cancelled%'
             )
             /
             COUNT(*)
             , 2) AS "Cancellation Rate"
  FROM Trips t1,
       Users u1
 WHERE t1.Client_Id = u1.Users_Id
   AND u1.Banned = 'No'
   AND t1.Request_at BETWEEN '2013-10-01' AND '2013-10-03'
GROUP BY t1.Request_at