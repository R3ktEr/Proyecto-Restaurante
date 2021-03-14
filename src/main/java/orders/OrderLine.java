package orders;

import products.Product;

public class OrderLine {

	private Product product;
	private int cuantity;
	
	public OrderLine() {
		this.cuantity = 0;
	}

	public OrderLine(Product product, int cuantity) {
		this.product = product;
		this.cuantity = cuantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCuantity() {
		return cuantity;
	}

	public void setCuantity(int cuantity) {
		this.cuantity = cuantity;
	}

	@Override
	public String toString() {
		return "Producto: " + product.toString() + " Cantidad: " + cuantity;
	}
	
	
}
