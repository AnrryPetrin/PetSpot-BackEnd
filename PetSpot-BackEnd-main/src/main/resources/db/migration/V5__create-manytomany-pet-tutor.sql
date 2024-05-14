create table pets_client_tutor(
    id int AUTO_INCREMENT primary key,
    pet_id VARCHAR(36),
    pet_owner_id VARCHAR(36),
    FOREIGN KEY (pet_id) REFERENCES pet(id),
    FOREIGN KEY (pet_owner_id) REFERENCES pet_owner(id)
)