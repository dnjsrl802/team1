package dev.mvc.actor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import nation.web.tool.Tool;
import nation.web.tool.Upload;

@Controller
public class ActorCont {
  @Autowired
  @Qualifier("dev.mvc.actor.ActorProc")
  private ActorProcInter actorProc = null;
  
  public ActorCont() {
    System.out.println("--> ActorCont created");
  }
  // http://localhost:9090/movie/actor/create.do
  @RequestMapping(value="/actor/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
    System.out.println("--> create() GET excuted");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/actor/create"); // /webapp/admin/create.jsp
    
    
    return mav;
  }
  /**
   * ��� ó��
   * 
   * @param request
   * @param contentsVO
   * @return
   */
  @RequestMapping(value = "/actor/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, ActorVO actorVO) {
    System.out.println("--> create() POST executed");
    ModelAndView mav = new ModelAndView();
    //mav.setViewName("/actor/message");

    
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/actor/storage");
    MultipartFile filesMF = actorVO.getFilesMF();
    String files = ""; // �÷��� ������ ���ϸ�
    long sizes = filesMF.getSize();
    String thumbs = ""; // �÷��� ������ ���ϸ�
    
    //long count = filesMF.getSizes();  
    
    if (sizes > 0) {
      files = Upload.saveFileSpring(filesMF, upDir);

      if (Tool.isImage(files)) {
        thumbs = Tool.preview(upDir, files, 120, 80); // Thumb �̹��� ����
      }
    }
    actorVO.setFiles(files);
    actorVO.setSizes(sizes);
    actorVO.setThumbs(thumbs);
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------

    /*mav.setViewName(
        "redirect:/actor/create_message.jsp?count=" + count + "&actorno=" + actorVO.getActorno());*/ // /webapp/actor/create_message.jsp
    int count = actorProc.create(actorVO);
    
    mav.setViewName("redirect:/actor/create_message.jsp?count=" + count);
    
    
    
    return mav;
  }
  
/*  @RequestMapping(value = "/actor/list.do", method = RequestMethod.GET)
  public ModelAndView list(String s_word) {
    System.out.println("--> list() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/actor/list"); // webapp/actor/list.jsp


    // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    // hashMap.put("actorno", actorno);
    hashMap.put("s_word", s_word);
    
    // �˻� ���
    List<ActorVO> list = actorProc.list(hashMap);
    mav.addObject("list", list);



    ActorVO actorVO = actorProc.read(actorno);
    mav.addObject("actorVO", actorVO);
    
    return mav;
  }*/

  @RequestMapping(value = "/actor/list.do", method = RequestMethod.GET)
  public ModelAndView list(
      @RequestParam(value="s_word", defaultValue="") String s_word,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage
      ) {
    System.out.println("--> nowPage: " + nowPage);
    
    System.out.println("--> list() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/actor/list"); // webapp/actor/list.jsp


    // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    // hashMap.put("actorno", actorno); // #{categoryno}
    hashMap.put("s_word", s_word); // #{word}
    hashMap.put("nowPage", nowPage);
    
    
    // �˻� ���
    List<ActorVO> list = actorProc.list(hashMap);
    mav.addObject("list", list);

    int search_count = actorProc.search_count(hashMap);
    mav.addObject("search_count", search_count);
    
    String paging = actorProc.paging(search_count, nowPage, s_word);
    mav.addObject("paging", paging);
  
    mav.addObject("nowPage", nowPage);

   /* ActorVO actorVO = actorProc.read(actorno);
    mav.addObject("actorVO", actorVO);*/
    
    return mav;
  }

 // http://localhost:9090/movie/actor/read.do
 @RequestMapping(value = "/actor/read.do", method = RequestMethod.GET)
 public ModelAndView read(int actorno) {
   System.out.println("--> read() GET executed");
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/actor/read"); // /webapp/actor/read.jsp

   ActorVO actorVO = actorProc.read(actorno);
   mav.addObject("actorVO", actorVO);


   
   return mav;
 }
 
//http://localhost:9090/movie/actor/update.do
@RequestMapping(value = "/actor/update.do", method = RequestMethod.GET)
public ModelAndView update(int actorno) {
  System.out.println("--> update() GET executed");
  ModelAndView mav = new ModelAndView();
  mav.setViewName("/actor/update");
  

  ActorVO actorVO = actorProc.read(actorno);
  mav.addObject("actorVO", actorVO);


  return mav;
}
@RequestMapping(value="/actor/update.do", method=RequestMethod.POST)
public ModelAndView update(HttpServletRequest request, ActorVO actorVO){
  // System.out.println("--> update() POST called.");
  ModelAndView mav = new ModelAndView();
  mav.setViewName("/actor/message");
  
  // ---------------------------------------------------------------------------
  // ���� ����
  // ---------------------------------------------------------------------------
  String upDir = Tool.getRealPath(request, "/actor/storage");
  /*
  <input type="file" class="form-control input-lg" name='file1MF' id='file1MF' size='40' >
  ��
   name='file1MF'�� �ش��ϴ� �ʵ带 ã�Ƽ� File ��ü�� �ڵ����� �Ҵ�
  ��
  BlogVO.java: private MultipartFile file1MF;
  ��
   ���� ��ü ���: MultipartFile file1MF = blogVO.getFile1MF();          
   */
  MultipartFile filesMF = actorVO.getFilesMF();
  String files = "";                    // DBMS file1 �÷��� ��
  long sizes = filesMF.getSize(); // ���� ũ��
  String thumbs = "";                 // DBMS thumb �÷��� ��
  
  // ������ ��ϵ� �� ���� �ε�
  ActorVO actorVO_bf = actorProc.read(actorVO.getActorno());
  
  if (sizes > 0) { // ��ϵ� ������ �ִٸ�
    Tool.deleteFile(upDir, actorVO_bf.getFiles());    // ���� ���� ����
    Tool.deleteFile(upDir, actorVO_bf.getThumbs()); // ���� Thumb ���� ����
    
    files = Upload.saveFileSpring(filesMF, upDir); // �ű� ���� ���ε�
    
    if (Tool.isImage(files)) { // ���ο� ���� �̹������� �˻�
      thumbs = Tool.preview(upDir, files, 120, 80); // Thumb �̹��� ����
    } 
  } else {
    // ������ �������� �ʴ� ��� ���� ���� ���� ���
    files = actorVO_bf.getFiles();
    sizes = actorVO_bf.getSizes();
    thumbs = actorVO_bf.getThumbs();
  }
  actorVO.setFiles(files);
  actorVO.setSizes(sizes);
  actorVO.setThumbs(thumbs);
  // ---------------------------------------------------------------------------
  
  String root = request.getContextPath();
  int count = actorProc.update(actorVO);
  
  mav.setViewName("redirect:/actor/update_message.jsp?count=" + count);
  
  
  
  
  
  return mav;
}

//http://localhost:9090/blog/blog/delete.do
@RequestMapping(value = "/actor/delete.do", method = RequestMethod.GET)
public ModelAndView delete(int actorno) {
 System.out.println("--> delete() GET executed");
 ModelAndView mav = new ModelAndView();
 mav.setViewName("/actor/delete"); // /webapp/blog/delete.jsp

 ActorVO actorVO = actorProc.read(actorno);
 mav.addObject("actorVO", actorVO);
 
 return mav;
}

@RequestMapping(value="/actor/delete.do", method=RequestMethod.POST)
public ModelAndView delete(HttpServletRequest request, int actorno){
 // System.out.println("--> update() POST called.");
 ModelAndView mav = new ModelAndView();
 mav.setViewName("/actor/message");
 


 ActorVO actorVO = actorProc.read(actorno);
 
 // ---------------------------------------------------------------------------
 // ���� ����
 // ---------------------------------------------------------------------------
 String upDir = Tool.getRealPath(request, "/actor/storage");

 Tool.deleteFile(upDir, actorVO.getFiles());    // ���� ���� ����
 Tool.deleteFile(upDir, actorVO.getThumbs()); // ���� Thumb ���� ����
   
 // ---------------------------------------------------------------------------
 
 String root = request.getContextPath();
 int count = actorProc.delete(actorno);

 mav.setViewName("redirect:/actor/delete_message.jsp?count=" + count);
 
 
/* String root = request.getContextPath();
 if (actorProc.delete(actorno) == 1) {
   System.out.println("�����Ƕ�");
   msgs.add("���� �����߽��ϴ�.");

 } else {
   msgs.add("�� ������ �����߽��ϴ�.");
   System.out.println("�����Ƕ�");
   msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
   links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
   
 }
 links.add("<button type='button' onclick=\"location.href='./list.do?actorno="+actorVO.getActorno()+"'\">���</button>");

 
 mav.addObject("msgs", msgs);
 mav.addObject("links", links);*/
 
 return mav;
}

/**
 * �˻� ���
 * 
 * @param categoryno
 * @param word
 * @return
 */
/*@RequestMapping(value = "/actor/list_by_actor_search.do", method = RequestMethod.GET)
public ModelAndView list_by_category_search(int actorno, String s_word) {
  // System.out.println("--> list_by_category(int categoryno, String
  // word_find) GET called.");
  ModelAndView mav = new ModelAndView();
  // mav.setViewName("/contents/list_by_categoryno"); //
  // webapp/contents/list_by_categoryno.jsp

  // �˻� ��� �߰�, webapp/contents/list_by_category_search.jsp
  mav.setViewName("/actor/list_by_actor_search");

  // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
  HashMap<String, Object> hashMap = new HashMap<String, Object>();
  hashMap.put("actorno", actorno); // #{categoryno}
  hashMap.put("s_word", s_word); // #{word}

  // System.out.println("categoryno: " + categoryno);
  // System.out.println("word_find: " + word_find);

  // �˻� ���
  List<ActorVO> list = actorProc.list_by_actor_search(hashMap);
  mav.addObject("list", list);



  ActorVO actorVO = actorProc.read(actorno);
  mav.addObject("actorVO", actorVO);

  // mav.addObject("word", word);

  return mav;
}*/
}
