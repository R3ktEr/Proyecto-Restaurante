package menu;

import java.time.LocalDate;

import clients.Client;

public interface IMainMenuController {
	
	public boolean newOrder();
	public boolean changeOrder(Client c);
	public boolean changeOrder(LocalDate d);
	public boolean changeOrder(Client c, LocalDate d);
	public boolean deleteOrder(Client c);
	public boolean deleteOrder(LocalDate d);
	public boolean deleteOrder(Client c, LocalDate d);
	public void cashToday();
	public void cashThisMonth();
	public void cashTotal();
	public void viewOrdersNotPayed();
	public void viewOrderPendingDelivered();
	public boolean saveAllAndClose();
	
}
