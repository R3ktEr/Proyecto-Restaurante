package products;

import java.util.ArrayList;

public interface IRepositoryProducts {
	public ArrayList<Product> getAllProducts();
	public ArrayList<Drink> getAllDrinks();
	public ArrayList<Food> getAllFoods();
	public ArrayList<Drink> getAllNoAlcoholicDrinks();
	public ArrayList<Drink> getAllAlcoholicDrinks();
	public ArrayList<Food> getAllForVeganFood();
	public Product[] getBundleProducts(Product p);
	public Product searchProduct(String name);
	public Drink searchDrinks(String name);
	public Food searchFoods(String name);
}
