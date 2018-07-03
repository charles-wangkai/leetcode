SELECT question_id AS survey_log
FROM (
    SELECT question_id, SUM(CASE WHEN action = 'answer' THEN 1 ELSE 0 END) / SUM(CASE WHEN action = 'show' THEN 1 ELSE 0 END) AS answer_rate
      FROM survey_log
    GROUP BY question_id
) t
ORDER BY answer_rate DESC
LIMIT 1