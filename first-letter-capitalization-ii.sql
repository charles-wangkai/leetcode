-- https://leetcode.com/problems/first-letter-capitalization-ii/solutions/6101988/sql-solution-recursive-cte-split-string-by-multiple-delimiters-and-save-delimeter/
WITH recursive content_sep_words AS (
  SELECT
    content_id,
    content_text,
    cast('' AS char(1)) AS delim,
    CASE
      WHEN instr(content_text, ' ') = 0
      AND instr(content_text, '-') = 0 THEN content_text
      WHEN instr(content_text, ' ') <> 0
      AND instr(content_text, '-') <> 0
      AND instr(content_text, ' ') < instr(content_text, '-') THEN substr(content_text, 1, INSTR(content_text, ' ') - 1)
      WHEN instr(content_text, ' ') <> 0
      AND instr(content_text, '-') <> 0
      AND instr(content_text, '-') < instr(content_text, ' ') THEN substr(content_text, 1, INSTR(content_text, '-') - 1)
      WHEN instr(content_text, ' ') <> 0
      AND instr(content_text, '-') = 0 THEN substr(content_text, 1, INSTR(content_text, ' ') - 1)
      WHEN instr(content_text, ' ') = 0
      AND instr(content_text, '-') <> 0 THEN substr(content_text, 1, INSTR(content_text, '-') - 1)
    END AS word,
    CASE
      WHEN instr(content_text, ' ') = 0
      AND instr(content_text, '-') = 0 THEN ''
      WHEN instr(content_text, ' ') <> 0
      AND instr(content_text, '-') <> 0
      AND instr(content_text, ' ') < instr(content_text, '-') THEN substr(content_text, INSTR(content_text, ' '))
      WHEN instr(content_text, ' ') <> 0
      AND instr(content_text, '-') <> 0
      AND instr(content_text, '-') < instr(content_text, ' ') THEN substr(content_text, INSTR(content_text, '-'))
      WHEN instr(content_text, ' ') <> 0
      AND instr(content_text, '-') = 0 THEN substr(content_text, INSTR(content_text, ' '))
      WHEN instr(content_text, ' ') = 0
      AND instr(content_text, '-') <> 0 THEN substr(content_text, INSTR(content_text, '-'))
    END AS remaining,
    1 AS word_level
  FROM
    user_content
  UNION
  ALL
  SELECT
    content_sep_words.content_id,
    content_sep_words.content_text,
    substr(remaining, 1, 1) AS delim,
    CASE
      WHEN instr(substr(remaining, 2), ' ') = 0
      AND instr(substr(remaining, 2), '-') = 0 THEN substr(remaining, 2)
      WHEN instr(substr(remaining, 2), ' ') <> 0
      AND instr(substr(remaining, 2), '-') <> 0
      AND instr(substr(remaining, 2), ' ') < instr(substr(remaining, 2), '-') THEN substr(
        substr(remaining, 2),
        1,
        INSTR(substr(remaining, 2), ' ') - 1
      )
      WHEN instr(substr(remaining, 2), ' ') <> 0
      AND instr(substr(remaining, 2), '-') <> 0
      AND instr(substr(remaining, 2), '-') < instr(substr(remaining, 2), ' ') THEN substr(
        substr(remaining, 2),
        1,
        INSTR(substr(remaining, 2), '-') - 1
      )
      WHEN instr(substr(remaining, 2), ' ') <> 0
      AND instr(substr(remaining, 2), '-') = 0 THEN substr(
        substr(remaining, 2),
        1,
        INSTR(substr(remaining, 2), ' ') - 1
      )
      WHEN instr(substr(remaining, 2), ' ') = 0
      AND instr(substr(remaining, 2), '-') <> 0 THEN substr(
        substr(remaining, 2),
        1,
        INSTR(substr(remaining, 2), '-') - 1
      )
    END AS word,
    CASE
      WHEN instr(substr(remaining, 2), ' ') = 0
      AND instr(substr(remaining, 2), '-') = 0 THEN ''
      WHEN instr(substr(remaining, 2), ' ') <> 0
      AND instr(substr(remaining, 2), '-') <> 0
      AND instr(substr(remaining, 2), ' ') < instr(substr(remaining, 2), '-') THEN substr(
        substr(remaining, 2),
        INSTR(substr(remaining, 2), ' ')
      )
      WHEN instr(substr(remaining, 2), ' ') <> 0
      AND instr(substr(remaining, 2), '-') <> 0
      AND instr(substr(remaining, 2), '-') < instr(substr(remaining, 2), ' ') THEN substr(
        substr(remaining, 2),
        INSTR(substr(remaining, 2), '-')
      )
      WHEN instr(substr(remaining, 2), ' ') <> 0
      AND instr(substr(remaining, 2), '-') = 0 THEN substr(
        substr(remaining, 2),
        INSTR(substr(remaining, 2), ' ')
      )
      WHEN instr(substr(remaining, 2), ' ') = 0
      AND instr(substr(remaining, 2), '-') <> 0 THEN substr(
        substr(remaining, 2),
        INSTR(substr(remaining, 2), '-')
      )
    END AS remaining,
    word_level + 1
  FROM
    content_sep_words
  WHERE
    remaining <> ''
),
content_sep_words_capitalized AS (
  SELECT
    content_id,
    content_text,
    word_level,
    word,
    CONCAT(
      delim,
      upper(substr(WORD, 1, 1)),
      lower(substr(WORD, 2))
    ) AS formatted_word
  FROM
    content_sep_words
)
SELECT
  content_id,
  content_text AS original_text,
  GROUP_CONCAT(
    formatted_word
    ORDER BY
      word_level SEPARATOR ''
  ) AS converted_text
FROM
  content_sep_words_capitalized
GROUP BY
  content_id,
  content_text