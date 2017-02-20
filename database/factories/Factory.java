package factories;

import com.github.javafaker.Faker;
import com.sun.org.apache.xpath.internal.operations.Mod;
import dal.contracts.Creatable;
import dal.exceptions.NotConnectedException;
import dal.jdbcdao.JDBCDAO;
import models.Model;

import java.util.ArrayList;

public abstract class Factory {
	static Faker faker = new Faker();
	static JDBCDAO jdbcdao = new JDBCDAO("Weight");
	public static Factory factory;

	private int times = 1;


	public Factory times(int x){
		this.times = x;
		return this;
	}

	@SuppressWarnings("unchecked")
	public <T> T make(){
		ArrayList<? extends Model> data = new ArrayList<>();

		// Instantiates the object x times.
		for (int i = 0; i < times; i++)
			data.add(instantiate());

		// Reset times.
		this.times = 1;

		if(data.size() == 1)
			return (T) data.get(0);
		return (T) data;
	}

	@SuppressWarnings("unchecked")
	public <T> T create() throws NotConnectedException{
		T data = this.make();

		if(data instanceof Model)
			((Creatable)this.getDAO()).create(data);
		else{
			for (Model model : (ArrayList<Model>)data )
				((Creatable) this.getDAO()).create(model);
		}

		return data;
	}


	abstract  <T> T getDAO();

	abstract  <T extends Model> T instantiate();

	/**
	 * Sets the faker instance to another one.
	 *
	 * @param faker to replace with
	 */
	public static void setFaker(Faker faker) {
		Factory.faker = faker;
	}
}
