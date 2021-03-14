package menu;

import java.time.LocalDate;
import java.util.ArrayList;

import clients.Client;
import orders.Order;
import orders.OrderLine;
import utils.Number;

public class OrderMenuModel {

	private static OrderMenuModel myOrderMenuModel;
	private OrderMenuVista orderMenuVista;
	
	private Order newOrder;
	
	private OrderMenuModel() {
		this.orderMenuVista=OrderMenuVista.getMyOrderMenuVista();
	}
	
	public static OrderMenuModel getMyOrderMenuModel() {
		if(myOrderMenuModel==null) {
			myOrderMenuModel=new OrderMenuModel();
		}
		
		return myOrderMenuModel;
	}
	
	public Order getNewOrder() {
		return newOrder;
	}

	public void setNewOrder(Order newOrder) {
		this.newOrder = newOrder;
	}

	public static void setMyOrderMenuModel(OrderMenuModel myOrderMenuModel) {
		OrderMenuModel.myOrderMenuModel = myOrderMenuModel;
	}

	public void newOrder(Client c) {
		this.newOrder=new Order(c);
	}
	
	public void changeOrder(Client c) {
		int select=0;
		
		MainMenuModel.getMainMenuModel().getRepositoryOrders().showOrdersByClient(c);
		orderMenuVista.selectOrder();
		
		select=(Number.getNumberInRange(1, MainMenuModel.getMainMenuModel().getRepositoryOrders().getOrdersByClient(c.getDni()).size()))-1;
		newOrder=MainMenuModel.getMainMenuModel().getRepositoryOrders().getOrdersByClient(c.getDni()).get(select);
	}
	
	public Order selectOrderByClient(Client c) {
		int select=0;
		
		if(MainMenuModel.getMainMenuModel().getRepositoryOrders().showOrdersByClient(c)) {
			orderMenuVista.selectOrder();			
			select=(Number.getNumberInRange(1, MainMenuModel.getMainMenuModel().getRepositoryOrders().getOrdersByClient(c.getDni()).size()))-1;			
			return MainMenuModel.getMainMenuModel().getRepositoryOrders().getOrdersByClient(c.getDni()).get(select);			
		}else {
			MainMenuVista.getMyMainMenuVista().noOrdersFound();
			return null;
		}
	}
	
	public Order selectOrderByDate(LocalDate d) {
		int select=0;
		
		if(MainMenuModel.getMainMenuModel().getRepositoryOrders().showOrdersByDate(d)) {
			orderMenuVista.selectOrder();			
			select=(Number.getNumberInRange(1, MainMenuModel.getMainMenuModel().getRepositoryOrders().getOrdersByDate(d).size()))-1;		
			return MainMenuModel.getMainMenuModel().getRepositoryOrders().getOrdersByDate(d).get(select);			
		}else {
			MainMenuVista.getMyMainMenuVista().noOrdersByDate();
			return null;
		}	
	}
	
	public Order selectOrderByClientAndDate(Client c, LocalDate d) {
		int select=0;
		
		if(MainMenuModel.getMainMenuModel().getRepositoryOrders().showOrdersByClientAndDate(c, d)) {
			orderMenuVista.selectOrder();			
			select=(Number.getNumberInRange(1, MainMenuModel.getMainMenuModel().getRepositoryOrders().getOrdersByClientAndDate(c, d).size()))-1;			
			return MainMenuModel.getMainMenuModel().getRepositoryOrders().getOrdersByClientAndDate(c, d).get(select);			
		}else {
			MainMenuVista.getMyMainMenuVista().noOrdersByClientAndDate();
			return null;
		}
	}
	
	public boolean saveNoPaidOrder(Order newOrder) {
		
		ArrayList<OrderLine> noConfirmedOrders=newOrder.getCart().getNoConfirmedOrders();
		
		try {
			if((newOrder!=null)&&(noConfirmedOrders!=null)&&(noConfirmedOrders.size()!=0)) {
				
				newOrder.setSaved(true);
				MainMenuModel.getMainMenuModel().getRepositoryOrders().getAllOrders().add(newOrder);
				return true;
			}else {			
				orderMenuVista.noOrdersFound();
				return false;
			}			
		} catch (Exception e) {
			System.out.println("Error al guardar la orden en el repositorio!");
			return false;
		}
	}
	
	public boolean savePaidOrder(Order newOrder) {
		
		ArrayList<OrderLine> noConfirmedOrders=newOrder.getCart().getNoConfirmedOrders();
		
		try {
			if((newOrder!=null)&&(noConfirmedOrders!=null)&&(noConfirmedOrders.size()!=0)) {
				
				newOrder.setSaved(true);
				MainMenuModel.getMainMenuModel().getRepositoryOrders().getAllOrders().add(newOrder);
				return true;
			}else {			
				orderMenuVista.noOrdersFound();
				return false;
			}			
		} catch (Exception e) {
			System.out.println("Error al guardar la orden en el repositorio!");
			return false;
		}
	}
}
