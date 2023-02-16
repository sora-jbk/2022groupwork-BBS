-- POST表へのサンプルデータの挿入 --
INSERT INTO POST(CONTENT, POSTED_TIME)
 VALUES('test', '2023-01-30 23:55:52.971');

INSERT INTO POST(REPLY_TO, AUTHOR, CONTENT, POSTED_TIME) 
 VALUES(101, '友達は愛と勇気', '結果はどうだったんだい?', '2023-01-31 17:33:50.228');

INSERT INTO POST(CONTENT, POSTED_TIME) 
 VALUES('今日のご飯がもやしのもやし炒めな件についてwww', '2023-01-31 19:27:33.137');

INSERT INTO POST(REPLY_TO, CONTENT, POSTED_TIME) 
 VALUES(102, 'まずまずといった感じかな', '2023-02-01 08:31:42.729');

INSERT INTO POST(AUTHOR, CONTENT, POSTED_TIME) 
 VALUES('ラーメン界の王', '俺ラーメンにトッピングしないやつ許せねえわ。まぢで', '2023-02-01 11:17:38.642');

INSERT INTO POST(REPLY_TO, CONTENT, POSTED_TIME) 
 VALUES(103, 'そりゃあすげえななんだそれ', '2023-02-01 15:48:13.982');

INSERT INTO POST(REPLY_TO, CONTENT, POSTED_TIME) 
 VALUES(105, '俺まぢでってちに濁点つけてるやつ許せねえわ。まぢで', '2023-02-01 22:19:45.334');

INSERT INTO POST(REPLY_TO, CONTENT, POSTED_TIME) 
 VALUES(105, 'は？トッピングつけるかなんて自由だろ　誰だよ', '2023-02-02 01:41:26.831');

INSERT INTO POST(CONTENT, POSTED_TIME) 
 VALUES('おなかへったー', '2023-02-02 03:29:44.615');

INSERT INTO POST(CONTENT, POSTED_TIME) 
 VALUES('知ってるか？朝日って気持ちいいんだぜ？', '2023-02-02 07:00:03.682');

INSERT INTO POST(REPLY_TO, CONTENT, POSTED_TIME) 
 VALUES(109, 'ステーキ食おうぜ、ステーキ', '2023-02-02 13:05:51.315');

INSERT INTO POST(REPLY_TO, CONTENT, POSTED_TIME) 
 VALUES(109, '何深夜に腹空かせてんだ', '2023-02-02 14:55:58.267');

INSERT INTO POST(REPLY_TO, CONTENT, POSTED_TIME) 
 VALUES(110, 'この時期寒いわ、あほう', '2023-02-03 00:11:23.449');

INSERT INTO POST(REPLY_TO, CONTENT, POSTED_TIME)
 VALUES(111, '野菜炒めこそ至高', '2023-02-03 04:12:01.586');

INSERT INTO POST(REPLY_TO, AUTHOR, CONTENT, POSTED_TIME)
 VALUES(109, 'sasakue大熊猫', '笹食えお前', '2023-02-04 08:17:22.185');

INSERT INTO POST(REPLY_TO, AUTHOR, CONTENT, POSTED_TIME)
 VALUES(115, 'My醤油Peypey', 'ささくえパンダおもろいなぁセンスないけど', '2023-02-04 14:56:30.091');

INSERT INTO POST(REPLY_TO, CONTENT, POSTED_TIME)
 VALUES(105, 'なんか言われてるけど俺は核心ついてると思うね', '2023-02-04 17:29:43.276');

INSERT INTO POST(REPLY_TO, CONTENT, POSTED_TIME)
 VALUES(105, '一人で納得するならともかく押し付けたらもう駄目よ', '2023-02-04 20:32:57.422');

INSERT INTO POST(REPLY_TO, CONTENT, POSTED_TIME)
 VALUES(118, 'これに尽きるな。105の言いたいことはわかるけども', '2023-02-05 01:28:55.091');

INSERT INTO POST(REPLY_TO, AUTHOR, CONTENT, POSTED_TIME)
 VALUES(116, 'sasakue大熊猫', 'センスないとか言うな傷つくだろ', '2023-02-05 08:22:28.472');

INSERT INTO POST(CONTENT, POSTED_TIME)
 VALUES('今日のメモ : もやし、米俵、HotMot', '2023-02-05 13:31:23.233');

INSERT INTO POST(REPLY_TO, CONTENT, POSTED_TIME)
 VALUES(121, '米俵！？HotMotだけでええやん', '2023-02-05 15:03:13.168');

INSERT INTO POST(CONTENT, POSTED_TIME)
 VALUES('数字とかいらんw漢数字でいいしなんであるん?ww ', '2023-02-05 18:56:10.481');

INSERT INTO POST(REPLY_TO, CONTENT, POSTED_TIME)
 VALUES(123, 'そんなんいったら日本語の方がきもいやん', '2023-02-05 21:55:17.581');

INSERT INTO POST(AUTHOR, CONTENT, POSTED_TIME)
 VALUES('瓦割3枚記録保持強者!', '瓦20枚割れないやつは雑魚www', '2023-02-05 23:55:36.789');

INSERT INTO POST(REPLY_TO, CONTENT, POSTED_TIME)
 VALUES(125, 'でもお前3枚しか割れないじゃん', '2023-02-06 03:11:49.630');

INSERT INTO POST(REPLY_TO, AUTHOR, CONTENT, POSTED_TIME)
 VALUES(126, '瓦割3枚記録保持強者!', 'その通りです。。。', '2023-02-06 08:54:00.684');

INSERT INTO POST(REPLY_TO, CONTENT, POSTED_TIME)
 VALUES(127, 'おう...なんかすまねぇな', '2023-02-06 15:11:37.963');

INSERT INTO POST(REPLY_TO, CONTENT, POSTED_TIME)
 VALUES(128, 'このスレッド平和だな', '2023-02-06 19:46:29.883');

INSERT INTO POST(REPLY_TO, AUTHOR, CONTENT, POSTED_TIME)
 VALUES(129, 'sasakue大熊猫', 'これこそ掲示板のあるべき姿だな', '2023-02-06 23:58:46.297');

INSERT INTO POST(REPLY_TO, AUTHOR, CONTENT, POSTED_TIME)
 VALUES(105, 'komadori', '俺は何もない方が好き、はーと', '2023-02-07 08:11:26.411');

INSERT INTO POST(REPLY_TO, AUTHOR, CONTENT, POSTED_TIME)
 VALUES(125, 'smallbird0503', '俺小指だけで200枚しかいけんかった<br>俺って実は強い?', '2023-02-07 11:37:53.225');

COMMIT;
EXIT;