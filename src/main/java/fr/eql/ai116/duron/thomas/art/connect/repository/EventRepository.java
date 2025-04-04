package fr.eql.ai116.duron.thomas.art.connect.repository;

import fr.eql.ai116.duron.thomas.art.connect.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(nativeQuery = true,
           value = "SELECT e.id, " +
                   "    ( " +
                   "        6371 * acos( " +
                   "            cos(RADIANS(a.latitude)) * cos(RADIANS(:longitude)) * " +
                   "            cos(RADIANS(:latitude) - RADIANS(a.longitude)) + " +
                   "            sin(RADIANS(a.latitude)) * sin(RADIANS(:longitude)) " +
                   "        ) " +
                   "    ) AS distance_km " +
                   "FROM event e " +
                   "JOIN event_address ea ON e.id = ea.event_id " +
                   "JOIN address a ON a.id = ea.address_id " +
                   "ORDER BY distance_km"
    )
    List<Object[]> search(long latitude, long longitude);

    /// Cette méthode casse l'ordre des ids, il faudrait que l'ordre soit conservé
    @Query("SELECT e FROM Event e WHERE e.id IN :ids")
    List<Event> findByIds(List<Long> ids);
}
