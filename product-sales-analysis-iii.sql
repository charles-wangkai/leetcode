SELECT product_id,
       MIN(year) AS first_year,
       quantity,
       price
  FROM Sales
GROUP BY product_id