package hu.viktorbarabas.contact_group.repository;

import hu.viktorbarabas.contact_group.Contacts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactsRepository extends CrudRepository<Contacts, Long> {

    @Override
    List<Contacts> findAll();

}
