package dev.mvc.attachfile;

import java.util.List;

public interface AttachfileDAOInter {
  
  /**
   * <xmp>
   * 파일 등록
   * <insert id="create" parameterType="AttachfileVO">
   * </xmp>
   * @param attachfileVO
   * @return
   */
  public int create(AttachfileVO attachfileVO);
  
  /**
   * 파일 목록
   * @return
   */
  public List<AttachfileVO> list();
  
  /**
   * contentsno별 첨부 파일 목록 
   * @param contentsno
   * @return
   */
  public List<AttachfileVO> list_by_noticeno(int noticeno);
  
  /**
   * 번호에따른 파일 삭제 
   * @param attachfileno
   * @return 삭제된 레코드 갯수
   */
  public int delete(int attachno); 
  
  /**
   * noticeno 별 레코드 갯수
   * @param noticeno
   * @return 레코드 갯수
   */
  public int count_by_noticeno(int noticeno);
  
  /**
   * noticeno 별 레코드 삭제
   * @param noticeno
   * @return 레코드 갯수
   */
  public int delete_by_noticeno(int noticeno);
  
}






