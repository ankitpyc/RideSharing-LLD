package RIdeSharing.entities;

import RIdeSharing.enums.City;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RIdes {
  private int rideId;
  private User userAssigned;
  private int totalAvailableSeats;
  private LocalDateTime journeyStartDate;
  private Double duration;
  private City startDestination;
  private City endDestination;
}
