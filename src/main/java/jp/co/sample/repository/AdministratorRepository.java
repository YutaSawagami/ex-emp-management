package jp.co.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

@Repository
public class AdministratorRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs,i) -> {
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};
	
	public void insert(Administrator administrator) throws EmptyResultDataAccessException{
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		String insertSql = "insert into administrators (name,mail_address,password) values(:name,:mailAddress,:password)";
		template.update(insertSql, param);
		System.out.println("abc");
	}
	
	public Administrator findByMailAddressAndPassWord(String mailAddress, String password){
		
		String sql = "select * from administrators where mail_address = :mailAddress and password = :password";
		System.out.println();
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password", password);
		System.out.println("templateの直前");
		Administrator administrator = template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
		return administrator;
		
		
		/*
		 * Copyright 2020 the original author or authors.
		 *
		 * Licensed under the Apache License, Version 2.0 (the "License");
		 * you may not use this file except in compliance with the License.
		 * You may obtain a copy of the License at
		 *
		 *      https://www.apache.org/licenses/LICENSE-2.0
		 *
		 * Unless required by applicable law or agreed to in writing, software
		 * distributed under the License is distributed on an "AS IS" BASIS,
		 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
		 * See the License for the specific language governing permissions and
		 * limitations under the License.
		 */

	}
}
