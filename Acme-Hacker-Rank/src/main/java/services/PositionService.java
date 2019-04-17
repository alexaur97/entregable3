
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.PositionRepository;
import domain.Company;
import domain.Position;

@Service
@Transactional
public class PositionService {

	//Managed repository -------------------
	@Autowired
	private PositionRepository				positionRepository;

	//Supporting Services ------------------
	@Autowired
	private ConfigurationParametersService	configParamsService;

	@Autowired
	private Validator						validator;

	@Autowired
	private CompanyService					companyService;


	//COnstructors -------------------------
	public PositionService() {
		super();
	}

	//Simple CRUD methods--------------------

	public Position create() {
		Position result;

		result = new Position();

		return result;
	}

	public Collection<Position> findAll() {
		Collection<Position> result;

		result = this.positionRepository.findAll();

		return result;
	}

	public Position findOne(final int positionId) {
		Position result;

		result = this.positionRepository.findOne(positionId);

		return result;
	}

	public void save(final Position position) {
		Assert.notNull(position);

		this.positionRepository.save(position);
	}

	public void delete(final Position position) {
		this.positionRepository.delete(position);
	}

	public Collection<Position> findByCompany(final Integer companyId) {
		Assert.notNull(companyId);
		final Collection<Position> res = this.positionRepository.findByCompany(companyId);
		return res;
	}
	//Other Methods--------------------
	public Collection<Position> searchPosition(final String keyword) {
		return this.positionRepository.searchPositionKeyWord(keyword);

	}

	public Collection<Double> statsPositionsPerCompany() {
		final Collection<Double> result = this.positionRepository.statsPositionsPerCompany();
		Assert.notNull(result);
		return result;
	}

	public Collection<Double> statsSalaryOfferedPerPosition() {
		final Collection<Double> result = this.positionRepository.statsSalaryOfferedPerPosition();
		Assert.notNull(result);
		return result;
	}

	public Collection<Position> bestPositionsSalary() {
		final Collection<Position> result = this.positionRepository.bestPositionsSalary();
		Assert.notNull(result);
		return result;
	}

	public Collection<Position> worstPositionsSalary() {
		final Collection<Position> result = this.positionRepository.worstPositionsSalary();
		Assert.notNull(result);
		return result;
	}

	public Collection<Position> findByCompanyFinal(final Integer companyId) {
		Assert.notNull(companyId);
		final Collection<Position> res = this.positionRepository.findByCompanyFinal(companyId);
		return res;
	}

	public Collection<Position> findFinal() {
		final Collection<Position> result = this.positionRepository.findFinal();
		Assert.notNull(result);
		return result;
	}

	public Collection<Position> findFinalNotBanned() {
		final Collection<Position> result = this.positionRepository.findFinalNotBanned();
		Assert.notNull(result);
		return result;
	}

	public Collection<Position> findAllByProblem(final int id) {
		final Collection<Position> result = this.positionRepository.findAllByProblem(id);
		return result;
	}

	public Collection<Position> searchPositions(final String keyword, final Integer minSalary, final Integer maxSalary, final Date deadline) {
		Collection<Position> positionsByKeyWord = new ArrayList<>();
		Collection<Position> positionsByMinSalary = new ArrayList<>();
		Collection<Position> positionsByMaxSalary = new ArrayList<>();
		Collection<Position> positionsByDeadline = new ArrayList<>();
		Collection<Position> result = new ArrayList<>();
		if (keyword.isEmpty())
			positionsByKeyWord = this.positionRepository.findFinalNotBanned();
		else
			positionsByKeyWord = this.positionRepository.searchPositionsKeyWord(keyword);
		if (Objects.equals(null, minSalary))
			positionsByMinSalary = this.positionRepository.findFinalNotBanned();
		else
			positionsByMinSalary = this.positionRepository.searchPositionsMinSalary(minSalary);

		if (Objects.equals(null, maxSalary))
			positionsByMaxSalary = this.positionRepository.findFinalNotBanned();
		else
			positionsByMaxSalary = this.positionRepository.searchPositionsMaxSalary(maxSalary);

		if (Objects.equals(null, deadline))
			positionsByDeadline = this.positionRepository.findFinalNotBanned();
		else
			positionsByDeadline = this.positionRepository.searchPositionsDeadline(deadline);

		positionsByKeyWord.retainAll(positionsByMinSalary);
		positionsByKeyWord.retainAll(positionsByMaxSalary);
		positionsByKeyWord.retainAll(positionsByDeadline);
		if (this.configParamsService.find().getFinderMaxResults() < positionsByKeyWord.size())
			for (int i = 0; i < this.configParamsService.find().getFinderMaxResults(); i++)
				result.add((Position) positionsByKeyWord.toArray()[i]);
		else
			result = positionsByKeyWord;
		return result;

	}

	public Position reconstruct(final Position position, final BindingResult binding) {
		final Position res = position;
		final Company c = this.companyService.findByPrincipal();

		res.setCompany(c);
		final String name = this.createName(position.getCompany().getCommercialName());
		final String num = this.creaNum();
		final String ticker = name + "-" + num;
		res.setTicker(ticker);
		res.setCancelled(false);

		if (res.getMode() == null)
			res.setMode("DRAFT");

		this.validator.validate(res, binding);
		return res;
	}

	private String createName(final String commercialName) {
		String resultado;
		final char[] res = new char[4];

		final Collection<String> elementos = new ArrayList<>();
		elementos.add("A");
		elementos.add("N");
		elementos.add("B");
		elementos.add("O");
		elementos.add("C");
		elementos.add("P");
		elementos.add("D");
		elementos.add("Q");
		elementos.add("E");
		elementos.add("R");
		elementos.add("F");
		elementos.add("S");
		elementos.add("G");
		elementos.add("T");
		elementos.add("H");
		elementos.add("U");
		elementos.add("I");
		elementos.add("V");
		elementos.add("J");
		elementos.add("W");
		elementos.add("K");
		elementos.add("X");
		elementos.add("L");
		elementos.add("Y");
		elementos.add("M");
		elementos.add("Z");

		final char[] nuevo = commercialName.toUpperCase().toCharArray();
		int a = 0;
		for (int i = 0; i <= nuevo.length; i++) {
			char letra;
			final String s = String.valueOf(nuevo[i]);
			if (elementos.contains(s)) {
				letra = commercialName.charAt(i);
				res[a] = letra;
				a++;
				if (a == 4)
					break;
			}
		}
		resultado = new String(res);
		if (resultado.length() < 4)
			for (int j = resultado.length(); j <= 4; j++)
				resultado = resultado.concat("X");
		return resultado;
	}

	public String creaNum() {
		String res = null;

		final char[] elementos = {
			'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'
		};

		final char[] conjunto = new char[4];

		for (int i = 0; i < 4; i++) {

			final int el = (int) (Math.random() * 10);

			conjunto[i] = elementos[el];
		}
		res = new String(conjunto);

		return res;
	}

	public Position saveMode(final Position position) {
		final Position res = position;
		res.setMode("FINAL");
		return res;
	}
}
