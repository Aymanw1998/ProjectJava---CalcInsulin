package ViewModels.Source;

import Exceptions.MyException;
import Models.Source.HistoryItem;
import Services.Source.DBService;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculateInsulinViewModelTest {
    private CalculateInsulinViewModel calc;
    private DBService dbService;
    private String userId;
    final static Logger logger = Logger.getLogger(CalculateInsulinViewModel.class);
    @Before
    public void setUp() throws Exception {
        userId = "ayman@test.com";
        calc = new CalculateInsulinViewModel(userId);
        dbService = new DBService();
    }

    @After
    public void tearDown() throws Exception {
        userId = null;
        calc = null;
        dbService = null;
    }

    @Test
    public void calculate() throws MyException {
        double bloodSugar=180,carbsAmount=0;
        if (bloodSugar <= 0 || carbsAmount < 0) {
            throw new MyException();
        }
        double res = (bloodSugar - 100) / 80 + carbsAmount / 15;
        logger.info("Adding history item to userId=" + userId);
        HistoryItem item = new HistoryItem(String.valueOf(bloodSugar), String.valueOf(carbsAmount), String.valueOf(res));
        dbService.addHistoryItem(userId,item);

        double res2 = 0;
        try {
            res2 = calc.calculate(bloodSugar,carbsAmount);
        } catch (MyException.NegNumNotAllowed negNumNotAllowed) {
            negNumNotAllowed.printStackTrace();
        }

        assertEquals(res,res2,0.3);
    }

    @Test
    public void getUserId() {
        assertEquals(userId, calc.getUserId());
    }
}