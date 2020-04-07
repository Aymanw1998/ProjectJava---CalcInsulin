package Views.Interface;

import ViewModels.Source.CalculateInsulinViewModel;
import Views.Source.CalculateInsulinView;

public interface ICalculateInulinView {

	/**
	 * on function -
	 * has three actions:
	 * 			1. calcButton -
	 * 				calls calculate function (of viewModel) and send its 2 params: (one) for amount of Sugar in the blood and (two) for amount of carbohydrate in a meal
	 * 				and displays the result (insulin level) to the user
	 * 			2. logoutButton -
	 * 				logout of user
	 * 			3. backButton -
	 * 				back to the previous screen
	 *
	 * @param viewModel is connection between View and Model (MVVM)
	 */
	public void commond(CalculateInsulinViewModel viewModel);

	/**
	 *
	 * on function -
	 * Creates the screen display for the user
	 *
	 */
	public void initializeUI();

	/**
	 *
	 * on function -
	 * Print the result of calculation for user
	 *
	 * @param res is the result (insulin level)
	 */
	public void handleCalculateResult(double res);
}
