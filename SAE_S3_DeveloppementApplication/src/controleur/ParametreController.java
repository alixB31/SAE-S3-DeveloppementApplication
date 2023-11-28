/*
 * ParametreController.java                                      27 oct. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft" 
 */
package controleur;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.Tooltip;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputDialog;
import vue.Main;
import modele.*;

/** 
 * Controleur de la vue Parametre.
 * @author Alix.Brugier Mateo.faussurier Nathan.Girardin Rayan.Ibrahime
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
	public ComboBox<String> comboBox;

	@FXML
	void nouvelleCategorieAction(ActionEvent event) {
	    // Création d'une boîte de dialogue de saisie de texte
	    TextInputDialog boiteSaisie = new TextInputDialog("");
	    boiteSaisie.setHeaderText("Ajouter une nouvelle catégorie");
	    boiteSaisie.setTitle("Catégorie");
	    boiteSaisie.setContentText("Entrez le nom de votre nouvelle catégorie: ");
	    Optional<String> valider = boiteSaisie.showAndWait();
	    if (valider.isPresent()) {
	    	// Récupération du résultat de la boîte de saisie
		    String resultat = boiteSaisie.getResult();
		    // Vérification que le résultat n'est pas nul, non vide et satisfait une condition de regex
		    if (resultat != null && !resultat.isEmpty() && verifierRegex(resultat)) {
		        // Ajout de la nouvelle catégorie dans le stockage et mise à jour de l'interface
		        if (Main.stockage.ajouterCategorie(new Categorie(resultat.trim()))) {
		            // Ajout du nouvel élément à la liste déroulante (comboBox)
		            comboBox.getItems().add(resultat.trim());
		            // Sélection de la nouvelle catégorie dans la liste déroulante
		            comboBox.setValue(resultat.trim());
		            // Affichage de la liste des catégories dans la console
		        } else {
		            // Affichage d'une alerte si la catégorie existe déjà
		            afficherAlerte("Catégorie déjà existante", "L'intitulé de la catégorie que vous voulez créer existe déjà.");
		        }
		    } else {
		        // Affichage d'une alerte si le résultat est vide ou ne satisfait pas la condition de regex
		        afficherAlerte("Catégorie vide", "L'intitulé de la catégorie que vous voulez créer ne peut pas être vide ou dépasser 20 caractères.");
		    }
	    } else {
	        boiteSaisie.close();
	    }
	    
	}

	@FXML
	void deleteCategorieAction(ActionEvent event) {
	    // Vérifie si une catégorie est sélectionnée dans la comboBox
	    if (comboBox.getValue() != null) {
	        // Affiche une boîte de dialogue de confirmation
	        Alert boiteAlerte = new Alert(Alert.AlertType.CONFIRMATION, "Confirmez-vous votre choix ?", ButtonType.YES, ButtonType.NO);
	        // Attend la réponse de l'utilisateur dans la boîte de dialogue
	        Optional<ButtonType> option = boiteAlerte.showAndWait();
	        
	        if (option.isPresent() && option.get() == ButtonType.YES) {
	            // Tente de supprimer la catégorie sélectionnée du stockage
	            if (Main.stockage.supprimerElementListeCategorie((Categorie)Main.stockage.getListeCategorie().get(comboBox.getValue()))) {
	                // Supprime la catégorie de lacomboBox
	                comboBox.getItems().remove(comboBox.getValue());
	            } else {
	                // Affiche une alerte si la suppression de la catégorie échoue
	                afficherAlerte("Impossible Suppression Catégorie", "La suppression de la catégorie est impossible");
	            }
	        }
	    } else {
	        // Si aucune catégorie n'est sélectionnée, affiche une alerte
	        Tooltip tooltipSup = new Tooltip("Impossible de supprimer une catégorie");
	        Tooltip.install(btnDelete, tooltipSup);
	        afficherAlerte("Impossible supprimer catégorie", "Impossible de supprimer une catégorie pour l'instant");
	    }
	}
	
	@FXML
	void modifierCategorieAction(ActionEvent event) {
	    // Vérifie si une catégorie est sélectionnée dans la comboBox
	    if (comboBox.getValue() != null) {
	        // Récupère le nom de la catégorie actuellement sélectionnée
	        String categorieCourante = comboBox.getValue();
	        // Affiche une boîte de dialogue de saisie de texte avec le nom de la catégorie actuelle en tant que valeur par défaut
	        TextInputDialog boiteSaisie = new TextInputDialog(categorieCourante);
	        boiteSaisie.setHeaderText("Modifier la catégorie : " + categorieCourante);
	        boiteSaisie.setTitle("Catégorie");
	        boiteSaisie.setContentText("Entrez le nouveau nom de votre catégorie : ");
	        boiteSaisie.showAndWait();
	        Optional<String> valider = boiteSaisie.showAndWait();
	        if (valider.isPresent()) {
		        // Récupère le résultat de la boîte de saisie
		        String resultat = boiteSaisie.getResult();
	
		        // Vérifie si le résultat n'est pas nul et non vide
		        if (resultat != null && !resultat.isEmpty()) {
		            // Tente de modifier le nom de la catégorie dans le stockage
		            if (Main.stockage.modifierElementListeCategorie((Categorie)Main.stockage.getListeCategorie().get(comboBox.getValue()), resultat)) {
		                // Met à jour la liste déroulante avec le nouveau nom de catégorie
		                comboBox.getItems().set(comboBox.getItems().indexOf(categorieCourante), resultat);
		                comboBox.setValue(resultat);
		            } else {
		                // Affiche une alerte si la modification de la catégorie échoue
		                afficherAlerte("Impossible modifier catégorie", "Impossible de modifier la catégorie");
		            }
		        } else {
		            // Affiche une alerte si le résultat de la boîte de saisie est vide
		            afficherAlerte("Impossible modifier catégorie", "Impossible de ne pas mettre d'intitulé à une catégorie");
		        }
	        } else {
	        	boiteSaisie.close();
	        }
	    } else {
	        // Si aucune catégorie n'est sélectionnée, affiche une alerte
	        Tooltip tooltip = new Tooltip("Impossible de modifier une catégorie");
	        Tooltip.install(btnModifier, tooltip);
	        afficherAlerte("Impossible modifier catégorie", "Veuillez choisir une catégorie pour la modifier");
	    }
	}

	
	@FXML
	void suivant(ActionEvent event) {
		try {
			if (comboBox.getValue() != null) {
				Main.voirParamCategorie(comboBox.getValue());
			} else {
				afficherAlerte("Choisissez une catégorie","Choisissez une catégorie avant d'appuyer sur voir les questions.");
			}
		} catch (Exception e) {
			// Gérer l'exception (afficher un message d'erreur, journaliser, etc.)
			e.printStackTrace();
		}
	}

	@FXML
	void retourMenu(ActionEvent event){
		Main.retourMenuPrincipal();

	}

	/** 
	 * Afficher les pop sur toutes les vues
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

	/**
	 * Vérifier que la taille de catégorie ne dépasse pas 
	 * 20 caractères
	 * @param intitule
	 * @return true si le regex est OK 
	 */
	public boolean verifierRegex(String intitule) {
		String nomCategorie = intitule;
		String regex = "^.{1,20}$";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(nomCategorie);

		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}
}
