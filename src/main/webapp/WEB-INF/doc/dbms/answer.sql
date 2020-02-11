/**********************************/
/* Table Name: �亯 */
/**********************************/
DROP TABLE answer;

CREATE TABLE answer(
    awno                    NUMBER(10)     NOT NULL  PRIMARY KEY,
    iqynum                  NUMBER(10)    NOT NULL,
    memno                  NUMBER(10)   NOT NULL,
    awcontent               CLOB             NOT NULL,
    awdate                   DATE             NOT NULL,
  FOREIGN KEY (iqynum) REFERENCES one_inquiry (iqynum),
  FOREIGN KEY (memno) REFERENCES mem (memno)
);

COMMENT ON TABLE answer is '�亯';
COMMENT ON COLUMN answer.awno is '�亯��ȣ';
COMMENT ON COLUMN answer.iqynum is '���ǹ�ȣ';
COMMENT ON COLUMN answer.memno is 'ȸ����ȣ';
COMMENT ON COLUMN answer.content is '����';
COMMENT ON COLUMN answer.awdate is '�����';

-- ���
INSERT INTO answer(awno, iqynum, memno, awcontent, awdate)
VALUES((SELECT NVL(MAX(awno), 0) + 1 as awno FROM answer), 1, 1, 'ȯ�� ������ �������׿� �ԽõǾ� �ֽ��ϴ�.', sysdate);

INSERT INTO answer(awno, iqynum, memno, awcontent, awdate)
VALUES((SELECT NVL(MAX(awno), 0) + 1 as awno FROM answer), 2, 1, '�ش� ��ǰ�� ������ �� �԰�˴ϴ�.', sysdate);

-- ��ȸ
SELECT *
FROM answer
WHERE memno = 1;

-- ����

UPDATE answer
SET awcontent = '��ǰ ��ȯ�ϰ� �;��'
WHERE awno=1;

-- ���� 
DELETE FROM answer
WHERE awno=1;

