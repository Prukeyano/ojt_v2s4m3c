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
  public List<AttachfileVO> list_by_noticeno(int noticeno);
  
  /**
   * ��ȣ������ ���� ���� 
   * @param attachfileno
   * @return ������ ���ڵ� ����
   */
  public int delete(int attachno); 
  
  /**
   * noticeno �� ���ڵ� ����
   * @param noticeno
   * @return ���ڵ� ����
   */
  public int count_by_noticeno(int noticeno);
  
  /**
   * noticeno �� ���ڵ� ����
   * @param noticeno
   * @return ���ڵ� ����
   */
  public int delete_by_noticeno(int noticeno);
  
}






