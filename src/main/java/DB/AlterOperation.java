package DB;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import Spring.AOPOperation;

public class AlterOperation {
	
	private String title;
	private String author;
	private int id;
	
	public AlterOperation(String title, String author, int id) {
		initAlterInformation(title, author, id);
		alterTheDatabase();
	}
	
	private void initAlterInformation(String title, String author, int id) {
		this.title = title;
		this.author = author;
		this.id = id;
	}
	
	private void alterTheDatabase() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("database.xml");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("databaseInfo");
		String sqlAlterInfo = "update info set title = ? , author = ? where id = ?";
		jdbcTemplate.update(sqlAlterInfo,new Object[]{title, author, id});
		System.out.println("---altered---");
	}
}
