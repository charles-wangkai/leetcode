SELECT
      t2.seller_id
FROM
      (
            SELECT
                  max(sum_price) AS max_sum_price
            FROM
                  (
                        SELECT
                              seller_id,
                              sum(price) AS sum_price
                        FROM
                              Sales
                        GROUP BY
                              seller_id
                  ) t
      ) t1,
      (
            SELECT
                  seller_id,
                  sum(price) AS sum_price
            FROM
                  Sales
            GROUP BY
                  seller_id
      ) t2
WHERE
      t2.sum_price = t1.max_sum_price