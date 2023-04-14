package uz.pdp.appnewssite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appnewssite.entity.Lavozim;

import java.util.Optional;

public interface LavozimRepo extends JpaRepository<Lavozim,Long> {

   Optional<Lavozim> findByName(String name);

   boolean existsByName(String name);

}
