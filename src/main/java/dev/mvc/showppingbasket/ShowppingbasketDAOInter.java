package dev.mvc.showppingbasket;

import java.util.HashMap;
import java.util.List;

public interface ShowppingbasketDAOInter {

	/**
	 * ��ٱ��� ����
	 * 
	 * @param shoppingbasketVO
	 * @return
	 */
	public int showppingbasketCreate(ShowppingbasketVO showppingbasketVO);

	/**
	 * ��ٱ��� ����
	 * 
	 * @param memberid
	 * @return
	 */
	public int showppingbasketCnt(String memberid);

	/**
	 *  ��ٱ��� ��ǰ �ߺ� üũ
	 * @param shoppingbasketVO
	 * @return
	 */
	public int showppingbasketDuplicate(ShowppingbasketVO showppingbasketVO);

	/**
	 * Ư�� ���� ��ٱ��� �ҷ�����
	 * @param memeberid
	 * @return
	 */
	public List<ShowppingbasketVO> showppingbasketList(String memeberid);
	
	/**
	 *  ��ٱ��� ����Ʈ ����
	 * @param map
	 * @return
	 */
	public int showppingbasketDelete(HashMap<String, Object>map);
}
