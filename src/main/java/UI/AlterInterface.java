package UI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import DB.AlterOperation;

//@Component
//@Aspect
public class AlterInterface extends JFrame {
	
	private JLabel titleLabel;
	private JLabel authorLabel;
	private JTextField titleTextField;
	private JTextField authorTextField;
	private JButton confirmButton;
	private JButton cancelButton;
	
	private int id;
	private String title;
	private String author;
	
	public AlterInterface(String title, String author, int id) {
		// TODO Auto-generated constructor stub
		setId(id);
		initInterface(title, author);
		initActionListener();
	}
	
	private void setId(int id) {
		this.id = id;
	}
	
	private void initInterface(String title, String author) {
		titleLabel = new JLabel("标题");
		authorLabel = new JLabel("作者");
		titleTextField = new JTextField(title);
		titleTextField.setEditable(true);
		authorTextField = new JTextField(author);
		authorTextField.setEditable(true);
		
		confirmButton = new JButton("确认");
		cancelButton = new JButton("取消");
		
		JPanel tempPanel = (JPanel) getContentPane();
		tempPanel.setLayout(new GridLayout(3, 2));
		tempPanel.add(titleLabel);
		tempPanel.add(titleTextField);
		tempPanel.add(authorLabel);
		tempPanel.add(authorTextField);
		tempPanel.add(confirmButton);
		tempPanel.add(cancelButton);
		
		setTitle("修改信息");
		setLocationRelativeTo(null);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
	}
	
	private void initActionListener() {
		confirmButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				title = titleTextField.getText();
				author = authorTextField.getText();
				alter();
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
	
	private void alter() {
		AlterOperation alterOperation = new AlterOperation(title, author, id);
	}
	
//	@Around("execution(* Spring.AOPOperation.alterInformation(..))")
//	public String alterTheValue(ProceedingJoinPoint proceedingJoinPoint){
//		String string = null;
//		try{
//			string = (String) proceedingJoinPoint.proceed();
//			string = title + "///" + author + "///" + id;
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		return string;
//	}
}
