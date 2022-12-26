CREATE PROCEDURE UnpivotProducts() BEGIN
SET
  SESSION group_concat_max_len = 1000000;

SET
  @sql = NULL;

SELECT
  GROUP_CONCAT(
    CONCAT(
      'SELECT product_id, ''',
      column_name,
      ''' AS store, ',
      column_name,
      ' AS price FROM Products'
    ) SEPARATOR ' UNION ALL '
  ) INTO @sql
FROM
  information_schema.columns
WHERE
  table_name = 'Products'
  AND ordinal_position >= 2;

SET
  @sql = CONCAT(
    'SELECT * FROM (',
    @sql,
    ') t WHERE price IS NOT NULL'
  );

PREPARE stmt
FROM
  @sql;

EXECUTE stmt;

DEALLOCATE PREPARE stmt;

END