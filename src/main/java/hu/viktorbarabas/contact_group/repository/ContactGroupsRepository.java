package hu.viktorbarabas.contact_group.repository;

import hu.viktorbarabas.contact_group.ContactGroups;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactGroupsRepository extends CrudRepository<ContactGroups, Long> {
}
