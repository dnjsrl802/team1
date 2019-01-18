DROP TABLE admin;
/**********************************/
/* Table Name: ������ */
/**********************************/
1. ���̺� ����
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

COMMENT ON TABLE admin is '������';
COMMENT ON COLUMN admin.adminno is '������ ��ȣ';
COMMENT ON COLUMN admin.id is '���̵�';
COMMENT ON COLUMN admin.passwd is '��� ��ȣ';
COMMENT ON COLUMN admin.auth is '����Ű';
COMMENT ON COLUMN admin.act is ' ����';
COMMENT ON COLUMN admin.mdate is '������';
COMMENT ON COLUMN admin.adminno is '��ȭ ��ȣ';
COMMENT ON COLUMN admin.adminno is '����';


2. ���
INSERT INTO admin(adminno, id, passwd, auth, act, mdate, tel, name)
VALUES((SELECT NVL(MAX(adminno), 0)+1 as adminno FROM admin), 'admin1@gmail.com', '1234', 'AXD0123456789012', 'M', sysdate, '010-0000-0000', '������1');
INSERT INTO admin(adminno, id, passwd, auth, act, mdate, tel, name)
VALUES((SELECT NVL(MAX(adminno), 0)+1 as adminno FROM admin), 'admin2@gmail.com', '1234', 'AXD0123456789012', 'M', sysdate, '010-0000-0000', '������2');
INSERT INTO admin(adminno, id, passwd, auth, act, mdate, tel, name)
VALUES((SELECT NVL(MAX(adminno), 0)+1 as adminno FROM admin), 'admin3@gmail.com', '1234', 'AXD0123456789012', 'M', sysdate, '010-0000-0000', '������3');

3. ���
SELECT adminno, id, passwd, auth, act, mdate, tel, name
FROM admin
ORDER BY id ASC;

ADMINNO ID               PASSWD AUTH             ACT MDATE                 TEL           NAME
 ------- ---------------- ------ ---------------- --- --------------------- ------------- ----
       1 admin1@gmail.com 1234   AXD0123456789012 M   2018-12-18 10:40:30.0 010-0000-0000 ������1
       2 admin2@gmail.com 1234   AXD0123456789012 M   2018-12-18 10:41:11.0 010-0000-0000 ������2
       3 admin3@gmail.com 1234   AXD0123456789012 M   2018-12-18 10:41:12.0 010-0000-0000 ������3

4. ����
DELETE FROM admin
WHERE adminno = 3;

5. ��ȸ
SELECT adminno, id, passwd, auth, act, mdate, tel, name
FROM admin
WHERE adminno = 1;

6. ����
UPDATE admin
SET id = 'admin5@gmail.com'
WHERE adminno = 1;

1) �ߺ� �̸��� �˻� ���� SQL 
    -- 0: �ߺ� �ƴ�, 1: �ߺ�  
    SELECT COUNT(*) as cnt
    FROM admin
    WHERE id = 'admin5@gmail.com';
    
2) MASTER ������ ��ȸ
    SELECT COUNT(*) as cnt
    FROM admin
    WHERE act = 'M';
    
2) ������ ����(admin4DAO: int updateAct(int admin4no, String act))
UPDATE admin
SET  act='Y'
WHERE adminno=1;

7. �н����� ����
1) ���� �н����� �˻�
- DAO: public int countPasswd(int admin4no, String passwd){ ... }
SELECT count(*) as cnt
FROM admin
WHERE adminno = 1 AND passwd='123';

2) �н����� ����
- DAO: public int updatePasswd(int admin4no, String passwd){ ... }
UPDATE admin
SET passwd=''
WHERE adminno=1;
-----------------------------------------------------------------------------------------------------
/**********************************/
/* Table Name: ���� ���� */
/**********************************/
DROP TABLE serch;
1. ���̺� ���
CREATE TABLE serch(
		serchno NUMBER(30) NOT NULL,
		adminno NUMBER(10) NOT NULL,
		memberno NUMBER(10) NOT NULL,
		moviename VARCHAR(30) NOT NULL,
		good VARCHAR(10) NOT NULL,
		repair VARCHAR(100) NOT NULL,
		theater VARCHAR(30) NOT NULL,
		moviedate VARCHAR(30) NOT NULL,
		showtime VARCHAR(30) NOT NULL,
		
		PRIMARY KEY(serchno),
		FOREIGN KEY (adminno) REFERENCES admin(adminno)
);

COMMENT ON TABLE serch is '��������';
COMMENT ON COLUMN serch.serchno is '�������� ��ȣ';
COMMENT ON COLUMN serch.adminno is '������ ��ȣ';
COMMENT ON COLUMN serch.memberno is 'ȸ�� ��ȣ';
COMMENT ON COLUMN serch.moviename is '��ȭ��';
COMMENT ON COLUMN serch.good is '������';
COMMENT ON COLUMN serch.repair is '������';
COMMENT ON COLUMN serch.theater is '�� ����';
COMMENT ON COLUMN serch.moviedate is '�� ��¥';
COMMENT ON COLUMN serch.showtime is '�� �ð�';

2. ���
INSERT INTO serch(serchno, adminno, memberno, moviename, good, repair, theater, moviedate, showtime) 
VALUES ((SELECT NVL(MAX(serchno), 0)+1 as serchno FROM serch), 2, 3, '�Ϻ��� Ÿ��', '3.5', '�ʹ� ������', '��õ cgv', '2018-12-18', '16:10~18:00');
INSERT INTO serch(serchno, adminno, memberno, moviename, good, repair, theater, moviedate, showtime) 
VALUES ((SELECT NVL(MAX(serchno), 0)+1 as serchno FROM serch), 2, 3, '��ġ�� �Ѳ�', '3.5', '�ʹ� ������', '��õ cgv', '2018-12-18', '15:10~16:00');
INSERT INTO serch(serchno, adminno, memberno, moviename, good, repair, theater, moviedate, showtime) 
VALUES ((SELECT NVL(MAX(serchno), 0)+1 as serchno FROM serch), 2, 3, 'ģ��', '3.5', '�ʹ� ������', '��õ cgv', '2018-12-18', '13:10~14:00');


3. ���
SELECT serchno, adminno, memberno,moviename, good, repair, theater, moviedate, showtime
FROM serch
ORDER BY moviename ASC;

SERCHNO ADMINNO MEMBERNO MOVIENAME GOOD REPAIR THEATER MOVIEDATE  SHOWTIME
 ------- ------- -------- --------- ---- ------ ------- ---------- -----------
       2       2        3 ��ġ�� �Ѳ�    3.5  �ʹ� ������ ��õ cgv  2018-12-18 15:10~16:00
       1       2        3 �Ϻ��� Ÿ��    3.5  �ʹ� ������ ��õ cgv  2018-12-18 16:10~18:00
       3       2        3 ģ��        3.5  �ʹ� ������ ��õ cgv  2018-12-18 13:10~14:00
       
       
4. ����
DELETE FROM serch
WHERE serchno = 1;

5. ��ȸ
SELECT serchno, adminno, memberno,moviename, good, repair, theater, moviedate, showtime
FROM serch
WHERE serchno = 1;

6. ����
UPDATE serch
SET moviename = '�ظ�����'
WHERE serchno = 1;

-------------------------------------------------------------------------------------------
DROP TABLE serchlist;

/**********************************/
/* Table Name: ���� ���� �׸� */
/**********************************/
1. ���̺� ���
CREATE TABLE serchlist(
		serchlistno NUMBER(30) NOT NULL,
		serchno NUMBER(30) NOT NULL,
		serchtitle VARCHAR(100) NOT NULL,
		listprintorder NUMBER(30) NOT NULL,
		orderlist VARCHAR(30) NOT NULL,
		orderfile VARCHAR(100) NOT NULL,
		listchoiceman NUMBER(30) NOT NULL,
		PRIMARY KEY(serchlistno),
		FOREIGN KEY (serchno) REFERENCES serch(serchno)
);

COMMENT ON TABLE serchlist is '���� ���� �׸�';
COMMENT ON COLUMN serchlist.serchno is '���� ���� ��ȣ';
COMMENT ON COLUMN serchlist.serchlistno is '���� ���� �׸� ��ȣ';
COMMENT ON COLUMN serchlist.serchtitle is '���� ���� ����';
COMMENT ON COLUMN serchlist.listprintorder is '�׸� ��� ����';
COMMENT ON COLUMN serchlist.orderlist is '�׸�';
COMMENT ON COLUMN serchlist.orderfile is '�׸� ����';
COMMENT ON COLUMN serchlist.listchoiceman is '�׸� ���� �ο�';

2. ���
INSERT INTO serchlist (serchno, serchlistno, serchtitle, listprintorder, orderlist, orderfile, listchoiceman)
VALUES (1, 1 ,'��õ CGV ��', 1, '��ȭ��', '111.jpg', 5);
INSERT INTO serchlist (serchno, serchlistno, serchtitle, listprintorder, orderlist, orderfile, listchoiceman)
VALUES (2, 2 ,'���� CGV ��', 1, '��ȭ��', '111.jpg', 5);
INSERT INTO serchlist (serchno, serchlistno, serchtitle, listprintorder, orderlist, orderfile, listchoiceman)
VALUES (3, 3 ,'��� CGV ��', 1, '��ȭ��', '111.jpg', 5);

3. ���
SELECT serchno, serchlistno, serchtitle, listprintorder, orderlist, orderfile, listchoiceman
FROM serchlist
ORDER BY serchtitle ASC;

SERCHNO SERCHLISTNO SERCHTITLE LISTPRINTORDER ORDERLIST ORDERFILE LISTCHOICEMAN
 ------- ----------- ---------- -------------- --------- --------- -------------
       3           3        ��� CGV ��           1           ��ȭ��       111.jpg               5
       2           2        ���� CGV ��           1           ��ȭ��       111.jpg               5
       1           1        ��� CGV ��           1           ��ȭ��       111.jpg               5

4. ����
DELETE FROM serchlist
WHERE serchlistno = 1;

SERCHNO SERCHLISTNO SERCHTITLE LISTPRINTORDER ORDERLIST ORDERFILE LISTCHOICEMAN
 ------- ----------- ---------- -------------- --------- --------- -------------
       3           3          ��� CGV ��         1           ��ȭ��       111.jpg               5
       2           2          ���� CGV ��         1           ��ȭ��       111.jpg               5
       
5. ��ȸ
SELECT serchno, serchlistno, serchtitle, listprintorder, orderlist, orderfile, listchoiceman
FROM serchlist
WHERE serchlistno = 2;

6. ����
UPDATE serchlist
SET serchtitle = '���� CGV ��'
WHERE serchlistno = 3;
----------------------------------------------------------------------------------------------
/**********************************/
/* Table Name: ���� ���� ȸ�� */
/**********************************/
1. ���̺� ���
CREATE TABLE serchmember(
		serchmemberno NUMBER(30) NOT NULL,
    /*memberno NUMBER(30) NOT NULL,*/
		serchdate DATE NOT NULL,
		serchlistno NUMBER(30) NOT NULL,
		PRIMARY KEY (serchmemberno),
		FOREIGN KEY (serchlistno) REFERENCES serchlist(serchlistno)
		/*FOREIGN KEY (memberno) REFERENCES member(memberno)*/
);

DROP TABLE serchmember;


/* ȸ�� ���̺� �޾ƿ��� ���� ��*/



















