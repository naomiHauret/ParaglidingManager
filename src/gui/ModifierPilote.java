package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class ModifierPilote extends JDialog {
	
	private int indexModification;
	
	private String[] lesCategories= {"A", "B", "C", "D"};
	private JLabel labelNom;
	private JLabel labelPrenom;
	private JLabel labelLicence;
	private JLabel labelCategorie;
	private JLabel labelTaille;
	private JLabel labelPoids;
	private JLabel labelDateNaissance;
	private JLabel labelAdresse;
	private JLabel labelNumeroTelephone;
	
	private JTextField textfieldNom;
	private JTextField textfieldPrenom;
	private JTextField textfieldDateNaissance;
	private JTextField textfieldLicence;
	private JComboBox comboBoxCategorie;
	private JTextField textfieldTaille;
	private JTextField textfieldPoids;
	private JScrollPane scrollPaneAdresse;
	private JTextArea textareaAdresse;
	private JTextField textfieldNumeroTelephone;
	private JButton buttonValider;
	private JButton buttonQuitter;
	
	private JPanel panelPrincipal;
	private JPanel panelNom;
	private JPanel panelPrenom;
	private JPanel panelDateNaissance;
	private JPanel panelLicence;
	private JPanel panelCategorie;
	private JPanel panelTaille;
	private JPanel panelPoids;
	private JPanel panelAdresse;
	private JPanel panelNumeroTelephone;
	private JPanel panelBoutons;
	

	public ModifierPilote(){
		this.setSize(500, 350);
		this.setLayout(new BorderLayout());
		this.setTitle("Modifier un pilote");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.construireFenetre();
		this.desactiveBouton();
	}
	
	public void construireFenetre(){
		
		this.panelPrincipal= new JPanel();
		this.panelNom= new JPanel();
		this.panelPrenom= new JPanel();
		this.panelDateNaissance= new JPanel();
		this.panelLicence= new JPanel();
		this.panelCategorie= new JPanel();
		this.panelTaille= new JPanel();
		this.panelPoids= new JPanel();
		this.panelDateNaissance= new JPanel();
		this.panelAdresse= new JPanel();
		this.panelNumeroTelephone= new JPanel();
		this.panelBoutons= new JPanel();
		
		this.labelNom= new JLabel("Nom");
		this.labelPrenom= new JLabel("Pr�nom");
		this.labelDateNaissance= new JLabel("Date de naissance");
		this.labelLicence= new JLabel("Num�ro de licence");
		this.labelCategorie= new JLabel("Cat�gorie");
		this.labelTaille= new JLabel("Taille en cm");
		this.labelPoids= new JLabel("Poids en kg");
		this.labelAdresse= new JLabel("Adresse");
		this.labelNumeroTelephone= new JLabel("Num�ro de t�l�phone");
		
		this.textfieldNom= new JTextField();
		this.textfieldNom.setMaximumSize(new Dimension(250,20));
		
		this.textfieldPrenom= new JTextField();
		this.textfieldPrenom.setMaximumSize(new Dimension(250,20));
		
		this.textfieldDateNaissance= new JTextField();
		this.textfieldDateNaissance.setMaximumSize(new Dimension(80,20));
		
		this.textfieldLicence= new JTextField();
		this.textfieldLicence.setMaximumSize(new Dimension(250,20));
		
		this.comboBoxCategorie= new JComboBox(this.lesCategories);
		this.comboBoxCategorie.setMaximumSize(new Dimension(42,20));
		this.textfieldTaille= new JTextField();
		this.textfieldTaille.setMaximumSize((new Dimension(50,20)));
		
		this.textfieldPoids= new JTextField();
		this.textfieldPoids.setMaximumSize((new Dimension(50,20)));

		this.textareaAdresse= new JTextArea();
		this.textareaAdresse.setMaximumSize(new Dimension(300,60));
		this.scrollPaneAdresse= new JScrollPane(this.textareaAdresse);
		
		this.textfieldNumeroTelephone= new JTextField();
		this.textfieldNumeroTelephone.setMaximumSize(new Dimension(90,20));
		
		this.buttonValider= new JButton("Valider");
		this.buttonQuitter= new JButton("Quitter");
		
		this.panelNom.setLayout(new BoxLayout(this.panelNom, BoxLayout.LINE_AXIS));
		this.panelPrenom.setLayout(new BoxLayout(this.panelPrenom, BoxLayout.LINE_AXIS));
		this.panelDateNaissance.setLayout(new BoxLayout(this.panelDateNaissance, BoxLayout.LINE_AXIS));
		this.panelLicence.setLayout(new BoxLayout(this.panelLicence, BoxLayout.LINE_AXIS));
		this.panelCategorie.setLayout(new BoxLayout(this.panelCategorie, BoxLayout.LINE_AXIS));
		this.panelTaille.setLayout(new BoxLayout(this.panelTaille, BoxLayout.LINE_AXIS));
		this.panelPoids.setLayout(new BoxLayout(this.panelPoids, BoxLayout.LINE_AXIS));
		this.panelAdresse.setLayout(new BoxLayout(this.panelAdresse, BoxLayout.LINE_AXIS));
		this.panelNumeroTelephone.setLayout(new BoxLayout(this.panelNumeroTelephone, BoxLayout.LINE_AXIS));
		this.panelBoutons.setLayout(new BoxLayout(this.panelBoutons, BoxLayout.LINE_AXIS));
		
		this.panelNom.add(Box.createRigidArea(new Dimension(5,0)));
		this.panelNom.add(this.labelNom);
		this.panelNom.add(Box.createRigidArea(new Dimension(200,0)));
		this.panelNom.add(this.textfieldNom);

		this.panelPrenom.add(Box.createRigidArea(new Dimension(5,0)));
		this.panelPrenom.add(this.labelPrenom);
		this.panelPrenom.add(Box.createRigidArea(new Dimension(181,0)));
		this.panelPrenom.add(this.textfieldPrenom);
		
		this.panelDateNaissance.add(Box.createRigidArea(new Dimension(5,0)));
		this.panelDateNaissance.add(this.labelDateNaissance);
		this.panelDateNaissance.add(Box.createRigidArea(new Dimension(291,0)));
		this.panelDateNaissance.add(this.textfieldDateNaissance);
		
		this.panelLicence.add(Box.createRigidArea(new Dimension(5,0)));
		this.panelLicence.add(this.labelLicence);
		this.panelLicence.add(Box.createRigidArea(new Dimension(120,0)));
		this.panelLicence.add(this.textfieldLicence);
		
		this.panelCategorie.add(Box.createRigidArea(new Dimension(5,0)));
		this.panelCategorie.add(this.labelCategorie);
		this.panelCategorie.add(Box.createRigidArea(new Dimension(375,0)));
		this.panelCategorie.add(this.comboBoxCategorie);

		this.panelTaille.add(Box.createRigidArea(new Dimension(5,0)));
		this.panelTaille.add(this.labelTaille);
		this.panelTaille.add(Box.createRigidArea(new Dimension(355,0)));
		this.panelTaille.add(this.textfieldTaille);
		
		this.panelPoids.add(Box.createRigidArea(new Dimension(5,0)));
		this.panelPoids.add(this.labelPoids);
		this.panelPoids.add(Box.createRigidArea(new Dimension(356,0)));
		this.panelPoids.add(this.textfieldPoids);
		
		this.panelAdresse.add(Box.createRigidArea(new Dimension(5,0)));
		this.panelAdresse.add(this.labelAdresse);
		this.panelAdresse.add(Box.createRigidArea(new Dimension(80,0)));
		this.panelAdresse.add(this.scrollPaneAdresse);
		
		this.panelNumeroTelephone.add(Box.createRigidArea(new Dimension(5,0)));
		this.panelNumeroTelephone.add(this.labelNumeroTelephone);
		this.panelNumeroTelephone.add(Box.createRigidArea(new Dimension(260,0)));
		this.panelNumeroTelephone.add(this.textfieldNumeroTelephone);
		
		this.panelBoutons.add(Box.createRigidArea(new Dimension(310,0)));
		this.panelBoutons.add(this.buttonValider);
		this.panelBoutons.add(Box.createRigidArea(new Dimension(10,0)));
		this.panelBoutons.add(this.buttonQuitter);

		this.panelPrincipal.setLayout(new BoxLayout(this.panelPrincipal, BoxLayout.PAGE_AXIS));
		this.panelPrincipal.add(Box.createRigidArea(new Dimension(0,5)));
		this.panelPrincipal.add(this.panelNom);
		this.panelPrincipal.add(Box.createRigidArea(new Dimension(0,5)));
		this.panelPrincipal.add(this.panelPrenom);
		this.panelPrincipal.add(Box.createRigidArea(new Dimension(0,5)));
		this.panelPrincipal.add(this.panelDateNaissance);
		this.panelPrincipal.add(Box.createRigidArea(new Dimension(0,5)));
		this.panelPrincipal.add(this.panelLicence);
		this.panelPrincipal.add(Box.createRigidArea(new Dimension(0,5)));
		this.panelPrincipal.add(this.panelCategorie);
		this.panelPrincipal.add(Box.createRigidArea(new Dimension(0,5)));
		this.panelPrincipal.add(this.panelTaille);
		this.panelPrincipal.add(Box.createRigidArea(new Dimension(0,5)));
		this.panelPrincipal.add(this.panelPoids);
		this.panelPrincipal.add(Box.createRigidArea(new Dimension(0,5)));
		this.panelPrincipal.add(this.panelAdresse);
		this.panelPrincipal.add(Box.createRigidArea(new Dimension(0,5)));
		this.panelPrincipal.add(this.panelNumeroTelephone);
		this.panelPrincipal.add(Box.createRigidArea(new Dimension(0,10)));
		this.panelPrincipal.add(this.panelBoutons);	
		
		this.add(this.panelPrincipal, BorderLayout.CENTER);
		
		//ECOUTEURS
		this.buttonValider.addActionListener(new Boutons());
		this.buttonQuitter.addActionListener(new Boutons());
		this.textareaAdresse.addKeyListener(new Clavier());
		this.textfieldDateNaissance.addKeyListener(new Clavier());
		this.textfieldNom.addKeyListener(new Clavier());
		this.textfieldLicence.addKeyListener(new Clavier());
		this.textfieldNumeroTelephone.addKeyListener(new Clavier());
		this.textfieldPoids.addKeyListener(new Clavier());
		this.textfieldPrenom.addKeyListener(new Clavier());
		this.textfieldTaille.addKeyListener(new Clavier());
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public class Boutons implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////

			if(e.getSource()==buttonValider){
				Principale.modifierPilote(indexModification, getNomPilote(), getPrenomPilote(), getDateNaissancePilote(), getLicencePilote(), getCategoriePilote(), getPoidsPilote(), getTaillePilote(), getAdressePilote(), getTelephonePilote() );
				dispose();
			}
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if(e.getSource()==buttonQuitter){
				dispose();
			}
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		}
		
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public class Clavier implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		@Override
		public void keyReleased(KeyEvent e) {
			if( getDateNaissancePilote().isEmpty() || getAdressePilote().isEmpty() || getNomPilote().isEmpty() || getPrenomPilote().isEmpty() || getLicencePilote().isEmpty() || getPoidsPilote().isEmpty() || getTaillePilote().isEmpty() || getTelephonePilote().isEmpty()){
				buttonValider.setEnabled(false);

			}
			
			else{
				buttonValider.setEnabled(true);
			}

		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		@Override
		public void keyTyped(KeyEvent e) {
			
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getNomPilote(){
		return this.textfieldNom.getText();
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getPrenomPilote(){
		return this.textfieldPrenom.getText();
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getDateNaissancePilote(){
		return this.textfieldDateNaissance.getText();
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getAdressePilote(){
		return this.textareaAdresse.getText();
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getLicencePilote(){
		return this.textfieldLicence.getText();
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getPoidsPilote(){
		return this.textfieldPoids.getText();
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getTaillePilote(){
		return this.textfieldTaille.getText();
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getTelephonePilote(){
		return this.textfieldNumeroTelephone.getText();
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getCategoriePilote(){
		return this.comboBoxCategorie.getSelectedItem().toString();
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setNomPilote(String ceNom){
		this.textfieldNom.setText(ceNom);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setPrenomPilote(String cePrenom){
		this.textfieldPrenom.setText(cePrenom);
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setDateNaissancePilote(String cetteDate){
		this.textfieldDateNaissance.setText(cetteDate);
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setAdressePilote(String cetteAdresse){
		this.textareaAdresse.setText(cetteAdresse);
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setLicencePilote(String cetteLicence){
		this.textfieldLicence.setText(cetteLicence);
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setPoidsPilote(String cePoids){
		this.textfieldPoids.setText(cePoids);
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setTaillePilote(String cetteTaille){
		this.textfieldTaille.setText(cetteTaille);
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setTelephonePilote(String ceTelephone){
		this.textfieldNumeroTelephone.setText(ceTelephone);
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setCategoriePilote(String cetteCategorie){
		this.comboBoxCategorie.setSelectedItem(cetteCategorie);
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void desactiveBouton(){
		this.buttonValider.setEnabled(false);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setTextFields(String licence, String nom, String prenom, String dateNaissance, String categorie){
		this.setLicencePilote(licence);
		this.setNomPilote(nom);
		this.setPrenomPilote(prenom);
		this.setCategoriePilote(categorie);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setIndex(int index){
		this.indexModification=index;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args){
		ModifierPilote a= new ModifierPilote();
		a.setVisible(true);
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
}
