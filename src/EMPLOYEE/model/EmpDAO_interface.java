package EMPLOYEE.model;

import java.util.*;

public interface EmpDAO_interface {
          public void insert(EmpVO empVO);
          public void update(EmpVO empVO);
          public void delete(String empno);
          public EmpVO findByPrimaryKey(String empno);
          public List<EmpVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
		public EmpVO findByPrimaryKeyLogin(String empid);
		public String checkid(String empid);
		public EmpVO checkOnthejob(String empid);
}
