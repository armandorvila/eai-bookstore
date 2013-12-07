package com.armandorv.miw.eai.bookstore.api.domain;

import com.armandorv.miw.eai.bookstore.api.service.ICustomerService;

/**
 * Model a customer of the bookshop.
 * 
 * @author armandorv
 * 
 */
public class Customer {

	private Long id;

	private String name;

	private String nif;

	private String address;
	
	private String mail;

	private boolean vip;

	private boolean loan;

	private boolean loanAccepted;
	
	private double debt;

	public Customer() {
	}

	public Customer(String name, String nif , String address) {
		super();
		this.name = name;
		this.nif = nif;
		this.address = address;
	}
	
	public void addDebt(double debt){
		this.debt += debt;
	}

	public boolean isVip(ICustomerService service) {
		return false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public boolean isVip() {
		return vip;
	}

	public void setVip(boolean vip) {
		this.vip = vip;
	}

	public boolean hasLoan() {
		return loan;
	}

	public void setLoan(boolean loan) {
		this.loan = loan;
	}

	public boolean hasLoanAccepted() {
		return loanAccepted;
	}

	public void setLoanAccepted(boolean loanAccepted) {
		this.loanAccepted = loanAccepted;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getDebt() {
		return debt;
	}

	public void setDebt(double debt) {
		this.debt = debt;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nif == null) ? 0 : nif.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (nif == null) {
			if (other.nif != null)
				return false;
		} else if (!nif.equals(other.nif))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", nif=" + nif
				+ ", address=" + address + ", mail=" + mail + ", vip=" + vip
				+ ", loan=" + loan + ", loanAccepted=" + loanAccepted
				+ ", debt=" + debt + "]";
	}
	
	

}