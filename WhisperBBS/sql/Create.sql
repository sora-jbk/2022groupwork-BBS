-- systemユーザーへの切り替え --
CONN system / orcl

-- 初期化、作り直し用の削除 --
DROP TABLE POST;
DROP SEQUENCE POST_CNT;
DROP USER whisper CASCADE;
DROP TABLESPACE cosmos INCLUDING CONTENTS AND DATAFILES;

-- 表領域の作成 --
CREATE TABLESPACE cosmos
-- ファイルを置くパスと領域の許容量の指定 --
DATAFILE 'C:\pleiades\2022-12\workspace\2022groupwork-BBS\WhisperBBS\CreateTableSpace.dbf' SIZE 40M;

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

-- 表の作成 --
CREATE SEQUENCE POST_CNT