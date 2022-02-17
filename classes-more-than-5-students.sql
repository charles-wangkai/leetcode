SELECT
  class
FROM
  courses
GROUP BY
  class
HAVING
  count(DISTINCT student) >= 5