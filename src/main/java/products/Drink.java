package products;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="drink")
@XmlAccessorType(XmlAccessType.FIELD)
public class Drink extends Product {
	private boolean alcoholic;
	
	public Drink() {
		super();
		this.alcoholic=false;
	}
	
	public Drink(int id, String name, double price, boolean forCeliac, boolean alcoholic) {
		super(id, name, price, forCeliac);
		this.alcoholic = alcoholic;
	}

	public boolean isAlcoholic() {
		return alcoholic;
	}
	
	/**
	 * Este metodo devuelve el array de ID's productos con el que hace pack este producto instanciado con un ID determinado.
	 * @return pack: El array de ID's productos con el que hace pack.
	 */
	public int[] getBundlePack() {
		
		int[] pack=null;
		
		switch(this.id)
		{
		case 1: pack=new int[] {6,9,10};
				break;
		case 2: pack=new int[] {8};
				break;
		case 3: pack=new int[0];
				break;
		case 4: pack=new int[] {9};
				break;
		case 5: pack=new int[0];
		}
		
		return pack;
	}

	@Override
	public String toString() {
		return id+".-" + " Nombre: " + name + " Precio: " + price +" Alcoholica: " + (alcoholic ? "Si":"No") +" Para Celiacos: "
				+ (forCeliac ? "Si":"No");
	}
	
}
