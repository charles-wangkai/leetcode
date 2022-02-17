SELECT
       business_id
FROM
       EVENTS e,
       (
              SELECT
                     event_type,
                     sum(occurences) / count(*) AS avg_occurences
              FROM
                     EVENTS
              GROUP BY
                     event_type
       ) t
WHERE
       e.event_type = t.event_type
       AND e.occurences > t.avg_occurences
GROUP BY
       e.business_id
HAVING
       count(*) > 1