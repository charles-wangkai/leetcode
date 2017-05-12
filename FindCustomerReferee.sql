SELECT name
  FROM customer
 WHERE referee_id IS NULL
    OR referee_id <> (SELECT id FROM customer WHERE name = 'Jane')