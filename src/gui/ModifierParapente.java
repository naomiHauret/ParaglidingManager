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
import javax.swing.JTextField;

public class ModifierParapente extends JDialog {
	private int indexModification;
	
	private JPanel panelPrincipal;
	
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel panel5;
	private JPanel panel6;
	private JPanel panel7;

	private JLabel labelIdentifiant;
	private JLabel labelNomModele;
	private JLabel labelNomMarque;
	private JLabel labelBiplace;
	private JLabel labelPoids;
	private JLabel labelDernierControleTechnique;
	
	private JTextField textfieldIdentifiant;
	private JTextField textfieldNomModele;
	private JTextField textfieldNomMarque;
	private JTextField textfieldPoids;
	private JTextField textfieldDernierControleTechnique;
	private JCheckBox checkboxBiplace;
	private JButton buttonModifier;
	private JButton buttonQuitter;
	
	public ModifierParapente(){		
		this.setSize(500, 350);
		this.setLayout(new BorderLayout());
		this.setTitle("Modifier un parapente");
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
		this.panel2= new JPanel();
		this.panel3= new JPanel();
		this.panel4= new JPanel();
		this.panel5= new JPanel();
		this.panel6= new JPanel();
		this.panel7= new JPanel();
		
		this.panel1.setLayout(new BoxLayout(this.panel1, BoxLayout.LINE_AXIS));
		this.panel2.setLayout(new BoxLayout(this.panel2, BoxLayout.LINE_AXIS));
		this.panel3.setLayout(new BoxLayout(this.panel3, BoxLayout.LINE_AXIS));
		this.panel4.setLayout(new BoxLayout(this.panel4, BoxLayout.LINE_AXIS));
		this.panel5.setLayout(new BoxLayout(this.panel5, BoxLayout.LINE_AXIS));
		this.panel6.setLayout(new BoxLayout(this.panel6, BoxLayout.LINE_AXIS));
		this.panel7.setLayout(new BoxLayout(this.panel7, BoxLayout.LINE_AXIS));

		this.labelIdentifiant= new JLabel("Identifiant du parapente");
		this.labelNomModele= new JLabel("Modèle du parapente");
		this.labelNomMarque= new JLabel("Marque du parapente");
		this.labelBiplace= new JLabel("Parapente bi-place");
		this.labelPoids= new JLabel("Poids du parapente en kg");
		this.labelDernierControleTechnique= new JLabel("Date du dernier contrôle technique du parapente");
		
		this.textfieldIdentifiant= new JTextField();
		this.textfieldIdentifiant.setMaximumSize(new Dimension(180,20));
		
		this.textfieldNomModele= new JTextField();
		this.textfieldNomModele.setMaximumSize(new Dimension(180,20));

		this.textfieldNomMarque= new JTextField();
		this.textfieldNomMarque.setMaximumSize(new Dimension(180,20));

		this.textfieldPoids= new JTextField();
		this.textfieldPoids.setMaximumSize(new Dimension(80,20));

		this.textfieldDernierControleTechnique= new JTextField();
		this.textfieldDernierControleTechnique.setMaximumSize(new Dimension(70,20));

		this.checkboxBiplace= new JCheckBox("oui");
		
		this.buttonModifier= new JButton("Modifier");
		this.buttonQuitter= new JButton("Quitter");
		
		this.panel1.add(this.labelIdentifiant);
		this.panel1.add(Box.createRigidArea(new Dimension(150,20)));
		this.panel1.add(this.textfieldIdentifiant);
		this.panel1.add(Box.createRigidArea(new Dimension(20,20)));

		
		this.panel2.add(this.labelNomModele);
		this.panel2.add(Box.createRigidArea(new Dimension(165,20)));
		this.panel2.add(this.textfieldNomModele);
		this.panel2.add(Box.createRigidArea(new Dimension(20,20)));

		
		this.panel3.add(this.labelNomMarque);
		this.panel3.add(Box.createRigidArea(new Dimension(165,20)));
		this.panel3.add(this.textfieldNomMarque);
		this.panel3.add(Box.createRigidArea(new Dimension(20,20)));

		this.panel4.add(this.labelBiplace);
		this.panel4.add(Box.createRigidArea(new Dimension(10,20)));
		this.panel4.add(this.checkboxBiplace);
		this.panel4.add(Box.createRigidArea(new Dimension(325,20)));


		this.panel5.add(this.labelPoids);
		this.panel5.add(Box.createRigidArea(new Dimension(10,20)));
		this.panel5.add(this.textfieldPoids);
		this.panel5.add(Box.createRigidArea(new Dimension(250,20)));

		this.panel6.add(this.labelDernierControleTechnique);
		this.panel6.add(Box.createRigidArea(new Dimension(10,20)));
		this.panel6.add(this.textfieldDernierControleTechnique);
		this.panel6.add(Box.createRigidArea(new Dimension(130,20)));

		this.panel7.add(Box.createRigidArea(new Dimension(280,20)));
		this.panel7.add(this.buttonModifier);
		this.panel7.add(Box.createRigidArea(new Dimension(10,20)));
		this.panel7.add(this.buttonQuitter);
		
		this.panelPrincipal.add(Box.createRigidArea(new Dimension(20,20)));
		this.panelPrincipal.add(this.panel1);
		this.panelPrincipal.add(Box.createRigidArea(new Dimension(10,20)));
		this.panelPrincipal.add(this.panel2);
		this.panelPrincipal.add(Box.createRigidArea(new Dimension(10,20)));
		this.panelPrincipal.add(this.panel3);
		this.panelPrincipal.add(Box.createRigidArea(new Dimension(10,20)));
		this.panelPrincipal.add(this.panel4);
		this.panelPrincipal.add(Box.createRigidArea(new Dimension(10,20)));
		this.panelPrincipal.add(this.panel5);
		this.panelPrincipal.add(Box.createRigidArea(new Dimension(10,20)));
		this.panelPrincipal.add(this.panel6);
		this.panelPrincipal.add(Box.createRigidArea(new Dimension(10,30)));
		this.panelPrincipal.add(this.panel7);

		this.add(this.panelPrincipal, BorderLayout.CENTER);
		
		//ECOUTEURS
		this.buttonModifier.addActionListener(new Boutons());
		this.buttonQuitter.addActionListener(new Boutons());
		this.textfieldDernierControleTechnique.addKeyListener(new Clavier());
		this.textfieldIdentifiant.addKeyListener(new Clavier());
		this.textfieldNomMarque.addKeyListener(new Clavier());
		this.textfieldNomModele.addKeyListener(new Clavier());
		this.textfieldPoids.addKeyListener(new Clavier());
		
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public class Boutons implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==buttonQuitter){
				dispose();
			}
			
			if(e.getSource()==buttonModifier){
				//TODO méthode
				String estBiplace;
				if(getEstBiplace()){
					estBiplace="oui";
				}
				else{
					estBiplace="non";
				}
				Principale.demandeModificationParapente(getIdentifiantParapente(), getModeleParapente(), getMarqueParapente(), estBiplace, getDateDernierControle(), indexModification);
				Principale.modifierParapente(indexModification,getDateDernierControle(), getIdentifiantParapente(), getPoidsParapente(), getModeleParapente(), getMarqueParapente(), getEstBiplace());

				dispose();
			}
		}
		
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public class Clavier implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if( getModeleParapente().isEmpty() || getMarqueParapente().isEmpty() || getPoidsParapente().isEmpty() || getIdentifiantParapente().isEmpty() || getDateDernierControle().isEmpty() || getDateDernierControle()=="JJ/MM/AAAA"){
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
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getIdentifiantParapente(){
		return this.textfieldIdentifiant.getText();
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getModeleParapente(){
		return this.textfieldNomModele.getText();
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getMarqueParapente(){
		return this.textfieldNomMarque.getText();
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public boolean getEstBiplace(){
		return this.checkboxBiplace.isSelected();
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getPoidsParapente(){
		return this.textfieldPoids.getText();
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getDateDernierControle(){
		return this.textfieldDernierControleTechnique.getText();
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setIdentifiantParapente(String identifiant){
		this.textfieldIdentifiant.setText(identifiant);
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setModeleParapente(String modele){
		this.textfieldNomModele.setText(modele);
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setMarqueParapente(String marque){
		this.textfieldNomMarque.setText(marque);
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setEstBiplace(boolean estBiplace){
		this.checkboxBiplace.setSelected(estBiplace);
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setPoidsParapente(String poids){
		this.textfieldPoids.setText(poids);
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setDateDernierControle(String date){
		this.textfieldDernierControleTechnique.setText(date);
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void desactiveAjout(){
		this.buttonModifier.setEnabled(false);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setTextFields(String identifiant, String modele, String marque){
		
		this.setIdentifiantParapente(identifiant);
		this.setModeleParapente(modele);
		this.setMarqueParapente(marque);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setIndex(int index){
		this.indexModification=index;
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args){
		ModifierParapente a = new ModifierParapente();
		a.setVisible(true);
	}
}

