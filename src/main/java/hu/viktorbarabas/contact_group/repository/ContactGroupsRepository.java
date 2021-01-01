package hu.viktorbarabas.contact_group.repository;

import hu.viktorbarabas.contact_group.ContactGroups;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactGroupsRepository extends CrudRepository<ContactGroups, Long> {

    @Override
    List<ContactGroups> findAll();

    @Query(value = "SELECT cp FROM ContactGroups cp WHERE cp.name = :name")
    ContactGroups findById(@Param("name") String name);
}