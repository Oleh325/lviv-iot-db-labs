SELECT name FROM ships
WHERE name REGEXP '.*[:space:]+.*'
UNION
SELECT ship FROM outcomes
WHERE ship REGEXP '.*[:space:]+.*'