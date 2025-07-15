# https://leetcode.com/problems/find-students-with-study-spiral-pattern/solutions/6959955/3617-find-students-with-study-spiral-pattern/
WITH session_blocks AS (
  SELECT
    ss.*,
    DATEDIFF(
      ss.session_date,
      LAG(ss.session_date) OVER (
        PARTITION BY student_id
        ORDER BY
          session_date
      )
    ) AS gap
  FROM
    study_sessions ss
),
block_numbers AS (
  SELECT
    sb.*,
    SUM(
      CASE
        WHEN gap IS NULL
        OR gap > 2 THEN 1
        ELSE 0
      END
    ) OVER (
      PARTITION BY student_id
      ORDER BY
        session_date
    ) AS block_id
  FROM
    session_blocks sb
),
valid_blocks AS (
  SELECT
    student_id,
    block_id
  FROM
    block_numbers
  GROUP BY
    student_id,
    block_id
  HAVING
    COUNT(*) >= 6
),
block_subjects AS (
  SELECT
    bn.student_id,
    bn.block_id,
    bn.subject,
    bn.session_date,
    bn.hours_studied,
    ROW_NUMBER() OVER (
      PARTITION BY bn.student_id,
      bn.block_id
      ORDER BY
        bn.session_date
    ) AS seq_num
  FROM
    block_numbers bn
    JOIN valid_blocks vb ON bn.student_id = vb.student_id
    AND bn.block_id = vb.block_id
),
block_sequences AS (
  SELECT
    bs.student_id,
    bs.block_id,
    GROUP_CONCAT(
      bs.subject
      ORDER BY
        bs.seq_num SEPARATOR ','
    ) AS subject_seq,
    COUNT(*) AS total_sessions,
    COUNT(DISTINCT bs.subject) AS cycle_length,
    SUM(bs.hours_studied) AS total_hours
  FROM
    block_subjects bs
  GROUP BY
    bs.student_id,
    bs.block_id
  HAVING
    cycle_length >= 3
    AND total_sessions % cycle_length = 0
    AND REPEAT(
      CONCAT(
        SUBSTRING_INDEX(subject_seq, ',', cycle_length),
        ','
      ),
      total_sessions / cycle_length
    ) = CONCAT(subject_seq, ',')
)
SELECT
  bs.student_id,
  s.student_name,
  s.major,
  bs.cycle_length,
  bs.total_hours AS total_study_hours
FROM
  block_sequences bs
  JOIN students s ON s.student_id = bs.student_id
ORDER BY
  bs.cycle_length DESC,
  total_study_hours DESC;