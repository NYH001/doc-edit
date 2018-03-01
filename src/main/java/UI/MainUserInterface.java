package UI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DB.QueryOperation;
import datatype.Info;

public class MainUserInterface extends JFrame {
	
	private JPanel mainPanel;
	private JPanel headPanel;
	public static List<QueryPanel> queryPanels;
	
	private JTextField inputTextField;
	private JButton searchButton;
	private JButton addButton;
//	private JButton deleteButton;
	
	public MainUserInterface() {
		// TODO Auto-generated constructor stub
		initUI();
		initActionListener();
	}
	
	private void initUI() {
		mainPanel = (JPanel) getContentPane();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		headPanel = initHeadPanel();
		
		queryPanels = initQueryPanels();
				
//		deleteButton = new JButton("删除");
		mainPanel.add(headPanel);
//		mainPanel.add(deleteButton);
		
		for(int i = 0; i < queryPanels.size(); i++){
			mainPanel.add(queryPanels.get(i));
		}
		
		setTitle("主界面");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setResizable(false);
		pack();
	}
	
	private JPanel initHeadPanel() {
		inputTextField = new JTextField();
		inputTextField.setEditable(true);
		searchButton = new JButton("搜索");
		addButton = new JButton("增加");
		
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.X_AXIS));
		tempPanel.add(inputTextField);
		tempPanel.add(searchButton);
		tempPanel.add(addButton);
		tempPanel.setPreferredSize(new Dimension(400, 45));
		return tempPanel;
	}
	
	private List<QueryPanel> initQueryPanels() {
		List<QueryPanel> manyPanels = new ArrayList<QueryPanel>();
		QueryOperation queryOperation = new QueryOperation();
		List<Info> data = queryOperation.getData();
		for (int i = 0; i < data.size(); i++) {
			manyPanels.add(new QueryPanel(data.get(i).getTitle(), data.get(i).getAuthor(), data.get(i).getId()));
		}
		return manyPanels;
	}
	
	private void initActionListener() {
		addButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddUserInterface addUserInterface = new AddUserInterface();
				addUserInterface.setVisible(true);
			}
		});
		
		searchButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String input = (inputTextField.getText().isEmpty() ? null : inputTextField.getText());
				repaintInterface(input);
			}
		});
	}
	
	public void repaintInterface(String input) {
		QueryOperation queryOperation = new QueryOperation(input);
		List<Info> data = queryOperation.getData();
		List<QueryPanel> panels = new ArrayList<QueryPanel>();
		for (int i = 0; i < data.size(); i++) {
			panels.add(new QueryPanel(data.get(i).getTitle(), data.get(i).getAuthor(), data.get(i).getId()));
		}
//		System.out.println(manyPanels.size());
		
		mainPanel.removeAll();
		mainPanel.add(headPanel);
		for(int i = 0; i < panels.size(); i++){
			mainPanel.add(panels.get(i));
		}
		Random random = new Random();
		boolean flag = false;
		flag = random.nextBoolean();
		int newWidth;
		if(flag){
			newWidth = this.getWidth() + 1;
		}else{
			newWidth = this.getWidth() - 1;
		}
		this.setSize(newWidth, 45 * (panels.size() + 1));
		System.out.println( panels.size());
		this.repaint();
	}
}
