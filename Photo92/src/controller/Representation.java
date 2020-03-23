package controller;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Photo;

public class Representation extends ListCell<Photo> {

	AnchorPane anchorPane = new AnchorPane();
	StackPane stackPane = new StackPane();
	ImageView imageView = new ImageView();
	Label captionLabel = new Label(), captionText = new Label(), dateLabel = new Label(), dateText = new Label();

	/**
	 * Constructor
	 */
	public Representation() {
		super();

	
		imageView.setFitWidth(100.0);
		imageView.setFitHeight(100.0);
		imageView.setPreserveRatio(true);

		StackPane.setAlignment(imageView, Pos.CENTER);

		stackPane.getChildren().add(imageView);
		


		AnchorPane.setLeftAnchor(stackPane, 0.0);
		AnchorPane.setTopAnchor(dateLabel, 110.0);
	
		AnchorPane.setTopAnchor(dateText, 110.0);
		AnchorPane.setLeftAnchor(dateText, 50.0);
	

		
		AnchorPane.setTopAnchor(captionLabel, 150.0);
		AnchorPane.setLeftAnchor(captionText, 50.0);
		AnchorPane.setTopAnchor(captionText, 150.0);

		anchorPane.getChildren().addAll(stackPane, dateLabel, dateText, captionLabel, captionText);

		anchorPane.setPrefHeight(55.0);

		captionLabel.setMaxWidth(300.0);

		setGraphic(anchorPane);
	}

	@Override
	/**
	 * Updates item
	 */
	public void updateItem(Photo photo, boolean empty) {
		super.updateItem(photo, empty);
		setText(null);
		if (photo == null) {
			imageView.setImage(null);
			dateLabel.setText("");
			dateText.setText("");
			captionLabel.setText("");
			captionText.setText("");
		}
		if (photo != null) {
			imageView.setImage(photo.getImage());
			dateLabel.setText("date: ");

			dateText.setText(photo.getDate());
			captionLabel.setText("Caption: ");
			captionText.setText(photo.getCaption());
		}
	}

}
