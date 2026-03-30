CREATE TABLE residence (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(100) NOT NULL,
                           owner_id BIGINT,
                           invite_code INT,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                           CONSTRAINT fk_residence_owner
                               FOREIGN KEY (owner_id) REFERENCES user(id)
);