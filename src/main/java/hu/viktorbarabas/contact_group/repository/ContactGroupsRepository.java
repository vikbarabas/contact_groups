package hu.viktorbarabas.contact_group.repository;

import hu.viktorbarabas.contact_group.entities.ContactGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactGroupsRepository extends CrudRepository<ContactGroup, Long> {

    @Override
    List<ContactGroup> findAll();

    @Query(value = "SELECT cg FROM ContactGroup cg WHERE cg.name = :name")
    ContactGroup findById(@Param("name") String name);

    /*@Query(value = "UPDATE ContactGroups cp SET cp.name = :groupName WHERE cp.id = :groupId")
    void update(@Param("groupId") long groupId, @Param("groupName") String groupName);*/

}
