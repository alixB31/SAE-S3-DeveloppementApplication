package controleur;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import modele.EchecSerialisationRestauration;
import vue.Main;

/** TODO comment class responsibility (SRP)
 * @author rayanibrahime
 *
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
