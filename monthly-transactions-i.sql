SELECT
    date_format(trans_date, "%Y-%m") AS MONTH,
    country,
    count(*) AS trans_count,
    sum(IF(state = 'approved', 1, 0)) AS approved_count,
    sum(amount) AS trans_total_amount,
    sum(IF(state = 'approved', amount, 0)) AS approved_total_amount
FROM
    Transactions
GROUP BY
    date_format(trans_date, "%Y-%m"),
    country