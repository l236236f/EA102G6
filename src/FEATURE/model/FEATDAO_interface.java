package FEATURE.model;

import java.util.*;

public interface FEATDAO_interface {
          public void insert(FEATVO featVO);
          public void update(FEATVO featVO);
          public void delete(String featno);
          public FEATVO findByPrimaryKey(String featno);
          public List<FEATVO> getAll();
}
