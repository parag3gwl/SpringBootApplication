package com.parag.account.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.parag.account.model.Account;

//implement the CrudRepository interface to access the spring data
//<Account - which type of data we want to access,String- primary key type>
//Spring data functions to perform CRUD operation on data
@Repository
public interface AccountDAO extends CrudRepository<Account, String>{
}

