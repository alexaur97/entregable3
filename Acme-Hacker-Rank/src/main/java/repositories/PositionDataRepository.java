
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.PositionData;

@Repository
public interface PositionDataRepository extends JpaRepository<PositionData, Integer> {

	@Query("select p.")
	Collection<PositionData> findByHacker(int id);

	//@Query("") 
	//Method 

}
