package zhedron.comments.services;

import org.springframework.stereotype.Service;
import zhedron.comments.models.User;
import zhedron.comments.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void save (User user) {
        repository.save(user);
    }

    public List<User> findAll () {
        return repository.findAll();
    }

    public User findUser (int id) {
        return repository.findById(id).orElse(null);
    }

    public void delete (int id) {
        repository.deleteById(id);
    }
}
