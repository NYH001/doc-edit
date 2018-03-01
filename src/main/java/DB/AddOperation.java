package DB;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import Spring.AOPOperation;

public class AddOperation {
	
	private String title;
	private String author;
	private String path;
	private String article;
	
	private String url;
	private String username;
	private String password;
	
	public AddOperation() {
		// TODO Auto-generated constructor stub
		initInformation();
		readDoc();
		addToDatabase();
	}
	
	private void initInformation() {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans.xml");
		AOPOperation aopOperation = (AOPOperation) classPathXmlApplicationContext.getBean("addOperation");
		String infomation = aopOperation.initInformation();
//		System.out.println(infomation);
		String[] infomations = infomation.split("///");
		title = infomations[0];
		author = infomations[1];
		path = infomations[2];
//		System.out.println(title+" "+author);
	}
	
	private void readDoc() {
		File file = new File(path);
		try{
			 FileInputStream fileInputStream = new FileInputStream(file);
			 WordExtractor wordExtractor = new WordExtractor(fileInputStream);
			 article = wordExtractor.getText();
			 fileInputStream.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
//		System.out.println(article);
	}
	
	private void addToDatabase() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("database.xml");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("databaseInfo");
		String sqlInsertInfo = "insert into info (author,title,id) values (?,?,?)";
		String sqlInsertArticle = "insert into article (content,id) values (?,?)";
		int index = IndexQueue.index.poll();
		String sqlDelete = "delete from idRemain where id = ?";
		jdbcTemplate.update(sqlInsertInfo,new Object[]{author,title,index});
		jdbcTemplate.update(sqlInsertArticle,new Object[]{article,index});
		jdbcTemplate.update(sqlDelete, index);
		System.out.println("---success---");
	}
	
//	public void setUrl(String url) {
//		this.url = url;
////		System.out.println(url);
//	}
//	
//	public void setUsername(String username) {
//		this.username = username;
////		System.out.println(this.username);
//	}
//	
//	public void setPassword(String password) {
//		this.password = password;
////		System.out.println(this.password);
//	}
}
