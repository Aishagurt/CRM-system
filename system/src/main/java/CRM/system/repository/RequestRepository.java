package CRM.system.repository;

import CRM.system.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;



@Repository

@Transactional

public interface RequestRepository extends JpaRepository<Request, Long> {

}
