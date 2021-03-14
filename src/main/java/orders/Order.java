package orders;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import clients.Client;
import products.Drink;
import products.Food;
import products.Product;
import utils.LocalDateTimeAdapter;

@XmlRootElement(name="order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {

	private static int id=0;
	
	private Client client;
	private Chart cart;

	private int orderId;
	private double total;
	private int totalProducts;
	@XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
	private LocalDateTime date ;
	private String address;
	private boolean delivered;
	private boolean paid;
	private boolean saved;
	
	public Order() {
		id++;
		this.orderId=id;
		this.cart=new Chart();
		this.date=LocalDateTime.now();
		this.saved=false;
		this.delivered=false;
		this.paid=false;
		this.address="";
		this.total=0.0f;
		this.totalProducts=0;
	}
	
	public Order(Client c) {
		id++;
		this.orderId=id;
		this.client=c;
		this.cart=new Chart();
		this.date=LocalDateTime.now();
		this.saved=false;
		this.delivered=false;
		this.paid=false;
		this.address="";
		this.total=0.0f;
		this.totalProducts=0;
	}
	
	public Order(Client client, Chart cart, float total, int totalProducts, LocalDateTime date, String adress,
			boolean delivered, boolean paid, boolean saved) {
		super();
		id++;
		this.orderId=id;
		this.client = client;
		this.cart = cart;
		this.total = total;
		this.date = date;
		this.address = adress;
		this.delivered = delivered;
		this.paid = paid;
		this.saved = saved;
		this.totalProducts = totalProducts;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Client getClient() {
		return client;
	}
	
	public void setClient(Client clients) {
		this.client = clients;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(float total) {
		this.total = total;
	}
	
	public LocalDateTime getDate() {
		return this.date;
	}
	
	public void setDateTime(LocalDateTime date) {
		this.date = date;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public boolean getDelivered() {
		return delivered;
	}
	
	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}
	
	public Chart getCart() {
		return cart;
	}

	public void setCart(Chart cart) {
		this.cart = cart;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public boolean isSaved() {
		return saved;
	}

	public void setSaved(boolean saved) {
		this.saved = saved;
	}

	public int getTotalProducts() {
		return totalProducts;
	}

	public void setTotalProducts(int totalProducts) {
		this.totalProducts = totalProducts;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (orderId != other.orderId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		DateTimeFormatter dt=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DecimalFormat twoDecimals=new DecimalFormat("####.##");
		return "Fecha del pedido: " + dt.format(date) + " Direccion del pedido: " + address + " Productos: " + totalProducts + " Precio Total: " + twoDecimals.format(total)
				+  " Entregado: " + (delivered?"Si":"No") + " Pagado: " + (paid?"Si":"No");
	}
	
	public String toStringShort() {
		DateTimeFormatter dt=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DecimalFormat twoDecimals=new DecimalFormat("####.##");
		return "Cliente: "+client.getName() + " Fecha del pedido: " + dt.format(date) + " Direccion del pedido: " + address + " Productos: " + totalProducts + " Precio Total: " + twoDecimals.format(total)
				+  " Entregado: " + (delivered?"Si":"No") + " Pagado: " + (paid?"Si":"No");
	}
	
	public String toStringWithClient() {
		DateTimeFormatter dt=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DecimalFormat twoDecimals=new DecimalFormat("####.##");
		return client + " Fecha del pedido: " + dt.format(date) + " Direccion del pedido: " + address + " Productos: " + totalProducts + " Precio Total: " + twoDecimals.format(total)
				+  " Entregado: " + (delivered?"Si":"No") + " Pagado: " + (paid?"Si":"No");
	}
	
	public double calculateTotal() {
		
		double total=0;
		ArrayList<ArrayList<Product>> packs=getPacks();
		
		for (int i = 0; i < packs.size(); i++) {
			ArrayList<Product> singlePack=packs.get(i);
			for (int j = 0; j < singlePack.size(); j++) {
				
				//Aplica el descuento a todos los productos de cada uno de los paquetes excepto el ultimo, el cual es un paquete de productos sin paquetizar
				if(i<packs.size()-1) {
					total+=(singlePack.get(j).getPrice()*0.9);
				}
				else
				{
					total+=singlePack.get(j).getPrice();
				}
			}
		}
		
		this.total=total;
		this.client.setPoints((int)total/5);
		
		return total;
	}
	
	/*
	 * @return productsInPackages: Devuelve los productos paquetizados. El ultimo paquete del ArrayList es el paquete de productos sin descuento
	 */
	public ArrayList<ArrayList<Product>> getPacks() {
		
		ArrayList<OrderLine> temporalyCart;
		ArrayList<OrderLine> productsInCart=new ArrayList<OrderLine>();
		ArrayList<Product> packagesWithDiscount=new ArrayList<Product>();
		ArrayList<Product> packagesWithNoDiscount=new ArrayList<Product>();
		ArrayList<ArrayList<Product>> productsInPackages=new ArrayList<ArrayList<Product>>();
		Product p=null;
		Product p2=null;
		OrderLine ol1=null;
		OrderLine ol2=null;
		Food f=null;
		Drink d=null;
		int[] ids=null;
		int[] cuantities=null;
		
		//Guardar las cantidades de los productos que se van a modificar y despues restaurarlas al final del programa
		
		temporalyCart=this.cart.getNoConfirmedOrders();
		cuantities=new int[temporalyCart.size()];
		for (int i = 0; i < temporalyCart.size(); i++) {
			
			productsInCart.add(temporalyCart.get(i));
			cuantities[i]=temporalyCart.get(i).getCuantity();
		}
		
		Iterator<OrderLine> cartIterator=productsInCart.iterator();
		while(cartIterator.hasNext()) {
			
			//Consigo las ids de los productos que hacen pack con el producto que tengo en este momento en esta linea de producto.			 
			ol1=(OrderLine)cartIterator.next();
			p=ol1.getProduct();
			if(p instanceof Food)
			{
				f=(Food)p;
				ids=f.getBundlePack();
			}
			else if(p instanceof Drink)
			{
				d=(Drink)p;
				ids=d.getBundlePack();
			}				
			
			//Vuelvo a recorrer el ArrayList de OrderLine del carrito pero con un nuevo Iterator
			boolean match=false;
			
			while(cartIterator.hasNext()&&!match) {
				
				ol2=(OrderLine)cartIterator.next();
				p2=ol2.getProduct();
				
				
				//Recorro las ids proporcionadas por el primer producto que he añadido al paquete y si hacen match con la id de otro producto dentro de
				//una OrderLine del carrito, compruebo la cantidad. Si hay mas de 1 producto, cantidad se actualiza restando 1 producto. Si la cantidad de
				//la OrderLine es 1, borro la OrderLine. En ambos casos, el producto 2 es añadido al paquete con descuento
				for (int i = 0; i < ids.length&&!match; i++) {
					if(p2.getId()==ids[i]) {
						
						if(ol1.getCuantity()>1) {
							productsInCart.remove(ol1);
							ol1.setCuantity(ol1.getCuantity()-1);
							productsInCart.add(ol1);
							cartIterator=productsInCart.iterator();
						}
						else
						{
							productsInCart.remove(ol1);
							cartIterator=productsInCart.iterator();
						}
						
						if(ol2.getCuantity()>1)
						{
							productsInCart.remove(ol2);
							ol2.setCuantity(ol2.getCuantity()-1);
							productsInCart.add(ol2);
							cartIterator=productsInCart.iterator();
						}
						else
						{
							productsInCart.remove(ol2);
							cartIterator=productsInCart.iterator();
						}
						
						packagesWithDiscount.add(p);
						packagesWithDiscount.add(p2);
						match=true;
					}
				}		
			}		
		}
		
		//Añado el paquete con descuento al ArrayList de productos paquetizados.
		productsInPackages.add(packagesWithDiscount);
		
		//Añado los productos restantes del carrito que no se van a paquetizar con otros productos a un paquete por defecto al que no se le aplica descuento
		cartIterator=productsInCart.iterator();
		while(cartIterator.hasNext()) {
			
			ol1=(OrderLine)cartIterator.next();
			p=ol1.getProduct();
			
			if(ol1.getCuantity()>1)
			{
				cartIterator.remove();
				ol1.setCuantity(ol1.getCuantity()-1);
				productsInCart.add(ol1);
				cartIterator=productsInCart.iterator();
			}
			else
			{
				cartIterator.remove();
			}
			
			packagesWithNoDiscount.add(p);
			
		}
		
		//Añade el paquete con productos sin descuento, IMPORTANTE: AUNQUE ESTÉ VACIO
		productsInPackages.add(packagesWithNoDiscount);
		
		//Restauro las cantidades que tenia al principio
		for (int i = 0; i < temporalyCart.size(); i++) {
			
			temporalyCart.get(i).setCuantity(cuantities[i]);
		}
		
		return productsInPackages;
	}
	
	public void showOrder() {
		DateTimeFormatter dateFormat=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DecimalFormat decimalFormat=new DecimalFormat("####.##");
		System.out.println("\n\tPedido");
		System.out.println("Cliente: "+this.client.getName()+" DNI: "+this.client.getDni()+" Fecha del pedido: "+this.date.format(dateFormat));
		System.out.println("Direccion del pedido: "+this.address);
		System.out.println("\n\tProductos");
		this.cart.showProductInCart();
		System.out.println("\nTotal: "+decimalFormat.format(calculateTotal())+" Euros Pagado: "+(this.paid? "Si":"No")+" Entregado: "+(this.delivered? "Si":"No")+"\n");
	}
}
