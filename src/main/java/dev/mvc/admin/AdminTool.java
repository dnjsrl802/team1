package dev.mvc.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 
 * ȸ�� ���� DAO class
 * <pre>
 * ������Ʈ��     : (��)�ֵ���ũ IT �������� JAVA CBD Project 1��
 * PMO, PM      : ���� �Ʒñ��� 
 * ��Ű����        : nation.web.admin4
 * ���ϸ�           : AdminTool.java 2018. 12. 12.
 * �ۼ���           : �ڽ��� ���� �Ǵ� ����
 * �ۼ��� email   : zz@zzz.com
 * ��������
 * ------------------------------------------------------------------
 * ���� �̷�
 * ------------------------------------------------------------------ 
 * ������        ������  ����ó               ���� ����
 * ------------------------------------------------------------------ 
 * 
 * ------------------------------------------------------------------
 * 
 *</pre>
 */
public class AdminTool {
  /**
   * ������ �������� �˻��մϴ�.
   * @param request
   * @return true or false
   */
  public static synchronized boolean isAdmin(HttpServletRequest request){
  //  public static synchronized boolean isMember(HttpServletRequest request){
    boolean sw = false;
    
    HttpSession session = request.getSession(); // java class���� session ��ü ����
    String act = (String)session.getAttribute("act"); // ���� ����
    
    if (act != null){
      if (act.equals("M") || act.equals("Y")){ // �α��� ����
        sw = true;
      }
    }
    return sw;
  }

  /**
   * ������ �������� �˻��մϴ�.
   * @param request
   * @return true: ������ ����
   */
  public static synchronized boolean isMaster(HttpServletRequest request){
    boolean sw = false;
    
    HttpSession session = request.getSession();
    String act = (String)session.getAttribute("act"); // ��Ȱ
    // System.out.println("--> Tool.java act: " + act);
    
    if (act != null){
      if (act.equals("M")){ // ���������� �˻�
        sw = true;
      }
    }
    return sw;
  }
  
}

 