package menu;

import java.time.LocalDate;
import java.util.ArrayList;

import Orders.order;
import clients.Client;

public class MainMenuController implements IMainMenuController{
	
	ArrayList<order> orders = new ArrayList<>();
	ArrayList<Client> clients = new ArrayList<>();
	
	@Override
	public OrderMenuController newOrder() {
		
		return null;
		
	}

	@Override
	public boolean changeOrder(Client c) {
		
		return false;
	}

	@Override
	public boolean changeOrder(LocalDate d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeOrder(Client c, LocalDate d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOrder(Client c) {
		boolean result = false;
		order o = null;
		Client cl = null;
		for(int i = 0; i < clients.size(); i++) {
			cl = clients.get(i);
			if(c.equals(cl)) {
			
			}
		}
		
		
		return result;
	}

	@Override
	public boolean deleteOrder(LocalDate d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOrder(Client c, LocalDate d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float cashToday() {
		float totalI = 0;
		order o = null;
		for(int i = 0; i < orders.size(); i++) {
			o = orders.get(i);
			totalI = totalI + o.getTotal();
		}
		
		return totalI;
	}

	@Override
	public float cashThisMonth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float cashTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public order viewOrdersNotPayed() {
		boolean getP = false;
		order o = null;
		
		for(int i = 0; i < orders.size(); i ++) {
			o = orders.get(i);
			if(o.isPayed() == false) {
				getP = true;
			}
		}
		return o;
	}

	@Override
	public order viewOrderPendingDelivered() {
		boolean getP = false;
		order o = null;
		
		for(int i = 0; i < orders.size(); i ++) {
			o = orders.get(i);
			if(o.isDelivered() == false) {
				getP = true;
			}
		}
		return o;
	}

	@Override
	public boolean saveAllAndClose() {
		// TODO Auto-generated method stub
		return false;
	}

}
