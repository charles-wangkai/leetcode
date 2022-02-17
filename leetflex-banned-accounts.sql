SELECT
  DISTINCT l1.account_id
FROM
  LogInfo l1
  JOIN LogInfo l2 ON l2.account_id = l1.account_id
  AND l2.ip_address <> l1.ip_address
  AND greatest(l1.login, l2.login) <= least(l1.logout, l2.logout)