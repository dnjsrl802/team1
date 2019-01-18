package dev.mvc.admin;

import java.util.List;

public interface AdminProcInter {
  /**
   * ���
   * @param adminVO
   * @return
   */  
  public abstract int create(AdminVO adminVO);
  /**
   * ���
   * @return
   */
  public List<AdminVO> list();
  /**
   * �Ѱ��� ���ڵ� ��ȸ
   * @param Adminno
   * @return
   */
  public AdminVO read(int adminno);
  /**
   * ���ڵ带 ����
   * @param adminVO
   * @return
   */
  public int update(AdminVO adminVO);
  /**
   * �Ѱ��� ���ڵ� ����
   * @param Adminno
   * @return
   */
  public int delete(int adminno);
}
