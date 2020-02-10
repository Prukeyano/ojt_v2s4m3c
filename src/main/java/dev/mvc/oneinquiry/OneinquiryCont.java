package dev.mvc.oneinquiry;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.answer.AnswerProcInter;
import dev.mvc.answer.AnswerVO;
import dev.mvc.attachfile.AttachfileProcInter;
import dev.mvc.attachfile.AttachfileVO;
import nation.web.tool.Tool;
import nation.web.tool.Upload;


@Controller
public class OneinquiryCont {
  @Autowired
  @Qualifier("dev.mvc.oneinquiry.OneinquiryProc")
  private OneinquiryProcInter oneinquiryProc;
  
  @Autowired
  @Qualifier("dev.mvc.answer.AnswerProc")
  private AnswerProcInter answerProc;
  
  @Autowired
  @Qualifier("dev.mvc.attachfile.AttachfileProc") // �̸� ����
  private AttachfileProcInter attachfileProc;
  
  public OneinquiryCont() {
    System.out.println("--> OneinquiryCont created");
  }
  
  //http://localhost:9090/team6/oneinquiry/create.do?memno=1
  @RequestMapping(value="/oneinquiry/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/oneinquiry/create");
    return mav;
  }
  
  @RequestMapping(value="/oneinquiry/create.do", method=RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request,
                                       OneinquiryVO oneinquiryVO) {
    ModelAndView mav = new ModelAndView();
    
    int count = oneinquiryProc.create(oneinquiryVO);
    //System.out.println(oneinquiryVO.getFnamesMF().get(0).getOriginalFilename());
    //System.out.println(oneinquiryVO.getFnamesMF().get(0).getSize());
    if(oneinquiryVO.getFnamesMF().size() == 0) {
      mav.addObject("count", count);
      mav.setViewName("/oneinquiry/create_msg");
    } else {
      // -----------------------------------------------------
      // ���� ���� �ڵ� ����
      // -----------------------------------------------------
      int max_no = oneinquiryProc.max_no();
      //System.out.println("max_no: " + max_no);
      int iqynum = max_no; // �θ�� ��ȣ
      String fname = ""; // ���� ���ϸ�
      String fupname = ""; // ���ε�� ���ϸ�
      long fsize = 0;  // ���� ������
      String thumb = ""; // Preview �̹���
      int upload_count = 0; // ����ó���� ���ڵ� ����
      
      String upDir = Tool.getRealPath(request, "/oneinquiry/storage");
      // ���� ������ ����� fnamesMF ��ü�� ������.
      List<MultipartFile> fnamesMF = oneinquiryVO.getFnamesMF();
      int fcount = fnamesMF.size(); // ���� ���� ����
      if (fcount > 0) {
        for (MultipartFile multipartFile:fnamesMF) { // ���� ����
          fsize = multipartFile.getSize();  // ���� ũ��
          if (fsize > 0) { // ���� ũ�� üũ
            fname = multipartFile.getOriginalFilename(); // ���� ���ϸ�
            fupname = Upload.saveFileSpring(multipartFile, upDir); // ���� ����
            
            if (Tool.isImage(fname)) { // �̹������� �˻�
              thumb = Tool.preview(upDir, fupname, 120, 80); // thumb �̹��� ����
            }
          }
          AttachfileVO vo = new AttachfileVO();
          vo.setIqynum(iqynum);
          vo.setFname(fname);
          vo.setFupname(fupname);
          vo.setThumb(thumb);
          vo.setFsize(fsize);
          
          upload_count = upload_count + attachfileProc.create(vo); // ���� 1�� ��� ���� dbms ����
        }
      }    
      // -----------------------------------------------------
      // ���� ���� �ڵ� ����
      // -----------------------------------------------------
      
      
      mav.addObject("count", count);
      mav.setViewName("/oneinquiry/create_msg");
    }
    System.out.println("file size: " + oneinquiryVO.getFnamesMF().size());
    System.out.println("oneinquiryVO.toString() =   " + oneinquiryVO.toString());

    return mav;
  }
  
  // http://localhost:9090/team6/oneinquiry/oneinquiry_list.do?memno=1
  /*@RequestMapping(value="/oneinquiry/oneinquiry_list.do", method=RequestMethod.GET)
  public ModelAndView list(int memno) {
    ModelAndView mav = new ModelAndView();
    
    List<OneinquiryVO> list = oneinquiryProc.list_by_memno(memno);
    mav.addObject("list", list);
    mav.setViewName("/oneinquiry/oneinquiry_list");
    return mav;
  }*/
  
  @ResponseBody
  @RequestMapping(value="/oneinquiry/list_a.do", method=RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public String list_a(int memno) {
    List<OneinquiryVO> list = oneinquiryProc.list_by_memno(memno);
    
    JSONObject obj = new JSONObject();
    obj.put("list", list);
    return obj.toString();
  }
  
  @RequestMapping(value="/oneinquiry/read.do", method=RequestMethod.GET)
  public ModelAndView read(int iqynum) {
    ModelAndView mav = new ModelAndView();
    
    OneinquiryVO oneinquiryVO = oneinquiryProc.read(iqynum);
    mav.addObject("oneinquiryVO", oneinquiryVO);
    
    List<AttachfileVO> attachfile_list = attachfileProc.list_by_iqynum(iqynum);
    mav.addObject("attachfile_list", attachfile_list);
    
    AnswerVO answerVO = answerProc.readByIqynum(iqynum);
    mav.addObject("answerVO", answerVO);
    mav.setViewName("/oneinquiry/read");
    return mav;
  }
  
  //http://localhost:9090/ojt/contents/update.do?memberno=1&categrpno=1
  @RequestMapping(value="/oneinquiry/update.do", method=RequestMethod.GET)
  public ModelAndView update(int iqynum) {
    ModelAndView mav = new ModelAndView();

    OneinquiryVO oneinquiryVO = oneinquiryProc.read(iqynum);
    mav.addObject("oneinquiryVO", oneinquiryVO);
    mav.setViewName("/oneinquiry/update");
    return mav;
  }
 
  /**
   * ���� ó��
   * @param contentsVO
   * @return
   */
  @RequestMapping(value="/oneinquiry/update.do", method=RequestMethod.POST)
  public ModelAndView update(RedirectAttributes ra, OneinquiryVO oneinquiryVO) {
    ModelAndView mav = new ModelAndView();
    
    int count = oneinquiryProc.update(oneinquiryVO);
   
    ra.addAttribute("count", count);
    ra.addAttribute("iqynum", oneinquiryVO.getIqynum());
    ra.addAttribute("memno", oneinquiryVO.getMemno());
   
    mav.setViewName("redirect:/oneinquiry/update_msg.jsp");
    return mav;
  }
  
  //������
  @RequestMapping(value="/oneinquiry/delete.do", method=RequestMethod.GET)
  public ModelAndView delete(int iqynum) {
    ModelAndView mav = new ModelAndView();
    
    OneinquiryVO oneinquiryVO = oneinquiryProc.read(iqynum);
    mav.addObject("oneinquiryVO", oneinquiryVO);
    mav.setViewName("/oneinquiry/delete");
    //System.out.println("delete iqynum=" + iqynum);

    // mav.setViewName("redirect:/categrp/create_msg.jsp?count=" + count);
       
    return mav;
  }
 
  // ���� ó��
  @RequestMapping(value="/oneinquiry/delete.do", method=RequestMethod.POST)
  public ModelAndView delete_proc(int iqynum) {
    ModelAndView mav = new ModelAndView();
   
    int atcf_count = attachfileProc.delete_by_iqynum(iqynum);
    int oiq_count = 0;
    if (atcf_count >= 0 ) {
      oiq_count = oneinquiryProc.delete(iqynum);
    }
    mav.addObject("oiq_count", oiq_count);
    mav.setViewName("/oneinquiry/delete_msg");
                              
    return mav;
  }
  
  /**
   * ÷�� ���� 1�� ���� ��
   * 
   * @param contentsno
   * @return
   */
  @RequestMapping(value = "/oneinquiry/file_delete.do", 
                             method = RequestMethod.GET)
  public ModelAndView file_delete(int iqynum) {
    ModelAndView mav = new ModelAndView();

    OneinquiryVO oneinquiryVO = oneinquiryProc.read(iqynum);
    mav.addObject("oneinquiryVO", oneinquiryVO);
    
    List<AttachfileVO> attachfile_list = attachfileProc.list_by_iqynum(iqynum);
    mav.addObject("attachfile_list", attachfile_list);
    
    mav.setViewName("/oneinquiry/file_delete"); // file_delete.jsp

    return mav;
  }
  
  /**
   * ÷�� ���� 1�� ���� ó��
   * 
   * @param contentsno
   * @return
   */
  @RequestMapping(value = "/oneinquiry/file_delete_proc.do", 
                             method = RequestMethod.GET)
  public ModelAndView file_delete_proc(int iqynum, int attachfileno) {
    ModelAndView mav = new ModelAndView();

    OneinquiryVO oneinquiryVO = oneinquiryProc.read(iqynum);
    mav.addObject("oneinquiryVO", oneinquiryVO);

    // 1���� ���� ����
    attachfileProc.delete(attachfileno);
    
    List<AttachfileVO> attachfile_list = attachfileProc.list_by_iqynum(iqynum);
    mav.addObject("attachfile_list", attachfile_list);
    
    mav.setViewName("/oneinquiry/file_delete"); // file_delete.jsp

    return mav;
  }
  
  /*@RequestMapping(value="/mypage/mypage_main.do", method=RequestMethod.GET)
  public ModelAndView mypage(int memno) {
    ModelAndView mav = new ModelAndView();
    
    List<OneinquiryVO> list = oneinquiryProc.list_by_memno(memno);
    mav.addObject("list", list);
    mav.setViewName("/mypage/mypage_main");
    return mav;
  }*/
  
  @RequestMapping(value="/mypage/mypage_main.do", method=RequestMethod.GET)
  public ModelAndView mypage(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    int memno = (int) session.getAttribute("memno");
    List<OneinquiryVO> list = oneinquiryProc.list_by_memno(memno);
    mav.addObject("list", list);
    mav.setViewName("/mypage/mypage_main");
    return mav;
  }
  
  @RequestMapping(value="/oneinquiry/oneinquiry_list.do", method=RequestMethod.GET)
  public ModelAndView list_by_memno_paging(
      @RequestParam(value="memno", defaultValue="1") int memno,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage) {
    
    //System.out.println("--> nowPage: " + nowPage);
    
    ModelAndView mav = new ModelAndView();
    // /contents/list_by_categrpno_search_paging.jsp
    mav.setViewName("/oneinquiry/oneinquiry_list");   
    
    // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("memno", memno); // #{memno}
    map.put("nowPage", nowPage);       
    
    // �˻� ���
    List<OneinquiryVO> list = oneinquiryProc.list_by_memno_paging(map);
    mav.addObject("list", list);
    
    // �˻��� ���ڵ� ����
    int search_count = oneinquiryProc.search_count(map);
    mav.addObject("search_count", search_count);
    
    OneinquiryVO oneinquiryVO = oneinquiryProc.read(memno);
    mav.addObject("oneinquiryVO", oneinquiryVO);
  
    /*
     * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
     * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
     * 
     * @param listFile ��� ���ϸ� 
     * @param categrpno ī�װ���ȣ 
     * @param search_count �˻�(��ü) ���ڵ�� 
     * @param nowPage     ���� ������
     * @param word �˻���
     * @return ����¡ ���� ���ڿ�
     */ 
    String paging = oneinquiryProc.pagingBox("oneinquiry_list.do", memno, search_count, nowPage);
    mav.addObject("paging", paging);
  
    mav.addObject("nowPage", nowPage);
    
    return mav;
  }
  
  @RequestMapping(value="/mypage/main_post/rec_inq.do", method=RequestMethod.GET)
  public ModelAndView recent_inq(int memno, int cnt) {
    ModelAndView mav = new ModelAndView();
    
    List<OneinquiryVO> list = oneinquiryProc.list_by_memno(memno);
    mav.addObject("list", list);
    mav.addObject("cnt", cnt);
    mav.setViewName("/mypage/main_post/rec_inq");
    return mav;
  }

}
