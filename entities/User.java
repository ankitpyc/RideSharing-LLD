package RIdeSharing.entities;


import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {
  private String name;
  private int userId;
  private int totalRidesTaken;
  private int totalRidesOffered;
  private Character gender;
  private List<Vehicles> userRegisteredVehicles;
}
