package jp.co.sample.form;

public class UpdateEmployeeForm {
	private String id;
	private Integer dependentsCount;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getDependentsCount() {
		return dependentsCount;
	}

	public void setDependentsCount(Integer dependentsCount) {
		this.dependentsCount = dependentsCount;
	}

	@Override
	public String toString() {
		return "UpdateEmployeeForm [dependentsCount=" + dependentsCount + "]";
	}
	
}
