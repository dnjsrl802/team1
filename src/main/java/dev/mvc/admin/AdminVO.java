package dev.mvc.admin;


/*1. ���̺� ����
CREATE TABLE admin(
    adminno NUMBER(10) NOT NULL, -- ������ ��ȣ
    id VARCHAR(60) NOT NULL,         -- ���̵�
    passwd VARCHAR(10) NOT NULL,  -- ��� ��ȣ
    auth VARCHAR(30) NOT NULL,      -- ����Ű
    act CHAR(1) DEFAULT 'N' NOT NULL,   -- M: ������, Y: �α��� ����, N: �α��� �Ұ�, H: ���� ���, G: �մ�, C: Ż��
    mdate DATE NOT NULL,            -- ������
    tel VARCHAR(20) NOT NULL,     -- ��ȭ ��ȣ
    name VARCHAR(30) NOT NULL,  -- ����
  PRIMARY KEY(adminno)
);*/
public class AdminVO {
  private int adminno;
  private String id;
  private String passwd;
  private String auth;
  private String act;
  private String mdate;
  private String tel;
  private String name;
  
  public AdminVO(){
    
  }

  public int getAdminno() {
    return adminno;
  }

  public void setAdminno(int adminno) {
    this.adminno = adminno;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  public String getAuth() {
    return auth;
  }

  public void setAuth(String auth) {
    this.auth = auth;
  }

  public String getAct() {
    return act;
  }

  public void setAct(String act) {
    this.act = act;
  }

  public String getMdate() {
    return mdate;
  }

  public void setMdate(String mdate) {
    this.mdate = mdate;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
}













