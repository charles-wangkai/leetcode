SELECT *
FROM Users
WHERE mail RLIKE '^[[:alpha:]][a-z0-9_.-]*@leetcode\\.com$'