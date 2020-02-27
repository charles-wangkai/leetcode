SELECT
    i.invoice_id,
    c.customer_name,
    i.price,
    COALESCE(t1.contacts_cnt, 0) AS contacts_cnt,
    COALESCE(t1.trusted_contacts_cnt, 0) AS trusted_contacts_cnt
FROM Invoices i
JOIN Customers c
ON c.customer_id = i.user_id
LEFT OUTER JOIN (
    SELECT
        t.user_id,
        COUNT(*) AS contacts_cnt,
        COUNT(c.customer_name) AS trusted_contacts_cnt
    FROM Contacts t
    LEFT OUTER JOIN Customers c
    ON c.customer_name = t.contact_name
    AND c.email = t.contact_email
    GROUP BY t.user_id
) t1
ON t1.user_id = i.user_id
ORDER BY 1