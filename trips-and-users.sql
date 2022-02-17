SELECT
  t1.request_at DAY,
  round(
    (
      SELECT
        count(*)
      FROM
        Trips t2,
        Users c2,
        Users d2
      WHERE
        t2.request_at = t1.request_at
        AND t2.client_id = c2.users_id
        AND c2.banned = 'No'
        AND t2.driver_id = d2.users_id
        AND d2.banned = 'No'
        AND t2.status LIKE 'cancelled%'
    ) / count(*),
    2
  ) AS "Cancellation Rate"
FROM
  Trips t1,
  Users c1,
  Users d1
WHERE
  t1.client_id = c1.users_id
  AND c1.banned = 'No'
  AND t1.driver_id = d1.users_id
  AND d1.banned = 'No'
  AND t1.request_at BETWEEN '2013-10-01'
  AND '2013-10-03'
GROUP BY
  t1.request_at