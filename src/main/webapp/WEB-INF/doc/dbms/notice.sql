/**********************************/
/* Table Name: ���� */
/**********************************/
DROP TABLE employee;

CREATE TABLE employee(
		adminno                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY
);

COMMENT ON TABLE employee is '����';
COMMENT ON COLUMN employee.adminno is '������ȣ';


/**********************************/
/* Table Name: �������� */
/**********************************/
DROP TABLE notice;

CREATE TABLE notice(
		noticeno                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		noticetitle                   		VARCHAR2(100)		 NOT NULL,
		noticecont                    		CLOB		 NOT NULL,
		seqno                         		NUMBER(1)		 NOT NULL,
		visible                       		VARCHAR2(10)		 DEFAULT 'Y'		 NOT NULL,
		rdate                         		DATE		 NOT NULL,
		adminno                       		NUMBER(10)		 NULL 
 
);

/*   FOREIGN KEY (adminno) REFERENCES employee (adminno) */

COMMENT ON TABLE notice is '��������';
COMMENT ON COLUMN notice.noticeno is '�������׹�ȣ';
COMMENT ON COLUMN notice.noticetitle is '������������';
COMMENT ON COLUMN notice.noticecont is '�������׳���';
COMMENT ON COLUMN notice.seqno is '��¼���';
COMMENT ON COLUMN notice.visible is '��¸��';
COMMENT ON COLUMN notice.rdate is '�����';
COMMENT ON COLUMN notice.adminno is '������ȣ';

--���

SELECT NVL(MAX(noticeno), 0) + 1 as noticeno FROM notice;


INSERT INTO notice(noticeno, noticetitle, noticecont, seqno, visible, rdate)
VALUES((SELECT NVL(MAX(noticeno), 0) + 1 as noticeno FROM notice),
                '��÷��ǰ ���ɹ��', '����~~' ,1 , 'Y', sysdate);
INSERT INTO notice(noticeno, noticetitle, noticecont, seqno, visible, rdate)
VALUES((SELECT NVL(MAX(noticeno), 0) + 1 as noticeno FROM notice),
                ' ���ݿ����� ��û���', '����~~' ,2 , 'Y', sysdate);
 INSERT INTO notice(noticeno, noticetitle, noticecont, seqno, visible, rdate)
VALUES((SELECT NVL(MAX(noticeno), 0) + 1 as noticeno FROM notice),
                ' ��ȯ,��ǰ�� ȯ�Ҿȳ�', '����~~' ,3 , 'Y', sysdate);
            
-- ���            
SELECT * FROM notice ORDER BY seqno ASC;

DELETE FROM notice;


/**********************************/
/* Table Name: �������� ÷������ */
/**********************************/

DROP TABLE attachfile_notice;

CREATE TABLE attachfile_notice(
    attachno                          NUMBER(10)     NOT NULL    PRIMARY KEY,
    fname                             VARCHAR2(100)    NOT NULL,
    fupname                           VARCHAR2(100)    NOT NULL,
    thumb                             VARCHAR2(100)    NULL ,
    fsize                             NUMBER(10)     DEFAULT 0     NOT NULL,
    rdate                             DATE     NOT NULL,
    noticeno                          NUMBER(10)     NULL ,
  FOREIGN KEY (noticeno) REFERENCES notice (noticeno)
);

COMMENT ON TABLE attachfile_notice is '�������� ÷������';
COMMENT ON COLUMN attachfile_notice.attachno is '÷�����Ϲ�ȣ';
COMMENT ON COLUMN attachfile_notice.fname is '�����̸�';
COMMENT ON COLUMN attachfile_notice.fupname is '���ε�������̸�';
COMMENT ON COLUMN attachfile_notice.thumb is 'thumb�����̸�';
COMMENT ON COLUMN attachfile_notice.fsize is '���ϻ�����';
COMMENT ON COLUMN attachfile_notice.rdate is '�����';
COMMENT ON COLUMN attachfile_notice.noticeno is '�������׹�ȣ';

SELECT NVL(MAX(attachno), 0) + 1 as attachno FROM attachfile_notice

--���
INSERT INTO attachfile_notice(attachno, noticeno, fname, fupname, thumb, fsize, rdate)
VALUES((SELECT NVL(MAX(attachno), 0) + 1 as attachno FROM attachfile_notice),
               1, 'samyang.jpg', 'samyang_1.jpg', 'samyang_t.jpg', 1000, sysdate);
INSERT INTO attachfile_notice(attachno, noticeno, fname, fupname, thumb, fsize, rdate)
VALUES((SELECT NVL(MAX(attachno), 0) + 1 as attachno FROM attachfile_notice),
               1, 'samyang2.jpg', 'samyang2_1.jpg', 'samyang2_t.jpg', 2000, sysdate);
 INSERT INTO attachfile_notice(attachno, noticeno, fname, fupname, thumb, fsize, rdate)
VALUES((SELECT NVL(MAX(attachno), 0) + 1 as attachno FROM attachfile_notice),
                1, 'samyang3.jpg', 'samyang3_1.jpg', 'samyang3_t.jpg', 3000, sysdate);        
             
-- ���            
SELECT attachno, noticeno, fname, fupname, thumb, fsize, rdate
FROM attachfile_notice 
ORDER BY attachno ASC;

 ATTACHNO NOTICENO FNAME       FUPNAME       THUMB           FSIZE  RDATE
 -------- -------- ----------- ------------- --------------- ------ ---------------------
        1        1 puppy06.jpg puppy06_0.jpg puppy06_0_t.jpg  54931 2019-12-16 16:29:27.0
        2        1 puppy13.jpg puppy13.jpg   puppy13_t.jpg   152171 2019-12-16 17:09:35.0
        3        4 puppy07.jpg puppy07.jpg   puppy07_t.jpg   253349 2019-12-16 17:28:45.0



DELETE FROM attachfile_notice;
        
        
        
SELECT attachno, noticeno, fname, fupname, thumb, fsize, rdate
FROM attachfile_notice 
ORDER BY attachno ASC;




DELETE FROM notice;





/**********************************/
/* Table Name: ������ ��ǰ ī�װ� */
/**********************************/
CREATE TABLE categroup(

);

COMMENT ON TABLE categroup is '������ ��ǰ ī�װ�';


/**********************************/
/* Table Name: ������ ��ǰ ��� */
/**********************************/
CREATE TABLE repository(
		adminno                       		NUMBER(10)		 NULL ,
  FOREIGN KEY (adminno) REFERENCES employee (adminno)
);

COMMENT ON TABLE repository is '������ ��ǰ ���';
COMMENT ON COLUMN repository.adminno is '������ȣ';


/**********************************/
/* Table Name: ������ ��ǰ �Ǹű� */
/**********************************/
CREATE TABLE product(
		cateno                        		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		productno                     		NUMBER(10)		 NOT NULL,
		adminno                       		NUMBER(10)		 NULL ,
  FOREIGN KEY (adminno) REFERENCES employee (adminno),
  FOREIGN KEY () REFERENCES categroup (),
  FOREIGN KEY () REFERENCES repository ()
);

COMMENT ON TABLE product is '������ ��ǰ �Ǹű�';
COMMENT ON COLUMN product.cateno is '�Խ��ǹ�ȣ';
COMMENT ON COLUMN product.productno is '��ǰ�ڵ�';
COMMENT ON COLUMN product.adminno is '������ȣ';


/**********************************/
/* Table Name: ȸ�� */
/**********************************/
CREATE TABLE mem(
		memno                         		NUMBER(10)		 NOT NULL		 PRIMARY KEY
);

COMMENT ON TABLE mem is 'ȸ��';
COMMENT ON COLUMN mem.memno is 'ȸ����ȣ';


/**********************************/
/* Table Name: qna���� */
/**********************************/
CREATE TABLE qna(
		qnano                         		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		qnatitle                      		VARCHAR2(100)		 NOT NULL,
		qnacont                       		VARCHAR2(300)		 NOT NULL,
		qnadate                       		DATE		 NOT NULL,
		qnastate                      		VARCHAR2(10)		 DEFAULT 'N'		 NOT NULL,
		cateno                        		NUMBER(10)		 NULL ,
		memno                         		NUMBER(10)		 NULL ,
  FOREIGN KEY (cateno) REFERENCES product (cateno),
  FOREIGN KEY (memno) REFERENCES mem (memno)
);

COMMENT ON TABLE qna is 'qna����';
COMMENT ON COLUMN qna.qnano is '������ȣ';
COMMENT ON COLUMN qna.qnatitle is '��������';
COMMENT ON COLUMN qna.qnacont is '��������';
COMMENT ON COLUMN qna.qnadate is '�ۼ���';
COMMENT ON COLUMN qna.qnastate is '�亯����';
COMMENT ON COLUMN qna.cateno is '�Խ��ǹ�ȣ';
COMMENT ON COLUMN qna.memno is 'ȸ����ȣ';


/**********************************/
/* Table Name: qna�亯 */
/**********************************/
CREATE TABLE qnaandswer(
		answerno                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		answertitle                   		VARCHAR2(100)		 NOT NULL,
		answercont                    		VARCHAR2(300)		 NOT NULL,
		answerdate                    		DATE		 NOT NULL,
		adminno                       		NUMBER(10)		 NULL ,
		qnano                         		NUMBER(10)		 NULL ,
  FOREIGN KEY (qnano) REFERENCES qna (qnano),
  FOREIGN KEY (adminno) REFERENCES employee (adminno)
);

COMMENT ON TABLE qnaandswer is 'qna�亯';
COMMENT ON COLUMN qnaandswer.answerno is '�亯��ȣ';
COMMENT ON COLUMN qnaandswer.answertitle is '�亯����';
COMMENT ON COLUMN qnaandswer.answercont is '�亯����';
COMMENT ON COLUMN qnaandswer.answerdate is '�亯��¥';
COMMENT ON COLUMN qnaandswer.adminno is '������ȣ';
COMMENT ON COLUMN qnaandswer.qnano is '������ȣ';


/**********************************/
/* Table Name: �����ı� */
/**********************************/

DROP TABLE review
CREATE TABLE review(
		reviewno                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		reviewtitle                   		VARCHAR2(100)		 NOT NULL,
		reviewcont                    		CLOB		 NOT NULL,
		reviewscore                     NUMBER(10) NOT NULL,
		reviewdate                    		DATE		 NOT NULL
);

/*
 * memno                            NUMBER(10)     NULL ,
    cateno                            NUMBER(10)     NULL ,
    productno                         NUMBER(10)     NOT NULL,
 * */

COMMENT ON TABLE review is '�����ı�';
COMMENT ON COLUMN review.reviewno is '�����ı��ȣ';
COMMENT ON COLUMN review.reviewtitle is '�ı�����';
COMMENT ON COLUMN review.reviewcont is '�ı⳻��';
COMMENT ON COLUMN review.reviewdate is '�ı�����';
COMMENT ON COLUMN review.reviewscore is '�ı�����';

/*
 * COMMENT ON COLUMN review.memno is 'ȸ����ȣ';
COMMENT ON COLUMN review.cateno is '�Խ��ǹ�ȣ';
COMMENT ON COLUMN review.productno is '��ǰ�ڵ�';*/


SELECT NVL(MAX(reviewno), 0) + 1 as reviewno FROM review

--���
INSERT INTO review(reviewno, reviewtitle, reviewcont, reviewscore, reviewdate)
VALUES((SELECT NVL(MAX(reviewno), 0) + 1 as reviewno FROM review),
                '�Ź� �����ϴ���....^^', '�ʹ� ���׿�......^^', 5, sysdate);
INSERT INTO review(reviewno, reviewtitle, reviewcont, reviewscore, reviewdate)
VALUES((SELECT NVL(MAX(reviewno), 0) + 1 as reviewno FROM review),
               '�߹޾ҽ��ϴ�', '��',4, sysdate);
 INSERT INTO review(reviewno, reviewtitle, reviewcont, reviewscore, reviewdate)
VALUES((SELECT NVL(MAX(reviewno), 0) + 1 as reviewno FROM review),
                '���� �۸��̰� �����ؿ�', '>-<',5, sysdate);        
             
-- ���            
SELECT reviewno, reviewtitle, reviewcont,reviewscore, reviewdate
FROM review 
ORDER BY reviewno ASC;

 REVIEWNO REVIEWTITLE    REVIEWCONT     REVIEWSCORE    REVIEWDATE
 -------- --------------              --------------         -----------  ---------------------
        1 �Ź� �����ϴ���....^^    �ʹ� ���׿�......^^           5             2019-12-19 10:57:44.0
        2 �߹޾ҽ��ϴ�                          ��                       4             2019-12-19 10:57:45.0
        3 ���� �۸��̰� �����ؿ�            >-<                    5              2019-12-19 10:57:46.0











