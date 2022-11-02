SELECT ship, battle, date
FROM outcomes JOIN battles ON battle = battles.name
WHERE ship IN (SELECT ship
FROM outcomes outcomes_s JOIN battles battles_s ON outcomes_s.battle = battles_s.name
WHERE result = 'damaged' AND date < battles.date);