
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Hacker;

@Repository
public interface HackerRepository extends JpaRepository<Hacker, Integer> {

	@Query("select h from Hacker h where h.userAccount.id=?1")
	Hacker findByUserId(int id);

	@Query("select h from Hacker h where (select count(a) from Application a where a.hacker.id = h.id) = (select max(1.0*(select count(a) from Application a where a.hacker.id = ha.id)) from Hacker ha)")
	Collection<Hacker> hackersHaveMadeMoreApplications();

	@Query("select avg(1.0*h.curriculums.size),min(1.0*h.curriculums.size),max(1.0*h.curriculums.size),stddev(1.0*h.curriculums.size) from Hacker h")
	Collection<Double> statsCurriculaPerHacker();
}
