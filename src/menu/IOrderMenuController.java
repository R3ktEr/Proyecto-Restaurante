package menu;

public interface IOrderMenuController {

	public boolean addProduct();
	public boolean editLine();
	public boolean removeLine();
	public boolean setAddress(String a);
	public boolean savePaid();
	public boolean saveNotPaid();
	
}
