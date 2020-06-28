SELECT DISTINCT c.title
FROM TVProgram p
JOIN Content c
ON c.content_id = p.content_id
WHERE c.Kids_content = 'Y'
AND c.content_type = 'Movies'
AND YEAR(p.program_date) = 2020
AND MONTH(p.program_date) = 6