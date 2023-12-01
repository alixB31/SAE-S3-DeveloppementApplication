/*
 * MenuController.java                                      27 oct. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft" 
 */
package controleur;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import modele.EchecSerialisationRestauration;
import vue.Main;

/** 
 * Controleur de la vue du Menu.
 * @author Alix.Brugier Mateo.faussurier Nathan.Girardin Rayan.Ibrahime
 */
public class MenuController {

	@FXML
	private Button btnExport;

	@FXML
	private Button btnImport;

	@FXML
	private Button btnLancerPartie;

	@FXML
	private Button btnNotice;

	@FXML
	private Button btnParam;

	@FXML
	private Button btnQuitter;

	@FXML
	private Button btnJoueur;

	@FXML
	void quitterQuiz(ActionEvent event) throws EchecSerialisationRestauration {
		try {
			Main.stockage.serialiser();
		} catch (EchecSerialisationRestauration e) {
			ParametreController.afficherAlerte("Echec serialisation","Échec de la sérialisation.");			
		}

		Platform.exit();
	}

	@FXML 
	void VoirNotice(ActionEvent event) {
		Main.voirNotice();
	}

	@FXML 
	void lancerPartie(ActionEvent event) {

		Main.lancerPartie();
	}

	@FXML 
	void lancerParam(ActionEvent event) {
		Main.lancerParametre();
	}

	@FXML
	void btnImportAction(ActionEvent event) {
		Main.ihmImportation();
	}

	@FXML
	void btnExportAction(ActionEvent event) {
		Main.ihmChoix();;
	}
}
