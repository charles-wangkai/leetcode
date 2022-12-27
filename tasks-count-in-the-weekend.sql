SELECT
  sum(IF(weekday(submit_date) >= 5, 1, 0)) AS weekend_cnt,
  sum(IF(weekday(submit_date) <= 4, 1, 0)) AS working_cnt
FROM
  Tasks