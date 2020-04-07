package ViewModels.Source;

import Exceptions.MyException;
import Models.Source.HistoryItem;
import Services.Source.DBService;
import ViewModels.Interface.ICalculateInulinViewModel;
import com.google.api.client.util.StringUtils;
import org.apache.log4j.Logger;

public class CalculateInsulinViewModel implements ICalculateInulinViewModel {
	private DBService dbService;
	private String userId;
	final static Logger logger = Logger.getLogger(CalculateInsulinViewModel.class);

	public CalculateInsulinViewModel(String userId) {
		this.dbService = new DBService();
		this.userId = userId;
	}


	public double calculate(double bloodSugar, double carbsAmount) throws MyException.NegNumNotAllowed {
		if (bloodSugar <= 0 || carbsAmount < 0) {
			throw new MyException.NegNumNotAllowed();
		}
			double res = (bloodSugar - 100) / 80 + carbsAmount / 15;
			logger.info("Adding history item to userId=" + userId);
			HistoryItem item = new HistoryItem(String.valueOf(bloodSugar), String.valueOf(carbsAmount), String.valueOf(res));
			dbService.addHistoryItem(userId,item);
			return res;


	}

	@Override
	public String getUserId() {
		return this.userId;
	}

}
