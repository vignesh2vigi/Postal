package com.postalweb.service;

import java.util.List;

import com.postalweb.model.Client;

public interface ClientService {
	public Client addlead(Client client);
	public Client login(Client client);
	
	public Client leadcount(Client client);
	public List<Client> list();
	public List<Client> atelist();
	public List<Client> complete();
	public Client update(Client client);
	public Client bill(Client client);
	public Client leadprocess(Client client);
	public Client leadverify(Client client);
	public Client leadnotverify(Client client);
	public List<Client> user();
}
