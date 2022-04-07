package RIdeSharing.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Vehicles {
  private int vehicleId;
  private String vrn;
  private String VehicleModel;
  private int totalCapacity;
}
