CREATE PROCEDURE PivotProducts() BEGIN
SET
  SESSION group_concat_max_len = 1000000;

SET
  @sql = NULL;

SELECT
  GROUP_CONCAT(
    DISTINCT CONCAT(
      'MAX(IF(store = ''',
      store,
      ''', price, NULL)) AS ',
      store
    )
    ORDER BY
      store
  ) INTO @sql
FROM
  Products;

SET
  @sql = CONCAT(
    'SELECT product_id, ',
    @sql,
    ' FROM Products GROUP BY product_id'
  );

PREPARE stmt
FROM
  @sql;

EXECUTE stmt;

DEALLOCATE PREPARE stmt;

END