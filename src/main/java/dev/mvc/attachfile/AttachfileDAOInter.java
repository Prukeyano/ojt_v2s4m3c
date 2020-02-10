package dev.mvc.attachfile;

import java.util.List;

public interface AttachfileDAOInter {
  
  /**
   * <xmp>
   * ���� ���
   * <insert id="create" parameterType="AttachfileVO">
   * </xmp>
   * @param attachfileVO
   * @return
   */
  public int create(AttachfileVO attachfileVO);
  
  /**
   * ���� ���
   * @return
   */
  public List<AttachfileVO> list();
  
  /**
   * contentsno�� ÷�� ���� ��� 
   * @param contentsno
   * @return
   */
  public List<AttachfileVO> list_by_iqynum(int iqynum);
  
  /**
   * ��ȣ������ ���� ���� 
   * @param attachfileno
   * @return ������ ���ڵ� ����
   */
  public int delete(int attachfileno); 
  
  /**
   * contentsno �� ���ڵ� ����
   * @param contentsno
   * @return ���ڵ� ����
   */
  public int count_by_iqynum(int iqynum);
  
  /**
   * contentsno �� ���ڵ� ����
   * @param contentsno
   * @return ���ڵ� ����
   */
  public int delete_by_iqynum(int iqynum);
  
}






