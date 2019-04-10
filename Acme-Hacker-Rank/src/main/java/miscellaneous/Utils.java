
package miscellaneous;

import org.joda.time.LocalDate;

import domain.CreditCard;

public class Utils {

	public static Boolean creditCardIsExpired(final CreditCard creditCard) {
		final LocalDate date = LocalDate.now();
		final Integer month = date.getMonthOfYear();
		final Integer year = date.getYear();
		Boolean res = false;
		if ((creditCard.getExpirationYear() + 2000) < year)
			res = true;
		else if ((creditCard.getExpirationYear() + 2000) == year && creditCard.getExpirationMonth() < month)
			res = true;
		return res;
	}

	public static Boolean creditCardIsExpired(final Integer month, final Integer year) {
		final LocalDate date = LocalDate.now();
		final Integer m = date.getMonthOfYear();
		final Integer y = date.getYear();
		Boolean res = false;
		if ((year + 2000) < y)
			res = true;
		else if ((year + 2000) == y && month < m)
			res = true;
		return res;
	}

}
