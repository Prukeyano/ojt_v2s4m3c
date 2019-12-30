package dev.mvc.notice;

import java.util.List;

public interface NoticeDAOInter {
  /**
   * <xmp> ��� <insert id="create" parameterType="NoticeVO"> </xmp>
   * 
   * @param ReviewVO
   * @return ��ϵ� ���ڵ� ����
   */
  public int create(NoticeVO noticeVO);

  /**
   * <xmp> ��ü ��� <select id="list" resultType="NoticeVO"> </xmp>
   * 
   * @return
   */
  public List<NoticeVO> list();
  
  public NoticeVO read(int noticeno);
  
  public int update(NoticeVO noticeVO);

  public int delete(int noticeno);

  public int count_by_noticeno(int noticeno);
  
  public int delete_by_noticeno(int noticeno);
  
}
