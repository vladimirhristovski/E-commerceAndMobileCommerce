package mk.ukim.finki.emtlab.repository;

import jakarta.transaction.Transactional;
import mk.ukim.finki.emtlab.model.views.HostsPerCountryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HostsPerCountryViewRepository extends JpaRepository<HostsPerCountryView, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.hosts_per_country", nativeQuery = true)
    void refreshMaterializedView();

}
