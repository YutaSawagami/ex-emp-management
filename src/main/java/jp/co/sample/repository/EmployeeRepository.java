package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import jp.co.sample.domain.Employee;

@Repository
public class EmployeeRepository {

	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER
	= (rs, i) -> {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setAddress(rs.getString("address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setSalary(rs.getInt("salary"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		return employee;
	};
	@Autowired
	private NamedParameterJdbcTemplate template;
	

	public List<Employee> findAll(){
		System.out.println("konn");
		String sql = "select * from employees order by hire_date";
		List<Employee> empList = template.query(sql, EMPLOYEE_ROW_MAPPER);
		return empList;
	}
	
	public Employee load(int id) {
		String sql = "select * from employees where id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Employee emp = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
		return emp;
	}
	
	public void update(Employee employee) {
		String sql ="update employees set dependents_count = :dependentsCount where id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("dependentsCount", employee.getDependentsCount()).addValue("id", employee.getId());
		template.update(sql, param);
	}

}
