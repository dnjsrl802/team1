package dev.mvc.actor;

import java.util.HashMap;
import java.util.List;







public interface ActorProcInter {
  
  /**
   * ���
   * @param actorVO
   * @return
   */
  public int create(ActorVO actorVO);
  
  /**
   * ���
   * @return
   */
/*  public List<ActorVO> list(HashMap hashMap);*/
  
  /**
   * category�� �˻��� ���ڵ� ����
   * @return
   */
  public int search_count(HashMap hashMap);
  
  
  /**
   * �˻� ��� + ����¡
   * @param hashMap
   * @return
   */
  public List<ActorVO> list(HashMap<String, Object> hashMap);
  
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param  
   * @param  
   * @param nowPage     ���� ������
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging(int search_count, int nowPage, String s_word);
  

  /**
   * �Ѱ��� ���ڵ� ��ȸ
   * <select id="read" resultType="ActorVO" parameterType="int">
   * @param actorno �� ��ȣ
   * @return
   */
  public ActorVO read(int actorno);

  /**
   * ������
   * @param actorno ��α� ��ȣ
   * @return
   */
  public int update(ActorVO actorVO);
  


  /**
   * ���� ó��
   * <delete id="delete" parameterType="int">
   * @param actorno
   * @return
   */
  public int delete(int actorno);
  
  /**
   * �˻� ���
   * @param categoryno
   * @return
   */
/*  public List<ActorVO> list_by_actor_search(HashMap hashMap);*/

}
