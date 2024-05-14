CREATE TABLE login (
   id VARCHAR(36) PRIMARY KEY,
   email VARCHAR(255) UNIQUE,
   password_login VARCHAR(255),
   type_of_user ENUM('PETOWNER')
);
