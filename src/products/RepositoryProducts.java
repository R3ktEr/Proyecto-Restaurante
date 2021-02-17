package products;

import java.util.ArrayList;

public class RepositoryProducts implements IRepositoryProducts{

	private ArrayList<Product> products;
	private static RepositoryProducts myRepositoryProducts;
	
	/**
	 * Constructor que inicializa un ArrayList con todos los productos.
	 */
	private RepositoryProducts() {
		products=new ArrayList<Product>();
		products.add(new Drink(1,"Coca Cola",1,true,false));
		products.add(new Drink(2,"Fanta",1,true,false));
		products.add(new Drink(3,"Agua",0.70f,true,false));
		products.add(new Drink(4,"Cerveza",1.50f,true,true));
		products.add(new Drink(5,"Vodka",4.50f,true,true));
		products.add(new Food(6,"Hamburguesa",3,false,false));
		products.add(new Food(7,"Patatas",0.70f,true,true));
		products.add(new Food(8,"Ensalada",1.50f,true,true));
		products.add(new Food(9,"Kebab",2.50f,false,false));
		products.add(new Food(10,"Pizza",4.80f,true,false));
	}
	
	@SuppressWarnings("unused")
	private static RepositoryProducts getMyRepositoryProducts()
	{
		if(myRepositoryProducts==null)
		{
			myRepositoryProducts=new RepositoryProducts();
		}
		
		return myRepositoryProducts;
	}

	@Override
	public String toString() {
		return "Products: " + products.toString();
	}

	@Override
	public ArrayList<Product> getAllProducts() {
		
		return products;
	}

	@Override
	public ArrayList<Drink> getAllDrinks() {
		
		ArrayList<Drink> drinks=new ArrayList<>();
		
		for (Product product : products) 
		{
			if(product instanceof Drink)
			{
				drinks.add((Drink) product);
			}
		}
		
		return drinks;
	}

	@Override
	public ArrayList<Food> getAllFoods() {
		
		ArrayList<Food> foods=new ArrayList<>();
		
		for (Product product : products) 
		{
			if(product instanceof Food)
			{
				foods.add((Food) product);
			}
		}
		
		return foods;
	}

	@Override
	public ArrayList<Drink> getAllNoAlcoholicDrinks() {
		
		ArrayList<Drink> drinks=new ArrayList<>();
		ArrayList<Drink> noAlcoholicDrinks=new ArrayList<>();
		drinks=getAllDrinks();
		
		for(Drink drink: drinks)
		{
			if(!drink.isAlcoholic())
			{
				noAlcoholicDrinks.add(drink);
			}
		}
		
		return noAlcoholicDrinks;
	}

	@Override
	public ArrayList<Drink> getAllAlcoholicDrinks() {
		
		ArrayList<Drink> drinks=new ArrayList<>();
		ArrayList<Drink> AlcoholicDrinks=new ArrayList<>();
		drinks=getAllDrinks();
		
		for(Drink drink: drinks)
		{
			if(drink.isAlcoholic())
			{
				AlcoholicDrinks.add(drink);
			}
		}
		
		return AlcoholicDrinks;
	}

	@Override
	public ArrayList<Food> getAllForVeganFood() {
		
		ArrayList<Food> foods=new ArrayList<>();
		ArrayList<Food> veganFoods=new ArrayList<>();
		foods=getAllFoods();
		
		for(Food food: foods)
		{
			if(food.isForVegans())
			{
				veganFoods.add(food);
			}
		}
		
		return veganFoods;
	}

	/**
	 * A este metodo se le pasa un producto y devuelve un array con los productos con los que hace pack.
	 * 
	 * @param p: Producto el cual se quiere saber con que otros productos hace pack.
	 * @return bundleProducts: Array de productos los cuales hacen pack con el producto pasado al metodo.
	 */
	@Override
	public Product[] getBundleProducts(Product p) {
		
		int[] pack=p.getBundlePack();
		Product[] bundleProducts=null;
		boolean found=false;
		
		if(p!=null)
		{
			bundleProducts=new Product[pack.length];
			
			for (int i = 0; i < pack.length; i++) {
				
				found=false;
				
				for (int j = 0; j < products.size()&&!found; j++) {
					
					if(products.get(j).id==pack[i])
					{
						bundleProducts[i]=products.get(j);
						found=true;
					}
				}
			}			
		}
		
		return bundleProducts;
	}

	@Override
	public Product searchProduct(String name) {
		
		boolean found=false;
		Product product=null;
		
		if(name!=null)
		{
			for(int i=0; i<products.size()&&!found; i++ )
			{
				if(products.get(i).name.equals(name));
				{
					product=products.get(i);
					found=true;
				}
			}
		}
		
		return product;
	}

	@Override
	public Drink searchDrinks(String name) {
		
		boolean found=false;
		ArrayList<Drink> drinks;
		Drink drink=null;
				
		if(name!=null)
		{
			drinks=getAllDrinks();
			
			for(int i=0; i<drinks.size()&&!found; i++ )
			{
				if(drinks.get(i).name.equals(name));
				{
					drink=drinks.get(i);
					found=true;
				}
			}
		}
		
		return drink;
	}

	@Override
	public Food searchFoods(String name) {
		
		boolean found=false;
		ArrayList<Food> foods;
		Food food=null;
				
		if(name!=null)
		{
			foods=getAllFoods();
			
			for(int i=0; i<foods.size()&&!found; i++ )
			{
				if(foods.get(i).name.equals(name));
				{
					food=foods.get(i);
					found=true;
				}
			}
		}
		
		return food;
	}

}
