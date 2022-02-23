SELECT
  member_id,
  name,
  CASE
    WHEN visit_cnt = 0 THEN 'Bronze'
    WHEN purchase_cnt * 2 < visit_cnt THEN 'Silver'
    WHEN purchase_cnt * 5 < visit_cnt * 4 THEN 'Gold'
    ELSE 'Diamond'
  END AS category
FROM
  (
    SELECT
      m.member_id,
      m.name,
      count(v.visit_id) AS visit_cnt,
      count(p.visit_id) AS purchase_cnt
    FROM
      Members m
      LEFT OUTER JOIN Visits v ON v.member_id = m.member_id
      LEFT OUTER JOIN Purchases p ON p.visit_id = v.visit_id
    GROUP BY
      m.member_id,
      m.name
  ) t