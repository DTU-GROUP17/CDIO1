package factories;

import com.github.javafaker.Faker;
import models.Model;

import java.util.ArrayList;

public abstract class Factory {
	Faker faker = new Faker();
	private ArrayList<Model> data;

	public Factory(int x){
		data = new ArrayList<>();
		for (int i = 0; i < x; i++) {
			data.add(create());
		}
	}

	public ArrayList<Model> make(){
		return data;
	}

	abstract  <T extends Model> T create();
}
