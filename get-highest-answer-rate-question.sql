SELECT
  question_id AS survey_log
FROM
  (
    SELECT
      question_id,
      sum(
        CASE
          WHEN ACTION = 'answer' THEN 1
          ELSE 0
        END
      ) / sum(
        CASE
          WHEN ACTION = 'show' THEN 1
          ELSE 0
        END
      ) AS answer_rate
    FROM
      SurveyLog
    GROUP BY
      question_id
  ) t
ORDER BY
  answer_rate DESC
LIMIT
  1