package menu;

import java.time.LocalDate;
import java.util.ArrayList;

import Orders.order;
import clients.Client;

public interface IMainMenuController {
	
	public OrderMenuController newOrder();
	public boolean changeOrder(Client c);
	public boolean changeOrder(LocalDate d);
	public boolean changeOrder(Client c, LocalDate d);
	public boolean deleteOrder(Client c);
	public boolean deleteOrder(LocalDate d);
	public boolean deleteOrder(Client c, LocalDate d);
	public float cashToday();
	public float cashThisMonth();
	public float cashTotal();
	public order viewOrdersNotPayed();
	public order viewOrderPendingDelivered();
	public boolean saveAllAndClose();
	
}
