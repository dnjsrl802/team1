package dev.mvc.admin;

import java.util.List;

public interface AdminDAOInter {
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
   * @param adminno
   * @return
   */
  public AdminVO read(int adminno);
  /**
   * ���ڵ� ����
   * @param adminVO
   * @return
   */
  public int update(AdminVO adminVO);
  /**
   * �Ѱ��� ���ڵ� ����
   * @param adminno
   * @return
   */
  public int delete(int adminno);
}


















