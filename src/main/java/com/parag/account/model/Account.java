package com.parag.account.model;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;

//POJO class
@Table(name="account")
@Entity
public class Account {
	@Id									//Primary field in database
	@Column(name="acc_no")			    //what you want to refer in database
	@JsonProperty(value="account_no")  //what you want to refer in json
	private String accountNo;		  //Variable Declaration

	@Column(name="acc_name")
	@JsonProperty(value="account_name")
	private String accountName;

	@Column(name="birthday")
	@JsonProperty(value="birthday")
	private Date birthday;

	@Column(name="is_active")
	@JsonProperty(value="is_active")
	private boolean isActive;

	@Column(name="age")
	@JsonProperty(value="age")
	private int age;

	//Getter setters for the variables
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
