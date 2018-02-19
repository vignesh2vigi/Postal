package com.postalweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.postalweb.dao.ClientDao;
import com.postalweb.model.Client;

public class ClientServiceImpl implements ClientService {
	
	@Override
	public Client leadcount(Client client) {
		// TODO Auto-generated method stub
		return clientDao.leadcount(client);
	}
	@Autowired
	ClientDao clientDao;
	@Override
	public Client addlead(Client client) {
		// TODO Auto-generated method stub
		return clientDao.addlead(client);
	}
	@Override
	public Client login(Client client) {
		// TODO Auto-generated method stub
		return clientDao.login(client);
	}
	
	@Override
	public List<Client> list() {
		// TODO Auto-generated method stub
		return clientDao.list();
	}
	@Override
	public List<Client> atelist() {
		// TODO Auto-generated method stub
		return clientDao.atelist();
	}
	@Override
	public List<Client> complete() {
		// TODO Auto-generated method stub
		return clientDao.complete();
	}
	@Override
	public Client update(Client client) {
		// TODO Auto-generated method stub
		return clientDao.update(client);
	}
	@Override
	public Client leadprocess(Client client) {
		// TODO Auto-generated method stub
		return clientDao.leadprocess(client);
	}
	@Override
	public Client leadverify(Client client) {
		// TODO Auto-generated method stub
		return clientDao.leadverify(client);
	}
	@Override
	public Client leadnotverify(Client client) {
		// TODO Auto-generated method stub
		return clientDao.leadnotverify(client);
	}
	@Override
	public Client bill(Client client) {
		// TODO Auto-generated method stub
		return clientDao.bill(client);
	}
	@Override
	public List<Client> user() {
		// TODO Auto-generated method stub
		return clientDao.user();
	}
	
	
	

}
