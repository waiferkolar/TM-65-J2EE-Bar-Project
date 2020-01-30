package coder.TM65.daos;

import coder.TM65.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CatDao extends JpaRepository<Category,Integer> {

}
