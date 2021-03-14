package menu;

import clients.RepositoryClients;
import orders.RepositoryOrders;
import products.RepositoryProducts;

public class MainMenuModel{

	private static MainMenuModel myMainMenuModel;
	private RepositoryClients repositoryClients;
	private RepositoryOrders repositoryOrders;
	private RepositoryProducts repositoryProducts;
	
	private MainMenuModel() {
		super();
		this.repositoryClients = RepositoryClients.getMyRepositoryClients();
		this.repositoryOrders = RepositoryOrders.getMyRepositoryOrders();
		this.repositoryProducts = RepositoryProducts.getMyRepositoryProducts();
	}
	
	public static MainMenuModel getMainMenuModel() {
		if (myMainMenuModel==null) 
		{
			myMainMenuModel=new MainMenuModel();
		}
		
		return myMainMenuModel;
	}

	public RepositoryClients getRepositoryClients() {
		return repositoryClients;
	}

	public void setRepositoryClients(RepositoryClients repositoryClients) {
		this.repositoryClients = repositoryClients;
	}

	public RepositoryOrders getRepositoryOrders() {
		return repositoryOrders;
	}

	public void setRepositoryOrders(RepositoryOrders repositoryOrders) {
		this.repositoryOrders = repositoryOrders;
	}

	public RepositoryProducts getRepositoryProducts() {
		return repositoryProducts;
	}

	public void setRepositoryProducts(RepositoryProducts repositoryProducts) {
		this.repositoryProducts = repositoryProducts;
	}
	
}
