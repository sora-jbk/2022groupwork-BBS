-- systemユーザーへの切り替え --
CONN system / orcl

-- ユーザーの作成 --
CREATE USER whisper
-- パスワード作成 --
IDENTIFIED BY bbs;
-- 容量の割り当て --
QUOTA 50M on cosmos;

-- 権限の付与 --
GRANT CREATE SESSION,
      CREATE TABLE,
      CREATE VIEW,
      CREATE SYNONYM,
      CREATE SEQUENCE,
      CREATE PUBLIC SYNONYM,
      CREATE INDEXTYPE,
      CREATE OPERATOR,
      CREATE TYPE,
      CREATE TRIGGER,
      CREATE PROCEDURE,
      CREATE CLUSTER,
      SET CONTAINER
TO whisper;

-- whisperユーザーに接続 --
CONN whisper/bbs

-- 順序の作成 --
CREATE SEQUENCE POST_CNT
START WITH 101
INCREMENT BY 1
MAXVALUE 2147483647
NOCYCLE
NOCACHE;

-- 表の作成 --
CREATE TABLE POST(
	POST_ID NUMBER(10) DEFAULT POST_CNT.NEXTVAL PRIMARY KEY,
	REPLY_TO NUMBER(10) DEFAULT NULL, 
	AUTHOR VARCHAR2(20) DEFAULT NULL,
	CONTENT VARCHAR2(240) NOT NULL,
	POSTED_TIME TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL,
	DELETED NUMBER(1) DEFAULT 0 NOT NULL,
	CONSTRAINT CK_POST_POSTID CHECK(POST_ID < 2147483648),
	CONSTRAINT FK_POST_REPLYTO FOREIGN KEY (REPLY_TO) REFERENCES POST(POST_ID),
	CONSTRAINT CK_POST_REPLYTO CHECK(REPLY_TO < 2147483648),
	CONSTRAINT CK_POST_DELETEFLAG CHECK(DELETED = 0 OR DELETED = 1)
);

