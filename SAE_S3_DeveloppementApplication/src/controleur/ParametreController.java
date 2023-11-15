/*
 * ParametreController.java                                      27 oct. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft" 
 */
package controleur;

import java.util.Optional;
import javafx.scene.control.Tooltip;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputDialog;
import vue.Main;
import modele.*;

/**
 * TODO comment class responsibility (SRP)
 * 
 * @author rayanibrahime
 * @author nathangirardin
 */
public class ParametreController {

	@FXML
	private Button btnAjouter;

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnModifier;

	@FXML
	private Button btnRetour;

	@FXML
	private Button btnSuivant;

	@FXML
	private ComboBox<String> comboBox;

	@FXML
	void nouvelleCategorieAction(ActionEvent event) {
		TextInputDialog boiteSaisie = new TextInputDialog("");
		boiteSaisie.setHeaderText("Ajouter une nouvelle catégorie");
		boiteSaisie.setTitle("Catégorie");
		boiteSaisie.setContentText("Entrez le nom de votre nouvelle catégorie: ");
		boiteSaisie.showAndWait();

		String result = boiteSaisie.getResult();
		if (result != null && !result.isEmpty() ) {

			if (Main.stockage.ajouterCategorie(new Categorie(result.trim()))) {
				comboBox.getItems().add(result.trim());

				System.out.println(Main.stockage.getListeCategorie());
			} else {
				afficherAlerte("Catégorie déja existante","L'intitulé de la catégorie que vous voulez créer existe déjà ou ne peut pas étre vide.");

			}
		}
	}

	@FXML
	void deleteCategorieAction(ActionEvent event) {
	    if (comboBox.getValue() != null) {
		Alert boiteAlerte = new Alert(Alert.AlertType.CONFIRMATION, "Confirmez-vous votre choix ?",
				ButtonType.YES, ButtonType.NO);

		Optional<ButtonType> option = boiteAlerte.showAndWait();
		if (option.isPresent() && option.get() == ButtonType.YES) {
			if (Main.stockage.supprimerElementListeCategorie((Categorie)Main.stockage.getListeCategorie().get(comboBox.getValue()))) {
				comboBox.getItems().remove(comboBox.getValue());
				System.out.println(Main.stockage.getListeCategorie());
			} else {
			    afficherAlerte("Suppresion Catégorie","La suppresion de la catégorie a échoué");
			}
		}
	    } else {
	       Tooltip tooltipSup = new Tooltip("Impossible de supprimer une catégorie");
	       Tooltip.install(btnDelete, tooltipSup);
	       afficherAlerte("Impossible supprimer catégorie","Impossible de supprimer une catégorie pour l'instant");
	    }
	}

	@FXML
	void modifierCategorieAction(ActionEvent event) {
	    if (comboBox.getValue() != null) {
		String categorieCourante = comboBox.getValue();
		TextInputDialog boiteSaisie = new TextInputDialog(categorieCourante);
		boiteSaisie.setHeaderText("Modifier la catégorie : " + categorieCourante);
		boiteSaisie.setTitle("Catégorie");
		boiteSaisie.setContentText("Entrez le nouveau nom de votre catégorie : ");
		boiteSaisie.showAndWait();
	    

		String result = boiteSaisie.getResult();
		if (result != null && !result.isEmpty()) {
			if (Main.stockage.modifierElementListeCategorie((Categorie)Main.stockage.getListeCategorie().get(comboBox.getValue()), result)) {
				comboBox.getItems().set(comboBox.getItems().indexOf(categorieCourante), result);
				comboBox.setValue(result);
				System.out.println(comboBox.getValue());
			} 
		} 
	    } else {
	        Tooltip tooltip = new Tooltip("Impossible de modifier une catégorie");
	        Tooltip.install(btnModifier, tooltip);
	              afficherAlerte("Impossible modifier catégorie","Impossible de modifier une catégorie pour l'instant");
	    }

	}

	@FXML
	void suivant(ActionEvent event) {
		try {
			if (comboBox.getValue() != null) {
				Main.voirParamCategorie(comboBox.getValue());

			} else {
				// peu etre modifier cela 
				afficherAlerte("Choisissez une catégorie","Choisissez une catégorie avant d'appuyer sur suivant.");
				
			}

		} catch (Exception e) {
			// Gérer l'exception (afficher un message d'erreur, journaliser, etc.)
			e.printStackTrace();
		}
	}

	@FXML
	void retourMenu(ActionEvent event) {
		Main.retourMenuPrincipal();

	}
	
	/** TODO comment method role
	 * @param titre
	 * @param message
	 */
	public static void afficherAlerte(String titre, String message) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle(titre);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
}
