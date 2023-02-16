rem user, table, sequenceの作成
@echo off
rem SQLPLUSに繋ぎ、Whisperユーザーにログイン
sqlplus whisper/bbs @%~dp0%\create.sql
