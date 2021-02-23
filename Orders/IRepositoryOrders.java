package Orders;

import java.time.LocalDate;
import java.util.ArrayList;

public interface IRepositoryOrders {
	
	public ArrayList<order> getAllOrders();
	public order getOrdersByClient(String dni);
	public order getOrdersByDate(LocalDate ini, LocalDate end);
	public order getOrdersNoDelivered();
	public order getOrdersNoPayed();
	public boolean getAllInput();
	public boolean getInputByDate(LocalDate ini, LocalDate end);
	
	
}
