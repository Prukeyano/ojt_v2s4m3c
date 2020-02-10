package dev.mvc.oneinquiry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface OneinquiryDAOInter {
  
  /**
   * <xmp>
   * <insert id="create" parameterType="AttachfileVO">
   * </xmp>
   * @param attachfileVO
   * @return
   */
  public int create(OneinquiryVO oneinquiryVO);
  
  /**
   * <xmp>
   * ���
   * <select id="list" resultType="OneinquiryVO">
   * </xmp>
   * @return
   */
  public List<OneinquiryVO> list_by_memno(int memno);
  
  /**
   * <xmp>
   * ��ȸ
   * <select id="read" resultType="OneinquiryVO" parameterType="int">
   * </xmp>
   * @param iqynum
   * @return
   */
  public OneinquiryVO read(int iqynum);
  
  /**
   * <xmp>
   * ����
   * <update id="update" parameterType="OneinquiryVO">
   * </xmp>
   * @param oneinquiryVO
   * @return
   */
  public int update(OneinquiryVO oneinquiryVO);
  
  /**
   * <xmp>
   * ����
   * <delete id="delete" parameterType="int">
   * </xmp>
   * @param iqynum
   * @return
   */
  public int delete(int iqynum);
  
  /**
   * ������ �Խ��� ��ȣ��������
   * @return
   */
  public int max_no();
  
  /**
   * ȸ���� �˻� ���ڵ� ����
   * @param hashMap
   * @return
   */
  public int search_count(HashMap<String, Object> hashMap);
  
  /**
   * <xmp>
   * �˻� + ����¡ ���
   * <select id="list_by_memno_paging" resultType="OneinquiryVO" parameterType="HashMap">
   * </xmp>
   * @param map
   * @return
   */
  public ArrayList<OneinquiryVO> list_by_memno_paging(HashMap<String, Object> map);

}
