package ViewModels.Interface;

import Exceptions.MyException;

public interface ICalculateInulinViewModel {

	/**
	 * on function -
	 * it calculates how much insulin should be injected according to a fixed formula
	 * a fixed formula is :
	 *      ((bloodSugar - 100) / 80) + (carbsAmount / 15)
	 * and save all this (bloodSugar,carbsAmount, resualt) and Date
	 *
	 * @param bloodSugar is amount of sugar in blood
	 * @param carbsAmount is amount of carbohydrate in a meal
	 * @return insulin level, the result from calculation
	 * @throws MyException if the Sugar in blood <= 0 or carbsAmount < 0
	 */
	public double calculate(double bloodSugar, double carbsAmount) throws MyException.NegNumNotAllowed, Exception;

	/**
	 *
	 * @return
	 */
	public String getUserId();
}
