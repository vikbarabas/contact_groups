package hu.viktorbarabas.contact_group.repository;

import hu.viktorbarabas.contact_group.entities.Contacts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactsRepository extends CrudRepository<Contacts, Long> {

    @Override
    List<Contacts> findAll();

    @Query(value = "SELECT c FROM Contacts c WHERE c.contactGroups.id = :id")
    List<Contacts> findAllByContactGroupsId(@Param("id") Long id);

}
