package dev.mvc.admin;

import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AdminCont {
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc")
  private AdminProcInter adminProc = null;

  public AdminCont() {
    System.out.println("-->AdminCont created.");
  }
  // http://localhost:9090/movie/admin/create.do
  @RequestMapping(value="/admin/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/admin/create"); // /webapp/admin/create.jsp
    
    return mav;
  }
  // request.getParameter() �ڵ� ����
  // ����ȯ �ڵ� ����
  // CategrpVO ��ü �ڵ� ����
  // http://localhost:9090/ojt/categrp/create.do
  @RequestMapping(value="/admin/create.do", method=RequestMethod.POST)
  public ModelAndView create(AdminVO adminVO, Locale locale) {
    ModelAndView mav = new ModelAndView();
    
    int count = adminProc.create(adminVO);
    // mav.setViewName("redirect:/categrp/create_message.jsp?count=" + count); // /webapp/categrp/create_message.jsp
    // mav.setViewName("redirect:/categrp/list.do"); // /webapp/categrp/list.jsp
    
/*    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    if (count == 1) {
      msgs.add(messages.getMessage("categrp.create.success", categrpVO.getName(), locale));
      links.add("<button type='button' onclick=\"location.href='./list.do'\">"+messages.getMessage("categrp.button.create", locale)+"</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">"+messages.getMessage("categrp.button.list", categrpVO.getName(),  locale)+"</button>");      
      // mav.setViewName("redirect:/categrp/list.do");
    } else {
      msgs.add(messages.getMessage("categrp.create.fail", locale));
      links.add("<button type='button' onclick=\"location.href='./list.do'\">"+messages.getMessage("categrp.button.retry", locale)+"</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">"+messages.getMessage("categrp.button.list", categrpVO.getName(),  locale)+"</button>");      

    }
    
    mav.addObject("msgs", msgs);
    // request.setAttribute("msgs", msgs);
    mav.addObject("links", links);
    
    mav.setViewName("/categrp/message"); // /categrp/message.jsp
*/    
    
    mav.addObject("count", count);
    mav.addObject("name", adminVO.getName());
   
    
    return mav;
  }
  // http://localhost:9090/movie/admin/list.do
  @RequestMapping(value="/admin/list.do", method=RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    
    List<AdminVO> list = adminProc.list();
    mav.addObject("list", list);
    mav.setViewName("/admin/list"); // /webapp/admin/list.jsp
    
    return mav;
  }
  
  // http://localhost:9090/ojt/categrp/update.do?categrpno=1
  //  �Ϲ� �ؽ�Ʈ ���
  @ResponseBody
  @RequestMapping(value="/admin/update.do", 
                             method=RequestMethod.GET,
                             produces="text/plain;charset=UTF-8")
  public ResponseEntity update(int adminno) {
    HttpHeaders responseHeaders = new HttpHeaders();
    AdminVO adminVO = adminProc.read(adminno);
    
    JSONObject json = new JSONObject();
    json.put("id", adminVO.getId());

    
    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
  
  // request.getParameter() �ڵ� ����
  // ����ȯ �ڵ� ����
  // CategrpVO ��ü  �ڵ� ����
  // http://localhost:9090/movie/admin/update.do -> ���� �ּ�
  @RequestMapping(value="/admin/update.do", method=RequestMethod.POST)
  public ModelAndView update(AdminVO adminVO) {
    ModelAndView mav = new ModelAndView();
    
    int count = adminProc.update(adminVO);
   // mav.setViewName("redirect:/admin/create_message.jsp?count=" + count); // /webapp/categrp/create_message.jsp �� ����
    mav.setViewName("redirect:/admin/list.do"); // /webapp/admin/list.jsp
    
    return mav;
  }
  
  /**
   * ���� ��
   * http://localhost:9090/movie/admin/delete.do?adminno=1
   * @param adminno
   * @return
   */
//http://localhost:9090/ojt/categrp/delete.do?categrpno=1
 // {"categrpno":1,"name":"����2"}
 @ResponseBody
 @RequestMapping(value="/admin/delete.do", 
                            method=RequestMethod.GET,
                            produces="text/plain;charset=UTF-8")
 public ResponseEntity delete(int adminno) {
   HttpHeaders responseHeaders = new HttpHeaders();
   AdminVO adminVO = adminProc.read(adminno);
      
   JSONObject json = new JSONObject();
   json.put("adminno", adminVO.getAdminno());

       
   return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
 }
  
 // http://localhost:9090/ojt/categrp/delete.do -> ���� �ּ�
 @RequestMapping(value="/admin/delete.do", method=RequestMethod.POST)
 public ModelAndView delete_proc(int adminno) {
   ModelAndView mav = new ModelAndView();
   
   int count = adminProc.delete(adminno);
   // mav.setViewName("redirect:/categrp/delete_message.jsp?count=" + count); // /webapp/categrp/create_message.jsp �� ����
   mav.setViewName("redirect:/admin/list.do"); // /webapp/categrp/list.jsp
   
   return mav;
 }
}





















