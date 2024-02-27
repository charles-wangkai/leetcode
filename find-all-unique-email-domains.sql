SELECT
  email_domain,
  count(*) AS count
FROM
  (
    SELECT
      SUBSTRING_INDEX(email, '@', -1) AS email_domain
    FROM
      Emails
    WHERE
      email LIKE '%.com'
  ) t
GROUP BY
  email_domain
ORDER BY
  email_domain