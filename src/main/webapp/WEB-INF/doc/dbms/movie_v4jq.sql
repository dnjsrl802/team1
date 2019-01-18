CREATE TABLE admin(
		adminno NUMBER(10) NOT NULL, -- ������ ��ȣ
		id VARCHAR(60) NOT NULL,				 -- ���̵�
		passwd VARCHAR(10) NOT NULL,	-- ��� ��ȣ
		auth VARCHAR(30) NOT NULL,			-- ����Ű
		act CHAR(1) DEFAULT 'N' NOT NULL,		-- M: ������, Y: �α��� ����, N: �α��� �Ұ�, H: ���� ���, G: �մ�, C: Ż��
		mdate DATE NOT NULL,						-- ������
		tel VARCHAR(20) NOT NULL,			-- ��ȭ ��ȣ
		name VARCHAR(30) NOT NULL,	-- ����
	PRIMARY KEY(adminno)
);

CREATE TABLE member(
		memberno NUMBER(10) NOT NULL, -- ������ ��ȣ
	PRIMARY KEY(memberno)
);



DROP TABLE movie;

/**********************************/
/* Table Name: ��ȭ */
/**********************************/
CREATE TABLE movie(
		movieno NUMBER(30) NOT NULL,
		title VARCHAR(100) NOT NULL,
		choice VARCHAR(300) NOT NULL,
		contents VARCHAR(500) NOT NULL,
		actor    VARCHAR(100) NOT NULL,
		thumbs VARCHAR(500) NOT NULL,
		image VARCHAR(10) NOT NULL,
		image_size NUMBER(20)  DEFAULT 0 NOT NULL,
		star VARCHAR(10) NOT NULL,
		rdate DATE NOT NULL,

	PRIMARY KEY(movieno)

);

COMMENT ON TABLE movie is '��ȭ';
COMMENT ON COLUMN movie.movieno is '��ȭ ��ȣ';
COMMENT ON COLUMN movie.title is '��ȭ��';
COMMENT ON COLUMN movie.choice is '�⺻����';
COMMENT ON COLUMN movie.contents is '�ٰŸ�';
COMMENT ON COLUMN movie.actor is '���';
COMMENT ON COLUMN movie.thumbs is '�����';
COMMENT ON COLUMN movie.image is '�̹���';
COMMENT ON COLUMN movie.image_size is '�̹��� ũ��';
COMMENT ON COLUMN movie.star is '����';
COMMENT ON COLUMN movie.rdate is '�����';


2. ���
INSERT INTO movie(movieno, title, choice, contents, actor, thumbs, image, image_size, star, rdate)
VALUES((SELECT NVL(MAX(movieno), 0)+1 as movieno FROM movie),'����̾� ���ҵ�', '������ 31.4% �帣: ����', '��վ��', '������ ��ť��', 10, 10, 10, 10, sysdate);
INSERT INTO movie(movieno, title, choice, contents, actor, thumbs, image, image_size, star, rdate)
VALUES((SELECT NVL(MAX(movieno), 0)+1 as movieno FROM movie),'�̽�Ʈ', '������ 31.4% �帣: ����', '��վ��', '���̼�', 10, 10, 10, 10, sysdate);
INSERT INTO movie(movieno, title, choice, contents, actor, thumbs, image, image_size, star, rdate)
VALUES((SELECT NVL(MAX(movieno), 0)+1 as movieno FROM movie),'�ظ�����', '������ 31.4% �帣: ����', '��վ��', '���̼�', 10, 10, 10, 10, sysdate);

3. ���
SELECT movieno, title, choice, contents, actor, thumbs, image, image_size, star, rdate
FROM movie
ORDER BY title ASC;

 MOVIENO TITLE    CHOICE                  CONTENTS             ACTOR   THUMBS IMAGE IMAGE_SIZE STAR RDATE
 ------- -------- ---------------- -------- ------- ------ ----- ---------- ---- ---------------------
       2 �̽�Ʈ      ������ 31.4% �帣: ����    ��վ��              ���̼�     10     10            10 10   2019-01-08 19:54:12.0
       1 ����̾�  ������ 31.4% �帣: ����     ��վ��     ������ ��ť��      10     10            10 10   2019-01-08 19:53:25.0
       3 �ظ�����     ������ 31.4% �帣: ����   ��վ��             ���̼�     10     10            10 10   2019-01-08 19:54:13.0

4. ����
DELETE FROM movie
WHERE movieno = 1;

5. ��ȸ
SELECT movieno, title, choice, contents, actor, thumbs, image, image_size, star, rdate
FROM movie
WHERE movieno = 2;

6. ����
UPDATE movie
SET title = '������'
WHERE movieno = 3;


/**********************************/
/* Table Name: ��ȭ��� */
/**********************************/
DROP TABLE m_actor;

CREATE TABLE m_actor(
		m_actorno NUMBER(30) NOT NULL,
		m_actorname VARCHAR(30) NOT NULL,
		m_actorbrd   VARCHAR(20) NOT NULL,
		m_actorwork	VARCHAR(100) NOT NULL,
		m_actorimage VARCHAR(500) NOT NULL,
		movieno		NUMBER(30) NOT NULL,
		PRIMARY KEY(m_actorno),
		FOREIGN KEY(movieno) REFERENCES movie(movieno)
);

COMMENT ON TABLE m_actor is '��ȭ ���';
COMMENT ON COLUMN m_actor.m_actorno is '��ȭ��� ��ȣ';
COMMENT ON COLUMN m_actor.m_actorname is '��ȭ��� �̸�';
COMMENT ON COLUMN m_actor.m_actorbrd is '��ȭ��� �������';
COMMENT ON COLUMN m_actor.m_actorwork is '��ȭ��� ��ǰ';
COMMENT ON COLUMN m_actor.m_actorimage is '��ȭ��� ����';
COMMENT ON COLUMN m_actor.movieno is '��ȭ ��ȣ';

2. ���
INSERT INTO m_actor(m_actorno, m_actorname, m_actorbrd, m_actorwork, m_actorimage, movieno)
VALUES((SELECT NVL(MAX(m_actorno), 0)+1 as m_actorno FROM m_actor), '�۰�ȣ', '1900-10-19', '��ǰ', '�̹���', 1);
INSERT INTO m_actor(m_actorno, m_actorname, m_actorbrd, m_actorwork, m_actorimage, movieno)
VALUES((SELECT NVL(MAX(m_actorno), 0)+1 as m_actorno FROM m_actor), '��â��', '1900-10-19', '��ǰ', '�̹���', 1);
INSERT INTO m_actor(m_actorno, m_actorname, m_actorbrd, m_actorwork, m_actorimage, movieno)
VALUES((SELECT NVL(MAX(m_actorno), 0)+1 as m_actorno FROM m_actor), '������', '1900-10-19', '��ǰ', '�̹���', 1);

3. ���
SELECT m_actorno, m_actorname, m_actorbrd, m_actorwork, m_actorimage, movieno
FROM m_actor
ORDER BY m_actorname ASC;
M_ACTORNO M_ACTORNAME M_ACTORBRD M_ACTORWORK M_ACTORIMAGE MOVIENO
 --------- ----------- ---------- ----------- ------------ -------
         3         ������         1900-10-19          ��ǰ                �̹���                1
         1         �۰�ȣ         1900-10-19          ��ǰ                �̹���                1
         2         ��â��         1900-10-19          ��ǰ                �̹���                1

4. ����
DELETE FROM m_actor
WHERE m_actorno = 1;













