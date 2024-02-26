SELECT
  *
FROM
  (
    SELECT
      'prime_eligible' AS item_type,
      floor(500000 / sum(square_footage)) * count(*) AS item_count
    FROM
      Inventory
    WHERE
      item_type = 'prime_eligible'
    UNION
    SELECT
      'not_prime' AS item_type,
      floor(
        (
          500000 - (
            SELECT
              sum(square_footage)
            FROM
              Inventory
            WHERE
              item_type = 'prime_eligible'
          ) * floor(
            500000 / (
              SELECT
                sum(square_footage)
              FROM
                Inventory
              WHERE
                item_type = 'prime_eligible'
            )
          )
        ) / sum(square_footage)
      ) * count(*) AS item_count
    FROM
      Inventory
    WHERE
      item_type = 'not_prime'
  ) t1
ORDER BY
  item_count DESC