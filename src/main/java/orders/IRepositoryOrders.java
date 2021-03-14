package orders;

import java.time.LocalDate;
import java.util.ArrayList;

public interface IRepositoryOrders {
	
	public ArrayList<Order> getAllOrders();
	public ArrayList<Order> getOrdersByClient(String dni);
	public ArrayList<Order> getOrdersByDate(LocalDate uniqueDate);
	public ArrayList<Order> getOrdersNoDelivered();
	public ArrayList<Order> getOrdersNoPayed();
	public double getAllInput();
	public double getInputByDate(LocalDate ini, LocalDate end);
	
	
}
