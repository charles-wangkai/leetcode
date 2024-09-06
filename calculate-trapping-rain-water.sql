# https://leetcode.com/problems/calculate-trapping-rain-water/solutions/4806856/window-function-least-detail-comment-explained/
SELECT
  sum(least(left_max_height, right_max_height) - height) AS total_trapped_water
FROM
  (
    SELECT
      height,
      MAX(height) OVER(
        ORDER BY
          id
      ) AS left_max_height,
      MAX(height) OVER(
        ORDER BY
          id DESC
      ) AS right_max_height
    FROM
      Heights
  ) t