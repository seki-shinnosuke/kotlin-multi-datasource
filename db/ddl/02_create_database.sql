USE base;
SET CHARSET utf8mb4;

CREATE TABLE client_user (
    client_user_id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'クライアントユーザーID',
    user_name VARCHAR(50) NOT NULL COMMENT 'ユーザー名',
    PRIMARY KEY(client_user_id)
) COMMENT = 'クライアントユーザー';

USE base_ro;
SET CHARSET utf8mb4;

CREATE TABLE client_user (
    client_user_id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'クライアントユーザーID',
    user_name VARCHAR(50) NOT NULL COMMENT 'ユーザー名',
    PRIMARY KEY(client_user_id)
) COMMENT = 'クライアントユーザー';
