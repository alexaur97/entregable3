
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	@Query("select a from Application a where a.problem.id=?1")
	Collection<Application> findAllByProblem(int id);

	@Query("select avg(1.0*(select count(a) from Application a where a.hacker.id = h.id)),min(1.0*(select count(a) from Application a where a.hacker.id = h.id)),max(1.0*(select count(a) from Application a where a.hacker.id = h.id)),stddev(1.0*(select count(a) from Application a where a.hacker.id = h.id)) from Hacker h")
	Collection<Double> statsApplicationsPerHacker();

	@Query("select a from Application a where a.position.company.id=?1 order by status")
	Collection<Application> findApplicationsByCompany(int id);
	@Query("select a from Application a where a.hacker.id=?1 order by status")
	Collection<Application> findApplicationsByHacker(int id);
}
