package dev.mvc.showppingbasket;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class ShowppingbasketCnt {

	@Qualifier("dev.mvc.shoppingbasket.ShowppingbasketProc")
	@Autowired
	ShowppingbasketProcInter showppingProc;

//	ajax�� ������ ����������  ajax�� ó���ϰڴٰ� ����������Ѵ�.
	/**
	 * ��ٱ��� �߰�
	 * 
	 * @param session
	 * @param showppingbasketVO
	 * @param re
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/showppingbasket/add.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String addShowppingbasket(HttpSession session, ShowppingbasketVO showppingbasketVO, HttpServletRequest re) {

		JSONObject ob = new JSONObject();
		System.out.println("ajax ����!!!!");
		String memberid = session.getAttribute("id").toString();
		showppingbasketVO.setMemberid(memberid);
		System.out.println(showppingbasketVO.toString());
		// �ִ� 5���� �Ѵ���?
		int count = showppingProc.showppingbasketCnt(showppingbasketVO.getMemberid());
		if (count < 5) {
			// �ش� ��ǰ�� ������ ��ǰ�� DB�� �ִ°�?
			count = showppingProc.showppingbasketDuplicate(showppingbasketVO);
			if (count != 1) {
				// ���� �� �� �ش��� �ȵȴٸ� ���
				showppingProc.showppingbasketCreate(showppingbasketVO);
				ob.put("messege", "��ٱ��Ͽ� ��ǰ�� ��ϵǾ����ϴ�.");
			} else {
				ob.put("messege", "��ٱ��Ͽ� �ش� ��ǰ�� ����ֽ��ϴ�.");
			}
		} else {
			ob.put("messege", "��ٱ��ϴ��ִ� 5���� ���� �մϴ�.");
		}
		return ob.toString();
	}

	/**
	 * ��ٱ��� ����Ʈ
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/showppingbasket/list.do", method = RequestMethod.GET)
	public ModelAndView showppingbasket_list(HttpSession session) {
		ModelAndView mav = new ModelAndView("showppingbasket/list");
		String memberid = session.getAttribute("id").toString();
		mav.addObject("list", showppingProc.showppingbasketList(memberid));
		return mav;
	}

	
	/**
	 * ��ٱ��� ����
	 * @param params
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/showppingbasket/delete.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String showppingbasketDelete(@RequestBody HashMap<String, ArrayList<Integer>> params, HttpSession session) {
	  System.out.println(params);
	  
		JSONObject jsonObject = new JSONObject();
		HashMap<String, Object> map = new HashMap<>();
		
		String memberid = session.getAttribute("id").toString();
		map.put("memberid", memberid);
		
		ArrayList<Integer> list = params.get("multiparam");
		for(int value : list) {
			map.put("productno", value);
			showppingProc.showppingbasketDelete(map);
		}
		jsonObject.put("messege", "�����Ǿ����ϴ�.");
		return jsonObject.toString();
	}

}
