package dev.mvc.admin;

import java.util.List;

public interface AdminDAOInter {
  /**
   * 등록
   * @param adminVO
   * @return
   */
    
  public abstract int create(AdminVO adminVO); 
  /**
   * 목록
   * @return
   */
  public List<AdminVO> list();
  /**
   * 한건의 레코드 조회
   * @param adminno
   * @return
   */
  public AdminVO read(int adminno);
  /**
   * 레코드 수정
   * @param adminVO
   * @return
   */
  public int update(AdminVO adminVO);
  /**
   * 한건의 레코드 삭제
   * @param adminno
   * @return
   */
  public int delete(int adminno);
}


















