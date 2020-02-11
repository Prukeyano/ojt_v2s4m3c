/**********************************/
/* Table Name: ÷������ */
/**********************************/
DROP TABLE attachfile_inquiry;
CREATE TABLE attachfile_inquiry(
    attachfileno                  NUMBER(10)     NOT NULL    PRIMARY KEY,
    iqynum                       NUMBER(10)     NOT NULL ,
    fname                        VARCHAR2(100) NOT NULL,
    fupname                     VARCHAR2(100)     NOT NULL,
    thumb                        VARCHAR2(100)    NULL ,
    fsize                           NUMBER(10)     DEFAULT 0     NOT NULL,
    fdate                          DATE     NOT NULL,
  FOREIGN KEY (iqynum) REFERENCES one_inquiry (iqynum)
);

COMMENT ON TABLE attachfile_inquiry is '÷������';
COMMENT ON COLUMN attachfile_inquiry.attachfileno is '÷�����Ϲ�ȣ';
COMMENT ON COLUMN attachfile_inquiry.iqynum is '���ǹ�ȣ';
COMMENT ON COLUMN attachfile_inquiry.fname is '���� ���ϸ�';
COMMENT ON COLUMN attachfile_inquiry.fupname is '���ε� ���ϸ�';
COMMENT ON COLUMN attachfile_inquiry.thumb is 'Thumb ����';
COMMENT ON COLUMN attachfile_inquiry.fsize is '���� ������';
COMMENT ON COLUMN attachfile_inquiry.fdate is '�����';

-- 1) ���
SELECT NVL(MAX(attachfileno), 0) + 1 as attachfileno FROM attachfile_inquiry;

 ATTACHFILENO
 ------------
            1

SELECT contentsno, memberno, categrpno, title
FROM contents
ORDER BY contentsno ASC;
          
INSERT INTO attachfile_inquiry(attachfileno, iqynum, fname, fupname, thumb, fsize, fdate)
VALUES((SELECT NVL(MAX(attachfileno), 0) + 1 as attachfileno FROM attachfile_inquiry),
             1, 'samyang.jpg', 'samyang_1.jpg', 'samyang_t.jpg', 1000, sysdate);
       
             
-- 2) ���( contentsno ���� ���� ����, attachfileno ���� ��������)
SELECT attachfileno, iqynum, fname, fupname, thumb, fsize, fdate
FROM attachfile_inquiry
ORDER BY iqynum DESC,  attachfileno ASC;

 ATTACHFILENO CONTENTSNO FNAME        FUPNAME        THUMB          FSIZE RDATE
 ------------ ---------- ------------ -------------- -------------- ----- ---------------------
            1          1 samyang.jpg  samyang_1.jpg  samyang_t.jpg   1000 2019-12-04 14:50:51.0
            2          1 samyang2.jpg samyang2_1.jpg samyang2_t.jpg  2000 2019-12-04 14:50:52.0
            3          1 samyang3.jpg samyang3_1.jpg samyang3_t.jpg  3000 2019-12-04 14:50:53.0

-- 3) �ۺ� ���� ���(contentsno ���� ���� ����, attachfileno ���� ��������)
SELECT attachfileno, iqynum, fname, fupname, thumb, fsize, rdate
FROM attachfile_inquiry
WHERE iqynum = 1
ORDER BY fname ASC;

 ATTACHFILENO CONTENTSNO FNAME        FUPNAME      THUMB          FSIZE  RDATE
 ------------ ---------- ------------ ------------ -------------- ------ ---------------------
            1          1 car1.jpg     car1_0.jpg   car1_0_t.jpg    12099 2019-12-04 15:01:20.0
            2          1 car2.jpg     car2_0.jpg   car2_0_t.jpg    10553 2019-12-04 15:01:20.0
            3          1 car3.jpg     car3.jpg     car3_t.jpg       9208 2019-12-04 15:01:20.0
            4          1 car4.jpg     car4.jpg     car4_t.jpg      11819 2019-12-04 15:01:20.0
            5          1 car5.jpg     car5.jpg     car5_t.jpg       9976 2019-12-04 15:01:20.0
            9          1 moon.zip     moon.zip     NULL           292777 2019-12-05 10:14:12.0
            6          1 spring18.jpg spring18.jpg spring18_t.jpg 444455 2019-12-05 09:55:07.0
            7          1 spring19.jpg spring19.jpg spring19_t.jpg 126983 2019-12-05 09:55:07.0
            8          1 spring20.jpg spring20.jpg spring20_t.jpg 345322 2019-12-05 09:55:07.0

-- 4) �ϳ��� ���� ����
DELETE FROM attachfile
WHERE attachfileno = 1;


-- 5) FK contentsno �θ� ���̺� ���ڵ� ���� ����
SELECT attachfileno, contentsno, fname, fupname, thumb, fsize, rdate
FROM attachfile
WHERE contentsno=1;

ATTACHFILENO CONTENTSNO FNAME        FUPNAME      THUMB          FSIZE  RDATE
 ------------ ---------- ------------ ------------ -------------- ------ ---------------------
            1          1 car3.jpg     car3.jpg     car3_t.jpg       9208 2019-12-04 15:01:20.0
            2          1 car4.jpg     car4.jpg     car4_t.jpg      11819 2019-12-04 15:01:20.0
            3          1 car5.jpg     car5.jpg     car5_t.jpg       9976 2019-12-04 15:01:20.0

SELECT COUNT(*) as cnt
FROM attachfile
WHERE contentsno=1;

 CNT
 ---
   3             
             
-- 6) FK �θ� ���̺� ���ڵ� ����
DELETE FROM attachfile
WHERE contentsno=1;

(3 rows affected)