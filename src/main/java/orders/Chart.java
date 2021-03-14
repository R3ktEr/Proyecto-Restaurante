package orders;

import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import utils.Utils;

@XmlRootElement(name="chart")
@XmlAccessorType(XmlAccessType.FIELD)
public class Chart{

	private ArrayList<OrderLine> noConfirmedOrder;

	public Chart() {
		
		this.noConfirmedOrder=new ArrayList<OrderLine>();
	}

	public ArrayList<OrderLine> getNoConfirmedOrders() {
		return noConfirmedOrder;
	}

	public void setNoConfirmedOrders(ArrayList<OrderLine> noConfirmedOrders) {
		this.noConfirmedOrder = noConfirmedOrders;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chart other = (Chart) obj;
		if (noConfirmedOrder == null) {
			if (other.noConfirmedOrder != null)
				return false;
		} else if (!noConfirmedOrder.equals(other.noConfirmedOrder))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Carrito: " + Arrays.toString(noConfirmedOrder.toArray());
	}
	
	public boolean showProductInCart() {
		
		if(noConfirmedOrder.size()!=0) {
			for (int i = 0; i < noConfirmedOrder.size(); i++) {
				System.out.println("Linea "+(i+1)+": "+noConfirmedOrder.get(i).toString());
			}
			
			return true;
		}else {
			System.out.println("\nNo hay lineas de pedido!");
			Utils.getMyUtils().promptEnterKey();
			return false;
		}
	}
}
