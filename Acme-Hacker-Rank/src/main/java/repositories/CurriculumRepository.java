package repositories;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.jpa.repository.Query; 
import org.springframework.stereotype.Repository; 

import domain.Curriculum; 

@Repository 
public interface CurriculumRepository extends JpaRepository<Curriculum, Integer>{ 

	//@Query("") 
	//Method 

} 
