package clients;

import java.util.ArrayList;

public class RepositoryClients implements IRepositoryClients{

	ArrayList<Client> clientList=new ArrayList<>();

	/**
	 * @return clientList: Devuelve el ArrayList de clientes.
	 */
	@Override
	public ArrayList<Client> getAllClients() {
		
		return clientList;
	}

	/**
	 * @param name: Nombre del cliente a buscar.
	 * @return c: Devuelve el primer cliente encontrado por nombre. En caso contrario devuelve null.
	 */
	@Override
	public Client searchClientByName(String name) {
		
		boolean found=false;
		Client c=null;
		
		for(int i=0; i<clientList.size()&&!found; i++)
		{
			c=clientList.get(i);
			if(c.name.equals(name))
			{
				found=true;
			}
		}
		
		return c;
	}

	/**
	 * @param c: Cliente a actualizar.
	 * @return updated: Devuelve un booleano a true si se a actualizado correctamente o a false en caso contrario.
	 */
	@Override
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
						clientList.add(i, c);
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
	@Override
	public boolean addClient(Client c) {
		
		boolean added=false;
		
		if(c!=null&&c.dni!=null&&searchClientByDni(c.dni)!=null)
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
	@Override
	public boolean deleteClient(String dni) {

		boolean deleted=false;
		
		if(dni!=null&&searchClientByDni(dni)==null)
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
	@Override
	public Client searchClientByDni(String dni) {
		
		boolean found=false;
		Client c=null;
		
		for(int i=0; i<clientList.size()&&!found; i++)
		{
			c=clientList.get(i);
			if(c.name.equals(dni))
			{
				found=true;
			}
		}
		
		return c;
	}
}
