package dev.mvc.actor;

import java.util.HashMap;
import java.util.List;


public interface ActorDAOInter {
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
   * actor�� �˻��� ���ڵ� ����
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
   * �Ѱ��� ���ڵ� ��ȸ
   * <select id="read" resultType="ActorVO" parameterType="int">
   * @param actorno �� ��ȣ
   * @return
   */
  public ActorVO read(int actorno);

  /**
   * ����ó��
   * @param vo
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
   * @param actorno
   * @return
   */
/*  public List<ActorVO> list_by_actor_search(HashMap hashMap);*/
  
}
  

