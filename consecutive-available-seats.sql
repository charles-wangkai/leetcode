SELECT seat_id
  FROM cinema c1
 WHERE free = 1
   AND EXISTS (SELECT 1 FROM cinema c2 WHERE c2.free = 1 AND (c2.seat_id = c1.seat_id - 1 OR c2.seat_id = c1.seat_id + 1))