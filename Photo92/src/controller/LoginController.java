package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Album;
import model.Photo;
import model.User;

public class LoginController {
	
		@FXML
		private Button loginButton;
		@FXML
		private TextField usernameField;
		ArrayList<User> users;
		private final String path = "data/photo92.dat";
		Boolean validUser = false;
		public void start(Stage stage) {
		}
	
		@SuppressWarnings("unchecked")
	
		@FXML
	public void handleLoginButton(ActionEvent event) {

		String username = usernameField.getText();

		File data = new File(path);
		System.out.println(data.toString());

		if (!data.exists() || !data.isFile()) {
			try {
				System.out.println("1");
				data.createNewFile();
				Album stockAlbum = new Album("stock");
			//	stockAlbum.setPhotos(new ArrayList<Photo> ());
				String stockPhotoPath = "data/stock";
				File photoFile;
				for (int currentPhoto = 1; currentPhoto <= 2; currentPhoto++) {
					photoFile = new File(stockPhotoPath + "/img" + Integer.toString(currentPhoto) + ".jpg");

					if (photoFile != null) {
						Image image = new Image(photoFile.toURI().toString());
						String name = photoFile.getName();
						Calendar date = Calendar.getInstance();
						date.setTimeInMillis(photoFile.lastModified());
						Photo newPhoto = new Photo(name,date,image);
						
						System.out.println(stockAlbum);
						System.out.println(stockAlbum.getPhotos());
						System.out.println(newPhoto);
						stockAlbum.getPhotos().add(newPhoto);
						
					}
				}

				User stock = new User("stock","stock");
				stock.getAlbumList().add(stockAlbum);
				users = new ArrayList<User>();
				users.add(stock);

				try {
					FileOutputStream fileOutputStream = new FileOutputStream(path);
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

					objectOutputStream.writeObject(users);

					objectOutputStream.close();
					fileOutputStream.close();
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		try {
			System.out.println("2");
			FileInputStream fileInputStream = new FileInputStream(path);
			System.out.println(fileInputStream);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			users = (ArrayList<User>) objectInputStream.readObject();
			objectInputStream.close();
			fileInputStream.close();

			User user = null;

			for (User currentUser : users) {
				if (currentUser.getUsername().equals(username)) {
					user = currentUser;

				}
			}

			if (username.equals("admin") || user != null) {
				FXMLLoader loader;
				Parent parent;

				if (username.equals("admin")) {
					loader = new FXMLLoader(getClass().getResource("/view/Admin.fxml"));
					parent = (Parent) loader.load();
					AdminController controller = loader.<AdminController>getController();
					Scene scene = new Scene(parent);
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					controller.start(users);
					stage.setScene(scene);
					stage.show();
				} else {
					loader = new FXMLLoader(getClass().getResource("/view/AlbumList.fxml"));
					parent = (Parent) loader.load();
					UserController controller = loader.<UserController>getController();
					Scene scene = new Scene(parent);
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					controller.start(user, users);
					stage.setScene(scene);
					stage.show();
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Login Error");
				alert.setHeaderText("User not found.");
				alert.setContentText("This user does not exist.");

				alert.showAndWait();
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}


