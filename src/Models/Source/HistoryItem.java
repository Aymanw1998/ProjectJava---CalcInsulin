package Models.Source;

import Models.Interface.IHistoryItem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryItem implements IHistoryItem {

	private String date;		// Date of the test (Amount of insulin that needs to be injected)
	private String bloodSugar; 	// Amount of sugar in blood
	private String carbsAmount;	// Amount of carbohydrate in a meal
	private String insulinLevel;// Insulin level (the result of the calculation)

	public HistoryItem(String bloodSugar, String carbsAmount, String insulinLevel) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date date = new Date();
		this.date = dateFormat.format(date);
		this.bloodSugar = bloodSugar;
		this.carbsAmount = carbsAmount;
		this.insulinLevel = insulinLevel;
	}

	public String getDate() {
		return date;
	}

	public String getBloodSugar() {
		return bloodSugar;
	}

	public String getCarbsAmount() {
		return carbsAmount;
	}

	public String getInsulinLevel() {
		return insulinLevel;
	}

}
