package dev.mvc.categrp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface CategrpDAOInter {
  
  /**
   * ī�װ� ��� ���
   * @return ���ڵ� ����
   */
  public ArrayList<CategrpVO> list();
  
  
  /**
   * @param categrpVO ���� �� ī�װ� ����
   * @return ���� ���� 1 ���� 0 ����
   */
  public int create(CategrpVO categrpVO);
  
  public List<CategrpVO> tag_select(); 
  
  public List<CategrpVO> update_select(int categrpno);  
  
  public int update(CategrpVO categrpVO);
  
  public int delete_child_cnt(int categrpno);
  
  public int delete(int categrpno);
  
  public List<CategrpJoinVO> repository_select(int categrpno);
  
  public int cnt_up_update(int categrpno);
  
  public int cnt_down_update(int categrpno);
  
  public int cnt_up_update_categrp(HashMap<Object, Object> map);
  
  public int cnt_down_update_categrp(HashMap<Object, Object> map);

  public List<CategrpVO> list_seqno_asc();
  
  public List<CategrpVO>  tag_select2();
}
