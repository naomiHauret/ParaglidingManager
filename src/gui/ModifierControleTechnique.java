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
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ModifierControleTechnique extends JDialog{
	
	private int indexModification;
	
	private JPanel panelPrincipal;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel panel5;
	private JPanel panel6;
	private JPanel panel7;

	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;

	private JTextField textFieldIdentifiant;
	private JTextField textFieldParapente;
	private JTextField textFieldDate;
	private JTextArea textAreaNomEtNombrePieces;
	private JTextField textFieldCout;
	private JCheckBox checkBoxVerificationAvantVol;
	private JCheckBox checkBoxVerificationApresVol;
	private JButton buttonModifier;
	private JButton buttonQuitter;
	
	public ModifierControleTechnique(){
		this.setSize(500, 400);
		this.setLayout(new BorderLayout());
		this.setTitle("Modifier un contrôle technique");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.construireFenetre();
		this.desactiveAjout();
	}
	
	public void construireFenetre(){
		this.panelPrincipal= new JPanel();
		this.panelPrincipal.setLayout(new BoxLayout(this.panelPrincipal, BoxLayout.PAGE_AXIS));
		
		this.panel1= new JPanel();
		this.panel1.setLayout(new BoxLayout(this.panel1, BoxLayout.LINE_AXIS));
		
		this.panel2= new JPanel();
		this.panel2.setLayout(new BoxLayout(this.panel2, BoxLayout.LINE_AXIS));
		
		this.panel3= new JPanel();
		this.panel3.setLayout(new BoxLayout(this.panel3, BoxLayout.LINE_AXIS));
		
		this.panel4= new JPanel();
		this.panel4.setLayout(new BoxLayout(this.panel4, BoxLayout.LINE_AXIS));
		
		this.panel5= new JPanel();
		this.panel5.setLayout(new BoxLayout(this.panel5, BoxLayout.LINE_AXIS));
		
		this.panel6= new JPanel();
		this.panel6.setLayout(new BoxLayout(this.panel6, BoxLayout.LINE_AXIS));
		
		this.panel7= new JPanel();
		this.panel7.setLayout(new BoxLayout(this.panel7, BoxLayout.LINE_AXIS));
		
		this.textFieldIdentifiant= new JTextField();
		this.textFieldIdentifiant.setMaximumSize(new Dimension(250,20));
		this.textFieldParapente= new JTextField();
		this.textFieldParapente.setMaximumSize(new Dimension(250,20));

		this.textFieldDate= new JTextField();
		this.textFieldDate.setMaximumSize(new Dimension(80,20));

		this.textAreaNomEtNombrePieces= new JTextArea();
		this.textAreaNomEtNombrePieces.setMaximumSize(new Dimension(250,180));
		
		this.textFieldCout= new JTextField();
		this.textFieldCout.setMaximumSize(new Dimension(100,20));

		this.checkBoxVerificationAvantVol= new JCheckBox("avant le vol");
		this.checkBoxVerificationApresVol= new JCheckBox("après le vol");
		
		this.buttonModifier= new JButton("Modifier");
		this.buttonQuitter= new JButton("Quitter");
		
		this.label1= new JLabel("Identifiant du contrôle technique");
		this.panel1.add(this.label1);
		this.panel1.add(Box.createRigidArea(new Dimension(29,20)));
		this.panel1.add(this.textFieldIdentifiant);
		this.panel1.add(Box.createRigidArea(new Dimension(20,20)));

		this.label2= new JLabel("Identifiant du parapente concerné");
		this.panel2.add(this.label2);
		this.panel2.add(Box.createRigidArea(new Dimension(20,20)));
		this.panel2.add(this.textFieldParapente);
		this.panel2.add(Box.createRigidArea(new Dimension(20,20)));

		this.label3= new JLabel("Date du contrôle technique       ");
		this.panel3.add(this.label3);
		this.panel3.add(Box.createRigidArea(new Dimension(206,20)));
		this.panel3.add(this.textFieldDate);
		this.panel3.add(Box.createRigidArea(new Dimension(20,20)));

		this.label4= new JLabel("Nom et nombre de pièces défectueuses");
		this.panel4.add(Box.createRigidArea(new Dimension(10,20)));
		this.panel4.add(this.label4);
		this.panel4.add(Box.createRigidArea(new Dimension(10,20)));
		this.panel4.add(this.textAreaNomEtNombrePieces);	
		this.panel4.add(Box.createRigidArea(new Dimension(10,20)));

		this.label5= new JLabel("Coût de la réparation en euros");
		this.panel5.add(Box.createRigidArea(new Dimension(10,20)));
		this.panel5.add(this.label5);
		this.panel5.add(Box.createRigidArea(new Dimension(180,20)));
		this.panel5.add(this.textFieldCout);
		this.panel5.add(new JLabel("euros"));
		this.panel5.add(Box.createRigidArea(new Dimension(20,20)));

		this.label6= new JLabel("Contrôle technique effectué ");
		this.panel6.add(this.label6);
		this.panel6.add(Box.createRigidArea(new Dimension(93,20)));
		this.panel6.add(this.checkBoxVerificationAvantVol);
		this.panel6.add(Box.createRigidArea(new Dimension(20,20)));
		this.panel6.add(this.checkBoxVerificationApresVol);
		this.panel6.add(Box.createRigidArea(new Dimension(20,20)));

		this.panel7.add(Box.createRigidArea(new Dimension(280,20)));
		this.panel7.add(this.buttonModifier);
		this.panel7.add(Box.createRigidArea(new Dimension(20,20)));
		this.panel7.add(this.buttonQuitter);
		
		this.panelPrincipal.add(Box.createRigidArea(new Dimension(0,5)));
		this.panelPrincipal.add(this.panel1);
		this.panelPrincipal.add(Box.createRigidArea(new Dimension(0,5)));
		this.panelPrincipal.add(this.panel2);
		this.panelPrincipal.add(Box.createRigidArea(new Dimension(0,5)));
		this.panelPrincipal.add(this.panel3);
		this.panelPrincipal.add(Box.createRigidArea(new Dimension(0,5)));
		this.panelPrincipal.add(this.panel4);
		this.panelPrincipal.add(Box.createRigidArea(new Dimension(0,5)));
		this.panelPrincipal.add(this.panel5);
		this.panelPrincipal.add(Box.createRigidArea(new Dimension(0,5)));
		this.panelPrincipal.add(this.panel6);
		this.panelPrincipal.add(Box.createRigidArea(new Dimension(0,15)));
		this.panelPrincipal.add(this.panel7);

		this.add(this.panelPrincipal, BorderLayout.CENTER);
		
		//ECOUTEURS
		this.buttonModifier.addActionListener(new Boutons());
		this.buttonQuitter.addActionListener(new Boutons());
		this.textAreaNomEtNombrePieces.addKeyListener(new Clavier());
		this.textFieldDate.addKeyListener(new Clavier());
		this.textFieldCout.addKeyListener(new Clavier());
		this.textFieldIdentifiant.addKeyListener(new Clavier());
		this.textFieldParapente.addKeyListener(new Clavier());
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	public class Boutons implements ActionListener{
	
		@Override
		public void actionPerformed(ActionEvent e) {
			///////////////////////////////////////////////////////////////////////////////////
			if(e.getSource()==buttonQuitter){ //on quitte la fenêtre
				dispose();
			}
			///////////////////////////////////////////////////////////////////////////////////	
			if(e.getSource()==buttonModifier){
				//TODO méthode pour effectuer les modifications sur les données
				//Principale.demandeModificationControleTechnique(textField_site.getText(), textField_id.getText(), textField_mdp.getText(), indexModification);
				dispose();
			}
		}
	
	}
	//////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	public class Clavier implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if(getIdentifiant().isEmpty() || getDate().isEmpty() || getParapente().isEmpty() || getCout().isEmpty() || getPieces()=="1 extrados\n1 élévateur\n2 suspente basse" || getDate()== "JJ/MM/AAAA"){
				buttonModifier.setEnabled(false);
			}
			else{
				buttonModifier.setEnabled(true);
			}
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			
		}
		
	}
	//////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////

	//identifiant
	
	public String getIdentifiant(){
		return this.textFieldIdentifiant.getText();
	}
	//////////////////////////////////////////////////////////////////////////////////////
	public void setIdentifiant(String texte){
		this.textFieldIdentifiant.setText(texte);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	//parapente
	
	public String getParapente(){
		return this.textFieldParapente.getText();
	}
	//////////////////////////////////////////////////////////////////////////////////////
	public void setParapente(String texte){
		this.textFieldParapente.setText(texte);
	}
	///////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	//date
	
	public String getDate(){
		return this.textFieldDate.getText();
	}
	//////////////////////////////////////////////////////////////////////////////////////
	public void setDate(String texte){
		this.textFieldDate.setText(texte);
	}
	///////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	//nombre et nom de pièces 
	
	public String getPieces(){
		return this.textAreaNomEtNombrePieces.getText();
	}
	//////////////////////////////////////////////////////////////////////////////////////
	public void setPieces(String texte){
		this.textAreaNomEtNombrePieces.setText(texte);
	}
	///////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	//coût
	
	public String getCout(){
		return this.textFieldCout.getText();
	}
	//////////////////////////////////////////////////////////////////////////////////////
	public void setCout(String texte){
		this.textFieldCout.setText(texte);
	}
	///////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	//contrôle avant vol
	
	public boolean getEtatControleAvantVol(){
		return this.checkBoxVerificationAvantVol.isSelected();
	}
	//////////////////////////////////////////////////////////////////////////////////////
	public void setEtatControleAvantVol(boolean etat){
		this.checkBoxVerificationAvantVol.setSelected(etat);
	}
	///////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	//contrôle après vol
	
	public boolean getEtatControleApres(){
		return this.checkBoxVerificationApresVol.isSelected();
	}
	//////////////////////////////////////////////////////////////////////////////////////
	public void setEtatControleApresVol(boolean etat){
		this.checkBoxVerificationApresVol.setSelected(etat);
	}
	///////////////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////////////////
	public void desactiveAjout(){
		this.buttonModifier.setEnabled(false);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setTextFields(String identifiantControleTechnique, String identifiantParapente, String dateControleTechnique, String cout){	
		this.setIdentifiant(identifiantControleTechnique);
		this.setParapente(identifiantParapente);
		this.setDate(dateControleTechnique);
		this.setCout(cout);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setIndex(int index){
		this.indexModification=index;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args){
		ModifierControleTechnique a;
		a=new ModifierControleTechnique();
		a.setVisible(true);
	}
}
