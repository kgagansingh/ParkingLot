package src.com.parkingsolution.model;

public class Driver {
	
	private Integer age;
	
	public Driver() {}
	
	
	public Driver(Integer age) {
		this.age = age;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Driver [age=").append(age).append("]");
		return builder.toString();
	}
	

}
