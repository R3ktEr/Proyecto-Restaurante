package products;

public class Food extends Product {
	private boolean forVegans;
	
	public Food(int id, String name, double price, boolean forCeliac, boolean forVegans) {
		super(id, name, price, forCeliac);
		this.forVegans = forVegans;
	}
	
	public boolean isForVegans() {
		return forVegans;
	}

	/**
	 * Este metodo devuelve el array de ID's productos con el que hace pack este producto instanciado con un ID determinado.
	 * @return pack: El array de ID's productos con el que hace pack.
	 */
	@Override
	public int[] getBundlePack() {
		
		int[] pack=null;
		
		switch(this.id)
		{
		case 6: pack=new int[] {7,8,1};
				break;
		case 7: pack=new int[] {6,9};
				break;
		case 8: pack=new int[] {2,6,9};
				break;
		case 9: pack=new int[] {8,4};
				break;
		case 10: pack=new int[] {1};
				 break;
		}
		
		return pack;
	}
	@Override
	public String toString() {
		return "Comida:\n"  + "ID=" + id + " Nombre: " + name + " Precio: " + price + "Para Veganos: " + (forVegans ? "Si":"No") + "Para Celiacos: "
				+ (forCeliac ? "Si":"No");
	}
	
	
}
