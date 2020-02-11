DROP TABLE mem_grade;
DROP TABLE power;
DROP TABLE mem;
DROP table dilivery;

/**********************************/
/* Table Name: ȸ�� ��� */
/**********************************/
CREATE TABLE mem_grade(
		gradeno                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		gradename                     		VARCHAR2(10)		 NOT NULL
);

COMMENT ON TABLE mem_grade is 'ȸ�� ���';
COMMENT ON COLUMN mem_grade.gradeno is '��� ��ȣ';
COMMENT ON COLUMN mem_grade.gradename is '��� �̸�';

select * from mem_grade;

INSERT INTO mem_grade(gradeno, gradename)
VALUES ((SELECT NVL(MAX(gradeno), 0) + 1 as gradeno FROM mem_grade), 'basic');

INSERT INTO mem_grade(gradeno, gradename)
VALUES ((SELECT NVL(MAX(gradeno), 0) + 1 as gradeno FROM mem_grade), 'regular');

INSERT INTO mem_grade(gradeno, gradename)
VALUES ((SELECT NVL(MAX(gradeno), 0) + 1 as gradeno FROM mem_grade), 'star');

INSERT INTO mem_grade(gradeno, gradename)
VALUES ((SELECT NVL(MAX(gradeno), 0) + 1 as gradeno FROM mem_grade), 'gold');

INSERT INTO mem_grade(gradeno, gradename)
VALUES ((SELECT NVL(MAX(gradeno), 0) + 1 as gradeno FROM mem_grade), 'legend'); 

delete from mem_grade where gradeno = 6;

/**********************************/
/* Table Name: ���� */
/**********************************/
CREATE TABLE power(
		powerno                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY
);

COMMENT ON TABLE power is '����';
COMMENT ON COLUMN power.powerno is '���� ��ȣ';

INSERT INTO power(powerno)
VALUES (00);

INSERT INTO power(powerno)
VALUES (11);

/**********************************/
/* Table Name: ȸ�� */
/**********************************/
CREATE TABLE mem(
		memno                         		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		gradeno                       		NUMBER(10)		 NOT NULL,
		powerno                       		NUMBER(10)		 NOT NULL,
		name                          		VARCHAR2(10)		 NOT NULL,
		gender                        		CHAR(1)		 NOT NULL,
		phonenum                      		VARCHAR2(20)		 NOT NULL,
		zipcode                       		VARCHAR2(20)		 NOT NULL,
		address                       		VARCHAR2(100)		 NOT NULL,
		address_d                     		VARCHAR2(100)		 NOT NULL,
		id                            		VARCHAR2(30)		 NOT NULL,
		passwd                        		VARCHAR2(30)		 NOT NULL,
  FOREIGN KEY (powerno) REFERENCES power (powerno),
  FOREIGN KEY (gradeno) REFERENCES mem_grade (gradeno)
);

COMMENT ON TABLE mem is 'ȸ��';
COMMENT ON COLUMN mem.memno is 'ȸ����ȣ';
COMMENT ON COLUMN mem.gradeno is '���';
COMMENT ON COLUMN mem.powerno is '���� ��ȣ';
COMMENT ON COLUMN mem.name is '�̸�';
COMMENT ON COLUMN mem.gender is '����';
COMMENT ON COLUMN mem.phonenum is '����ȣ';
COMMENT ON COLUMN mem.zipcode is '�����ȣ';
COMMENT ON COLUMN mem.address is '�ּ�';
COMMENT ON COLUMN mem.address_d is '���ּ�';
COMMENT ON COLUMN mem.id is '���̵�';
COMMENT ON COLUMN mem.passwd is '���';

INSERT INTO mem(memno, gradeno, powerno, name, gender, phonenum, zipcode, address, address_d, id, passwd)
VALUES ((SELECT NVL(MAX(memno), 0) + 1 as memno FROM mem), 1, 11, 'no1', 'm', '010-1234-5678', '12345', '����� ���α�', '�� 24���� 4-1', 'abcd1234', '1111');

INSERT INTO mem(memno, gradeno, powerno, name, gender, phonenum, zipcode, address, address_d, id, passwd)
VALUES ((SELECT NVL(MAX(memno), 0) + 1 as memno FROM mem), 1, 11, '����', 'm', '010-1234-5678', '12345', '����� ���α�', '�� 24���� 4-1', 'abcde', '1234');

select * from mem;

/**********************************/
/* Table Name: ����Ʈ */
/**********************************/
drop table point;

CREATE TABLE point(
		pnum                          		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		memno                         	NUMBER(10)		 NULL ,
		division                          VARCHAR2(20)      NOT NULL,
		history                        		VARCHAR2(100)		 NOT NULL,
		pdate                         		DATE		 NOT NULL,
		dvcomplete                    	VARCHAR2(10)		 DEFAULT 'N'		NULL,
		usep                              NUMBER(10)    DEFAULT 0    NULL,
		ampoint                       		NUMBER(10)		 DEFAULT 0		 NOT NULL,
		total                              NUMBER(10)   DEFAULT 0 NULL,
  FOREIGN KEY (memno) REFERENCES mem (memno)
);

COMMENT ON TABLE point is '����Ʈ';
COMMENT ON COLUMN point.pnum is '����Ʈ��ȣ';
COMMENT ON COLUMN point.memno is 'ȸ����ȣ';
COMMENT ON COLUMN point.division is '����';
COMMENT ON COLUMN point.history is '����';
COMMENT ON COLUMN point.pdate is '������';
COMMENT ON COLUMN point.dvcomplete is '��ۿϷ�����';
COMMENT ON COLUMN point.usep is '��� ����Ʈ';
COMMENT ON COLUMN point.ampoint is '���� ����Ʈ';
COMMENT ON COLUMN point.total is '���� ����Ʈ';

-- ���

INSERT INTO point(pnum, memno, division, history, pdate, dvcomplete, usep, ampoint, total)
VALUES((SELECT NVL(MAX(pnum), 0) + 1 as pnum FROM point), 1, '����', '��ǰ ����', sysdate, 'y', 0, 150);

INSERT INTO point(pnum, memno, division, history, pdate, dvcomplete, usep, ampoint, total)
VALUES((SELECT NVL(MAX(pnum), 0) + 1 as pnum FROM point), 1, '����', '��ǰ ����', sysdate, 'y', 0, 250);

INSERT INTO point(pnum, memno, division, history, pdate, dvcomplete, usep, ampoint, total)
VALUES((SELECT NVL(MAX(pnum), 0) + 1 as pnum FROM point), 1, '����', '��ǰ ����', sysdate, 'y', 0, 500);

-- ���

SELECT pnum, memno, division, history, pdate, dvcomplete, usep, ampoint
FROM point
WHERE memno = 1;

 PNUM MEMNO DIVISION HISTORY PDATE                 DVCOMPLETE USEP AMPOINT
 ---- ----- -------- ------- --------------------- ---------- ---- -------
    1     1 ����       ��ǰ ����   2019-12-05 14:55:32.0 y             0     150
    2     1 ����       ��ǰ ����   2019-12-05 17:17:56.0 y             0     250
    3     1 ����       ��ǰ ����   2019-12-05 17:18:00.0 y             0     500

-- ����

UPDATE point
SET history='�⼮'
WHERE memno=1 AND pnum=2;

-- ����

DELETE point
WHERE pnum = 3;

/**********************************/
/* Table Name: ��� */
/**********************************/
CREATE TABLE dilivery(
		odcode                        		NUMBER(10)		       NOT NULL		 PRIMARY KEY,
		memno                         	NUMBER(10)		       NOT NULL ,
		dvaddress                     		VARCHAR2(200)		 NOT NULL,
		zipcode                       		NUMBER(10)		       NOT NULL,
		product                       		VARCHAR2(100)		 NOT NULL,
		pdcnt                             NUMBER(10)         NOT NULL,
		dvstatus                    	   VARCHAR2(100)		 DEFAULT 'preparing'	 NOT NULL,
  FOREIGN KEY (memno) REFERENCES mem (memno),
);

COMMENT ON TABLE dilivery is '���';
COMMENT ON COLUMN dilivery.odcode is '�ֹ��ڵ�';
COMMENT ON COLUMN dilivery.memno is 'ȸ����ȣ';
COMMENT ON COLUMN dilivery.dvaddress is '�����';
COMMENT ON COLUMN dilivery.zipcode is '�����ȣ';
COMMENT ON COLUMN dilivery.product is '��ǰ �ڵ�';
COMMENT ON COLUMN dilivery.pdcnt is '��ǰ ����';
COMMENT ON COLUMN dilivery.dvstatus is '�����Ȳ';

SELECT * FROM dilivery;


