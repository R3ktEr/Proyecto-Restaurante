package Orders;

import java.time.LocalDate;
import java.util.ArrayList;

import com.sun.security.ntlm.Client;



public class RepositoryOrders implements IRepositoryOrders {

	ArrayList<order> orders = new ArrayList<>();
	
	@Override
	public ArrayList<order> getAllOrders() {
		
		return orders;
		
	}

	@Override
	public order getOrdersByClient(String dni) {
		boolean getC = false;
		client c;
		order o;
		for(int i = 0; i < client.size(); i ++) {
			if(this.dni.equals(c.getDni())) {
				getC = true;
				
			}
		}
		return o;
		
	}

	@Override
	public order getOrdersByDate(LocalDate ini, LocalDate end) {
		boolean getD = false;
		order o = null;
		
		for(int i = 0; i < orders.size(); i++) {
			o = orders.get(i);
			if(o.getLocalDateTime().equals(ini) || o.getLocalDateTime().equals(end)) {
				getD = true;
			}
		}
		
		return o;
	}

	@Override
	public order getOrdersNoDelivered() {
		boolean getD = false;
		order o = null;
		
		for(int i = 0; i < orders.size(); i++) {
			o = orders.get(i);
			if(o.getDelivered() == false) {
				getD = true;
			}
		}
		return o;
	}

	@Override
	public order getOrdersNoPayed() {
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
	public boolean getAllInput() {

		
		
		return false;
	}

	@Override
	public boolean getInputByDate(LocalDate ini, LocalDate end) {

		
		
		return false;
	}

}
