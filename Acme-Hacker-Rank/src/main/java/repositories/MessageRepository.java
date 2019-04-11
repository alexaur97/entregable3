
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

	@Query("select m from Message m where m.recipient=?1 and m.spam=false and m.delete=false")
	Collection<Message> findRecives(int id);

	@Query("select m from Message m where m.recipient=?1 and m.spam=false and m.delete=false")
	Collection<Message> findSend();

}
