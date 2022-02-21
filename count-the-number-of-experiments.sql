SELECT
  t1.platform,
  t2.experiment_name,
  count(e.platform) AS num_experiments
FROM
  (
    SELECT
      'Android' AS platform
    UNION
    SELECT
      'IOS' AS platform
    UNION
    SELECT
      'Web' AS platform
  ) t1
  JOIN (
    SELECT
      'Reading' AS experiment_name
    UNION
    SELECT
      'Sports' AS experiment_name
    UNION
    SELECT
      'Programming' AS experiment_name
  ) t2
  LEFT OUTER JOIN Experiments e ON e.platform = t1.platform
  AND e.experiment_name = t2.experiment_name
GROUP BY
  t1.platform,
  t2.experiment_name