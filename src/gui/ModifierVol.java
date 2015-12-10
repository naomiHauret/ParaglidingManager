package gui;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ModifierVol extends JDialog{
	
	private int indexModification;
	
	private JPanel panelPrincipal;	
	private JTextField textfieldIdentifantLocation;
	private JTextField textfieldPiloteNom;
	private JTextField textfieldPilotePrenom;
	private JTextField textfieldPiloteNumeroLicence;
	private JTextField textfieldParapente;
	private JTextField textfielIdentifiantVol;
	private JTextField textfieldDateReservation;
	private JTextField textfieldDateVol;
	private JTextField textfieldNomParcours;
	private JTextField textfieldPrenomInvite;
	private JTextField textfieldNomInvite;
	private JTextField textfieldPoidsInvite;
	private JTextField textfieldTailleInvite;
	
	private JLabel labelIdentifantLocation;
	private JLabel labelPiloteNom;
	private JLabel labelPilotePrenom;
	private JLabel labelPiloteNumeroLicence;
	private JLabel labelParapente;
	private JLabel labelIdentifiantVol;
	private JLabel labelDateReservation;
	private JLabel labelDateVol;
	private JLabel labelNomParcours;
	private JLabel labelNomInvite;
	private JLabel labelPrenomInvite;
	private JLabel labelPassager;
	private JLabel labelTailleInvite;
	private JLabel labelPoidsInvite;

	private JCheckBox checkBoxInvite;
	
	private JButton buttonValider;
	private JButton buttonQuitter;
	
	public ModifierVol(){
		this.setSize(500, 400);
		this.setLayout(new BorderLayout());
		this.setTitle("Modifier un vol");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.construireFenetre();
		this.desactiveChamps();
		this.desactiveBouton();
	}
	
	public void construireFenetre(){
		
		this.panelPrincipal= new JPanel();
		this.panelPrincipal.setLayout(new GridLayout(15,20,10,2));

		this.textfieldIdentifantLocation= new JTextField();
		this.textfieldPiloteNom= new JTextField();
		this.textfieldPilotePrenom= new JTextField();
		this.textfieldPiloteNumeroLicence= new JTextField();
		this.textfieldParapente= new JTextField();
		
		this.textfielIdentifiantVol= new JTextField();
		this.textfieldDateReservation= new JTextField();
		this.textfieldDateVol= new JTextField();
		this.textfieldNomParcours= new JTextField();
		this.textfieldNomInvite= new JTextField();
		this.textfieldPrenomInvite= new JTextField();
		this.textfieldTailleInvite= new JTextField();
		this.textfieldPoidsInvite= new JTextField();
		
		this.textfieldIdentifantLocation.setMaximumSize(new Dimension(250,20));
		
		this.textfieldPiloteNom.setMaximumSize(new Dimension(250,20));
		this.textfieldPilotePrenom.setMaximumSize(new Dimension(250,20));
		this.textfieldPiloteNumeroLicence.setMaximumSize(new Dimension(250,20));
		
		this.textfieldParapente.setMaximumSize(new Dimension(250,20));
		
		
		this.textfielIdentifiantVol.setMaximumSize(new Dimension(50,20));
		this.textfieldDateReservation.setMaximumSize(new Dimension(80,20));
		this.textfieldDateVol.setMaximumSize(new Dimension(80,20));
		this.textfieldNomParcours.setMaximumSize(new Dimension(250,20));
		
		this.textfieldNomInvite.setMaximumSize(new Dimension(250,20));
		this.textfieldTailleInvite.setMaximumSize(new Dimension(50,20));
		this.textfieldPoidsInvite.setMaximumSize(new Dimension(50,20));

		
		this.labelIdentifantLocation= new JLabel("Identifiant de la location");
		this.labelPiloteNom= new JLabel("Nom du pilote");
		this.labelPilotePrenom= new JLabel("Prénom du pilote");
		this.labelPiloteNumeroLicence= new JLabel("Numéro de licence du pilote");
		this.labelParapente= new JLabel("Identifiant du parapente");
		this.labelNomParcours= new JLabel("Nom du parcours");
		this.labelIdentifiantVol= new JLabel("Identifiant du vol");
		this.labelDateReservation= new JLabel("Date de la réservation");	
		this.labelDateVol= new JLabel("Date du vol");	
		this.labelPassager= new JLabel("Présence d'un passager");
		this.labelPrenomInvite= new JLabel("Prénom");
		this.labelNomInvite= new JLabel("Nom");
		this.labelPoidsInvite= new JLabel("Poids en kg");
		this.labelTailleInvite= new JLabel("Taille en cm");
	
		this.checkBoxInvite= new JCheckBox("Oui");
	
		this.buttonValider= new JButton("Valider");
		this.buttonQuitter= new JButton("Quitter");
		
		
		this.panelPrincipal.add(this.labelIdentifantLocation);
		this.panelPrincipal.add(this.textfieldIdentifantLocation);
		this.panelPrincipal.add(this.labelDateReservation);
		this.panelPrincipal.add(this.textfieldDateReservation);
		this.panelPrincipal.add(this.labelPiloteNom);
		this.panelPrincipal.add(this.textfieldPiloteNom);
		this.panelPrincipal.add(this.labelPilotePrenom);
		this.panelPrincipal.add(this.textfieldPilotePrenom);
		this.panelPrincipal.add(this.labelPiloteNumeroLicence);
		this.panelPrincipal.add(this.textfieldPiloteNumeroLicence);
		this.panelPrincipal.add(this.labelParapente);
		this.panelPrincipal.add(this.textfieldParapente);
		
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
		
		this.panelPrincipal.add(this.labelIdentifiantVol);
		this.panelPrincipal.add(this.textfielIdentifiantVol);
		this.panelPrincipal.add(this.labelDateVol);
		this.panelPrincipal.add(this.textfieldDateVol);
		this.panelPrincipal.add(this.labelNomParcours);
		this.panelPrincipal.add(this.textfieldNomParcours);
		
		this.panelPrincipal.add(this.buttonValider);
		this.panelPrincipal.add(this.buttonQuitter);
		
		this.add(this.panelPrincipal, BorderLayout.CENTER);
		
		//ECOUTEURS
		this.buttonValider.addActionListener(new Boutons());
		this.buttonQuitter.addActionListener(new Boutons());
		this.textfieldDateVol.addKeyListener(new Clavier());
		this.textfielIdentifiantVol.addKeyListener(new Clavier());
		this.textfieldNomParcours.addKeyListener(new Clavier());

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
			if(e.getSource()==buttonValider){
				//TODO la méthode
				Principale.demandeModificationVol(getIdentifiantLocation(),getNomPilote(), getPrenomPilote(), getNumeroLicence(), getParapente(), getIdentifiantVol(), getDateVol(), getNomParcours(),  indexModification);
				dispose();
			}
		}
		
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public class Clavier implements KeyListener{
	
		@Override
		public void keyPressed(KeyEvent e) {
		
	}
	//////////////////////////////////////////////////////////////////////////////
	
		@Override
		public void keyReleased(KeyEvent e) {
			if(getDateVol().isEmpty() || getDateVol()=="JJ/MM/AAAA" || getNomParcours().isEmpty() || getIdentifiantVol().isEmpty()) {
				buttonValider.setEnabled(false);
			}
			else{
				buttonValider.setEnabled(true);
			}
		}
	//////////////////////////////////////////////////////////////////////////////
	
		@Override
		public void keyTyped(KeyEvent e) {
		
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
		return this.textfieldParapente.getText();
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getDateVol(){
		return this.textfieldDateVol.getText();
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getDateReservation(){
		return this.textfieldDateReservation.getText();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getNomParcours(){
		return this.textfieldNomParcours.getText();
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getIdentifiantVol(){
		return this.textfielIdentifiantVol.getText();
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
		  this.textfieldParapente.setText(unParapente);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setDateVol(String uneDateVol){
		  this.textfieldDateVol.setText(uneDateVol);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setDateReservation(String uneDateReservation){
		  this.textfieldDateReservation.setText(uneDateReservation);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setNomParcours(String unNomParcours){
		  this.textfieldNomParcours.setText(unNomParcours);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setIdentifiantVol(String unIdentifiantVol){
		  this.textfielIdentifiantVol.setText(unIdentifiantVol);
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
	public void desactiveChamps(){
		this.textfieldDateReservation.setEnabled(false);
		this.textfieldIdentifantLocation.setEnabled(false);
		this.textfieldNomInvite.setEnabled(false);
		this.textfieldParapente.setEnabled(false);
		this.textfieldPiloteNom.setEnabled(false);
		this.textfieldPiloteNumeroLicence.setEnabled(false);
		this.textfieldPilotePrenom.setEnabled(false);
		this.textfieldPoidsInvite.setEnabled(false);
		this.textfieldPrenomInvite.setEnabled(false);
	
		this.textfieldTailleInvite.setEnabled(false);
	
		this.checkBoxInvite.setEnabled(false);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void desactiveBouton(){
		this.buttonValider.setEnabled(false);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setTextFields(String identifiantLocation, String nomPilote, String prenomPilote, String numeroLicence, String identifiantParapente, String identifiantVol, String dateVol, String nomParcours){
		this.setIdentifiantLocation(identifiantLocation);
		this.setNomPilote(nomPilote);
		this.setPrenomPilote(prenomPilote);
		this.setNumeroLicence(numeroLicence);
		this.setParapente(identifiantParapente);
		this.setIdentifiantVol(identifiantVol);
		this.setDateVol(dateVol);
		this.setNomParcours(nomParcours);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setIndex(int index){
		this.indexModification=index;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args){
		ModifierVol a;
		a= new ModifierVol();
		a.setVisible(true);
	}
}
