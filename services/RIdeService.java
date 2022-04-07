package RIdeSharing.services;

import RIdeSharing.Repository.RidesRepository;
import RIdeSharing.Repository.UserRepository;
import RIdeSharing.entities.RIdes;
import RIdeSharing.entities.Route;
import RIdeSharing.entities.User;
import RIdeSharing.enums.City;
import RIdeSharing.enums.SelectionStrategy;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RIdeService {
  private static int rideId = 1;
  public UserService userService = new UserService();

  public void offerRide(
      String username,
      City origin,
      City end,
      String vrn,
      LocalDateTime startTime,
      Double duration) {
    User user = (User) UserRepository.getUsers().get(username);
    RIdes rides =
        RIdes.builder()
            .rideId(rideId++)
            .duration(duration)
            .endDestination(end)
            .startDestination(origin)
            .userAssigned(user)
            .journeyStartDate(startTime)
            .totalAvailableSeats(0)
            .build();
    Route route = Route.builder().startDestination(origin).endDestination(end).build();
    List<RIdes> ridesMap = new ArrayList<>();
    if (RidesRepository.getRides()
        .containsKey(route.getStartDestination() + "," + route.getEndDestination())) {
      ridesMap =
          RidesRepository.getRides()
              .get(route.getStartDestination() + "," + route.getEndDestination());
      if (Objects.nonNull(ridesMap)) ridesMap.add(rides);
      User userDetails = (User) UserRepository.getUsers().get(username);
      userDetails.setTotalRidesOffered(userDetails.getTotalRidesOffered() + 1);
      RidesRepository.getRides()
          .put(route.getStartDestination() + "," + route.getEndDestination(), ridesMap);
      log.info(
          "for route start {} - end {} ",
          route.getStartDestination(),
          route.getEndDestination(),
          RidesRepository.getRides().size());
      UserRepository.getUsers().put(username, userDetails);
    } else {
      List<RIdes> rIdes = new ArrayList<>();
      rIdes.add(rides);
      log.info(
          "Adding new route start {} - end {} ",
          route.getStartDestination(),
          route.getEndDestination(),
          RidesRepository.getRides().size());
      RidesRepository.getRides()
          .put(route.getStartDestination() + "," + route.getEndDestination(), rIdes);
    }
  }

  public RIdes selectRide(
      String userName, City startDes, City endDest, SelectionStrategy strategy) {
    Route route = Route.builder().startDestination(startDes).endDestination(endDest).build();
    RIdes actualRide = null;
    List<RIdes> availableRides =
        RidesRepository.getRides()
            .get(route.getStartDestination() + "," + route.getEndDestination());
    log.info("Got total rides {} ", availableRides.size());
    List<RIdes> rides = null;
    switch (strategy) {
      case FASTEST_RIDE:
        rides = availableRides;
        Collections.sort(
            rides,
            (r1, r2) -> {
              int val = (int) (r1.getDuration() - r2.getDuration());
              return val;
            });
        actualRide = rides.get(0);
        userService.updateUserRideOffered(actualRide.getUserAssigned().getName());

        userService.updateUserRideTaken(userName);
        break;
      case EARLIEST_RIDE:
        rides = availableRides;
        Collections.sort(
            rides,
          Comparator.comparing(RIdes::getJourneyStartDate));
        actualRide = rides.get(0);
        userService.updateUserRideOffered(actualRide.getUserAssigned().getName());

        userService.updateUserRideTaken(userName);
        break;
    }
    return actualRide;
  }
}
