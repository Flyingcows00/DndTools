DELETE FROM users
WHERE username=:username
LIMIT ONE ROW