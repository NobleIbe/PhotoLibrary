package controller;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.User;

public class SaveUser {
	public static void saveData(ArrayList<User> users) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("data/photo92.dat");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

			objectOutputStream.writeObject(users);

			objectOutputStream.close();
			fileOutputStream.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
