package QA.model;

import java.util.*;

public interface QADAO_interface {
          public void insert(QAVO qaVO);
          public void update(QAVO qaVO);
          public void delete(String qano);
          public QAVO findByPrimaryKey(String qano);
          public List<QAVO> getAll();
}
