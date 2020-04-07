package Models.Interface;

public interface IHistoryItem {
	/**
	 * @return Date of the test (Amount of insulin that needs to be injected)
	 */
	public String getDate();

	/**
	 * @return Amount of sugar in blood
	 */
	public String getBloodSugar();

	/**
	 * @return amount of carbohydrate in a meal
	 */
	public String getCarbsAmount();

	/**
	 * @return insulin level (the result of the calculation)
	 */
	public String getInsulinLevel();
}
