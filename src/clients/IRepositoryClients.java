package clients;

import java.util.ArrayList;

public interface IRepositoryClients {
	public ArrayList<Client> getAllClients();
	public Client searchClientByName(String name);
	public boolean updateClient(Client c);
	public boolean addClient(Client c);
	public boolean deleteClient(String dni);
	public Client searchClientByDni(String dni);
}
