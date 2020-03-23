package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import model.Album;
import model.Photo;
import model.User;


public class AlbumController {
	private User user;
	private Album selectedAlbum;
	private ArrayList<User> users;
	@FXML
	ListView<Photo> photos;
	@FXML
	private TextField album; //when create and rename
	@FXML
	private Label albumName;
	public void start(User user,ArrayList<User> users,Album selectedAlbum) {
		this.selectedAlbum=selectedAlbum;
		this.users=users;
		this.user = user;
		this.selectedAlbum=selectedAlbum;


		
	}
	public void initiateAlbum(Album selectedAlbum) {
		this.albumName.setText(selectedAlbum.getName());
		
		ObservableList<Photo> ophotoList=FXCollections.observableArrayList(selectedAlbum.getPhotos());
		photos.setItems(ophotoList);
		photos.getSelectionModel().select(0);
		photos.setOrientation(Orientation.HORIZONTAL);
		photos.setCellFactory(new Callback<ListView<Photo>, ListCell<Photo>>() {
			@Override
			public ListCell<Photo> call(ListView<Photo> photoList) {
				return new Representation();
			}
		});
		
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

	public void handleAddPhotoButton() {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Image");
		chooser.getExtensionFilters().addAll(
				new ExtensionFilter("Image Files", "*.bmp", "*.BMP", "*.gif", "*.GIF", "*.jpg", "*.JPG", "*.png",
						"*.PNG"),
				new ExtensionFilter("Bitmap Files", "*.bmp", "*.BMP"),
				new ExtensionFilter("GIF Files", "*.gif", "*.GIF"), new ExtensionFilter("JPEG Files", "*.jpg", "*.JPG"),
				new ExtensionFilter("PNG Files", "*.png", "*.PNG"));
		File selectedFile = chooser.showOpenDialog(null);

		if (selectedFile != null) {
			Image image = new Image(selectedFile.toURI().toString());
			String name = selectedFile.getName();
			Calendar date = Calendar.getInstance();
			date.setTimeInMillis(selectedFile.lastModified());
			Photo newPhoto = new Photo(name, date, image);

			for (Photo currentPhoto : selectedAlbum.getPhotos()) {
				if (currentPhoto.equals(newPhoto)) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Album Display Error");
					alert.setHeaderText("Photo Add Error.");
					alert.setContentText("A photo with this name already exists.");

					alert.showAndWait();
					return;
				}
			}

			photos.getItems().add(newPhoto);
			selectedAlbum.getPhotos().add(newPhoto);
			SaveUser.saveData(users);
		}
	}
	public void handleDeletePhotoButton(ActionEvent event) {
		selectedAlbum.getPhotos().remove(photos.getSelectionModel().getSelectedItem());
		photos.getItems().remove(photos.getSelectionModel().getSelectedIndex());
		photos.getSelectionModel().select(0);
		photos.refresh();
		SaveUser.saveData(users);
	}

public void handleAddConfirmButton(ActionEvent event) {
	if (album.getText().equals("")) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Error adding new album.");
		alert.setContentText("Cannot add an album with empty name");

		alert.showAndWait();
		return;
	}
	
	else {
	Album newAlbum= new Album(album.getText());
	for (int i =0;i<user.getAlbumList().size();i++) {
		if (user.getAlbumList().get(i).getName().equals(album.getText())) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error adding album.");
			alert.setContentText("Cannot add an album with same name of an existing name");
			alert.showAndWait();
			return;
		}
	}
	user.getAlbumList().add(newAlbum);
	SaveUser.saveData(users);
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AlbumList.fxml"));
	Parent parent;
	try {
		parent = (Parent) loader.load();
		UserController controller = loader.<UserController>getController();
		
	Scene scene = new Scene(parent);
	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	 controller.start(user,users);
	stage.setScene(scene);
	stage.show();} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}}
public void handleRenameConfirmButton(ActionEvent event) {
	if (album.getText().equals("")) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Error changing new album.");
		alert.setContentText("Cannot changing an album with empty name");

		alert.showAndWait();
		return;
	}
	
	else {

	for (int i =0;i<user.getAlbumList().size();i++) {
		if (user.getAlbumList().get(i).getName().equals(album.getText())) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error changing album.");
			alert.setContentText("Cannot change an album with same name of an existing name");
			alert.showAndWait();
			return;
		}
	}
	
	selectedAlbum.setName(	album.getText());
	SaveUser.saveData(users);
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AlbumList.fxml"));
	Parent parent;
	try {
		parent = (Parent) loader.load();
		UserController controller = loader.<UserController>getController();
		
	Scene scene = new Scene(parent);
	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	 controller.start(user,users);
	stage.setScene(scene);
	stage.show();} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}}

public void handleRenameCancelButton(ActionEvent event) {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AlbumList.fxml"));
	Parent parent;
	try {
		parent = (Parent) loader.load();
		UserController controller = loader.<UserController>getController();
		
	Scene scene = new Scene(parent);
	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	 controller.start(user,users);
	stage.setScene(scene);
	stage.show();} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
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
public void handleOpenPhotoButton(ActionEvent event) {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/photoDetail.fxml"));
	Parent parent;
	try {
	parent = (Parent) loader.load();
	PhotoController controller = loader.<PhotoController>getController();	
	Scene scene = new Scene(parent);
	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	 controller.start(users,photos,user,selectedAlbum);
	stage.setScene(scene);
	stage.show();} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
public void handleAddCancelButton(ActionEvent event) {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AlbumList.fxml"));
	Parent parent;
	try {
	parent = (Parent) loader.load();
	UserController controller = loader.<UserController>getController();	
	Scene scene = new Scene(parent);
	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	 controller.start(user,users);
	stage.setScene(scene);
	stage.show();} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
