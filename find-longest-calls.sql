SELECT
  c.first_name,
  t3.type,
  concat(
    lpad(duration DIV 3600, 2, '0'),
    ':',
    lpad(duration % 3600 DIV 60, 2, '0'),
    ':',
    lpad(duration % 60, 2, '0')
  ) AS duration_formatted
FROM
  (
    SELECT
      contact_id,
      TYPE,
      duration
    FROM
      (
        SELECT
          contact_id,
          TYPE,
          duration
        FROM
          Calls
        WHERE
          TYPE = 'incoming'
        ORDER BY
          duration DESC
        LIMIT
          3
      ) t1
    UNION
    SELECT
      contact_id,
      TYPE,
      duration
    FROM
      (
        SELECT
          contact_id,
          TYPE,
          duration
        FROM
          Calls
        WHERE
          TYPE = 'outgoing'
        ORDER BY
          duration DESC
        LIMIT
          3
      ) t2
  ) t3
  JOIN Contacts c ON c.id = t3.contact_id
ORDER BY
  t3.type DESC,
  t3.duration DESC,
  c.first_name DESC