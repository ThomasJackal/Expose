package fr.eql.ai116.duron.thomas.art.connect.repository;

import fr.eql.ai116.duron.thomas.art.connect.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(nativeQuery = true,
           value = "SELECT id, distance_km " +
                   "FROM ( " +
                   "    SELECT e.id, " +
                   "           ( " +
                   "               6371 * acos( " +
                   "                   cos(radians(:latitude)) * cos(RADIANS(a.latitude)) * " +
                   "                   cos(RADIANS(a.longitude) - radians(:longitude)) + " +
                   "                   sin(radians(:latitude)) * sin(RADIANS(a.latitude)) " +
                   "               ) " +
                   "           ) AS distance_km " +
                   "    FROM event e " +
                   "    JOIN event_address ea ON e.id = ea.event_id " +
                   "    JOIN address a ON a.id = ea.address_id " +
                   ") AS subquery " +
                   "WHERE distance_km < :radius " +
                   "ORDER BY distance_km DESC; "
    )
    List<Object[]> search(float latitude, float longitude, float radius);

    /// Cette méthode casse l'ordre des ids, il faudrait que l'ordre soit conservé
    @Query("SELECT e FROM Event e WHERE e.id IN :ids")
    List<Event> findByIds(List<Long> ids);
}
