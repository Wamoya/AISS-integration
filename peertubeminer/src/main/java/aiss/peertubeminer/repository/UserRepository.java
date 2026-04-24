package aiss.peertubeminer.repository;

import aiss.peertubeminer.model.peertube.Comment;
import aiss.peertubeminer.model.peertube.Picture;
import aiss.peertubeminer.model.peertube.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    List<User> users = new ArrayList<>();

    public UserRepository() {
//        users.add(
//                new User(
//                        1,
//                        "Hello!",
//                        "https://peertube.tv/accounts/user1",
//                        new ArrayList<Picture>(List.of())
//                )
//
//        );
////        users.add(
////
////                )
////        );
////        users.add(
////
////        );
    }

    public List<User> findAll() { return this.users; }

    public User findOneById(Integer id) {
        return users.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
