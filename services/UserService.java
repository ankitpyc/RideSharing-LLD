package RIdeSharing.services;

import RIdeSharing.Repository.UserRepository;
import RIdeSharing.entities.User;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserService {
  private static int userId = 1;

  public User createUser(String name, Character gender, Integer age) {
    User user =
        User.builder()
            .userId(userId++)
            .gender(gender)
            .userRegisteredVehicles(new ArrayList<>())
            .totalRidesOffered(0)
            .name(name)
            .totalRidesTaken(0)
            .build();
    UserRepository.getUsers().put(user.getName(), user);
    log.info("total user created  : {}", UserRepository.getUsers().size());
    return user;
  }

  public void updateUserRideTaken(String username) {
    User user = UserRepository.getUsers().get(username);
    user.setTotalRidesTaken(user.getTotalRidesTaken() + 1);
    UserRepository.getUsers().put(username, user);
  }

  public void updateUserRideOffered(String username) {
    User user = UserRepository.getUsers().get(username);
    user.setTotalRidesOffered(user.getTotalRidesOffered() + 1);
    UserRepository.getUsers().put(username, user);
  }
}
