package AUTHORITY.model;

import java.util.*;

public interface AUTHODAO_interface {
          public void insert(AUTHOVO authoVO);
          public void delete(String featno, String empno);
          public void deleteByEmpno(String empno);
          public List<AUTHOVO> findByFeatno(String featno);
          public List<AUTHOVO> findByEmpno(String empno);
          public List<AUTHOVO> getAll();
	
		

}
