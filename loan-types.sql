SELECT
  DISTINCT user_id
FROM
  Loans l
WHERE
  EXISTS (
    SELECT
      1
    FROM
      Loans l1
    WHERE
      l1.user_id = l.user_id
      AND l1.loan_type = 'Refinance'
  )
  AND EXISTS (
    SELECT
      1
    FROM
      Loans l1
    WHERE
      l1.user_id = l.user_id
      AND l1.loan_type = 'Mortgage'
  )
ORDER BY
  user_id