SELECT DISTINCT l.page_id AS recommended_page
FROM Likes l
JOIN (
    SELECT user2_id AS user_id
    FROM Friendship
    WHERE user1_id = 1

    UNION

    SELECT user1_id AS user_id
    FROM Friendship
    WHERE user2_id = 1
) f
ON f.user_id = l.user_id
WHERE l.page_id NOT IN (
    SELECT page_id
    FROM Likes
    WHERE user_id = 1
)