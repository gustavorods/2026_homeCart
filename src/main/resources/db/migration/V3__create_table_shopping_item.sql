CREATE TABLE shopping_item (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,

                               residence_id BIGINT,
                               created_by BIGINT,

                               name VARCHAR(100) NOT NULL,
                               description TEXT,

                               priority VARCHAR(50),
                               status VARCHAR(50),

                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                               CONSTRAINT fk_item_residence
                                   FOREIGN KEY (residence_id) REFERENCES residence(id),

                               CONSTRAINT fk_item_user
                                   FOREIGN KEY (created_by) REFERENCES user(id)
);