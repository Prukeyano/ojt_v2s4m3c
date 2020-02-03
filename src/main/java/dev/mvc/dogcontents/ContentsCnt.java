package dev.mvc.dogcontents;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.categrp.CategrpProcInter;
import dev.mvc.productfile.ProductfileProcInter;
import dev.mvc.productfile.ProductfileVO;
import dev.mvc.repository.RepositoryProcInter;
import nation.web.tool.Tool;
import nation.web.tool.Upload;

@Controller
public class ContentsCnt {

  @Qualifier("dev.mvc.dogcontents.ContentsProc")
  @Autowired
  ContentsProcInter contentsProc;
  

  @Qualifier("dev.mvc.categrp.CategrpProc")
  @Autowired
  CategrpProcInter categrpProc;
  
  @Autowired
  @Qualifier("dev.mvc.productfile.ProductfileProc") // �̸� ����
  private ProductfileProcInter productfileProc;
  
  @Autowired
  @Qualifier("dev.mvc.repository.RepositoryProc") // �̸� ����
  private RepositoryProcInter repositoryProc;
  
  @RequestMapping(value = "/contents/list.do", method = RequestMethod.GET)
  public ModelAndView list_by_categrpno(int categrpno) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("list",contentsProc.list(categrpno));
    System.out.println(contentsProc.list(categrpno).toString());
    mav.setViewName("contents/list");
    return mav;
  }
  
  /**
   * �۾��� ��� �̵�
   * @return
   */
  @RequestMapping(value="/contents/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.addObject("select_tag",categrpProc.tag_select2());
    mav.setViewName("contents/create");
    return mav;
  }
  
  /**
   * �� ���� DB�� ����
   * @param contentsVO
   * @param request
   * @return
   */
  @RequestMapping(value="/contents/create.do", method=RequestMethod.POST)
  public ModelAndView create(ContentsVO contentsVO,HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    //  ��ǰ ����
    contentsProc.create(contentsVO);
    // ī�װ� cnt ����
    categrpProc.cnt_up_update(contentsVO.getCategrpno());
    int productno =  contentsProc.select_productno(); //������ �� ��ȣ ��������
    String upDir = Tool.getRealPath(request, "/contents/storage");// ���� ���� ��ġ
    // ���� DB�� ������ϰ� �ϴ� ���� �и��ؼ� �ø���
    
    // ============================================
    // ���� ���� �ڵ� ����
    // ============================================
    // ������ �����´�.
    List<MultipartFile> fnamesMF = contentsVO.getFnamesMF();
    if(fnamesMF.get(0).getSize() > 0) {
      System.out.println("=================================== �̹��� ���� ========================");
      String fname = ""; //�������� �̸�
      String fupname = ""; // ���ε� �� ���� �̸�
      long fsize = 0; // ���ε� �� ���� ������
      
      int fnamesMF_count = fnamesMF.size(); // ���� ���� ����
      // �Ϲ� ����
      if (fnamesMF_count > 0) {
        for (MultipartFile multipartFile : fnamesMF) { // ���� ����
          fsize = multipartFile.getSize(); // ���� ũ��
          if (fsize > 0) { // ���� ũ�� üũ
            fname = multipartFile.getOriginalFilename();
            fupname = Upload.saveFileSpring(multipartFile, upDir); // ���� ����
          }
          ProductfileVO fnamesMFVO = new ProductfileVO();
          fnamesMFVO.setFname(fname);// ���� ���� �̸�
          fnamesMFVO.setFupname(fupname); // ���ε� �� ���� �̸�
          fnamesMFVO.setFsize(fsize); // ���ε� �� ���� ������
          fnamesMFVO.setProductno(productno); // ��ǰ ��ȣ
          productfileProc.create(fnamesMFVO); // DB�� ���� ���
        }
      }
    }
    
    MultipartFile fthum = contentsVO.getFthum();
    // ����� ����
    if(fthum.getSize() > 0) {
      System.out.println("=================================== ����� ���� ========================");
       
        String fupname ="";
       
        //������� ������ ���� �� �� ���� �� �о�ͼ� ������� �����.
        fupname = Upload.saveFileSpring(fthum, upDir); // ���� ����
        String thum_image = Tool.preview(upDir,fupname, 200, 300);// ����� ����
        
        if(thum_image != null) {// ������� ���� ��ٸ�
          Tool.deleteFile(upDir,fupname);// �������� ����
          String fname= contentsVO.getFthum().getOriginalFilename();
          long size = contentsVO.getFthum().getSize();
          
          ProductfileVO fthumVO = new ProductfileVO();
          fthumVO.setFname(fname);
          fthumVO.setFsize(size);
          fthumVO.setThumb(thum_image);
          fthumVO.setProductno(productno); // ��ǰ ��ȣ
          productfileProc.create(fthumVO);
        }else {
          Tool.deleteFile(upDir,fupname);// �������� ����
        }
    }
    // ============================================
    // ���� ���� �ڵ� ����
    // ============================================
    
    mav.setViewName("redirect:/contents/list_all.do?PageNumber=&col=&categrpno=&search=&keyword=");
    return mav;
  }
  
  @RequestMapping(value="/contents/list_all.do", method=RequestMethod.GET)
  public ModelAndView list_all(@RequestParam(defaultValue = "1")  int PageNumber,@RequestParam("col") String col, 
		  @RequestParam(defaultValue = "0") int categrpno , @RequestParam("search") String search,
		  @RequestParam("keyword") String keyword) {
    ModelAndView mav = new ModelAndView();
    
    System.out.println("PageNuber  ================ "  + PageNumber );
    System.out.println("col  ================ "  + col );
    System.out.println("categrpno  ================ "  + categrpno );
    System.out.println("search  ================ "  + search );
    System.out.println("keyword  ================ "  + keyword );
    HashMap<String , Object> map = new HashMap<>();
    int EndPageNumber = PageNumber * 5; 
    int StartPageNumber = EndPageNumber - 4;
    map.put("StartPageNumber", StartPageNumber);
    map.put("EndPageNumber", EndPageNumber);
    map.put("categrpno", categrpno);
    if(!(col.equals(""))) {
    	map.put("col", col);
    }
    if(!(search.equals(""))) {
    	map.put("search", search);
    }
    if( !(keyword.equals(""))) {
    	map.put("keyword", keyword);
    }

    mav.addObject("list",contentsProc.list_all(map)); 
    mav.addObject("total_count",contentsProc.count(map));
    mav.addObject("PageNumber",PageNumber);
    mav.addObject("col",col);
    mav.addObject("categrpno",categrpno);
    mav.addObject("search",search);
    mav.addObject("keyword",keyword);
    
    mav.setViewName("contents/list");
    return mav;
  }
  
  @ResponseBody
  @RequestMapping(value="/contents/repository_select.do", 
  method=RequestMethod.GET,
  produces = "text/plain;charset=UTF-8")
  public String repository_select(int categrpno) {
    JSONObject obj = new JSONObject();
    obj.put("repositoy_select", categrpProc.repository_select(categrpno));
    return obj.toString();
  }
  
  /**
   * ��ǰ �ҷ�����
   * @param productno
   * @return
   */
  @RequestMapping(value="/contents/read.do", method=RequestMethod.GET)
  public ModelAndView read(int productno) {
    ModelAndView mav = new ModelAndView();
    contentsProc.cnt_update(productno);
    mav.addObject("contentsVO",contentsProc.read(productno));// ��ǰ �ҷ�����
    mav.setViewName("contents/read");
    return mav;
  }
  
  /**
   * ��ǰ ���� �� �ҷ�����
   * @param productno
   * @return
   */
  @RequestMapping(value="/contents/update.do", method=RequestMethod.GET)
  public ModelAndView update(int productno) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("contentsVO",contentsProc.update_read(productno));
    mav.addObject("select_tag",categrpProc.tag_select2());
    mav.setViewName("contents/update");
    return mav;
  }
  /**
   * ��ǰ ���� 
   * @param productno
   * @return
   */
  @RequestMapping(value="/contents/update.do", method=RequestMethod.POST)
  public ModelAndView update(ContentsVO contentsVO) {
    ModelAndView mav = new ModelAndView();
    
    HashMap<Object,Object> map = new HashMap<Object,Object>();
    System.out.println("contentsVO ===============================" + contentsVO.getCategrpno());
    System.out.println("contentsVO ===============================" + contentsVO.getProductno());
    System.out.println("contentsVO ===============================" + contentsVO.getDeliveryCharge());
    
    // ���� �� ī�װ� ��ȣ
    int befor_categrpno = contentsProc.read(contentsVO.getProductno()).getCategrpno();
    // ���� �� ī�װ� ��ȣ
    int afterno_categrpno = contentsVO.getCategrpno();
    int product_update_count = 0;
    
    System.out.println("���� �� ī�װ� ��ȣ == >" + befor_categrpno);
    System.out.println("���� �� ī�װ� ��ȣ == >" + afterno_categrpno);
    // ī�װ� ����
    if(befor_categrpno != afterno_categrpno) {
      System.out.println("================================ ���� ");
      product_update_count = contentsProc.update(contentsVO);
      if(product_update_count > 0) {
          map.put("content_update_count", product_update_count);
          map.put("afterno_categrpno", afterno_categrpno);
          categrpProc.cnt_up_update_categrp(map);
          map.put("befor_categrpno", befor_categrpno);
          categrpProc.cnt_down_update_categrp(map);
      }
    }else {
      // ī�װ� ������ ������
      product_update_count = contentsProc.update(contentsVO);
    }
    
    mav.addObject("count",product_update_count);
    mav.addObject("productno",contentsVO.getProductno());
    mav.setViewName("contents/update_msg");
    return mav;
  }
  
  
  /**
   * ��ǰ ����
   * @param productno
   * @return 
   */
  @RequestMapping(value="/contents/delete.do", method=RequestMethod.GET)
  public ModelAndView delete(int productno) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("contentsVO",contentsProc.read(productno));
    mav.setViewName("contents/delete");
    return mav;
  }
  
  /**
   * ��ǰ ����
   * @param productno
   * @return 
   */
  @RequestMapping(value="/contents/delete.do", method=RequestMethod.POST)
  public ModelAndView delete_proc(int productno,int categrpno,HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    
    // ��ü ���� ��������
    List<ProductfileVO> file_list = productfileProc.file_all_read(productno);
    String upDir = Tool.getRealPath(request, "/contents/storage");// ���� ���� ��ġ
    for(ProductfileVO vo : file_list) {
      if(vo.getThumb() == null) {
        Tool.deleteFile(upDir, vo.getFupname());
      }else {
        Tool.deleteFile(upDir, vo.getThumb());
      }
    }
    
    categrpProc.cnt_down_update(categrpno);
    int count = contentsProc.delete(productno);
    mav.addObject("count",count);
    mav.setViewName("contents/delete_msg");
    return mav;
  }
  
  @RequestMapping(value="/contents/images_load.do", method=RequestMethod.GET)
  public ModelAndView imagesLoad(int productno){
    ModelAndView mav = new ModelAndView();
    // ���� �� ��ǰ �̹��� ȣ��
    int images_count = contentsProc.images_cnt(productno);
    mav.addObject("image_list",contentsProc.images_load(productno));
    mav.addObject("images_count",images_count);
    mav.addObject("productno",productno);
    mav.setViewName("contents/images_update");
    return mav;
  }
  
  @ResponseBody
  @RequestMapping(value="/contents/images_delete.do", method=RequestMethod.GET,
      produces = "text/plain;charset=UTF-8")
  public String images_delete(String fupname,int productno,HttpServletRequest request){
    String dir = request.getServletContext().getRealPath("/contents/storage");
    Tool.deleteFile(dir, fupname);// �������� ����
    contentsProc.images_delete(fupname); // ��񿡼� ����
    
    JSONObject obj = new JSONObject();
    obj.put("images_count", contentsProc.images_cnt(productno));// ������ �� ����
    return obj.toString();
  }
  
  @ResponseBody
  @RequestMapping(value="/contents/thum_delete.do", method=RequestMethod.GET,
      produces = "text/plain;charset=UTF-8")
  public String thum_delete(String thumb,int productno,HttpServletRequest request){
    //����� ����
    String dir = request.getServletContext().getRealPath("/contents/storage");
    Tool.deleteFile(dir, thumb);// �������� ����
    contentsProc.thum_delete(productno);
    JSONObject obj = new JSONObject();
    return obj.toString();
  }
  
  @RequestMapping(value="/contents/file_update.do", method=RequestMethod.POST)
  public ModelAndView file_update(ContentsVO contentsVO,HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    
    // ============================================
    // ���� ���� �ڵ� ����
    // ============================================
    int productno =  contentsVO.getProductno();// �� ��ȣ ��������
    String upDir = Tool.getRealPath(request, "/contents/storage");// ���� ���� ��ġ
    // ���� DB�� ������ϰ� �ϴ� ���� �и��ؼ� �ø���
    
    // ============================================
    // ���� ���� �ڵ� ����
    // ============================================
    // ������ �����´�.
    List<MultipartFile> fnamesMF = contentsVO.getFnamesMF();
    if(fnamesMF.get(0).getSize() > 0) {
      System.out.println("=================================== ������Ʈ �̹��� ���� ========================");
      String fname = ""; //�������� �̸�
      String fupname = ""; // ���ε� �� ���� �̸�
      long fsize = 0; // ���ε� �� ���� ������
      
      int fnamesMF_count = fnamesMF.size(); // ���� ���� ����
      // �Ϲ� ����
      if (fnamesMF_count > 0) {
        for (MultipartFile multipartFile : fnamesMF) { // ���� ����
          fsize = multipartFile.getSize(); // ���� ũ��
          if (fsize > 0) { // ���� ũ�� üũ
            fname = multipartFile.getOriginalFilename();
            fupname = Upload.saveFileSpring(multipartFile, upDir); // ���� ����
          }
          ProductfileVO fnamesMFVO = new ProductfileVO();
          fnamesMFVO.setFname(fname);// ���� ���� �̸�
          fnamesMFVO.setFupname(fupname); // ���ε� �� ���� �̸�
          fnamesMFVO.setFsize(fsize); // ���ε� �� ���� ������
          fnamesMFVO.setProductno(productno); // ��ǰ ��ȣ
          productfileProc.create(fnamesMFVO); // DB�� ���� ���
        }
      }
    }
    
    MultipartFile fthum = contentsVO.getFthum();
    // ����� ����
    if(fthum.getSize() > 0) {
      System.out.println("=================================== ������Ʈ ����� ���� ========================");
      
        // ���� ����� ����
        Tool.deleteFile(upDir,contentsProc.thum_select(productno).getThumb());// �������� ����
        contentsProc.thum_delete(productno);
        String fupname ="";
       
        //������� ������ ���� �� �� ���� �� �о�ͼ� ������� �����.
        fupname = Upload.saveFileSpring(fthum, upDir); // ���� ����
        String thum_image = Tool.preview(upDir,fupname, 200, 300);// ����� ����
        
        if(thum_image != null) {// ������� ���� ��ٸ�
          Tool.deleteFile(upDir,fupname);// �������� ����
          String fname= contentsVO.getFthum().getOriginalFilename();
          long size = contentsVO.getFthum().getSize();
          
          ProductfileVO fthumVO = new ProductfileVO();
          fthumVO.setFname(fname);
          fthumVO.setFsize(size);
          fthumVO.setThumb(thum_image);
          fthumVO.setProductno(productno); // ��ǰ ��ȣ
          productfileProc.create(fthumVO);
        }else {
          Tool.deleteFile(upDir,fupname);// �������� ����
        }
    }
    // ============================================
    // ���� ���� �ڵ� ����
    // ============================================
    mav.setViewName("redirect:/contents/list_all.do?PageNumber=&col=&categrpno=&search=&keyword=");
    return mav;
  }
  
}
