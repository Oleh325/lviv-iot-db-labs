SELECT pc1.model model1, pc2.model model2, pc1.speed, pc1.ram
FROM pc pc1, pc pc2
WHERE pc1.speed = pc2.speed AND pc1.ram = pc2.ram AND pc1.model <= pc2.model AND pc1.code != pc2.code
GROUP BY model1, model2;