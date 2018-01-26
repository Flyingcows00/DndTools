SELECT u.user_id, u.username AS username, m.name, m.size, m.type, m.subtype, m.alignment, m.armor_class, m.hit_points, m.hit_dice,
	m.speed, m.senses, m.languages, m.challenge_rating, m.damage_vulnerabilities, m.damage_resistances, m.damage_immunities, m.condition_immunities,
    al.strength, al.dexterity, al.constitution, al.wisdom, al.intelligence, al.charisma, st.strength AS strength_save, st.dexterity AS dexterity_save, 
    st.constitution AS constitution_save, st.wisdom AS wisdom_save, st.intelligence AS intelligence_save, st.charisma AS charisma_save,
    sk.acrobatics, sk.animal_handling, sk.arcana, sk.athletics, sk.deception, sk.history, sk.insight, sk.intimidation, sk.investigation, 
    sk.medicine, sk.nature, sk.perception, sk.performance, sk.persuasion, sk.religion, sk.slight_of_hand, sk.stealth, sk.survival
FROM monster AS m
	INNER JOIN ability_levels AS al ON m.name = al.name
    INNER JOIN saving_throws AS st ON m.name = st.name
    INNER JOIN skills AS sk ON m.name = sk.name
    INNER JOIN users AS u ON m.created_by = u.user_id
WHERE m.name = :monster_name