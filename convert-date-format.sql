SELECT
  concat(
    dayname(DAY),
    ', ',
    monthname(DAY),
    ' ',
    DAY(DAY),
    ', ',
    year(DAY)
  ) AS DAY
FROM
  Days