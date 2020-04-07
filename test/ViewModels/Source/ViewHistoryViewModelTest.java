package ViewModels.Source;

import Models.Source.HistoryItem;
import Services.Source.DBService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ViewHistoryViewModelTest {
    private ViewHistoryViewModel viewHistory;
    private DBService dbService;
    private String userId;
    @Before
    public void setUp() throws Exception {
        userId = "ayman@test.com";
        viewHistory = new ViewHistoryViewModel(userId);
        dbService = new DBService();
    }

    @After
    public void tearDown() throws Exception {
        userId = null;
        viewHistory = null;
        dbService = null;
    }

    @Test
    public void getUserId() {
        assertEquals(userId,viewHistory.getUserId());
    }

    @Test
    public void getHistory() {
        List<HistoryItem> li= dbService.getUserHistory(userId);
        List<HistoryItem> li2 = viewHistory.getHistory();
        assertEquals(li.get(0).toString(), li.get(0).toString());
    }
}