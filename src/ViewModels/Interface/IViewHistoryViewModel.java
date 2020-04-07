package ViewModels.Interface;

import Models.Source.HistoryItem;
import java.util.List;

public interface IViewHistoryViewModel {
    /**
     *
     * @return the userId of user
     */
    public String getUserId();

    /**
     *
     * @return return the list of history of user
     */
    public List<HistoryItem> getHistory();
}
