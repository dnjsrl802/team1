1. ��ϵ� ����� ���
    SELECT username, user_id  
    FROM dba_users 
    ORDER BY username ASC;
 
       USERNAME                                USER_ID
       --------------------------------------- ----------
       OUTLN                                        9
       SOLDESK                                     48
       SYS                                             0
       SYSTEM                                       5
       XDB                                            34
       XS$NULL                                     2147483638
 
 
2. SOLDESK������ �⺻������ �����ϳ� �����ϸ� SOLDESK + IP�� �����Ͽ�
   ������ ����ϴٴ�. 1234�� �н������Դϴ�.
   ��) �Ʒñ���: SOLDESK
        �Ʒû�: SOLDESK1, SOLDESK2, SOLDESK3...
 
   CREATE USER movie IDENTIFIED BY 1234;
 
 
3. ���� �ο�
    GRANT CONNECT, RESOURCE to movie;
    GRANT CREATE VIEW TO movie;
 
 
4. ����� ���� 
   DROP USER movie CASCADE; 
  
5. �н����� ����
   ALTER USER movie IDENTIFIED BY 1234;
 
 
   
[����] ���� ���� ����������� ����� ����
        �Ϲ� ������ �������� Ǫ�� ���: system���� �α����Ͽ� ����  
        ALTER USER movie ACCOUNT UNLOCK;  
 
 
 
[����] �������� system ������ Lock ��� ���
         - 'Run SQL Command Line' ����
        SQL> connect
        Enter user-name: sys as SYSDBA
        Enter password: 1234 or oracle
        Connected.
 
        SQL> ALTER USER system ACCOUNT UNLOCK;
        User altered.