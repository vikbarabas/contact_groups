package hu.viktorbarabas.contact_group.repository;

import hu.viktorbarabas.contact_group.entities.Contact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactsRepository extends CrudRepository<Contact, Long> {

    @Query(value = "SELECT c FROM Contact c WHERE c.name = :name")
    Contact findByName(@Param("name") String name);

    @Query(value = "SELECT c FROM Contact c WHERE c.contactGroup.id = :id")
    List<Contact> findAllByContactGroupsId(@Param("id") Long id);

}
