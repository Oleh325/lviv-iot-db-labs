SELECT *
FROM (SELECT maker, COUNT(CASE WHEN type='pc' THEN 1 ELSE NULL END) pcs FROM product GROUP BY maker) count
WHERE count.pcs >= 2;