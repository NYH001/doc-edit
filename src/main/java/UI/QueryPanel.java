package UI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DB.DeleteOperation;

public class QueryPanel extends JPanel{
	
	private JTextField titleTextField;
	private JTextField authorTextField;
	private JButton	viewButton;
	private JButton editButton;
	private JButton deleteButton;
	
	private int id;
	private String title;
	private String author;
	
	public QueryPanel(String title, String author, int id) {
		initInfo(title, author, id);
		initPanel();
		initActionListener();
	}
	
	private void initPanel() {
		titleTextField = new JTextField();
		titleTextField.setText(title);
		titleTextField.setEditable(false);
		authorTextField = new JTextField();
		authorTextField.setText(author);
		authorTextField.setEditable(false);
		viewButton = new JButton("²é¿´");
		editButton = new JButton("±à¼­");
		deleteButton = new JButton("É¾³ý");
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(titleTextField);
		this.add(authorTextField);
		this.add(viewButton);
		this.add(editButton);
		this.add(deleteButton);
		this.setPreferredSize(new Dimension(400, 45));
	}
	
	private void initInfo(String title, String author, int id) {
		setTitle(title);
		setAuthor(author);
		setId(id);
	}
	
	private void setTitle(String title) {
		this.title = title;
	}
	
	private void setAuthor(String author) {
		this.author = author;
	}
	
	private void setId(int id) {
		this.id = id;
	}
	
	private void initActionListener() {
		viewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ViewInterface viewInterface = new ViewInterface(title, author, id);
				viewInterface.setVisible(true);
//				System.out.println("id = " + id + " was clicked!");
			}
		});
		
		editButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AlterInterface alterInterface = new AlterInterface(title, author, id);
				alterInterface.setVisible(true);
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				delete();
			}
		});
	}
	
	private void delete() {
		DeleteOperation deleteOperation = new DeleteOperation(id);
		this.removeAll();
	}
}
