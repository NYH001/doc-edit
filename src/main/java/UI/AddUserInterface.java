package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import DB.AddOperation;
import DB.IndexQueue;

@Component
@Aspect
public class AddUserInterface extends JFrame{
	
	private JPanel addPanel;
	private JPanel titlePanel;
	private JPanel authorPanel;
	private JPanel openPanel;
	private JPanel buttonPanel;
	private JLabel titleLabel;
	private JLabel authorLabel;
	private JTextField titleTextField;
	private JTextField authorTextField;
	private JTextField pathTextField;
	private JButton openButton;
	private JButton confirmButton;
	private JButton cancelButton;
	
	private boolean flag = false;
	
	private static String title;
	private static String author;
	private static String path;
	
//	private String PATH = "./src/main/java/prop.properties";
	
	public AddUserInterface() {
		// TODO Auto-generated constructor stub
		initUI();
		initActionListener();
	}
	
	private void initUI() {
		titlePanel = initTitlePanel();
		authorPanel = initAuthorPanel();
		openPanel = initOpenPanel();
		buttonPanel = initButtonPanel();
		
		addPanel = (JPanel) getContentPane();
		addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
		addPanel.add(titlePanel);
		addPanel.add(authorPanel);
		addPanel.add(openPanel);
		addPanel.add(buttonPanel);
		
		this.setTitle("增加文件");
		setLocationRelativeTo(null);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
	}
	
	private JPanel initTitlePanel() {
		titleLabel = new JLabel("标题：");
		titleTextField = new JTextField();
		titleTextField.setEditable(true);
		
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.X_AXIS));
		tempPanel.add(titleLabel);
		tempPanel.add(titleTextField);
		return tempPanel;
	}
	
	private JPanel initAuthorPanel() {
		authorLabel = new JLabel("作者：");
		authorTextField = new JTextField();
		authorTextField.setEditable(true);
		
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.X_AXIS));
		tempPanel.add(authorLabel);
		tempPanel.add(authorTextField);
		return tempPanel;
	}
	
	private JPanel initOpenPanel() {
		pathTextField = new JTextField();
		pathTextField.setEditable(false);
		openButton = new JButton("打开");
		
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.X_AXIS));
		tempPanel.add(pathTextField);
		tempPanel.add(openButton);
		return tempPanel;
	}
	
	private JPanel initButtonPanel() {
		confirmButton = new JButton("确认");
		cancelButton = new JButton("取消");
		
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.X_AXIS));
		tempPanel.add(confirmButton);
		tempPanel.add(cancelButton);
		return tempPanel;
	}
	
	private void initActionListener() {
		openButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fileChooser = new JFileChooser();
				if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(openButton)) {
					pathTextField.setText(fileChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		
		confirmButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				title = titleTextField.getText();
				author = authorTextField.getText();
				path = pathTextField.getText();
				
//				if (title.isEmpty() || author.isEmpty() || path.isEmpty()){
//					System.out.println("Something was not filled!");
//					return;
//				}
//				
//				Properties tempProperties = new Properties();
//				tempProperties.setProperty("title", title);
//				tempProperties.setProperty("author", author);
//				tempProperties.setProperty("path", path);
//				
//				try {
//					FileOutputStream fileOutputStream = new FileOutputStream(PATH);
//					tempProperties.store(fileOutputStream, "For Spring");
//					fileOutputStream.close();
//					System.out.println("writed to "+PATH);
//				} catch (FileNotFoundException e1) {
//					// TODO: handle exception
//					e1.printStackTrace();
//					return;
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//					return;
//				}
				
//				System.out.println("---1---"+title+author);
				
//				MainUserInterface.queryPanels.add(new QueryPanel(title, author, IndexQueue.index.peek()));
				
				addWithSpring();
				flag = true;
				setVisible(false);
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
	}
	
	private void addWithSpring(){
		AddOperation addOperation = new AddOperation();
	}
	
	@Around("execution(* Spring.AOPOperation.initInformation(..))")
	public String giveTheValue(ProceedingJoinPoint proceedingJoinPoint){
		String string = null;
//		System.out.println(title+"///"+author+"///"+path);
		try {
			string = (String) proceedingJoinPoint.proceed();
			string = title+"///"+author+"///"+path;
//			System.out.println(string);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return string;
	}
	
	public boolean getFlag() {
		return flag;
	}
}
