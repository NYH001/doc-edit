package Spring;

import org.springframework.stereotype.Repository;

@Repository
public class AOPOperation {

	public String initInformation() {
		return "title///author///path";
	}
	
//	public String alterInfomation() {
//		return "title///author///id";
//	}
}
