package clients;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import utils.Utils;

@SuppressWarnings("serial")
@XmlRootElement(name="RepositoryClient")
@XmlAccessorType(XmlAccessType.FIELD)
public class RepositoryClients implements IRepositoryClients,Serializable{

	@XmlElement(name="client")
	private ArrayList<Client> clientList;
	private static RepositoryClients myRepository;
	
	private RepositoryClients() {
	
		clientList=new ArrayList<Client>();
	}
	
	public static RepositoryClients getMyRepositoryClients()
	{
		if(myRepository==null)
		{
			myRepository=new RepositoryClients();
		}
		
		return myRepository;
	}

	public static void setMyRepository(RepositoryClients myRepository) {
		RepositoryClients.myRepository = myRepository;
	}
	
	/**
	 * @return clientList: Devuelve el ArrayList de clientes.
	 */
	public ArrayList<Client> getAllClients() {
		
		return clientList;
	}

	public void setClientList(ArrayList<Client> clientList) {
		this.clientList = clientList;
	}

	/**
	 * @param name: Nombre del cliente a buscar.
	 * @return c: Devuelve el primer cliente encontrado por nombre. En caso contrario devuelve null.
	 */
	public Client searchClientByName(String name) {
		
		boolean found=false;
		Client c=null;
		
		for(int i=0; i<clientList.size()&&!found; i++)
		{
			c=clientList.get(i);
			if(c.name.equals(name))
			{
				found=true;
				return c;
			}
		}
		
		return null;
	}

	/**
	 * @param c: Cliente a actualizar.
	 * @return updated: Devuelve un booleano a true si se a actualizado correctamente o a false en caso contrario.
	 */
	public boolean updateClient(Client c) {
		
		boolean updated=false;
		
		if(c!=null&&c.name!=null)
		{
			if(searchClientByDni(c.dni)!=null)
			{
				Client c2;
				
				for(int i=0; i<clientList.size()&&!updated; i++)
				{
					c2=clientList.get(i);
					if(c2.dni.equals(c.dni))
					{
						clientList.set(i, c);
						updated=true;
					}
				}
				
			}
		}
		
		return updated;
	}

	/**
	 * @param c: Cliente a añadir.
	 * @return added: Devuelve un booleano a true si se a añadido correctamente o a false en caso contrario.
	 */
	public boolean addClient(Client c) {
		
		boolean added=false;
		
		if(c!=null&&c.dni!=null&&searchClientByDni(c.dni)==null)
		{
			clientList.add(c);
			added=true;
		}	
		
		return added;
	}

	/**
	 * @param dni: Dni del cliente a eliminar.
	 * @return deleted: Devuelve true si el cliente ha sido eliminado. En caso contrario devuelve false.
	 */
	public boolean deleteClient(String dni) {

		boolean deleted=false;
		
		if(dni!=null&&searchClientByDni(dni)!=null)
		{
			clientList.remove(searchClientByDni(dni));
			deleted=true;
		}	
		
		return deleted;
	}

	/**
	 * @param dni: Dni del cliente a buscar.
	 * @return c: Devuelve el cliente si ha sido encontrado por dni. En caso contrario devuelve null.
	 */
	public Client searchClientByDni(String dni) {
		
		boolean found=false;
		Client c=null;
		
		for(int i=0; i<clientList.size()&&!found; i++)
		{
			c=clientList.get(i);
			if(c.dni.equals(dni))
			{
				found=true;
				return c;
			}
		}
		
		if(found==false) {
			return null;
		}
		
		return c;
	}
	
	public boolean showClientList() {
		
		if(this.clientList.size()!=0) {
			for (int i = 0; i < clientList.size(); i++) {
				System.out.println((i+1)+".- "+clientList.get(i).toString());
			}			
			return true;
		}else {
			System.out.println("\nNo existen clientes en la base de datos!");
			Utils.getMyUtils().promptEnterKey();
			return false;
		}
	}
}
