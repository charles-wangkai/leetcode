SELECT id,
       CASE WHEN p_id IS NULL
            THEN 'Root'
            ELSE CASE WHEN EXISTS (SELECT 1 FROM tree t2 WHERE t2.p_id = t1.id)
                      THEN 'Inner'
                      ELSE 'Leaf'
                 END
       END AS Type
  FROM tree t1
ORDER BY id