
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

	//@Query("") 
	//Method

	@Query("select m from Message m where m.recipient.id=?1 and m.spam=false and m.deleted=false and m.owner=?1")
	Collection<Message> findRecives(int id);

	@Query("select m from Message m where m.sender.id=?1 and m.spam=false and m.deleted=false and m.owner=?1")
	Collection<Message> findSend(int id);

	@Query("select m from Message m where m.recipient.id=?1 and m.spam=true and m.deleted=false and m.owner=?1")
	Collection<Message> findSpam(int id);

	@Query("select m from Message m where m.recipient.id=?1 and m.deleted=true and m.owner=?1")
	Collection<Message> findDeleted(int id);

}
