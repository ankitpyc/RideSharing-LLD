package RIdeSharing;

import RIdeSharing.entities.RIdes;
import RIdeSharing.enums.City;
import RIdeSharing.enums.SelectionStrategy;
import RIdeSharing.services.RIdeService;
import RIdeSharing.services.UserService;
import RIdeSharing.services.VehicleService;
import java.time.LocalDateTime;
import javafx.util.converter.LocalDateTimeStringConverter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DriverClass {
  public static UserService userService = new UserService();
  public static VehicleService vehicleService = new VehicleService();
  public static RIdeService rideService = new RIdeService();

  public static void main(String args[]) {
    LocalDateTime startDateTime = null;
    userService.createUser("Rohan", 'M', 36);
    vehicleService.addVehiclesToUser("Rohan", "KA-1232-11", "Swift");
    userService.createUser("Shashank", 'M', 29);
    vehicleService.addVehiclesToUser("Shashank", "MH-2214-11", "Baleno");

    userService.createUser("Nandini", 'F', 29);
    userService.createUser("Shipra", 'F', 27);
    vehicleService.addVehiclesToUser("Shipra", "KA-54109-11", "Polo");
    userService.createUser("Gourav", 'M', 31);
    startDateTime = LocalDateTime.of(2022, 4, 23, 12, 25);
    rideService.offerRide(
        "Rohan", City.Bangalore, City.Hyedrabad, "KA-1232-11", startDateTime, Double.valueOf(13));
    startDateTime = LocalDateTime.of(2022, 4, 12, 23, 35);
    rideService.offerRide(
        "Shashank",
        City.Bangalore,
        City.Hyedrabad,
        "MH-2214-11",
        startDateTime,
        Double.valueOf(15));
    startDateTime = LocalDateTime.of(2022, 5, 11, 13, 35);
    rideService.offerRide(
        "Shashank", City.Delhi, City.Hardoi, "MH-2214-11", startDateTime, Double.valueOf(22.34));

    // Get rides
    RIdes ride =
        rideService.selectRide(
            "Gourav", City.Bangalore, City.Hyedrabad, SelectionStrategy.FASTEST_RIDE);
    log.info(
        "Got fastest ride as rider : {} , TotalRIdes : {} ",
        ride.getUserAssigned().getName(),
        ride.getUserAssigned().getTotalRidesOffered());
    RIdes ride2 =
        rideService.selectRide(
            "Gourav", City.Bangalore, City.Hyedrabad, SelectionStrategy.EARLIEST_RIDE);
    log.info(
        "Got earliest ride as rider : {} , TotalRIdes : {} , rider departure date : {} ",
        ride2.getUserAssigned().getName(),
        ride2.getUserAssigned().getTotalRidesOffered(),
        ride2.getJourneyStartDate());

    RIdes ride3 =
        rideService.selectRide("Gourav", City.Delhi, City.Hardoi, SelectionStrategy.EARLIEST_RIDE);
    log.info(
        "Got earliest ride as rider : {} , TotalRIdes : {} , rider departure date : {} ",
        ride3.getUserAssigned().getName(),
        ride3.getUserAssigned().getTotalRidesOffered(),
        ride3.getJourneyStartDate());
  }
}
