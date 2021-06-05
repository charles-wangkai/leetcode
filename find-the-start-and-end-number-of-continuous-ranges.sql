SELECT
    s.log_id AS start_id,
    e.log_id AS end_id
FROM Logs s
JOIN Logs e
WHERE s.log_id <= e.log_id
AND e.log_id - s.log_id + 1 = (
    SELECT COUNT(1)
    FROM Logs
    WHERE log_id BETWEEN s.log_id AND e.log_id
)
AND NOT EXISTS (
    SELECT 1
    FROM Logs s1
    JOIN Logs e1
    WHERE s1.log_id <= s.log_id
    AND e1.log_id >= e.log_id
    AND NOT (s1.log_id = s.log_id AND e1.log_id = e.log_id)
    AND e1.log_id - s1.log_id + 1 = (
        SELECT COUNT(1)
        FROM Logs
        WHERE log_id BETWEEN s1.log_id AND e1.log_id
    )
)
ORDER BY start_id
------------------
/*Another approach*/
WITH log_num AS
(
  SELECT
    s.log_id AS start_id,
    e.log_id AS end_id
  FROM Logs s
  ,Logs e
  WHERE s.log_id <= e.log_id
  AND e.log_id - s.log_id + 1 = (
    SELECT COUNT(1)
    FROM cloud_s2_ext.logs
    WHERE log_id BETWEEN s.log_id AND e.log_id
  )
)

SELECT MIN(start_id) start_num,end_num
FROM
(
SELECT start_id,end_id,MAX(end_id) over (partition by start_id) end_num FROM log_num 
)
GROUP BY 2
order by 1,2
;
