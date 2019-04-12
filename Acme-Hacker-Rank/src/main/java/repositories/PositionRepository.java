
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {

	@Query("select p from Position p where p.company.id=?1")
	Collection<Position> findByCompany(Integer id);

	@Query("select p from Position p where (p.title like %?1%)or (p.description like %?1%)or (p.profileRequired like %?1%)or (p.skillRequired like %?1%)or (p.techRequired like %?1%)or (p.company.commercialName like %?1%)")
	Collection<Position> searchPositionKeyWord(String keyword);

	@Query("select p from Position p join p.problems pr where pr.id=?1")
	Collection<Position> findAllByProblem(Integer id);

}
