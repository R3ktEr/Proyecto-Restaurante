package menu;

import clients.Client;
import orders.Order;

public interface IOrderMenuController {
	public boolean addProduct(Order newOrder);
	public boolean editLine(Order newOrder);
	public boolean removeLine(Order newOrder);
	public boolean setAddress(Order newOrder);
	public boolean save(Order newOrder, Client c);
	public boolean savePaid(Order newOrder, Client c);
	public boolean saveNotPaid(Order newOrder, Client c);
}
