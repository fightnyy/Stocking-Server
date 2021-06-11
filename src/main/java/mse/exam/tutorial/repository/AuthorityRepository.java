package mse.exam.tutorial.repository;

import mse.exam.tutorial.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
