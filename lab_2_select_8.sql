SELECT maker
FROM (SELECT maker, COUNT(CASE WHEN speed >= 600 THEN 1 ELSE NULL END) more, COUNT(CASE WHEN speed < 600 THEN 1 ELSE NULL END) less
FROM product JOIN laptop ON product.model = laptop.model
GROUP BY maker) counts
WHERE counts.more > 0 AND counts.less = 0;