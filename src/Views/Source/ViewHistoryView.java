package Views.Source;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Models.Source.HistoryItem;
import ViewModels.Source.UserViewModel;
import ViewModels.Source.ViewHistoryViewModel;
import Views.Interface.IViewHistoryView;

@SuppressWarnings("serial")
public class ViewHistoryView extends JFrame implements IViewHistoryView {


	// UI elements
	String[] columnNames = { "#", "Time", "Insulin Level", "Carbs Amount", "Result" };
	JTable table;
	List<HistoryItem> history;
	private JButton logoutButton;
	private JButton backButton;
	public ViewHistoryView(ViewHistoryViewModel viewModel) {
		this.history = viewModel.getHistory();
		initializeUI();
		commond(viewModel);

	}
	public void commond(ViewHistoryViewModel viewModel){
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginView frame = new LoginView(new UserViewModel());
				frame.setVisible(true);
				setVisible(false);
			}
		});
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserView frame = new UserView(viewModel.getUserId());
				frame.setVisible(true);
				setVisible(false);
			}
		});
	}
	public void initializeUI() {
		this.setTitle("Insulin Calculator - View History");

		this.logoutButton = new JButton("Logout");
		this.backButton = new JButton("Back");
		DefaultTableModel model = new DefaultTableModel(new Object[][] {}, columnNames) {
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		for (int i = 0; i < this.history.size(); i++) {
			HistoryItem item = this.history.get(i);
			Object[] objects = new Object[] {i+1,item.getDate(),item.getBloodSugar(),item.getCarbsAmount(),item.getInsulinLevel()};
			model.addRow(objects);
		}

		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);

		JPanel panelBackLogout = new JPanel();
		panelBackLogout.setLayout(new FlowLayout());
		panelBackLogout.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panelBackLogout.add(backButton);
		panelBackLogout.add(logoutButton);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(scrollPane);
		panel.add(panelBackLogout);

		// add panels to main frame
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setBounds(500, 200, 800, 500);
		this.setContentPane(panel);
		//this.setContentPane(backButton);
	}
}
