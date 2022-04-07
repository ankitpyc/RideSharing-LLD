package RIdeSharing.entities;

import RIdeSharing.enums.City;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Route implements Serializable {
  private City startDestination;
  private City endDestination;
}
