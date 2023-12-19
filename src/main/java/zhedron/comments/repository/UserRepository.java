package zhedron.comments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zhedron.comments.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
