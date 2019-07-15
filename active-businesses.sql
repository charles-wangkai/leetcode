SELECT business_id
FROM Events e,
     (
        SELECT event_type,
               SUM(occurences) / COUNT(*) AS avg_occurences
        FROM Events
        GROUP BY event_type
     ) t
WHERE e.event_type = t.event_type
AND e.occurences > t.avg_occurences
GROUP BY e.business_id
HAVING COUNT(*) > 1