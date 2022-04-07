package RIdeSharing.services;

import RIdeSharing.Repository.UserRepository;
import RIdeSharing.entities.User;
import RIdeSharing.entities.Vehicles;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VehicleService {
  private static int vehicleId = 1;

  public Vehicles addVehiclesToUser(String username, String vrn, String vehicleModel) {
    User user = (User) UserRepository.getUsers().get(username);
    log.info("Got user {}",user);
    Vehicles vehicle =
        Vehicles.builder().vehicleId(vehicleId++).vrn(vrn).VehicleModel(vehicleModel).build();
    List<Vehicles> vehiclesList = user.getUserRegisteredVehicles();
    if (Objects.isNull(user.getUserRegisteredVehicles()))
          vehiclesList = new ArrayList<>();
    vehiclesList.add(vehicle);
    user.setUserRegisteredVehicles(vehiclesList);
    UserRepository.getUsers().put(username, user);
    log.info(
        "Vehicle Added Successfully vrn : {} , vehicleModel : {} , userName : {}  ",
        vehicle.getVrn(),
        vehicle.getVehicleModel(),
        username);
    return vehicle;
  }
}
