package controller;

import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Album;
import model.User;

public class UserController {

		@FXML
		private Button createAlbumButton;
		private Button deleteAlbumButton;
		private Button renameAlbumButton;
		private Button openAlbumButton;
		private Button logOutButton;

		@FXML
		private ListView<Album> albums;
		private ArrayList<User> users;
		private User user;

		public boolean rename = false;
		public String albumname;

		
		public void start(User user, ArrayList<User> users) {
			System.out.println("user"+user+"users"+users);
			
			this.user = user;
			this.users = users;
			albums.setItems(FXCollections.observableArrayList(user.getAlbumList()));
			albums.getSelectionModel().select(0);
			
		}

	
		public void handleLogOutButton(ActionEvent event) {

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
				Parent parent = (Parent) loader.load();
				@SuppressWarnings("unused")
				LoginController controller = loader.<LoginController>getController();
				Scene scene = new Scene(parent);
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				// controller.start();
				stage.setScene(scene);
				stage.show();
			} catch (Exception exception) {
				exception.printStackTrace();
			}

		}

	
	
		public void handleAddAlbumButton(ActionEvent event) {
			try {
				Album selectedAlbum = albums.getSelectionModel().getSelectedItem();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NewAlbum.fxml"));
				Parent parent = (Parent) loader.load();
				AlbumController controller = loader.<AlbumController>getController();
				Scene scene = new Scene(parent);
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				controller.start(user,users,selectedAlbum);
				stage.setScene(scene);
				stage.show();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
	
		}

		/**
		 * Handles the Open album button and takes the selected album and passes it to
		 * Album display after loading the Album Display scene.
		 * 
		 * @param event
		 */
		public void handleOpenAlbumButton(ActionEvent event) {
			Album selectedAlbum = albums.getSelectionModel().getSelectedItem();

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AlbumDetail.fxml"));
				Parent parent = (Parent) loader.load();
				AlbumController controller = loader.<AlbumController>getController();
				Scene scene = new Scene(parent);
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				
				stage.setScene(scene);
				stage.show();
				controller.start(user,users,selectedAlbum);
				controller.initiateAlbum(selectedAlbum);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}


		/**
		 * Handles the delete button and deletes the selected album after confirming
		 * from user. Then saves the data.
		 */
		public void handleDeleteAlbumButton() {
			Album album = albums.getSelectionModel().getSelectedItem();

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Delete Album");
			alert.setHeaderText("Album deletion conformation.");
			alert.setContentText("Are you sure you want to delete \"" + album.getName() + "\"?");
			alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get().equals(ButtonType.YES)) {
				user.getAlbumList().remove(album);
				albums.getItems().remove(album);
				albums.refresh();
				SaveUser.saveData(users);
			}
		}

		/**
		 * Handles the rename button and loads the current name of the album in the text
		 * field for user to modify.
		 */
		public void handleRenameButton(ActionEvent event) {
			try {Album selectedAlbum = albums.getSelectionModel().getSelectedItem();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RenameAlbum.fxml"));
				Parent parent = (Parent) loader.load();
				AlbumController controller = loader.<AlbumController>getController();
				Scene scene = new Scene(parent);
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				controller.start(user,users,selectedAlbum);
				stage.setScene(scene);
				stage.show();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		/**
		 * Handles the search button and loads the search scene for the user.
		 * 
		 * @param event
		 */
//		public void handlesearchPhotosButton(ActionEvent event) {
//
//			try {
//				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SearchPhotos.fxml"));
//				Parent parent = (Parent) loader.load();
//				PhotoSearchController controller = loader.<PhotoSearchController>getController();
//				Scene scene = new Scene(parent);
//				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//				controller.start(user, users);
//				stage.setScene(scene);
//				stage.show();
//			} catch (Exception exception) {
//				exception.printStackTrace();
//			}
//
//		}
}
