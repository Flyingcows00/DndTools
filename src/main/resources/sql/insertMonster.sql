INSERT INTO monster(name,created_by,size,type,subtype,alignment,armor_class,hit_points,hit_dice,speed,senses,languages,challenge_rating,damage_vulnerabilities,damage_resistances,damage_immunities,condition_immunities)
VALUES(:name,:created_by,:size,:type,:subtype,:alignment,:armor_class,:hit_points,:hit_dice,:speed,:senses,:languages,:challenge_rating,:damage_vulnerabilities,:damage_resistances,:damage_immunities,:condition_immunities);

INSERT INTO ability_levels(name,strength,dexterity,constitution,wisdom,intelligence,charisma)
VALUES(:name,:strength,:dexterity,:constitution,:wisdom,:intelligence,:charisma);

INSERT INTO saving_throws(name,strength,dexterity,constitution,wisdom,intelligence,charisma)
VALUES(:name,:strength_save,:dexterity_save,:constitution_save,:wisdom_save,:intelligence_save,:charisma_save);

INSERT INTO skills(name,acrobatics,animal_handling,arcana,athletics,deception,history,insight,intimidation,investigation,medicine,nature,perception,performance,persuasion,religion,slight_of_hand,stealth,survival)
VALUES(:name,:acrobatics,:animal_handling,:arcana,:athletics,:deception,:history,:insight,:intimidation,:investigation,:medicine,:nature,:perception,:performance,:persuasion,:religion,:slight_of_hand,:stealth,:survival);