SELECT
  *
FROM
  Fraud f1
WHERE
  (
    SELECT
      count(*)
    FROM
      Fraud f2
    WHERE
      f2.state = f1.state
      AND f2.fraud_score > f1.fraud_score
  ) < (
    SELECT
      count(*)
    FROM
      Fraud f2
    WHERE
      f2.state = f1.state
  ) * 0.05
ORDER BY
  state,
  fraud_score,
  policy_id