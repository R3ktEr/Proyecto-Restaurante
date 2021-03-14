package products;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name="product")
@XmlSeeAlso({Food.class,Drink.class})
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Product implements IProduct {
	protected int id;
	protected String name;
	protected double price;
	protected boolean forCeliac;
	
	public Product() {
		this.id=-1;
		this.name="";
		this.price=0.0;
		this.forCeliac=false;
	}
	
	public Product(int id, String name, double price, boolean forCeliac) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.forCeliac = forCeliac;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public boolean getIsForCeliac() {
		return forCeliac;
	}

	public boolean isForCeliac() {
		return forCeliac;
	}

	public void setForCeliac(boolean forCeliac) {
		this.forCeliac = forCeliac;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
}
