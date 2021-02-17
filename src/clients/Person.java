package clients;

public class Person{

	protected String dni;
	protected String name;
	protected int age;
	
	public Person() {};
	
	public Person(String dni, String name, int age) {
		this.dni = dni;
		this.name = name;
		this.age = age;
	}

	public Person(String dni, String name) {
		this.dni = dni;
		this.name = name;
	}

	public Person(String dni) {
		this.dni = dni;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Persona:\n Dni: " + dni + " Nombre: " + name + " Edad: " + age;
	}
	
	
}
