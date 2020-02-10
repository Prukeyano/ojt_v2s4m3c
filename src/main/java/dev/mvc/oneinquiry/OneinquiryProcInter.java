package dev.mvc.oneinquiry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface OneinquiryProcInter {
  
  /**
   * <xmp>
   * <insert id="create" parameterType="OneinquiryVO">
   * </xmp>
   * @param oneinquiryVO
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

  public int max_no();
  
  /**
   * ȸ���� �˻� ���ڵ� ����
   * @param hashMap
   * @return
   */
  public int search_count(HashMap<String, Object> hashMap);
  
  /**
   * ������ ��� ���ڿ� ����, Box ����
   * @param listFile ��� ���ϸ� 
   * @param memno ȸ����ȣ
   * @param search_count �˻� ����
   * @param nowPage ���� ������, nowPage�� 1���� ����
   * @return
   */
  public String pagingBox(String listFile, int memno, int search_count, int nowPage);
   
  
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
