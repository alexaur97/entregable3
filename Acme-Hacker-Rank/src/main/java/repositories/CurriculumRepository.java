
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Curriculum;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Integer> {

	@Query("select h.curriculums from Hacker h where h.id=?1")
	Collection<Curriculum> findAllByPrincipal(int id);

	@Query("select c from Curriculum c where c.personalData.id=?1")
	Curriculum findByPersonalData(int id);

	//@Query("") 
	//Method 

}
