-- systemユーザーへの切り替え --
CONN system / orcl

-- 初期化、作り直し用の削除 --
DROP TABLE POST;
DROP SEQUENCE POST_CNT;
DROP USER whisper CASCADE;
DROP TABLESPACE cosmos INCLUDING CONTENTS;

-- 表領域の作成 --
CREATE TABLESPACE cosmos
-- ファイルを置くパスと領域の許容量の指定 --
DATAFILE 'C:\pleiades\2022-12\workspace\2022groupwork-BBS\WhisperBBS\CreateTableSpace.dbf' 
SIZE 40M reuse;

-- ユーザーの作成 --
CREATE USER whisper
-- パスワード作成 --
IDENTIFIED BY bbs
-- 表領域の使用場所 --
DEFAULT TABLESPACE cosmos
-- 一時表領域の使用場所 --
TEMPORARY TABLESPACE temp
-- 表領域の割り当て制限 --
QUOTA 40M on cosmos;

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
conn whisper/bbs

-- 順序の作成 --
CREATE SEQUENCE POST_CNT
START WITH 101
INCREMENT BY 1
MAXVALUE 2147483647
NOCYCLE
NOCACHE;


