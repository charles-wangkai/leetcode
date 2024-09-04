SELECT
  max(assignment1 + assignment2 + assignment3) - min(assignment1 + assignment2 + assignment3) AS difference_in_score
FROM
  Scores