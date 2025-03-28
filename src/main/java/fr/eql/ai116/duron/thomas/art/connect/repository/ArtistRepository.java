package fr.eql.ai116.duron.thomas.art.connect.repository;

import fr.eql.ai116.duron.thomas.art.connect.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist,Long> {
}
