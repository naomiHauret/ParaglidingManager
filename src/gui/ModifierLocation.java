package gui;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ModifierLocation extends JDialog{
	private int indexModification;
	
	private JPanel panelPrincipal;
	
	private JTextField textfieldIdentifantLocation;
	private JTextField textfieldPiloteNom;
	private JTextField textfieldPilotePrenom;
	private JTextField textfieldPiloteNumeroLicence;
	private JComboBox comboBoxParapente;
	private JTextField textfieldPrixTTC;
	private JTextField textfieldPrixHT;
	private JTextField textfielNombreHeures;
	private JTextField textfieldDateReservation;
	private JTextField textfieldPrenomInvite;
	private JTextField textfieldNomInvite;
	private JTextField textfieldPoidsInvite;
	private JTextField textfieldTailleInvite;
	
	private JLabel labelIdentifantLocation;
	private JLabel labelPiloteNom;
	private JLabel labelPilotePrenom;
	private JLabel labelPiloteNumeroLicence;
	private JLabel labelParapente;
	private JLabel labelPrixHT;
	private JLabel labelNombreHeures;
	private JLabel labelDateReservation;
	private JLabel labelNomInvite;
	private JLabel labelPrenomInvite;
	private JLabel labelPassager;
	private JLabel labelTailleInvite;
	private JLabel labelPoidsInvite;
	private JLabel labelPrixTTC;
	
	private JCheckBox checkBoxInvite;

	private JButton buttonModifier;
	private JButton buttonQuitter;
	
	public ModifierLocation(Vector<String> parapentesDisponibles){
		this.setSize(500, 400);
		this.setLayout(new BorderLayout());
		this.setTitle("Modifier une location");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.comboBoxParapente=new JComboBox<String>(parapentesDisponibles);
		this.comboBoxParapente.setMaximumSize(new Dimension(250,20));
		this.construireFenetre();
		this.desactiveBouton();
		this.initChamps();
	}
	
	public void construireFenetre(){
		
		this.panelPrincipal= new JPanel();
		this.panelPrincipal.setLayout(new GridLayout(15,2,20,2));

		this.textfieldIdentifantLocation= new JTextField();
		this.textfieldPiloteNom= new JTextField();
		this.textfieldPilotePrenom= new JTextField();
		this.textfieldPiloteNumeroLicence= new JTextField();
		this.textfieldPrixHT= new JTextField();
		this.textfieldPrixTTC= new JTextField();
		this.textfielNombreHeures= new JTextField();
		this.textfieldDateReservation= new JTextField();
		
		this.textfieldNomInvite= new JTextField();
		this.textfieldPrenomInvite= new JTextField();
		this.textfieldTailleInvite= new JTextField();
		this.textfieldPoidsInvite= new JTextField();
		
		this.textfieldIdentifantLocation.setMaximumSize(new Dimension(250,20));
		
		this.textfieldPiloteNom.setMaximumSize(new Dimension(250,20));
		this.textfieldPilotePrenom.setMaximumSize(new Dimension(250,20));
		this.textfieldPiloteNumeroLicence.setMaximumSize(new Dimension(250,20));
				
		this.textfieldPrixHT.setMaximumSize(new Dimension(50,20));
		this.textfieldPrixTTC.setMaximumSize(new Dimension(50,20));
		
		this.textfielNombreHeures.setMaximumSize(new Dimension(50,20));
		this.textfieldDateReservation.setMaximumSize(new Dimension(80,20));
		
		this.textfieldNomInvite.setMaximumSize(new Dimension(250,20));
		this.textfieldTailleInvite.setMaximumSize(new Dimension(50,20));
		this.textfieldPoidsInvite.setMaximumSize(new Dimension(50,20));

		
		this.labelIdentifantLocation= new JLabel("Identifiant de la location");
		this.labelPiloteNom= new JLabel("Nom du pilote");
		this.labelPilotePrenom= new JLabel("Prénom du pilote");
		this.labelPiloteNumeroLicence= new JLabel("Numéro de licence du pilote");
		this.labelParapente= new JLabel("Identifiant du parapente");
		this.labelNombreHeures= new JLabel("Durée de la location (arrondir à l'heure)");
		this.labelDateReservation= new JLabel("Date de la réservation");	
		this.labelPassager= new JLabel("Présence d'un passager");
		this.labelPrenomInvite= new JLabel("Prénom");
		this.labelNomInvite= new JLabel("Nom");
		this.labelPoidsInvite= new JLabel("Poids en kg");
		this.labelTailleInvite= new JLabel("Taille en cm");
		this.labelPrixHT= new JLabel("Prix hors taxe de la location");
		this.labelPrixTTC= new JLabel("Prix TTC de la location");
		
		this.checkBoxInvite= new JCheckBox("Oui");
		this.buttonModifier= new JButton("Valider");
		this.buttonQuitter= new JButton("Quitter");
		
		
		this.panelPrincipal.add(this.labelIdentifantLocation);
		this.panelPrincipal.add(this.textfieldIdentifantLocation);
		
		this.panelPrincipal.add(this.labelPiloteNom);
		this.panelPrincipal.add(this.textfieldPiloteNom);
		
		this.panelPrincipal.add(this.labelPilotePrenom);
		this.panelPrincipal.add(this.textfieldPilotePrenom);
		
		this.panelPrincipal.add(this.labelPiloteNumeroLicence);
		this.panelPrincipal.add(this.textfieldPiloteNumeroLicence);
		
		this.panelPrincipal.add(this.labelParapente);
		this.panelPrincipal.add(this.comboBoxParapente);
	
		this.panelPrincipal.add(this.labelNombreHeures);
		this.panelPrincipal.add(this.textfielNombreHeures);
		
		this.panelPrincipal.add(this.labelDateReservation);
		this.panelPrincipal.add(this.textfieldDateReservation);
		
		this.panelPrincipal.add(this.labelPassager);
		this.panelPrincipal.add(this.checkBoxInvite);
		this.panelPrincipal.add(this.labelNomInvite);
		this.panelPrincipal.add(this.textfieldNomInvite);
		this.panelPrincipal.add(this.labelPrenomInvite);
		this.panelPrincipal.add(this.textfieldPrenomInvite);
		this.panelPrincipal.add(this.labelTailleInvite);
		this.panelPrincipal.add(this.textfieldTailleInvite);
		this.panelPrincipal.add(this.labelPoidsInvite);
		this.panelPrincipal.add(this.textfieldPoidsInvite);
		
		this.panelPrincipal.add(this.labelPrixHT);
		this.panelPrincipal.add(this.textfieldPrixHT);
		
		this.panelPrincipal.add(this.labelPrixTTC);
		this.panelPrincipal.add(this.textfieldPrixTTC);
				
		this.panelPrincipal.add(this.buttonModifier);
		this.panelPrincipal.add(this.buttonQuitter);
		
		this.add(this.panelPrincipal, BorderLayout.CENTER);
		
		//ECOUTEURS
		this.buttonModifier.addActionListener(new Boutons());
		this.buttonQuitter.addActionListener(new Boutons());
		this.textfieldDateReservation.addKeyListener(new Clavier());
		this.textfieldIdentifantLocation.addKeyListener(new Clavier());
		this.textfieldNomInvite.addKeyListener(new Clavier());
		this.textfieldPiloteNom.addKeyListener(new Clavier());
		this.textfieldPiloteNumeroLicence.addKeyListener(new Clavier());
		this.textfieldPilotePrenom.addKeyListener(new Clavier());
		this.textfieldPoidsInvite.addKeyListener(new Clavier());
		this.textfieldPrenomInvite.addKeyListener(new Clavier());
		this.textfieldPrixHT.addKeyListener(new Clavier());
		this.textfieldPrixTTC.addKeyListener(new Clavier());
		this.textfieldTailleInvite.addKeyListener(new Clavier());
		this.textfielNombreHeures.addKeyListener(new Clavier());
		this.checkBoxInvite.addItemListener(new Checkbox());
		
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public class Boutons implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//////////////////////////////////////////////////////////////////////////////
			if(e.getSource()==buttonQuitter){
				dispose();
			}
			//////////////////////////////////////////////////////////////////////////////
			if(e.getSource()==buttonModifier){
				//TODO la méthode
				String presencePassager;
				if(getPresencePassager()){
					//Principale.modifierLocation(indexModification, getNumeroLicence(),  getParapente(), getIdentifiantLocation(), getPresencePassager(), new Passager(getNomInvite(), getPrenomInvite(), Integer.parseInt(getPoidsInvite()),  Integer.parseInt(getTailleInvite())), getNombreHeures(), getPrixHT(), getPrixTTC());
					presencePassager= "oui";
				}
				else{
					Principale.modifierLocation(indexModification, getNumeroLicence(),  getParapente(), getIdentifiantLocation(), getPresencePassager(), getNombreHeures(), getPrixHT(), getPrixTTC());
					presencePassager="non";
				}
				Principale.demandeModificationLocation(getIdentifiantLocation(),getNomPilote(), getPrenomPilote(), getNumeroLicence(), getParapente(), getNombreHeures(), getDateReservation(), presencePassager, getPrixTTC(),indexModification);
				dispose();
			}
		}
		
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public class Clavier implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			
		}
		//////////////////////////////////////////////////////////////////////////////

		@Override
		public void keyReleased(KeyEvent e) {
		if(getNombreHeures().isEmpty() || getNomPilote().isEmpty() || getPrenomPilote().isEmpty() || getNumeroLicence().isEmpty() || getDateReservation().isEmpty() ||  getParapente().isEmpty() || getPrixHT().isEmpty() || getPrixTTC().isEmpty() || getDateReservation()=="JJ/MM/AAAA"||( getPresencePassager() && (getPrenomInvite().isEmpty() || getNomInvite().isEmpty() || getTailleInvite().isEmpty() || getPoidsInvite().isEmpty()))) {
				buttonModifier.setEnabled(false);
			}
			else{
				buttonModifier.setEnabled(true);
			}
		}
		//////////////////////////////////////////////////////////////////////////////

		@Override
		public void keyTyped(KeyEvent e) {
			
		}
		
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public class Checkbox implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getSource()==checkBoxInvite){
				if(checkBoxInvite.isSelected()){
					textfieldNomInvite.setEnabled(true);
					textfieldPrenomInvite.setEnabled(true);
					textfieldPoidsInvite.setEnabled(true);
					textfieldTailleInvite.setEnabled(true);
				}
				else{
					setNomInvite(null);
					setPrenomInvite(null);
					setPoidsInvite(null);
					setTailleInvite(null);
					textfieldNomInvite.setEnabled(false);
					textfieldPrenomInvite.setEnabled(false);
					textfieldPoidsInvite.setEnabled(false);
					textfieldTailleInvite.setEnabled(false);
				}
				
			}
			
		}
		
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getIdentifiantLocation(){
		return this.textfieldIdentifantLocation.getText();
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getNumeroLicence(){
		return this.textfieldPiloteNumeroLicence.getText();
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getPrenomPilote(){
		return this.textfieldPilotePrenom.getText();
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getNomPilote(){
		return this.textfieldPiloteNom.getText();
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getParapente(){
		return this.comboBoxParapente.getSelectedItem().toString();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getDateReservation(){
		return this.textfieldDateReservation.getText();
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getPrixHT(){
		return this.textfieldPrixHT.getText();
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getPrixTTC(){
		return this.textfieldPrixTTC.getText();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getNombreHeures(){
		return this.textfielNombreHeures.getText();
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getNomInvite(){
		return this.textfieldNomInvite.getText();
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getPrenomInvite(){
		return this.textfieldPrenomInvite.getText();
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getTailleInvite(){
		return this.textfieldTailleInvite.getText();
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getPoidsInvite(){
		return this.textfieldPoidsInvite.getText();
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public boolean getPresencePassager(){
		return this.checkBoxInvite.isSelected();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setIdentifiantLocation(String cetIdentifiant){
		this.textfieldIdentifantLocation.setText(cetIdentifiant);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setNumeroLicence(String ceNumeroLicence){
		this.textfieldPiloteNumeroLicence.setText(ceNumeroLicence);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setPrenomPilote(String unPrenomPilote){
		  this.textfieldPilotePrenom.setText(unPrenomPilote);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setNomPilote(String unNomPilote){
		  this.textfieldPiloteNom.setText(unNomPilote);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setParapente(String unParapente){
		  this.comboBoxParapente.setSelectedItem(unParapente);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setDateReservation(String uneDateReservation){
		  this.textfieldDateReservation.setText(uneDateReservation);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setPrixHT(String unPrixHT){
		  this.textfieldPrixHT.setText(unPrixHT);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setPrixTTC(String unPrixTTC){
		  this.textfieldPrixTTC.setText(unPrixTTC);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setNombreHeures(String unNombreHeures){
		  this.textfielNombreHeures.setText(unNombreHeures);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setNomInvite(String unNomInvite){
		  this.textfieldNomInvite.setText(unNomInvite);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setPrenomInvite(String unPrenomInvite){
		  this.textfieldPrenomInvite.setText(unPrenomInvite);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setTailleInvite(String uneTailleInvite){
		  this.textfieldTailleInvite.setText(uneTailleInvite);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setPoidsInvite(String unPoidsInvite){
		  this.textfieldPoidsInvite.setText(unPoidsInvite);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setPresencePassager(boolean estPresent){
		  this.checkBoxInvite.setSelected(estPresent);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void desactiveBouton(){
		this.buttonModifier.setEnabled(false);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void initChamps(){
		this.textfieldPiloteNom.setEnabled(false);
		this.textfieldPiloteNumeroLicence.setEnabled(false);
		this.textfieldPilotePrenom.setEnabled(false);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void desactiveChampsInvite(){
		this.checkBoxInvite.setSelected(false);
		this.textfieldNomInvite.setEnabled(false);
		this.textfieldPrenomInvite.setEnabled(false);
		this.textfieldPoidsInvite.setEnabled(false);
		this.textfieldTailleInvite.setEnabled(false);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setTextFields(String identifiantLocation, String nomPilote, String prenomPilote, String numeroLicence, String identifiantParapente, String dureeLocation, String dateReservation, String presencePassager, String prixTTC){
		this.setIdentifiantLocation(identifiantLocation);
		this.setNomPilote(nomPilote);
		this.setPrenomPilote(prenomPilote);
		this.setNumeroLicence(numeroLicence);
		this.setParapente(identifiantParapente);
		this.setNombreHeures(dureeLocation);
		this.setDateReservation(dateReservation);
		if(presencePassager=="oui"){
			this.setPresencePassager(true);

		}
		else{
			this.setPresencePassager(false);
		}
		this.setPrixTTC(prixTTC);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setIndex(int index){
		this.indexModification=index;
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
