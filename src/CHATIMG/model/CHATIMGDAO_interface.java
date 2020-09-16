package CHATIMG.model;

import java.util.*;

public interface CHATIMGDAO_interface {
          public void insert(CHATIMGVO chatimgVO);
          public void update(CHATIMGVO chatimgVO);
          public void delete(String chatimgno);
          public CHATIMGVO findByPrimaryKey(String chatimgno);
          public List<CHATIMGVO> getAll();
}
