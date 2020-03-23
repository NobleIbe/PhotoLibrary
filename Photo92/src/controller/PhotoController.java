package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Album;
import model.Photo;
import model.Tag;
import model.User;


public class PhotoController {
	ArrayList<User> users;
	ListView<Photo> photos;
	@FXML
	private ImageView imageView;
	@FXML
	private TextField tagTypeField, tagValueField;
	@FXML
	private ListView<Tag> tags;
	private Album selectedAlbum;
	private User user;
	public void start(ArrayList<User> users, ListView<Photo> photos, User user, Album selectedAlbum) {
		this.users = users;
		this.photos = photos;
		this.user = user;
		this.selectedAlbum = selectedAlbum;
		Photo selectedPhoto = photos.getSelectionModel().getSelectedItem();
	
		imageView.setImage(selectedPhoto.getImage());
		
	//	photoNameText.setText(selectedPhoto.getName());
		//captionText.setText(selectedPhoto.getCaption());
		//dateTakenText.setText(dateTimeformat.format(selectedPhoto.getDate().getTime()));
//		tags.setItems(FXCollections.observableArrayList(photos.getSelectionModel().getSelectedItem().getTags()));
//		tags.getSelectionModel().select(0);
	//	disableInput(true);
	}
	
	public void returnDetailInitialize(ArrayList<User> users, ListView<Photo> photos, User user, Album selectedAlbum) {
	this.users=users;
	this.photos=photos;
	this.user=user;
	this.selectedAlbum=selectedAlbum;
	}
	public void handleAddTagButton(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddTag.fxml"));
		Parent parent;
		try {
			parent = (Parent) loader.load();
		
		PhotoController controller = loader.<PhotoController>getController();
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		controller.returnDetailInitialize(users, photos, user, selectedAlbum);
	
		stage.setScene(scene);
		stage.show(); 
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void handleAddCaptionButton(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddCaption.fxml"));
		Parent parent;
		try {
			parent = (Parent) loader.load();
		
		PhotoController controller = loader.<PhotoController>getController();
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		controller.returnDetailInitialize(users, photos, user, selectedAlbum);
	
		stage.setScene(scene);
		stage.show(); 
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void handleDeleteCaptionButton(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddTag.fxml"));
		Parent parent;
		try {
		parent = (Parent) loader.load();
		PhotoController controller = loader.<PhotoController>getController();
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		controller.start(users, photos, user, selectedAlbum);
		stage.setScene(scene);
		stage.show();}
		catch (IOException e) {
		
			e.printStackTrace();
		}
		}
public void handleTagAddConfirmButton(ActionEvent event) {
FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PhotoDetail.fxml"));
Parent parent;
try {
parent = (Parent) loader.load();
PhotoController controller = loader.<PhotoController>getController();
Scene scene = new Scene(parent);
Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
controller.start(users, photos, user, selectedAlbum);
stage.setScene(scene);
stage.show();}
catch (IOException e) {

	e.printStackTrace();
}
}
public void handleTagAddCancelButton(ActionEvent event) {
	
FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PhotoDetail.fxml"));
Parent parent;
try {
parent = (Parent) loader.load();
PhotoController controller = loader.<PhotoController>getController();
Scene scene = new Scene(parent);
Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
controller.start(users, photos, user, selectedAlbum);
stage.setScene(scene);
stage.show();}
catch (IOException e) {

	e.printStackTrace();
}}
public void handleCaptionAddConfirmButton(ActionEvent event) {
	
FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddCaption.fxml"));
Parent parent;
try {
parent = (Parent) loader.load();
PhotoController controller = loader.<PhotoController>getController();
Scene scene = new Scene(parent);
Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

stage.setScene(scene);
stage.show();}
catch (IOException e) {

	e.printStackTrace();
}
}
public void handleCaptionCancelButton(ActionEvent event) {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PhotoDetail.fxml"));
	Parent parent;
	try {
	parent = (Parent) loader.load();
	PhotoController controller = loader.<PhotoController>getController();
	controller.start(users, photos, user, selectedAlbum);
	Scene scene = new Scene(parent);
	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

	stage.setScene(scene);
	stage.show();}
	catch (IOException e) {

		e.printStackTrace();
	}
}
	public void handleDeleteTagButton(ActionEvent event) {
		photos.getSelectionModel().getSelectedItem().getTags().remove(tags.getSelectionModel().getSelectedItem());
		SaveUser.saveData(users);
		tags.getItems().remove(tags.getSelectionModel().getSelectedItem());
		tags.refresh();
		tags.getSelectionModel().select(0);
	}
	public void handleBackButton(ActionEvent event) {
		try {
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AlbumDisplay.fxml"));
//			Parent parent = (Parent) loader.load();
//			AlbumController controller = loader.<AlbumController>getController();
//			Scene scene = new Scene(parent);
//			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//			controller.start(users, user, selectedAlbum);
//			stage.setScene(scene);
//			stage.show();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	public void handleLogoutButton(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginScreen.fxml"));
			Parent parent = (Parent) loader.load();
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
	public void handleReturnButton(ActionEvent event) {
		FXMLLoader loader;
		Parent parent;
		loader = new FXMLLoader(getClass().getResource("/view/AlbumList.fxml"));
		try {
			parent = (Parent) loader.load();
		UserController controller = loader.<UserController>getController();
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		System.out.println(user+" "+users);
		controller.start(user, users);
		stage.setScene(scene);
		stage.show();	}
	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}}
}
