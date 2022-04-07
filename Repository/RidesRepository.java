package RIdeSharing.Repository;

import RIdeSharing.entities.RIdes;
import RIdeSharing.entities.Route;
import RIdeSharing.enums.City;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

public class RidesRepository {

  private static Map<String, List<RIdes>> ridesMap;
  private static RidesRepository ridesRepository = null;

  private RidesRepository() {
    ridesMap = new HashMap<>();
  }

  public static Map<String, List<RIdes>> getRides() {
    if (ridesRepository == null) ridesRepository = new RidesRepository();
    return ridesMap;
  }
}
