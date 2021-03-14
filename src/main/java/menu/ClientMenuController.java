package menu;

import java.util.ArrayList;

import clients.Client;
import orders.Order;
import utils.Number;
import utils.Utils;

public class ClientMenuController implements IClientMenuController{

	private static ClientMenuController myClientMenuController;
	private ClientMenuVista clientMenuVista;
	
	private ClientMenuController() {
		this.clientMenuVista=ClientMenuVista.getMyClientMenuVista();
	}

	public static ClientMenuController getMyClientMenuController()
	{
		if(myClientMenuController==null)
		{
			myClientMenuController=new ClientMenuController();
		}
		
		return myClientMenuController;
	}
	
	public void options()
	{
		boolean exit=false;
		int select;
		
		do {
			clientMenuVista.options();
			select=Number.getNumberInRange(1, 5);
			
			switch(select) {
			
			case 1: newClient();
					break;
			case 2: editClient();
					break;
			case 3: dropClient();
					break;
			case 4: showClients();
					break;
			case 5: exit=true;
					break;
			}
			
		}while(!exit);
		
	}
	
	public void editClient()
	{
		boolean exit=false;
		int select;
		Client c;
		
		c=MainMenuController.getMyMainMenuController().selectClient();
		
		if(c!=null) {
			do {
				clientMenuVista.editClient();
				select=Number.getNumberInRange(1, 5);
				
				switch(select)
				{
				case 1: changeName(c);
						break;
				case 2: changeDNI(c);
						break;
				case 3: changeAge(c);
						break;
				case 4: editAddresses(c);
						break;
				case 5: exit=true;
						break;
				}
			}while(!exit);			
		}
	}
	
	public void editAddresses(Client c)
	{
		boolean exit=false;
		int select;
		
		do {
			clientMenuVista.editAddresses();
			select=Number.getNumberInRange(1, 4);
			
			switch(select)
			{
			case 1: changeAddress(c);
					break;
			case 2: addAddress(c);
					break;
			case 3: dropAddress(c);
					break;
			case 4: exit=true;
					break;

			}
		}while(!exit);
	}
	
	public boolean changeName(Client c)
	{
		try {
			String name;
			
			if(c!=null)
			{
				clientMenuVista.insertNewName();
				name=Utils.getMyUtils().getName();
				c.setName(name);
				if(MainMenuModel.getMainMenuModel().getRepositoryClients().updateClient(c))
				{
					clientMenuVista.updateClientSuccessful();
					Utils.getMyUtils().promptEnterKey();
					return true;
				}
			}
			else {
				clientMenuVista.clientDoNotExist();
			}
			
			return false;
		} catch (Exception e) {
			System.out.println("Error al cambiar el nombre del cliente");
			return false;
		}
	}
	
	public boolean changeDNI(Client c) {
		
		try {
			String dni;

			if(c!=null)
			{
				clientMenuVista.insertNewDNI();
				dni=Utils.getMyUtils().getDNI();
				c.setDni(dni);
				if(MainMenuModel.getMainMenuModel().getRepositoryClients().updateClient(c))
				{
					clientMenuVista.updateClientSuccessful();
					Utils.getMyUtils().promptEnterKey();
					return true;
				}
			}
			else
			{
				clientMenuVista.clientDoNotExist();
			}
			
			return false;
			
		}catch(Exception e) {
			System.out.println("Error al cambiar el DNI del cliente");
			return false;
		}
	}
	
	public boolean changeAge(Client c) {
		
		try {
			int age;
			
			if(c!=null)
			{
				clientMenuVista.insertNewAge();
				age=Number.getPositiveNumber();
				c.setAge(age);
				if(MainMenuModel.getMainMenuModel().getRepositoryClients().updateClient(c))
				{
					clientMenuVista.updateClientSuccessful();
					Utils.getMyUtils().promptEnterKey();
					return true;
				}
			}
			else
			{
				clientMenuVista.clientDoNotExist();
			}
			
			return false;
			
		}catch(Exception e) {
			System.out.println("Error al cambiar la edad del cliente");
			return false;
		}
	}
	
	public boolean changeAddress(Client c) {
		
		try {
			String address;
			int select;
			
			if(c!=null)
			{
				if(c.getAddress().size()!=0) {		
					c.showAddress();
					clientMenuVista.selectAddress();
					select=Number.getNumberInRange(1,c.getAddress().size())-1;
					clientMenuVista.insertNewAddress();
					address=Utils.getMyUtils().getString();
					
					if(c.changeAddress(select, address)){
						clientMenuVista.updateClientSuccessful();
						Utils.getMyUtils().promptEnterKey();
						return true;
					}
				}else {
					clientMenuVista.clientHasNoAddress();
				}
			}
			else
			{
				clientMenuVista.clientDoNotExist();
			}
			
			return false;
			
		}catch(Exception e) {
			System.out.println("Error al cambiar la direccion del cliente");
			return false;
		}
	}
	
	public boolean addAddress(Client c) {
		
		try {
			String address;
			
			if(c!=null)
			{
				clientMenuVista.insertNewAddress();
				address=Utils.getMyUtils().getString();
				
				c.setAddress(address);
				clientMenuVista.updateClientSuccessful();
				Utils.getMyUtils().promptEnterKey();
				return true;
			}
			else
			{
				clientMenuVista.clientDoNotExist();
			}
			
			return false;
			
		}catch(Exception e) {
			System.out.println("Error al añadir la direccion del cliente");
			return false;
		}
	}
	
	public boolean dropAddress(Client c) {
		
		try {
			int select;
			String address;
			
			if(c!=null)
			{
				if(c.getAddress().size()!=0) {
					c.showAddress();
					clientMenuVista.selectDropAddress();
					select=Number.getNumberInRange(1,c.getAddress().size())-1;
					address=c.getAddress(select);
					
					if(c.dropAddress(select,address))
					{
						clientMenuVista.addressDroppedSuccessfuly();
						Utils.getMyUtils().promptEnterKey();
						return true;					
					}					
				}else {
					clientMenuVista.clientHasNoAddress();
				}
			}
			else
			{
				clientMenuVista.clientDoNotExist();
			}
			
			return false;
			
		}catch(Exception e) {
			System.out.println("Error al borrar la direccion del cliente");
			return false;
		}
	}
	
	public boolean newClient() {
		
		try
		{
			Client c=new Client();
			clientMenuVista.newClient();
			clientMenuVista.newName();
			c.setName(Utils.getMyUtils().getName());
			clientMenuVista.newDNI();
			c.setDni(Utils.getMyUtils().getDNI());
			clientMenuVista.newAge();
			c.setAge(Number.getPositiveNumber());
			clientMenuVista.newAddress();
			c.setAddress(Utils.getMyUtils().getString());
			
			if(MainMenuModel.getMainMenuModel().getRepositoryClients().searchClientByDni(c.getDni())!=null) {
				clientMenuVista.clientDoesExit();
				return false;
			}
			else {
				MainMenuModel.getMainMenuModel().getRepositoryClients().addClient(c);
				clientMenuVista.clientCreatedSuccessfuly();
				Utils.getMyUtils().promptEnterKey();
				return true;				
			}
			
		}catch(Exception e){		
			clientMenuVista.errorCreatingClient();
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean dropClient() {
		
		ArrayList<Order> orders=new ArrayList<Order>();
		Client c;
		
		try {		
			c=MainMenuController.getMyMainMenuController().selectClient();
			
			if(c!=null)
			{
				clientMenuVista.ordersWillBeDroppedWarning();
				if(Utils.getMyUtils().confirmation()) {
					orders=MainMenuModel.getMainMenuModel().getRepositoryOrders().getOrdersByClient(c.getDni());
					for (int i = 0; i < orders.size(); i++) {
						MainMenuModel.getMainMenuModel().getRepositoryOrders().getAllOrders().remove(orders.get(i));
					}
					MainMenuModel.getMainMenuModel().getRepositoryClients().deleteClient(c.getDni());
					clientMenuVista.clientDroppedSuccesfuly();
					Utils.getMyUtils().promptEnterKey();
					return true;						
				}
			}
			
			return false;
			
		} catch (Exception e) {
			System.out.println("Error al cambiar el nombre del cliente");
			return false;
		}
	}
	
	public void showClients() {
		if(MainMenuModel.getMainMenuModel().getRepositoryClients().showClientList()) {
			Utils.getMyUtils().promptEnterKey();			
		}
	}

}
