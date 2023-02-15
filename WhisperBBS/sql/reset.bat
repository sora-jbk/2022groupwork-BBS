rem PostTable内とシーケンスの初期化
@echo off
rem SQLPLUSに繋ぎ、Whisperユーザーにログイン
sqlplus whisper/bbs @%~dp0%\drop.sql
sqlplus whisper/bbs @%~dp0%\Insert.sql	