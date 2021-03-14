package menu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import clients.Client;
import orders.Order;
import utils.Number;
import utils.Utils;

public class MainMenuController implements IMainMenuController{
	
	private static MainMenuController myMainMenuController;
	private MainMenuModel mainMenuModel;
	private MainMenuVista mainMenuVista;
	private ClientMenuController clientMenuController;
	private OrderMenuController orderMenuController;
	
	
	private MainMenuController() {
		this.mainMenuModel=MainMenuModel.getMainMenuModel();
		this.mainMenuVista=MainMenuVista.getMyMainMenuVista();
		this.clientMenuController=ClientMenuController.getMyClientMenuController();
		this.orderMenuController=OrderMenuController.getMyOrderMenuController();
	}
	
	public static MainMenuController getMyMainMenuController() {
		if(myMainMenuController==null) {
			myMainMenuController=new MainMenuController();
		}
		
		return myMainMenuController;
	}

	public MainMenuModel getMainMenuModel() {
		return mainMenuModel;
	}

	public void setMainMenuModel(MainMenuModel mainMenuModel) {
		this.mainMenuModel = mainMenuModel;
	}

	public MainMenuVista getMainMenuVista() {
		return mainMenuVista;
	}

	public void setMainMenuVista(MainMenuVista mainMenuVista) {
		this.mainMenuVista = mainMenuVista;
	}

	public void options()
	{
		boolean exit=false;
		int select;
		
		loadAll();
				
		do {
			mainMenuVista.options();
			select=Number.getNumberInRange(1, 11);
			
			switch(select)
			{
			case 1: clientMenuController.options();
					break;
			case 2: newOrder();
					break;
			case 3: changeOrderBySelect();
					break;
			case 4: deleteOrderBySelect();
					break;
			case 5: showOrderBySelect();
					break;
			case 6: viewOrdersNotPayed();
					break;
			case 7: viewOrderPendingDelivered();
					break;
			case 8: cashToday();
					break;
			case 9: cashThisMonth();
					break;
			case 10: cashTotal();
					 break;
			case 11: saveAllAndClose();
					 exit=true;
					 break;
			}
			
		}while(!exit);
	}
	
	public void changeOrderBySelect() {
		Client c;
		boolean exit=false;
		int select;
		
		
		do {
			mainMenuVista.changeOrder();
			select=Number.getNumberInRange(1, 4);
			switch(select) {
			case 1: c=selectClient();
					if(c!=null) {
						if(changeOrder(c)) {
							exit=true;
						}
					}else {
						exit=true;						
					}
					break;
			case 2: mainMenuVista.insertDate();
					if(changeOrder(Utils.getMyUtils().getLocalDate())) {
						exit=true;
					}
					break;
			case 3: c=selectClient();
					if(c!=null) {
						mainMenuVista.insertDate();
						if(changeOrder(c, Utils.getMyUtils().getLocalDate())) {
							exit=true;
						}						
					}else {
						exit=true;
					}
					break;
			case 4: exit=true;
					break;
			}
		}while(!exit);
	}
	
	public void deleteOrderBySelect() {
		Client c;
		boolean exit=false;
		int select;
		
		
		do {
			mainMenuVista.deleteOrder();
			select=Number.getNumberInRange(1, 4);
			switch(select) {
			case 1: c=selectClient();
					if(c!=null) {
						if(deleteOrder(c)) {
							exit=true;
						}
					}else {
						exit=true;
					}
					break;
			case 2: mainMenuVista.insertDate();
					if(deleteOrder(Utils.getMyUtils().getLocalDate())) {
						exit=true;
					}
					break;
			case 3: c=selectClient();
					if(c!=null) {
						mainMenuVista.insertDate();
						if(deleteOrder(c, Utils.getMyUtils().getLocalDate())) {
							exit=true;
						}						
					}else {
						exit=true;
					}
					break;
			case 4: exit=true;
					break;
			}
		}while(!exit);
	}
	
	public void showOrderBySelect() {
		Client c;
		LocalDate d;
		Order o;
		boolean exit=false;
		int select;
		
		
		do {
			mainMenuVista.showOrder();
			select=Number.getNumberInRange(1, 5);
			switch(select) {
			case 1: showAllOrders();
					Utils.getMyUtils().promptEnterKey();
					break;
			case 2: c=selectClient();
					if(c!=null) {
						o=OrderMenuModel.getMyOrderMenuModel().selectOrderByClient(c);
						if(o!=null) {
							o.showOrder();
						}else {
							exit=true;
						}
						Utils.getMyUtils().promptEnterKey();
					}
					break;
			case 3: mainMenuVista.insertDate();
					d=Utils.getMyUtils().getLocalDate();
					o=OrderMenuModel.getMyOrderMenuModel().selectOrderByDate(d);
					if(o!=null) {
						o.showOrder();
					}
					Utils.getMyUtils().promptEnterKey();
					break;
			case 4: c=selectClient();
					if(c!=null) {
						mainMenuVista.insertDate();
						d=Utils.getMyUtils().getLocalDate();
						o=OrderMenuModel.getMyOrderMenuModel().selectOrderByClientAndDate(c, d);
						if(o!=null) {
							o.showOrder();
						}
						Utils.getMyUtils().promptEnterKey();
					}else {
						exit=true;
					}
					break;
			case 5: exit=true;
					break;
			}
		}while(!exit);
	}
	public boolean newOrder() {
		
		Client c=null;
		
		c=selectClient();
		
		if(c!=null) {
			orderMenuController.selectOption(c,true,false);			
		}
		
		return false;
	}

	public boolean changeOrder(Client c) {
		
		if(orderMenuController.selectOption(c,false,false)) {
			return true;
		}
		
		return false;
	}

	public boolean changeOrder(LocalDate d) {
		
		Order o;
		int select;
		
		if(mainMenuModel.getRepositoryOrders().showOrdersByDate(d)) {
			mainMenuVista.selectOrder();			
			select=(Number.getNumberInRange(1, mainMenuModel.getRepositoryOrders().getOrdersByDate(d).size()))-1;
			o=mainMenuModel.getRepositoryOrders().getOrdersByDate(d).get(select);
			OrderMenuModel.getMyOrderMenuModel().setNewOrder(o);
			orderMenuController.selectOption(null, false, true);
			return true;
		}else {
			mainMenuVista.noOrdersByDate();
		}
		
		return false;
	}

	public boolean changeOrder(Client c, LocalDate d) {
		
		Order o;
		int select;
		
		if(mainMenuModel.getRepositoryOrders().showOrdersByClientAndDate(c, d)) {
			mainMenuVista.selectOrder();	
			select=(Number.getNumberInRange(1, mainMenuModel.getRepositoryOrders().getOrdersByClientAndDate(c, d).size()))-1;
			o=mainMenuModel.getRepositoryOrders().getOrdersByClientAndDate(c, d).get(select);
			OrderMenuModel.getMyOrderMenuModel().setNewOrder(o);
			orderMenuController.selectOption(c, false, true);
			return true;
		}else {
			mainMenuVista.noOrdersByClientAndDate();
		}
		
		return false;
	}

	public boolean deleteOrder(Client c) {
		
		Order o;
		int select;
		
		if(mainMenuModel.getRepositoryOrders().showOrdersByClient(c)) {
			mainMenuVista.selectOrder();
			select=(Number.getNumberInRange(1, mainMenuModel.getRepositoryOrders().getOrdersByClient(c.getDni()).size()))-1;
			o=mainMenuModel.getRepositoryOrders().getOrdersByClient(c.getDni()).get(select);
			mainMenuVista.deleteOrderConfirmation();
			
			if(Utils.getMyUtils().confirmation()) {
				mainMenuModel.getRepositoryOrders().getAllOrders().remove(o);
				c.setOrders(c.getOrders()-1);
				mainMenuVista.orderDeleted();
				Utils.getMyUtils().promptEnterKey();
				return true;
			}
		}else {
			mainMenuVista.noOrdersFound();
			Utils.getMyUtils().promptEnterKey();
		}
		
		return false;
	}

	public boolean deleteOrder(LocalDate d) {
		Client c;
		Order o;
		int select;
		
		if(mainMenuModel.getRepositoryOrders().showOrdersByDate(d)) {
			mainMenuVista.selectOrder();			
			select=(Number.getNumberInRange(1, mainMenuModel.getRepositoryOrders().getOrdersByDate(d).size()))-1;
			o=mainMenuModel.getRepositoryOrders().getOrdersByDate(d).get(select);
			c=o.getClient();
			mainMenuVista.deleteOrderConfirmation();
			
			if(Utils.getMyUtils().confirmation()) {
				mainMenuModel.getRepositoryOrders().getAllOrders().remove(o);
				c.setOrders(c.getOrders()-1);
				mainMenuVista.orderDeleted();
				Utils.getMyUtils().promptEnterKey();
				return true;
			}
		}else {
			mainMenuVista.noOrdersByDate();
			Utils.getMyUtils().promptEnterKey();
		}
		
		return false;
	}

	public boolean deleteOrder(Client c, LocalDate d) {

		Order o;
		int select;
		
		if(mainMenuModel.getRepositoryOrders().showOrdersByClientAndDate(c, d)) {
			mainMenuVista.selectOrder();	
			select=(Number.getNumberInRange(1, mainMenuModel.getRepositoryOrders().getOrdersByClientAndDate(c, d).size()))-1;
			o=mainMenuModel.getRepositoryOrders().getOrdersByClientAndDate(c, d).get(select);
			mainMenuVista.deleteOrderConfirmation();
			
			if(Utils.getMyUtils().confirmation()) {
				mainMenuModel.getRepositoryOrders().getAllOrders().remove(o);
				c.setOrders(c.getOrders()-1);
				mainMenuVista.orderDeleted();
				Utils.getMyUtils().promptEnterKey();
				return true;
			}
		}else {
			mainMenuVista.noOrdersByClientAndDate();
			Utils.getMyUtils().promptEnterKey();
		}
		
		return false;
	}

	public void cashToday() {
	
		LocalDate dt=LocalDate.now();

		mainMenuVista.showCashToday();
		System.out.println(mainMenuModel.getRepositoryOrders().getInputByDate(dt)+" Euros");
		Utils.getMyUtils().promptEnterKey();
		
	}

	public void cashThisMonth() {
		String date="";
		LocalDate dt=LocalDate.now();
		DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy-MM");
		
		date=df.format(dt);
		date=date+"-01";
		LocalDate di=LocalDate.parse(date);
		
		date=df.format(dt);
		date=date+"-31";
		LocalDate de=LocalDate.parse(date);
		
		mainMenuVista.showCashThisMonth();
		System.out.println(mainMenuModel.getRepositoryOrders().getInputByDate(di, de)+" Euros");
		Utils.getMyUtils().promptEnterKey();
	}

	public void cashTotal() {
		mainMenuVista.showCashTotal();
		System.out.println(mainMenuModel.getRepositoryOrders().getAllInput()+" Euros");
		Utils.getMyUtils().promptEnterKey();
	}

	public void viewOrdersNotPayed() {
		ArrayList<Order> orders=new ArrayList<Order>();
		boolean found=false;
		
		if(mainMenuModel.getRepositoryOrders().getAllOrders().size()!=0) {
			orders=mainMenuModel.getRepositoryOrders().getAllOrders();
			for (int i = 0, j = 1; i < orders.size(); i++) {
				if(orders.get(i).isPaid()==false) {
					System.out.println(j+".- "+orders.get(i).toStringShort());
					j++;
					found=true;
				}				
			}
			Utils.getMyUtils().promptEnterKey();
		}
		
		if(!found) {
			mainMenuVista.noOrders();
			Utils.getMyUtils().promptEnterKey();
		}			
	}

	public boolean setDelivered(Client c) {
		
		mainMenuVista.askIfDelivered();
		int select;
		
		if(mainMenuModel.getRepositoryOrders().getOrdersByClient(c.getDni()).size()==0||mainMenuModel.getRepositoryOrders().getOrdersByClient(c.getDni())==null) {
			mainMenuVista.noOrdersFound();
		}
		else
		{
			mainMenuModel.getRepositoryOrders().showOrdersByClient(c);
			mainMenuVista.selectOrder();
			select=(Number.getNumberInRange(1, mainMenuModel.getRepositoryOrders().getOrdersByClient(c.getDni()).size()))-1;
			mainMenuVista.askIfDelivered();
			
			if(Utils.getMyUtils().confirmation()) {
				mainMenuModel.getRepositoryOrders().getOrdersByClient(c.getDni()).get(select-1).setDelivered(true);
				mainMenuVista.orderDelivered();
				Utils.getMyUtils().promptEnterKey();
			}			
		}
		
		return false;
	}
	
	public boolean setNotDelivered(Client c) {
		
		int select;
		
		if(mainMenuModel.getRepositoryOrders().getOrdersByClient(c.getDni()).size()==0||mainMenuModel.getRepositoryOrders().getOrdersByClient(c.getDni())==null) {
			mainMenuVista.noOrdersFound();
		}
		else
		{
			mainMenuModel.getRepositoryOrders().showOrdersByClient(c);
			mainMenuVista.selectOrder();
			select=(Number.getNumberInRange(1, mainMenuModel.getRepositoryOrders().getOrdersByClient(c.getDni()).size()))-1;
			mainMenuVista.askIfNotDelivered();
			
			if(Utils.getMyUtils().confirmation()) {
				mainMenuModel.getRepositoryOrders().getOrdersByClient(c.getDni()).get(select-1).setDelivered(false);
				mainMenuVista.orderNotDelivered();
				Utils.getMyUtils().promptEnterKey();
			}		
		}
		
		return false;
	}
	
	public void viewOrderPendingDelivered() {
		
		ArrayList<Order> orders=new ArrayList<Order>();
		boolean found=false;
		
		if(mainMenuModel.getRepositoryOrders().getAllOrders().size()!=0) {
			orders=mainMenuModel.getRepositoryOrders().getAllOrders();
			for (int i = 0, j = 1; i < orders.size(); i++) {
				if(orders.get(i).getDelivered()==false) {
					System.out.println(j+".- "+orders.get(i).toStringShort());
					j++;
					found=true;
				}				
			}
			Utils.getMyUtils().promptEnterKey();
		}
		
		if(!found) {
			mainMenuVista.noOrders();
			Utils.getMyUtils().promptEnterKey();
		}
	}

	public boolean saveAllAndClose() {
		Utils.getMyUtils().saveClients("clients.xml");
		Utils.getMyUtils().saveOrders("orders.xml");
		return true;
	}
	
	public boolean loadAll() {
		
		try {
			Utils.getMyUtils().loadClients("clients.xml");
			Utils.getMyUtils().loadOrders("orders.xml");
			return true;
		} catch (Exception e) {
			System.out.println("Error al cargar la base de datos");
			e.printStackTrace();
			return false;
		}
	}
	
	public Client selectClient() {
		
		Client c=null;
		int select;
		
		mainMenuVista.clientList();
		mainMenuModel.getRepositoryClients().showClientList();
		
		if(mainMenuModel.getRepositoryClients().getAllClients().size()>0) {
			mainMenuVista.selectClient();
			
			do
			{
				select=Number.getPositiveNumber()-1;
				if(select>=mainMenuModel.getRepositoryClients().getAllClients().size())
				{
					System.out.print("Por favor, introduzca un numero correspondiente a la lista de clientes: ");
				}
				
			}while(select>=mainMenuModel.getRepositoryClients().getAllClients().size());
			
			c=mainMenuModel.getRepositoryClients().getAllClients().get(select);
			
			if(c!=null)
			{
				return c;
			}
			else
			{
				mainMenuVista.clientNotFound();
				Utils.getMyUtils().promptEnterKey();
			}
		}
		
		return c;
	}
	
	public void showAllOrders() {
		int select;
		
		if(mainMenuModel.getRepositoryOrders().showAllOrders()) {
			OrderMenuVista.getMyOrderMenuVista().selectOrder();
			select=Number.getNumberInRange(1, mainMenuModel.getRepositoryOrders().getAllOrders().size())-1;
			mainMenuModel.getRepositoryOrders().getAllOrders().get(select).showOrder();
		}else {
			mainMenuVista.noOrders();
		}
	}
}
