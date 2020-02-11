select * from mem where memno=74;


DROP table REPOSITORY;
DROP table CATEGROUP;
DROP table PRODUCT;

     /*************/

DROP table notice;
DROP table qna;
DROP table qnaandswer;
DROP table review;

DROP table one_inquiry;
DROP TABLE answer;
DROP TABLE attachfile_inquiry;

DROP table employ;
DROP table mem;
DROP table memgrade;

/**********************************/
/* Table Name: ���� ���̺� */
/**********************************/
-- ���� ���̺�
CREATE TABLE EMPLOY(
    name varchar(20) not null, -- ��� �̸�
    id varchar(20) not null, -- ��� ���̵�
    pw varchar(20) not null, -- ��� ��й�ȣ
    email varchar(20) not null, -- ��� �̸���
    erank varchar(20) default 'nomal' not null, -- ��� ����
    rdate date default sysdate not null, -- ��� ��ϳ�¥
    CONSTRAINT ID_PK PRIMARY KEY(ID) 
);


insert into EMPLOY values('ö��', 'empl1', '1234', 'test.com', 'nomal', sysdate);


/**********************************/
/* Table Name: ȸ�� ��� */
/**********************************/
 
CREATE TABLE memgrade(
memgradeno                    NUMBER(10)  NOT NULL  PRIMARY KEY,
seqno                       NUMBER(10)  NOT NULL,
gname                VARCHAR2(20)  NOT NULL
);

COMMENT ON TABLE memgrade is 'ȸ�� ���';
COMMENT ON COLUMN memgrade.memgradeno is '��� ��ȣ';
COMMENT ON COLUMN memgrade.seqno is '��� ����';
COMMENT ON COLUMN memgrade.gname is '��� �̸�';

INSERT INTO memgrade(memgradeno, seqno, gname)
VALUES ((SELECT NVL(MAX(memgradeno), 0) + 1 as memgradeno FROM memgrade), 1, 'basic');

INSERT INTO memgrade(memgradeno, seqno, gname)
VALUES ((SELECT NVL(MAX(memgradeno), 0) + 1 as memgradeno FROM memgrade), 2, 'regular');

INSERT INTO memgrade(memgradeno, seqno, gname)
VALUES ((SELECT NVL(MAX(memgradeno), 0) + 1 as memgradeno FROM memgrade), 3, 'star');

INSERT INTO memgrade(memgradeno, seqno, gname)
VALUES ((SELECT NVL(MAX(memgradeno), 0) + 1 as memgradeno FROM memgrade), 4, 'gold');

INSERT INTO memgrade(memgradeno, seqno, gname)
VALUES ((SELECT NVL(MAX(memgradeno), 0) + 1 as memgradeno FROM memgrade), 5, 'legend'); 


/**********************************/
/* Table Name: ȸ�� */
/**********************************/

CREATE TABLE mem(
    memno                       NUMBER(10)      NOT NULL PRIMARY KEY,
    memgradeno                NUMBER(10)  DEFAULT 1 NOT NULL,
    mname                         VARCHAR(30)    NOT NULL ,
    gender                       CHAR(1)           NOT NULL ,
    tel                             VARCHAR(20)    NOT NULL,
    email                         VARCHAR(200)   NOT NULL,
    zipcode                       VARCHAR(20)    NOT NULL,
    address1                     VARCHAR(150)    NOT NULL,
    address2                     VARCHAR(150)    NOT NULL,
    rdate                          DATE            DEFAULT sysdate  NOT NULL,
    id                               VARCHAR(40)    UNIQUE NOT NULL,
    passwd                        VARCHAR(40)    NOT NULL,
    
    FOREIGN KEY (memgradeno) REFERENCES memgrade(memgradeno)
);

COMMENT ON TABLE mem is 'ȸ��';
COMMENT ON COLUMN mem.memno is 'ȸ����ȣ';
COMMENT ON COLUMN mem.memgradeno is '���';
COMMENT ON COLUMN mem.mname is '�̸�';
COMMENT ON COLUMN mem.tel is '����ȣ';
COMMENT ON COLUMN mem.email is '�̸���';
COMMENT ON COLUMN mem.zipcode is '�����ȣ';
COMMENT ON COLUMN mem.address1 is '�ּ�';
COMMENT ON COLUMN mem.address2 is '���ּ�';
COMMENT ON COLUMN mem.rdate is '������';
COMMENT ON COLUMN mem.id is '���̵�';
COMMENT ON COLUMN mem.passwd is '���';

INSERT INTO mem(memno, mname, gender, tel, email, zipcode, address1, address2, id, passwd)
VALUES((SELECT NVL(MAX(memno), 0) + 1 as memno FROM mem),
           '�մ���', 'M', '010-1111-2222', 'abcde123@naver.com','123-1234', '��⵵ ��õ��', '����� 11', 'iden', '1');
           
INSERT INTO mem(memno, mname, gender, tel, email, zipcode, address1, address2, id, passwd)
VALUES((SELECT NVL(MAX(memno), 0) + 1 as memno FROM mem),
           '�Ʒι�', 'W', '010-2222-1111', 'efg343@naver.com','321-4321', '����ϵ� ���ֽ�', '���ϴ�', 'id', 'pass');

INSERT INTO mem(memno, mname, gender, tel, email, zipcode, address1, address2, id, passwd)
VALUES((SELECT NVL(MAX(memno), 0) + 1 as memno FROM mem),
           '�մ���5', 'M', '010-1111-2222', 'higk15@naver.com','123-1234', '��⵵ ��õ��', '����� 11', 'id5', '1');
           
INSERT INTO mem(memno, mname, gender, tel, email, zipcode, address1, address2, id, passwd)
VALUES((SELECT NVL(MAX(memno), 0) + 1 as memno FROM mem),
           'user1', 'M', '010-1111-2222', 'abc123@naver.com','123-1234', '��⵵ ��õ��', '����� 11', 'user1', '1234');
           
INSERT INTO mem(memno, mname, gender, tel, email, zipcode, address1, address2, id, passwd)
VALUES(0, 'admin1', 'M', '010-1111-2322', 'abc1234@naver.com','123-1274', '��⵵ ��õ��', '����� 11', 'admin1', '1234');

commit
select * from mem

/**********************************/
/* Table Name: ������ ��ǰ ��� */
/**********************************/

CREATE TABLE REPOSITORY(
    repositoryno number(10) not null, -- ��� ������ȣ
    cnt number(10) not null, -- ��� ����
    name varchar(50) not null, -- ��� �̸�
    categrpno number(10) not null, -- ī�װ� ��ȣ
    manager varchar(20) not null, -- ��� ����� ����
    rdate date default sysdate not null, -- ��� ��ϳ�¥
    price number(10) not null, -- ��� ����
    primary key(repositoryno),
    foreign key (categrpno) references CATEGROUP(categrpno)
);

/**********************************/
/* Table Name: ������ ��ǰ ī�װ� */
/**********************************/
CREATE TABLE CATEGROUP(
    categrpno number(10) not null, -- ������ ��ǰ ī�װ� ��ȣ
    name varchar(50) not null, -- ī�װ� �̸�
    seqno number(7) default 0 not null , -- ī�װ� ��¼���
    visible char(1) default 'Y' not null, -- ī�װ� ��� ����
    rdate date default sysdate not null,  -- ī�װ� ���� ��¥
    cnt number(7) not null, -- ī�װ� ���� ���̺� ����
    constraint categrpno_pk primary key(categrpno)
);

insert into CATEGROUP(categrpno, name, seqno, visible, rdate, cnt) values(1, '���', 1, 'Y', sysdate, 0);

/**********************************/
/* Table Name: ������ �Ǹű� */
/**********************************/
CREATE TABLE PRODUCT(
    categrpno number(10) not null, --ī�װ� ���� ��ȣ FK
    productno number(10) not null, -- �Ǹű� ���� ��ȣ PK
    repositoryno number(10) not null, -- ��� ���� ��ȣ FK
    title varchar(100) not null, -- ����
    keyword varchar(30) null, -- ��ǰ �˻���
    likey number(10) default 0, -- ��ǰ�� ���ƿ�
    deliveryCharge number(10) not null, -- ��ǰ ��ۺ� 
    cnt number(10) default 0, -- ��ǰ�� ������ ��ȸ��
    rdate date not null, -- ��ǰ ��ϳ�¥
    manager varchar(20) not null, -- ��ǰ ������ ����� ����
    contents varchar(1024) not null, -- ��ǰ �� �Ұ�
    primary key(productno),
    FOREIGN key(categrpNo) REFERENCES CATEGROUP(categrpno),
    FOREIGN key(repositoryno) REFERENCES REPOSITORY(repositoryno)
);


/**********************************/
/* Table Name: ��ǰ �̹��� ���̺�*/
/**********************************/
drop table productfile;

CREATE TABLE productfile(
        productfileno                  NUMBER(10)         NOT NULL         PRIMARY KEY, -- ���� ��ȣ
        productno                   NUMBER(10)         NULL , -- ��ǰ ��ȣ
        fname                             VARCHAR2(100)         NOT NULL, -- ���� �̸�
        fupname                      VARCHAR2(100)          NULL, -- ���� ���� �̸� 
        thumb                         VARCHAR2(100)         NULL , -- ����� �̸�
        fsize                                 NUMBER(10)         DEFAULT 0         NOT NULL, -- ���� ������
    rdate                           DATE     NOT NULL, -- �����
  FOREIGN KEY (productno) REFERENCES product (productno) on delete CASCADE
);

SELECT * FROM all_tables WHERE table_name = 'mem'

/**********************************/
/* Table Name: ��ٱ��� ���̺�*/
/**********************************/
drop table showppingbasket;

CREATE TABLE showppingbasket(
    showppingno number(10) not null, --��ٱ��� ��ȣ
    productno number(10) not null, -- ��ǰ��ȣ
    title varchar(100) not null, -- ��ǰ ����
    cnt number(10) not null, -- ��ǰ ����
    memberid varchar(100) not null, -- ��ǰ ������ ���̵�
    uri varchar(100) not null,
    rdate date DEFAULT sysdate  not null, -- ��ٱ��� �������
    primary key(showppingno), -- �⺻Ű
    FOREIGN key(memberid) references mem(id) -- �ܷ�Ű
);

/**********************************/
/* Table Name: �ֹ� ���̺�*/
/**********************************/
drop table orderr;

create table orderr(
    orderno number(20) not null primary key, -- �ֹ� ��ȣ
    ordername varchar(100) not null, -- �ֹ��� �̸�
    orderaddress1 varchar(100) not null, -- �ֹ��� �ּ�1
    orderaddress2 varchar(100) not null, -- �ֹ��� �ּ�2
    orderzipcode varchar(100) not null, -- �ֹ��� �����ּ�
    ordernumber VARCHAR(100) not null, -- �ֹ��� ��ȣ
    productname varchar(100) not null, -- ��ǰ �̸�
    productprice varchar(100) not null, -- ��ǰ ����
    productcnt varchar(100) not null, -- ��ǰ ����
    paymentstatus varchar(100) not null, -- ���� ����
    paymentmemberid varchar(100) not null, -- ���� ���̵�
    paymentkind varchar(100) not null, -- ��������
    paymentprice varchar(100) not null, -- ���� ����
    deliverystatus varchar(100) not null, -- ��ۻ���
    rdate date DEFAULT sysdate not null, -- �ֹ��ð�
    foreign key(paymentmemberid) REFERENCES mem(id)
);