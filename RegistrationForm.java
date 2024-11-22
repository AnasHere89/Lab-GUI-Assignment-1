import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class RegistrationForm extends Application {
    private final ArrayList<Person> personList = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        VBox mainLayout = new VBox(15);
        mainLayout.setPadding(new Insets(15));

        HBox nameLayout = new HBox(10);
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        nameLayout.getChildren().addAll(nameLabel, nameField);

        HBox fatherNameLayout = new HBox(10);
        Label fatherNameLabel = new Label("Father Name:");
        TextField fatherNameField = new TextField();
        fatherNameLayout.getChildren().addAll(fatherNameLabel, fatherNameField);

        HBox cnicLayout = new HBox(10);
        Label cnicLabel = new Label("CNIC:");
        TextField cnicField = new TextField();
        cnicLayout.getChildren().addAll(cnicLabel, cnicField);

        HBox dateLayout = new HBox(10);
        Label dateLabel = new Label("Date:");
        DatePicker datePicker = new DatePicker();
        dateLayout.getChildren().addAll(dateLabel, datePicker);

        HBox genderLayout = new HBox(10);
        Label genderLabel = new Label("Gender:");
        ComboBox<String> genderComboBox = new ComboBox<>();
        genderComboBox.getItems().addAll("Male", "Female", "Other");
        genderLayout.getChildren().addAll(genderLabel, genderComboBox);

        HBox cityLayout = new HBox(10);
        Label cityLabel = new Label("City:");
        TextField cityField = new TextField();
        cityLayout.getChildren().addAll(cityLabel, cityField);

        HBox imageLayout = new HBox(10);
        Label imageLabel = new Label("Image:");
        Button uploadButton = new Button("Upload Image");
        Label uploadedFileName = new Label();
        ImageView imageView = new ImageView();
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);

        uploadButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                uploadedFileName.setText(file.getAbsolutePath());
                imageView.setImage(new Image(file.toURI().toString()));
            }
        });

        imageLayout.getChildren().addAll(imageLabel, uploadButton, uploadedFileName, imageView);

        Button saveButton = new Button("Save");
        Label statusLabel = new Label();

        saveButton.setOnAction(e -> {
            String name = nameField.getText();
            String fatherName = fatherNameField.getText();
            String cnic = cnicField.getText();
            String date = datePicker.getValue() != null ? datePicker.getValue().toString() : "";
            String gender = genderComboBox.getValue();
            String city = cityField.getText();
            String imagePath = uploadedFileName.getText();

            if (name.isEmpty() || fatherName.isEmpty() || cnic.isEmpty() || date.isEmpty() || gender == null || city.isEmpty() || imagePath.isEmpty()) {
                statusLabel.setText("Please fill in all fields.");
            } else {
                Person person = new Person(name, fatherName, cnic, date, gender, city, imagePath);
                personList.add(person);
                System.out.println("Person Saved: " + person);
                statusLabel.setText("Person saved successfully!");

                nameField.clear();
                fatherNameField.clear();
                cnicField.clear();
                datePicker.setValue(null);
                genderComboBox.setValue(null);
                cityField.clear();
                uploadedFileName.setText("");
                imageView.setImage(null);
            }
        });

        mainLayout.getChildren().addAll(
                nameLayout,
                fatherNameLayout,
                cnicLayout,
                dateLayout,
                genderLayout,
                cityLayout,
                imageLayout,
                saveButton,
                statusLabel
        );

        primaryStage.setScene(new Scene(mainLayout, 500, 500));
        primaryStage.setTitle("Custom Person Form");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
