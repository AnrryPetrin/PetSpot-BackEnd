create table pet(
    id VARCHAR(36) PRIMARY KEY,
    pet_name VARCHAR(255) NOT NULL,
    pet_weight VARCHAR(60),
    pet_birthday DATE,
    specie VARCHAR(255) NOT NULL,
    race VARCHAR(255),
    gender INTEGER
);
