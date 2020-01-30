package coder.TM65.daos;


import coder.TM65.models.Tables;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableDao extends JpaRepository<Tables, Integer> {
}
