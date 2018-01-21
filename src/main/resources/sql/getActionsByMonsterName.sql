SELECT name, description, action_type, damage_bonus, damage_dice, attack_bonus
FROM action
WHERE monster_name = :monster_name