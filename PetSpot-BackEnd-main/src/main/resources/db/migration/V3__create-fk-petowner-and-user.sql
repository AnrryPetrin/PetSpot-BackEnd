alter table login add column pet_owner_id varchar(36),
add constraint fk_login_pet_owner foreign key (pet_owner_id) references pet_owner(id);
