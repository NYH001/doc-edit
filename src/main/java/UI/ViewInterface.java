package UI;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import DB.ReadOperation;

public class ViewInterface extends JFrame{
	
	private JTextField titleTextField;
	private JTextField authourTextField;
	private JTextArea contentTextArea;
	
	private int id;
	private String title;
	private String author;
	private String content;

	public ViewInterface(String title, String author, int id) {
		initInfo(title, author, id);
		readContent();
		initInterface();
	}
	
	private void initInfo(String title, String author, int id) {
		this.id = id;
		this.title = title;
		this.author = author;
	}
	
	private void readContent() {
		ReadOperation readOperation = new ReadOperation(id);
		content = readOperation.getContent();
	}
	
	private void initInterface() {
		titleTextField = new JTextField();
		titleTextField.setText(title);
		titleTextField.setEditable(false);
		authourTextField = new JTextField();
		authourTextField.setText(author);
		authourTextField.setEditable(false);
		contentTextArea = new JTextArea();
		contentTextArea.setText(content);
		contentTextArea.setEditable(false);
		
		JPanel tempPanel = (JPanel)this.getContentPane();
		tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.Y_AXIS));
		tempPanel.add(titleTextField);
		tempPanel.add(authourTextField);
		tempPanel.add(contentTextArea);
		
		setTitle("²é¿´");
		setLocationRelativeTo(null);
		pack();
	}
}
