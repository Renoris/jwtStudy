
INSERT INTO "USER" (USER_NAME, PASSWORD, NICK_NAME, ACTIVATED) VALUES
    ( 'admin', '$2a$10$VOu0Qg.sKJniLFhJxTgRBuxSCtYTp2YmyuZZCSNothccUcC3eA0jq', 'admin', 1),
    ( 'user2', '$2a$10$VOu0Qg.sKJniLFhJxTgRBuxSCtYTp2YmyuZZCSNothccUcC3eA0jq', 'user2', 1);

INSERT INTO AUTHORITY (AUTHORITY_NAME) values ('ROLE_USER');
INSERT INTO AUTHORITY (AUTHORITY_NAME) values ('ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values (1, 'ROLE_USER');
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values (1, 'ROLE_ADMIN');

INSERT INTO BOARD(board_id, board_title, board_content, visibility, created_at, created_by, modified_at, modified_by)
values (1, 'testtitle', 'testcontent', 1, now(), 1, now(), 1),
       (2, 'testtitle2', 'testcontent2', 2, now(), 2, now(), 2);

