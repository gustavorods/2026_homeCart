CREATE TABLE residence_member (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,

                                  residence_id BIGINT,
                                  user_id BIGINT,

                                  role VARCHAR(50),

                                  joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                                  CONSTRAINT fk_member_residence
                                      FOREIGN KEY (residence_id) REFERENCES residence(id),

                                  CONSTRAINT fk_member_user
                                      FOREIGN KEY (user_id) REFERENCES user(id)
);