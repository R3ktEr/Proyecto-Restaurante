package products;

public abstract class Product implements IProduct {
	protected int id;
	protected String name;
	protected double price;
	protected boolean forCeliac;
	
	
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

}
