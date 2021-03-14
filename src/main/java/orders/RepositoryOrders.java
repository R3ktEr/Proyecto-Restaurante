package orders;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import clients.Client;
import clients.RepositoryClients;
import menu.MainMenuModel;
import menu.OrderMenuVista;


@SuppressWarnings("serial")
@XmlRootElement(name="RepositoryOrders")
@XmlAccessorType(XmlAccessType.FIELD)
public class RepositoryOrders implements IRepositoryOrders, Serializable {

	@XmlElement(name="order")
	private ArrayList<Order> orders;
	private static RepositoryOrders myRepository;
	
	
	private RepositoryOrders() {
		orders = new ArrayList<Order>();
	}
	
	public static RepositoryOrders getMyRepositoryOrders()
	{
		if(myRepository==null)
		{
			myRepository=new RepositoryOrders();
		}
		
		return myRepository;
	}

	public static void setMyRepository(RepositoryOrders myRepository) {
		RepositoryOrders.myRepository = myRepository;
	}

	public ArrayList<Order> getAllOrders() {
		
		return orders;
		
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public ArrayList<Order> getOrdersByClient(String dni) {
		
		ArrayList<Order> clientOrders=new ArrayList<Order>();
		
		if(dni!=null)
		{
			for (int i = 0; i < orders.size(); i++) 
			{
				if(orders.get(i).getClient().equals(RepositoryClients.getMyRepositoryClients().searchClientByDni(dni)))
				{						
					clientOrders.add(orders.get(i));
				}
			}
		}
		
		return clientOrders;
	}
	
	public ArrayList<Order> getOrdersByDate(LocalDate uniqueDate) {
		
		ArrayList<Order> dateOrders=new ArrayList<Order>();
		LocalDate orderDate;
		
		if(uniqueDate!=null)
		{
			for (int i = 0; i < orders.size(); i++) 
			{
				orderDate=orders.get(i).getDate().toLocalDate();
				if(orderDate.isEqual(uniqueDate))
				{
					dateOrders.add(orders.get(i));
				}
			}
		}
		
		return dateOrders;
	}

	public ArrayList<Order> getOrdersNoDelivered() {
		
		ArrayList<Order> noDeliveredOrders=new ArrayList<Order>();
		
		for (int i = 0; i < orders.size(); i++) 
		{
			if(orders.get(i).getDelivered()==false)
			{
				noDeliveredOrders.add(orders.get(i));
			}
		}
		
		return noDeliveredOrders;
	}

	public ArrayList<Order> getOrdersNoPayed() {
		
		ArrayList<Order> noPayedOrders=new ArrayList<Order>();
		
		for (int i = 0; i < orders.size(); i++) 
		{
			if(orders.get(i).getDelivered()==false)
			{
				noPayedOrders.add(orders.get(i));
			}
		}
		
		return null;
	}

	public double getAllInput() {

		double totalCash=0;
		
		for (int i = 0; i < orders.size(); i++) {
			totalCash+=orders.get(i).getTotal();
		}
		
		totalCash=Math.round(totalCash*100.0)/100.0;
		
		return totalCash;
	}

	public double getInputByDate(LocalDate ini, LocalDate end) {

		double totalCash=0;
		
		for (int i = 0; i < orders.size(); i++) {
			if((orders.get(i).getDate().toLocalDate().isAfter(ini)&&(orders.get(i).getDate().toLocalDate().isBefore(end)))) {
				totalCash+=orders.get(i).getTotal();				
			}
		}
		
		totalCash=Math.round(totalCash*100.0)/100.0;
		
		return totalCash;
	}
	
	public double getInputByDate(LocalDate d) {

		double totalCash=0;
		
		for (int i = 0; i < orders.size(); i++) {
			if((orders.get(i).getDate().toLocalDate().isEqual(d))) {		
				totalCash+=orders.get(i).getTotal();
			}
		}
		
		
		totalCash=Math.round(totalCash*100.0)/100.0;

		return totalCash;
	}

	public boolean showOrdersByClient(Client c) {
		
		ArrayList<Order> clientOrders=null;
		
		if((getOrdersByClient(c.getDni())!=null)&&(getOrdersByClient(c.getDni()).size()!=0)) {
			clientOrders=getOrdersByClient(c.getDni());
			
			OrderMenuVista.getMyOrderMenuVista().clientOrders();
			
			for (int i = 0; i < clientOrders.size(); i++) {
				
				System.out.println((i+1)+".- "+clientOrders.get(i).toString());
			}
			
			return true;
		}
		
		return false;
	}
	
	public boolean showOrdersByDate(LocalDate d) {
		
		ArrayList<Order> orders=new ArrayList<Order>();
		
		orders=getOrdersByDate(d);
		
		if(orders.size()!=0) {
			OrderMenuVista.getMyOrderMenuVista().ordersByDate();
			
			for (int i = 0; i < orders.size(); i++) {
				
				System.out.println((i+1)+".- "+orders.get(i).toStringWithClient());
			}
			
			return true;
		}
		
		return false;
	}
	
	public ArrayList<Order> getOrdersByClientAndDate(Client c, LocalDate d) {
		ArrayList<Order> ordersByClient=new ArrayList<Order>();
		ArrayList<Order> ordersByDate=new ArrayList<Order>();
		ArrayList<Order> ordersByClientAndDate=new ArrayList<Order>();
		
		ordersByClient=MainMenuModel.getMainMenuModel().getRepositoryOrders().getOrdersByClient(c.getDni());
		ordersByDate=MainMenuModel.getMainMenuModel().getRepositoryOrders().getOrdersByDate(d);
		
		for (int i = 0; i < ordersByClient.size(); i++) {
			for (int j = 0; j < ordersByDate.size(); j++) {
				if(ordersByClient.get(i).getDate().isEqual(ordersByDate.get(j).getDate())) {
					ordersByClientAndDate.add(ordersByDate.get(j));
				}
			}
		}
		
		return ordersByClientAndDate;
	}
	
	public boolean showOrdersByClientAndDate(Client c, LocalDate d) {
		
		ArrayList<Order> orders=new ArrayList<Order>();
		
		orders=getOrdersByClientAndDate(c, d);
		
		if(orders.size()!=0) {
			OrderMenuVista.getMyOrderMenuVista().ordersByClientAndDate();
			
			for (int i = 0; i < orders.size(); i++) {
				
				System.out.println((i+1)+".- "+orders.get(i).toString());
			}
			
			return true;
		}
		
		return false;
	}
	
	public boolean showAllOrders() {
		
		if(orders.size()!=0) {
			OrderMenuVista.getMyOrderMenuVista().orderList();
			
			for (int i = 0; i < orders.size(); i++) {
				
				System.out.println((i+1)+".- "+orders.get(i).toStringShort());
			}
			
			return true;
		}
		
		return false;
	}
}
