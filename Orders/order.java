package Orders;

import java.util.Date;

public class order {

	client clients;
	products produts;

	int total;
	Date LocalDateTime;
	String adress;
	boolean delivered;
	boolean payed;
	
	public client getClients() {
		return clients;
	}
	public void setClients(client clients) {
		clients = clients;
	}
	public products getProduts() {
		return produts;
	}
	public void setProduts(products produts) {
		produts = produts;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Date getLocalDateTime() {
		return LocalDateTime;
	}
	public void setLocalDateTime(Date localDateTime) {
		LocalDateTime = localDateTime;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public boolean getDelivered() {
		return delivered;
	}
	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}
	public boolean isPayed() {
		return payed;
	}
	public void setPayed(boolean payed) {
		this.payed = payed;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		order other = (order) obj;
		if (LocalDateTime == null) {
			if (other.LocalDateTime != null)
				return false;
		} else if (!LocalDateTime.equals(other.LocalDateTime))
			return false;
		if (adress == null) {
			if (other.adress != null)
				return false;
		} else if (!adress.equals(other.adress))
			return false;
		if (delivered != other.delivered)
			return false;
		if (payed != other.payed)
			return false;
		if (total != other.total)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Order [total=" + total + ", LocalDateTime=" + LocalDateTime + ", adress=" + adress + ", delivered="
				+ delivered + ", payed=" + payed + ", getTotal()=" + getTotal() + ", getLocalDateTime()="
				+ getLocalDateTime() + ", getAdress()=" + getAdress() + ", getDelivered()=" + getDelivered()
				+ ", isPayed()=" + isPayed() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	public order(client clients, products produts, int total, Date localDateTime, String adress, boolean delivered,
			boolean payed) {
		super();
		clients = clients;
		produts = produts;
		this.total = total;
		LocalDateTime = localDateTime;
		this.adress = adress;
		this.delivered = delivered;
		this.payed = payed;
	}

	
}
