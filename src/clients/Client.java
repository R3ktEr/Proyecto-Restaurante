package clients;

import java.util.Arrays;

public class Client extends Person{

	protected String[] address;
	protected int[] orders;
	protected int points;
	
	public Client() {};
	
	public Client(String[] address, int[] orders, int points, String dni, String name, int age) {
		super(dni,name,age);
		this.address = address;
		this.orders = orders;
		this.points = points;
	}

	public Client(String[] address, String dni, String name, int age) {
		super(dni,name,age);
		this.address = address;
	}

	public String[] getAddress() {
		return address;
	}

	public void setAddress(String[] address) {
		this.address = address;
	}

	public int[] getOrders() {
		return orders;
	}

	public void setOrders(int[] orders) {
		this.orders = orders;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	/**
	 * @return result: Devuelve true si dos clientes son iguales por dni o por nombre
	 */
	public boolean equals(Object obj)
	{
		boolean result=false;
		
		if(obj!=null)
		{
			if(obj instanceof String)
			{
				String other;
				other=(String)obj;
				if(this.name!=null)
				{
					if(this.name.equals(other))
					{
						result=true;
					}					
				}
				
				if(this.dni!=null)
				{
					if(this.dni.equals(other))
					{
						result=true;
					}					
				}
			}
		}
		
		return result;
	}

	@Override
	public String toString() {
		return "Cliente:\nDni: " + dni + " Nombre: " + name + " Edad: " + age + " Direccion: " + Arrays.toString(address) + " Pedidos: "+ orders.length + " Puntos: "
				+ points;
	}
	
	
}
