rem PostTable���ƃV�[�P���X�̏�����
@echo off
rem SQLPLUS�Ɍq���AWhisper���[�U�[�Ƀ��O�C��
sqlplus whisper/bbs @%~dp0%\drop.sql
sqlplus whisper/bbs @%~dp0%\Insert.sql	