SET time_zone = '+09:00';

CREATE TABLE IF NOT EXISTS users (

        user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
        username VARCHAR(15) NOT NULL,
        password VARCHAR(15) NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        deleted_at TIMESTAMP DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS boards (

        board_id BIGINT AUTO_INCREMENT PRIMARY KEY,
        boardname VARCHAR(30) NOT NULL
);


DROP TABLE IF EXISTS posts;
CREATE TABLE posts (

        post_id    BIGINT AUTO_INCREMENT PRIMARY KEY,
        title      VARCHAR(30) NOT NULL,
        body       TEXT         NOT NULL,
        user_id    BIGINT,
        board_id   BIGINT,
        test_id    BIGINT,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        deleted_at TIMESTAMP DEFAULT NULL,
        FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE SET NULL,
        FOREIGN KEY (board_id) REFERENCES boards(board_id) ON DELETE SET NULL,
        FOREIGN KEY (user_id) REFERENCES tests (test_id) ON DELETE SET NULL
);

ALTER TABLE posts DROP FOREIGN KEY posts_ibfk_3;

DROP TABLE IF EXISTS tests;
CREATE TABLE tests (

                       test_id    BIGINT AUTO_INCREMENT PRIMARY KEY,
                       url        VARCHAR(200) NOT NULL,
                       vus        BIGINT       NOT NULL,
                       duration   VARCHAR(20)  NOT NULL,
                       rps        BIGINT
);
