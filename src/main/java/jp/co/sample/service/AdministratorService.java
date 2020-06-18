package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

@Transactional
@Service
public class AdministratorService {
	@Autowired
	private AdministratorRepository administRepository;
	
	
	public void insert(Administrator administrator) {
		administRepository.insert(administrator);
	}
	
	
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) throws EmptyResultDataAccessException{
		return administRepository.findByMailAddressAndPassWord(mailAddress, password);
		
	}
	
	public Administrator login(String mailAddress, String password) {
		return administRepository.findByMailAddressAndPassWord(mailAddress, password);
	}

	
}
