package factories;

import com.github.javafaker.Faker;
import models.DTO;

import java.util.LinkedList;

public abstract class Factory {
	static Faker faker = new Faker();
	public static Factory factory;

	@SuppressWarnings("unchecked")
	public <T> T make(int times){
		if (times == 1) {
			return (T) this.instantiate();
		}
		LinkedList<? extends DTO> data = new LinkedList<>();
		for (int i = 0; i < times; i++)
			data.add(this.instantiate());
		return (T) data;
	}

	public <T> T make(){
		return this.make(1);
	}

	abstract  <T extends DTO> T instantiate();

}
