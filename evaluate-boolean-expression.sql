SELECT
    e.left_operand,
    e.operator,
    e.right_operand,
    IF(
        CASE
            WHEN e.operator = '<' THEN v1.value < v2.value
            WHEN e.operator = '>' THEN v1.value > v2.value
            ELSE v1.value = v2.value
        END,
        'true',
        'false'
    ) AS value
FROM
    Expressions e
    JOIN VARIABLES v1 ON v1.name = e.left_operand
    JOIN VARIABLES v2 ON v2.name = e.right_operand