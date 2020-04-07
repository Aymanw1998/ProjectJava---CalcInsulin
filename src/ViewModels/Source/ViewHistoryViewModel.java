package ViewModels.Source;

import java.util.List;

import Models.Source.HistoryItem;
import Services.Source.DBService;
import ViewModels.Interface.IViewHistoryViewModel;


public class ViewHistoryViewModel implements IViewHistoryViewModel {
	private DBService dbService;
	private String userId;

	public ViewHistoryViewModel(String userId) {
		this.dbService = new DBService();
		this.userId = userId;
	}
	public String getUserId(){return this.userId;}
	public List<HistoryItem> getHistory() {
		return this.dbService.getUserHistory(userId);
	}
}
