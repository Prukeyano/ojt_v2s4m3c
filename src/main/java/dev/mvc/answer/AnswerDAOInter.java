package dev.mvc.answer;

import java.util.List;

public interface AnswerDAOInter {
  /**
   * ���� ��ȣ�� ���� ��ȸ
   * @param iqynum
   * @return
   */
  public AnswerVO readByIqynum(int iqynum);

}
