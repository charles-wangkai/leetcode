SELECT
    country_name,
    CASE WHEN avg_weather_state <= 15 THEN 'Cold'
         WHEN avg_weather_state >= 25 THEN 'Hot'
         ELSE 'Warm'
    END AS weather_type
FROM (
    SELECT
        c.country_name,
        AVG(weather_state) AS avg_weather_state
    FROM Countries c
    JOIN Weather w
    ON w.country_id = c.country_id
    WHERE YEAR(w.day) = 2019
    AND MONTH(w.day) = 11
    GROUP BY w.country_id, c.country_name
) t