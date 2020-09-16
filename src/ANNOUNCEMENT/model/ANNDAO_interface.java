package ANNOUNCEMENT.model;

import java.util.*;

public interface ANNDAO_interface {
          public void insert(ANNVO annVO);
          public void update(ANNVO annVO);
          public void delete(String annno);
          public ANNVO findByPrimaryKey(String annno);
          public List<ANNVO> getAll();
}
