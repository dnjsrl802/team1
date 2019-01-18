DROP TABLE actor;

DROP TABLE actor CASCADE CONSTRAINTS;
/**********************************/
/* Table Name: ��� */
/**********************************/
CREATE TABLE actor(
		actorno NUMBER(30) NOT NULL,
		actorname VARCHAR2(10) NOT NULL,
		actorbrd VARCHAR2(30) NOT NULL,
		thumbs  VARCHAR2(1000)   NULL ,
		files   VARCHAR2(1000)    NULL,
		sizes  VARCHAR2(1000)    NULL ,
		actor_sang VARCHAR2(500) NOT NULL,
		physical VARCHAR2(100) NOT NULL,
		actorwork VARCHAR2(500) NOT NULL,
		s_word			VARCHAR2(1000) NOT NULL,
 	PRIMARY KEY(actorno)
);


COMMENT ON TABLE actor is '���';
COMMENT ON COLUMN actor.actorno is '��� ��ȣ';
COMMENT ON COLUMN actor.actorname is '��� �̸�';
COMMENT ON COLUMN actor.actorbrd is '��� ���';
COMMENT ON COLUMN actor.thumbs is '�����';
COMMENT ON COLUMN actor.files is '���ϸ�';
COMMENT ON COLUMN actor.sizes is '����ũ��';
COMMENT ON COLUMN actor.actor_sang is '��� ����';
COMMENT ON COLUMN actor.physical is '��� ��ü';
COMMENT ON COLUMN actor.actorwork is '��� ��ǰ';
COMMENT ON COLUMN actor.word is '�˻���';

2. ���
INSERT INTO actor(actorno, actorname, actorbrd, thumbs, files, sizes, actor_sang, physical, actorwork, s_word)
VALUES((SELECT NVL(MAX(actorno), 0) +1 as actorno FROM actor), '������', '1970�� 1�� 4��', 'm_you.jpg', 'yyou.jpg', 0,
						'2017�� 37ȸ �ѱ���ȭ��а���ȸ�� ���������� ����', '174cm,65kg', '�Ϻ��� Ÿ�� ���', '������');
INSERT INTO actor(actorno, actorname, actorbrd, actorimage, actor_sang, physical, actor_photo, actorwork)
VALUES((SELECT NVL(MAX(actorno), 0) +1 as actorno FROM actor), '���μ�', '1988�� 1�� 4��', 'jo.jpg', 
						'2017�� 38ȸ û�濵ȭ�� �α⽺Ÿ�� ����', '186cm,76kg', 'photo', '�Ƚü� ���');
INSERT INTO actor(actorno, actorname, actorbrd, actorimage, actor_sang, physical, actor_photo, actorwork)
VALUES((SELECT NVL(MAX(actorno), 0) +1 as actorno FROM actor), 'Ȳ����', '1979�� 1�� 4��', 'hwang.jpg', 
						'2018�� 55ȸ ������ ��ȭ�� �����ֿ��� ����', '180cm,75kg', 'photo', '�ż��� ���');


3. ���
SELECT actorno, actorname, actorbrd, thumbs, files, sizes, actor_sang, physical, actorwork
FROM actor
ORDER BY actorno ASC;

ACTORNO ACTORNAME ACTORBRD    ACTORIMAGE ACTOR_SANG                    PHYSICAL   ACTORWORK
 ------- --------- ----------- ---------- ----------------------------- ---------- ---------
       1 ������       1970�� 1�� 4�� you.jpg    2017�� 37ȸ �ѱ���ȭ��а���ȸ�� ���������� ���� 174cm,65kg �Ϻ��� Ÿ�� ���
       2 ���μ�       1988�� 1�� 4�� jo.jpg     2017�� 38ȸ û�濵ȭ�� �α⽺Ÿ�� ����      186cm,76kg �Ƚü� ���
       3 Ȳ����       1979�� 1�� 4�� hwang.jpg  2018�� 55ȸ ������ ��ȭ�� �����ֿ��� ����    180cm,75kg �ż��� ���

4. ����
DELETE FROM actor
WHERE actorno = 6;

5. ��ȸ
SELECT actorno, actorname, actorbrd, thumbs, files, sizes, actor_sang, physical, actorwork, word
FROM actor
WHERE actorno = 1;

ACTORNO ACTORNAME ACTORBRD    ACTORIMAGE ACTOR_SANG                 PHYSICAL   ACTORWORK
 ------- --------- ----------- ---------- -------------------------- ---------- ---------
       3 Ȳ����       1979�� 1�� 4�� hwang.jpg  2018�� 55ȸ ������ ��ȭ�� �����ֿ��� ���� 180cm,75kg �ż��� ���

6. ����
UPDATE actor
SET actorname='������', actorbrd = '1982�� 1�� 4��', thumbs='����.jpg', files='�ҷ�.jpg', sizes=0, actor_sang='�������� ����'
			,physical='ũ��', actorwork='������ ��ǰ��'
WHERE actorno = 1;

DROP TABLE PG;
CREATE TABLE PG(
  num NUMBER(5) NOT NULL,
  actorname  VARCHAR2(20) NOT NULL,
  PRIMARY KEY(num)
);

SELECT * FROM actor;
-- �˻�
 SELECT actorno, actorname, actorbrd, thumbs, files, sizes, actor_sang, physical, actorwork, s_word, r
    FROM (
              SELECT actorno, actorname, actorbrd, thumbs, files, sizes, actor_sang, physical, actorwork, s_word, rownum as r
              FROM (
                        SELECT actorno, actorname, actorbrd, thumbs, files, sizes, actor_sang, physical, actorwork, s_word
                        FROM actor
                  WHERE actorname AND s_word LIKE '%������%'
                  ORDER BY actorno DESC
         )
)
WHERE r >=1 AND r <= 3;


