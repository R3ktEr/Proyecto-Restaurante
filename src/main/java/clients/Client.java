package clients;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="client")
@XmlAccessorType(XmlAccessType.FIELD)
public class Client extends Person{

	protected ArrayList<String> address;
	protected int orders;
	protected int points;
	
	public Client() {
		this.points=0;
		this.address=new ArrayList<String>();
		this.orders=0;
	}
	
	public Client(ArrayList<String> address, int orders, int points, String dni, String name, int age) {
		super(dni,name,age);
		this.address = address;
		this.orders = orders;
		this.points = points;
	}

	public Client(ArrayList<String> address, String dni, String name, int age) {
		super(dni,name,age);
		this.address = address;
	}

	public ArrayList<String> getAddress() {
		return address;
	}
	
	public String getAddress(int i) {
		return address.get(i);
	}

	public void setAddress(String address) {	
		this.address.add(address);
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
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
			else if(obj instanceof Client)
			{
				Client c=(Client)obj;
				if(c.getName()!=null&&c.getDni()!=null)						
				{
					if(c.getName().equals(this.name)&&(c.getDni().equals(this.dni)))
					{
						result=true;
					}
				}
			}
		}
		
		return result;
	}
	
	public void showAddress() {
		
		System.out.println("\nDirecciones del cliente: ");
		for (int i = 0; i < address.size(); i++) {
			System.out.println((i+1)+".- "+address.get(i));			
		}
	}
	
	public boolean changeAddress(int i, String newAddress) {
		
		if((i>=0&&i<address.size())&&newAddress!=null) {
			
			address.set(i, newAddress);;
			return true;
		}
		
		return false;
	}
	
	public boolean dropAddress(int i, String address) {
		
		Iterator<String> myIterator=this.address.iterator();
		
		while(myIterator.hasNext())
		{
			if(myIterator.next().equals(address)) {
				this.address.remove(address);
				return true;
			}
		}
		
		return false;
	}

	@Override
	public String toString() {
		return "Cliente: "+"Nombre: " + name + " Dni: " + dni +  " Edad: " + age + " Direccion/es: " + Arrays.toString(address.toArray()) + " Pedidos: "+ orders + " Puntos: "
				+ points;
	}
	
}
