/**********************************/
/* Table Name: 1��1 ���� */
/**********************************/
drop table one_inquiry;
CREATE TABLE one_inquiry(
    iqynum                    NUMBER(10)          NOT NULL  PRIMARY KEY,
    memno                    NUMBER(10)          NOT NULL,
    title                        VARCHAR2(100)     NOT NULL,
    content                   CLOB             NOT NULL,
    idate                       DATE                  NOT NULL,
    status                      VARCHAR2(10)   DEFAULT  'N'   NOT NULL ,
  FOREIGN KEY (memno) REFERENCES mem (memno)
);

COMMENT ON TABLE one_inquiry is '1��1 ����';
COMMENT ON COLUMN one_inquiry.iqynum is '���ǹ�ȣ';
COMMENT ON COLUMN one_inquiry.memno is 'ȸ����ȣ';
COMMENT ON COLUMN one_inquiry.title is '����';
COMMENT ON COLUMN one_inquiry.content is '����';
COMMENT ON COLUMN one_inquiry.idate is '�����';
COMMENT ON COLUMN one_inquiry.status is '�亯����';

-- ���
INSERT INTO one_inquiry(iqynum, memno, title, content, idate)
VALUES((SELECT NVL(MAX(iqynum), 0) + 1 as iqynum FROM one_inquiry), 1, 'ȯ���ϰ�;��', 'ȯ�� ��� �ؿ�?', sysdate);

INSERT INTO one_inquiry(iqynum, memno, title, content, idate)
VALUES((SELECT NVL(MAX(iqynum), 0) + 1 as iqynum FROM one_inquiry), 1, 'ǰ���� ��ǰ ���� ��������?', '�� �������� �ϴµ�', sysdate);

INSERT INTO one_inquiry(iqynum, memno, title, content, idate)
VALUES((SELECT NVL(MAX(iqynum), 0) + 1 as iqynum FROM one_inquiry), 1, 'ǰ���� ��ǰ ���� ��������?', '��dfsd��', sysdate);

INSERT INTO one_inquiry(iqynum, memno, title, content, idate)
VALUES((SELECT NVL(MAX(iqynum), 0) + 1 as iqynum FROM one_inquiry), 1, 'ǰ���� ��ǰ ���� ��������?', '��d�µ�', sysdate);

INSERT INTO one_inquiry(iqynum, memno, title, content, idate)
VALUES((SELECT NVL(MAX(iqynum), 0) + 1 as iqynum FROM one_inquiry), 1, 'ǰ���� ��ǰ ���� ��������?', '��hfgh��', sysdate);

INSERT INTO one_inquiry(iqynum, memno, title, content, idate)
VALUES((SELECT NVL(MAX(iqynum), 0) + 1 as iqynum FROM one_inquiry), 1, 'ǰ���� ��ǰ ���� ��������?', '��asdg��', sysdate);

INSERT INTO one_inquiry(iqynum, memno, title, content, idate)
VALUES((SELECT NVL(MAX(iqynum), 0) + 1 as iqynum FROM one_inquiry), 1, 'ǰ���� ��ǰ ���� ��������?', '��dfgg�ϴµ�', sysdate);

INSERT INTO one_inquiry(iqynum, memno, title, content, idate)
VALUES((SELECT NVL(MAX(iqynum), 0) + 1 as iqynum FROM one_inquiry), 1, 'ǰ���� ��ǰ ���� ��������?', '��sdf�µ�', sysdate);

INSERT INTO one_inquiry(iqynum, memno, title, content, idate)
VALUES((SELECT NVL(MAX(iqynum), 0) + 1 as iqynum FROM one_inquiry), 1, 'ǰ���� ��ǰ ���� ��������?', ' ������', sysdate);

INSERT INTO one_inquiry(iqynum, memno, title, content, idate)
VALUES((SELECT NVL(MAX(iqynum), 0) + 1 as iqynum FROM one_inquiry), 1, 'ǰ���� ��ǰ ���� ��������?', 'sdfff', sysdate);


INSERT INTO one_inquiry(iqynum, memno, title, content, idate)
VALUES((SELECT NVL(MAX(iqynum), 0) + 1 as iqynum FROM one_inquiry), 2, 'ȯ���ϰ�;��', 'ȯ�� ��� �ؿ�?', sysdate);

INSERT INTO one_inquiry(iqynum, memno, title, content, idate)
VALUES((SELECT NVL(MAX(iqynum), 0) + 1 as iqynum FROM one_inquiry), 2, 'ǰ���� ��ǰ ���� ��������?', '�� �������� �ϴµ�', sysdate);

-- ��ȸ
SELECT iqynum, memno, title, content, idate, status
FROM one_inquiry
WHERE memno = 1;

SELECT iqynum, memno, title, content, idate, status
FROM one_inquiry
ORDER BY iqynum DESC
WHERE memno = 2;

-- ȸ���� ��� ��ȸ
SELECT mem_iqyno ,memno
FROM (select rownum as mem_iqyno , memno from one_inquiry where memno=1);

-- ����

UPDATE one_inquiry
SET content = '��ǰ ��ȯ�ϰ� �;��'
WHERE iqynum=1;

-- ���� 
DELETE FROM one_inquiry
WHERE iqynum = 29;

-- �÷� �߰�

ALTER TABLE one_inquiry
ADD (status VARCHAR2(9) DEFAULT 'N' NOT NULL);

COMMENT ON COLUMN one_inquiry.status is '�亯 ����';

UPDATE one_inquiry
SET status = 'Y'
WHERE (SELECT awcontent 
           FROM answer 
           WHERE one_inquiry.iqynum = answer.iqynum) is not null;

select * from one_inquiry order by iqynum desc;
select * from attachfile_inquiry;
SELECT NVL(MAX(iqynum), 0)  as iqynum FROM one_inquiry;

SELECT rownum as r, iqynum, memno, title, content, idate, status 
FROM one_inquiry 
WHERE memno = 2

-- step 1
SELECT iqynum, memno, title, content, idate, status
FROM one_inquiry
WHERE memno=1 
ORDER BY iqynum DESC;

-- step 2

SELECT rownum as r, iqynum, memno, title, content, idate, status
FROM (
          SELECT iqynum, memno, title, content, idate, status
          FROM one_inquiry
          WHERE memno=1 
          ORDER BY iqynum DESC
);


SELECT contentsno, memberno, categrpno, title, content, 
           recom, cnt, replycnt, rdate, word, rownum as r
FROM (
          SELECT contentsno, memberno, categrpno, title, content, 
                     recom, cnt, replycnt, rdate, word
          FROM contents
          WHERE categrpno=1 AND word LIKE '%������%'
          ORDER BY contentsno DESC
);

 R  IQYNUM MEMNO TITLE            CONTENT              IDATE                 STATUS
 -- ------ ----- ---------------- -------------------- --------------------- ------
  1     59     1 3333             3333                 2020-01-30 17:01:46.0 N
  2     56     1 gdfg             dfgdfgadfg           2020-01-15 09:55:23.0 N
  3     55     1 ggg              ggg                  2020-01-15 09:52:42.0 N
  4     54     1 ddd              dddd                 2020-01-15 09:49:34.0 N
  5     53     1 sdf              sdf                  2020-01-15 09:49:19.0 N
  6     52     1 �ҷ� ��ǰ ��ǰ ����      �ҷ� ��ǰ ��ǰ             2020-01-08 17:17:07.0 N
  7     51     1 ȸ�� Ż�� ����         Ż���ϸ� ���� ���� �簡�� �Ұ��ΰ��� 2020-01-08 17:09:29.0 N
  8     49     1 ������              ������                  2019-12-26 14:44:36.0 N
  9     48     1 ����������            ����������                2019-12-23 16:07:25.0 N
 10     47     1 ��������             ������                  2019-12-16 18:04:29.0 N
 11      9     1 ǰ���� ��ǰ ���� ��������? sdfff                2019-12-16 16:25:23.0 N
 12      8     1 ǰ���� ��ǰ ���� ��������? ��sdf�µ�               2019-12-06 18:02:43.0 N
 13      7     1 ǰ���� ��ǰ ���� ��������? ��dfgg�ϴµ�             2019-12-06 18:02:42.0 N
 14      6     1 ǰ���� ��ǰ ���� ��������? ��asdg��              2019-12-06 18:02:41.0 N
 15      5     1 ǰ���� ��ǰ ���� ��������? ��hfgh��               2019-12-06 18:02:40.0 N
 16      3     1 ǰ���� ��ǰ ���� ��������? ��dfsd��               2019-12-06 18:02:38.0 N
 17      2     1 ǰ��               ǰ��                   2019-12-06 17:41:09.0 Y
 18      1     1 ȯ���ϰ�;��          ȯ�� ��� �ؿ�?           2019-12-06 17:41:05.0 Y



-- step 3

SELECT r, iqynum, memno, title, content, idate, status
FROM (
					SELECT rownum as r, iqynum, memno, title, content, idate, status
					FROM (
					          SELECT iqynum, memno, title, content, idate, status
					          FROM one_inquiry
					          WHERE memno=1 
					          ORDER BY iqynum DESC
					)
);

 R  IQYNUM MEMNO TITLE            CONTENT              IDATE                 STATUS
 -- ------ ----- ---------------- -------------------- --------------------- ------
  1     59     1 3333             3333                 2020-01-30 17:01:46.0 N
  2     56     1 gdfg             dfgdfgadfg           2020-01-15 09:55:23.0 N
  3     55     1 ggg              ggg                  2020-01-15 09:52:42.0 N
  4     54     1 ddd              dddd                 2020-01-15 09:49:34.0 N
  5     53     1 sdf              sdf                  2020-01-15 09:49:19.0 N
  6     52     1 �ҷ� ��ǰ ��ǰ ����      �ҷ� ��ǰ ��ǰ             2020-01-08 17:17:07.0 N
  7     51     1 ȸ�� Ż�� ����         Ż���ϸ� ���� ���� �簡�� �Ұ��ΰ��� 2020-01-08 17:09:29.0 N
  8     49     1 ������              ������                  2019-12-26 14:44:36.0 N
  9     48     1 ����������            ����������                2019-12-23 16:07:25.0 N
 10     47     1 ��������             ������                  2019-12-16 18:04:29.0 N
 11      9     1 ǰ���� ��ǰ ���� ��������? sdfff                2019-12-16 16:25:23.0 N
 12      8     1 ǰ���� ��ǰ ���� ��������? ��sdf�µ�               2019-12-06 18:02:43.0 N
 13      7     1 ǰ���� ��ǰ ���� ��������? ��dfgg�ϴµ�             2019-12-06 18:02:42.0 N
 14      6     1 ǰ���� ��ǰ ���� ��������? ��asdg��              2019-12-06 18:02:41.0 N
 15      5     1 ǰ���� ��ǰ ���� ��������? ��hfgh��               2019-12-06 18:02:40.0 N
 16      3     1 ǰ���� ��ǰ ���� ��������? ��dfsd��               2019-12-06 18:02:38.0 N
 17      2     1 ǰ��               ǰ��                   2019-12-06 17:41:09.0 Y
 18      1     1 ȯ���ϰ�;��          ȯ�� ��� �ؿ�?           2019-12-06 17:41:05.0 Y

SELECT contentsno, memberno, categrpno, title, content, 
           recom, cnt, replycnt, rdate, word, r
FROM (
           SELECT contentsno, memberno, categrpno, title, content, 
                      recom, cnt, replycnt, rdate, word, rownum as r
           FROM (
                     SELECT contentsno, memberno, categrpno, title, content, 
                                recom, cnt, replycnt, rdate, word
                     FROM contents
                     WHERE categrpno=1 AND word LIKE '%������%'
                     ORDER BY contentsno DESC
           )          
);
