SELECT
    v.customer_id,
    count(*) AS count_no_trans
FROM
    Visits v
    LEFT OUTER JOIN Transactions t ON t.visit_id = v.visit_id
WHERE
    t.visit_id IS NULL
GROUP BY
    v.customer_id