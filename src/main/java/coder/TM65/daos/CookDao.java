package coder.TM65.daos;

import coder.TM65.models.Cook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CookDao extends JpaRepository<Cook,Integer> {
}
