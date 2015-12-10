package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AjouterParapente extends JDialog {
	
	private JPanel panelPrincipal;
	
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel panel5;
	private JPanel panel6;
	private JPanel panel7;

	private JLabel labelNomModele;
	private JLabel labelNomMarque;
	private JLabel labelBiplace;
	private JLabel labelPoids;
	private JLabel labelDernierControleTechnique;
	
	private JTextField textfieldNomModele;
	private JTextField textfieldNomMarque;
	private JTextField textfieldPoids;
	private JTextField textfieldDernierControleTechnique;
	private JCheckBox checkboxBiplace;
	private JButton buttonAjouter;
	private JButton buttonQuitter;
	
	public AjouterParapente(){		
		this.setSize(500, 350);
		this.setLayout(new BorderLayout());
		this.setTitle("Ajouter un parapente");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.construireFenetre();
		this.desactiveAjout();
		this.initChamps();
	}
	
	public void construireFenetre(){
		
		this.panelPrincipal= new JPanel();
		this.panelPrincipal.setLayout(new BoxLayout(this.panelPrincipal, BoxLayout.PAGE_AXIS));
		
		this.panel2= new JPanel();
		this.panel3= new JPanel();
		this.panel4= new JPanel();
		this.panel5= new JPanel();
		this.panel6= new JPanel();
		this.panel7= new JPanel();
		
		this.panel2.setLayout(new BoxLayout(this.panel2, BoxLayout.LINE_AXIS));
		this.panel3.setLayout(new BoxLayout(this.panel3, BoxLayout.LINE_AXIS));
		this.panel4.setLayout(new BoxLayout(this.panel4, BoxLayout.LINE_AXIS));
		this.panel5.setLayout(new BoxLayout(this.panel5, BoxLayout.LINE_AXIS));
		this.panel6.setLayout(new BoxLayout(this.panel6, BoxLayout.LINE_AXIS));
		this.panel7.setLayout(new BoxLayout(this.panel7, BoxLayout.LINE_AXIS));

		this.labelNomModele= new JLabel("Modèle du parapente");
		this.labelNomMarque= new JLabel("Marque du parapente");
		this.labelBiplace= new JLabel("Parapente bi-place");
		this.labelPoids= new JLabel("Poids du parapente en kg");
		this.labelDernierControleTechnique= new JLabel("Date du dernier contrôle technique du parapente");
		
		this.textfieldNomModele= new JTextField();
		this.textfieldNomModele.setMaximumSize(new Dimension(180,20));

		this.textfieldNomMarque= new JTextField();
		this.textfieldNomMarque.setMaximumSize(new Dimension(180,20));

		this.textfieldPoids= new JTextField();
		this.textfieldPoids.setMaximumSize(new Dimension(80,20));

		this.textfieldDernierControleTechnique= new JTextField();
		this.textfieldDernierControleTechnique.setMaximumSize(new Dimension(70,20));

		this.checkboxBiplace= new JCheckBox("oui");
		
		this.buttonAjouter= new JButton("Ajouter");
		this.buttonQuitter= new JButton("Quitter");
		
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
		this.panel7.add(this.buttonAjouter);
		this.panel7.add(Box.createRigidArea(new Dimension(10,20)));
		this.panel7.add(this.buttonQuitter);
		
		this.panelPrincipal.add(Box.createRigidArea(new Dimension(20,20)));
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
		this.buttonAjouter.addActionListener(new Boutons());
		this.buttonQuitter.addActionListener(new Boutons());
		this.textfieldDernierControleTechnique.addMouseListener(new Souris());
		this.textfieldDernierControleTechnique.addKeyListener(new Clavier());
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
			
			if(e.getSource()==buttonAjouter){
				String estBiplace;
				if(getEstBiplace()){
					estBiplace="oui";
				}
				else{
					estBiplace="non";
				}
				
				Principale.ajouterParapente(getDateDernierControle(),  getPoidsParapente(), getModeleParapente(), getMarqueParapente(), getEstBiplace());
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
			if( getModeleParapente().isEmpty() || getMarqueParapente().isEmpty() || getPoidsParapente().isEmpty() ||  getDateDernierControle().isEmpty() || getDateDernierControle()=="JJ/MM/AAAA"){
				buttonAjouter.setEnabled(false);
			}
			else{
				buttonAjouter.setEnabled(true);
	
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			
		}
		
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public class Souris implements MouseListener{
		int i=0;
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource()==textfieldDernierControleTechnique){
				setDateDernierControle("");
				i++;
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
		
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
		this.buttonAjouter.setEnabled(false);
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void initChamps(){
		setDateDernierControle("JJ/MM/AAAA");
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args){
		AjouterParapente a = new AjouterParapente();
		a.setVisible(true);
	}
}

