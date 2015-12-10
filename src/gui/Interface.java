package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;
import java.util.regex.PatternSyntaxException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;


public class Interface extends JFrame{

	
	//DIMENSIONS DE L'ECRAN
    private static Rectangle dimension =  GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    private static int height = (int)dimension.getHeight();
    private static int width  = (int)dimension.getWidth();
	
    
   //TABLEAUX
	private JTable tableauPilote;
	private static DefaultTableModel tDefaultTableModelPilote;
	private ListSelectionModel selectionModelPilote;
    private Object[][] dataPilote;
    private final String  titlePilote[] = {"Numéro de licence","Nom", "Prénom", "Date de naissance", "Catégorie"};
    
	private JTable tableauParapente;
	private static DefaultTableModel tDefaultTableModelParapente;
	private ListSelectionModel selectionModelParapente;
    private Object[][] dataParapente;
    private final String  titleParapente[] = {"Identifiant","Modèle", "Marque","Est biplace?", "Date du dernier contrôle technique"};
    
	private JTable tableauLocation;
	private static DefaultTableModel tDefaultTableModelLocation;
	private ListSelectionModel selectionModelLocation;
    private Object[][] dataLocation;
    private final String  titleLocation[] = {"Identifiant de la location", "Nom du pilote", "Prénom du pilote", "Numéro de licence du pilote", "Identifiant du parapente", "Durée de la location (en heures)", "Date de la réservation", "Présence d'un passager?",  "Prix TTC de la location"};
    
	private JTable tableauVol;
	private static  DefaultTableModel tDefaultTableModelVol;
	private ListSelectionModel selectionModelVol;
    private Object[][] dataVol;
    private final String  titleVol[] = {"Identifiant du vol", "Date du vol", "Nom du parcours","Identifiant de la location", "Nom du pilote", "Prénom du pilote", "Numéro de licence du pilote", "Identifiant du parapente"};
    
	private JTable tableauControleTechnique;
	private static  DefaultTableModel tDefaultTableModelControleTechnique;
	private ListSelectionModel selectionModelControleTechnique;
    private Object[][] dataControleTechnique;
    private final String  titleControleTechnique[] = {"Identifiant du contrôle technique", "Identifiant du parapente", "Date du contrôle technique", "Coût de la réparation"};
    
    
	//COMPOSANTS
    //--panel principal: panelPrincipal
    private JSplitPane panelPrincipal;
    private JTextPane panelHistorique;
    private JScrollPane scrollPane;
	private StyleContext context; 
    private StyledDocument document; 
	private static StyledDocument doc;
    
    private static JTabbedPane lesOnglets;
        
    //--- panel d'actions: panelBoutons
    private JPanel panelBoutons;
	private JButton buttonAjouterPilote;
	private JButton buttonAjouterLocation;
	private JButton buttonAjouterParapente;
	private JButton buttonAjouterVol;
	private JButton buttonAjouterControleTechnique;

	private JButton buttonSupprimer;
	private JButton buttonAfficher;
	private JButton buttonModifier;
	
	private JButton buttonHistorique;
	
	//--- panel de recherche: panelRecherche
	private JPanel containerPanelRecherche;
	private JPanel panelRecherche;
	private JTextField champRecherche;
	private JLabel labelRechercher;
	private JLabel labelResultatRecherche;
	
	//-- panel de messages
	private JPanel panelMessages;
	private JLabel message;
	
	//CONSTRUCTEURS
	public Interface(){
		
		this.setTitle("Base de données Breizh Y Z'Ailes");
	    this.setResizable(true);
	    this.setLayout(new BorderLayout());
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(this.width, this.height);
	    this.setDefaultLookAndFeelDecorated(true);
	    this.setExtendedState(this.MAXIMIZED_BOTH);

	}
	
	/**
	 * construireFenetre()
	 * construit l'interface
	 * 
	 */
	public void construireFenetre(){
		//tableaux
		//getContentPane(). DefaultTableModel model = (DefaultTableModel)table.getModel()
		this.tDefaultTableModelVol= new DefaultTableModel(this.dataVol,this.titleVol);
		this.tableauVol= new JTable(this.tDefaultTableModelVol);
		this.tableauVol.setRowSelectionAllowed(true);
		this.selectionModelVol = this.tableauVol.getSelectionModel();
		this.selectionModelVol.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tDefaultTableModelVol=(DefaultTableModel)tableauVol.getModel();
		
		this.tDefaultTableModelParapente= new DefaultTableModel(this.dataParapente,this.titleParapente);
		this.tableauParapente= new JTable(this.tDefaultTableModelParapente);
		this.tableauParapente.setRowSelectionAllowed(true);
		this.selectionModelParapente = this.tableauParapente.getSelectionModel();
		this.selectionModelParapente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tDefaultTableModelParapente=(DefaultTableModel)tableauParapente.getModel();

		
		this.tDefaultTableModelPilote= new DefaultTableModel(this.dataPilote,this.titlePilote);
		this.tableauPilote= new JTable(this.tDefaultTableModelPilote);
		this.tableauPilote.setRowSelectionAllowed(true);
		this.selectionModelPilote = this.tableauPilote.getSelectionModel();
		this.selectionModelPilote.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tDefaultTableModelPilote=(DefaultTableModel)tableauPilote.getModel();

		
		this.tDefaultTableModelControleTechnique= new DefaultTableModel(this.dataControleTechnique,this.titleControleTechnique);
		this.tableauControleTechnique= new JTable(this.tDefaultTableModelControleTechnique);
		this.tableauControleTechnique.setRowSelectionAllowed(true);
		this.selectionModelControleTechnique = this.tableauControleTechnique.getSelectionModel();
		this.selectionModelControleTechnique.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tDefaultTableModelControleTechnique=(DefaultTableModel)tableauControleTechnique.getModel();

		this.tDefaultTableModelLocation= new DefaultTableModel(this.dataLocation,this.titleLocation);
		this.tableauLocation= new JTable(this.tDefaultTableModelLocation);
		this.tableauLocation.setRowSelectionAllowed(true);
		this.selectionModelLocation = this.tableauLocation.getSelectionModel();
		this.selectionModelLocation.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tDefaultTableModelLocation=(DefaultTableModel)tableauLocation.getModel();

		
		//panel principal
		//-- les onglets
		this.lesOnglets= new JTabbedPane();
		this.context = new StyleContext();
	    this.document = new DefaultStyledDocument(context);
		this.panelHistorique= new JTextPane(document);
		this.doc = panelHistorique.getStyledDocument();
		
		this.panelHistorique.setText("Historique de vos actions\n");
		this.scrollPane= new JScrollPane(this.panelHistorique);
		this.panelPrincipal= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,this.lesOnglets, this.scrollPane);
		this.panelPrincipal.setDividerLocation(width*4/7);
		//---panel recherche: panelRecherche
		this.panelRecherche= new JPanel();
		this.panelRecherche.setLayout(new BoxLayout(this.panelRecherche, BoxLayout.LINE_AXIS));
		this.champRecherche= new JTextField ();
		this.labelRechercher= new JLabel("Rechercher un pilote");
		this.champRecherche.setMaximumSize(new Dimension(470,45));
		this.labelResultatRecherche= new JLabel("");
		
		this.containerPanelRecherche= new JPanel();
		this.containerPanelRecherche.setLayout(new BoxLayout(this.containerPanelRecherche, BoxLayout.PAGE_AXIS));
		this.containerPanelRecherche.add(Box.createRigidArea(new Dimension(0,15)));
		this.containerPanelRecherche.add(this.panelRecherche);
		this.containerPanelRecherche.add(Box.createRigidArea(new Dimension(100,5)));
		this.containerPanelRecherche.add(this.labelResultatRecherche);
		
		//construction de panelRecherche
		this.panelRecherche.add(Box.createRigidArea(new Dimension(width*10/15,20)));
		this.panelRecherche.add(this.labelRechercher);	
		this.panelRecherche.add(Box.createRigidArea(new Dimension(20,20)));
		this.panelRecherche.add(this.champRecherche);
		this.panelRecherche.setPreferredSize(new Dimension(width, height*1/10));

		//---panel actions: panelBoutons
		this.panelBoutons= new JPanel();
		this.panelBoutons.setLayout(new BoxLayout(this.panelBoutons, BoxLayout.PAGE_AXIS));
	
		this.buttonAjouterPilote = new JButton("Ajouter un pilote...");
		this.buttonAjouterParapente= new JButton("Ajouter un parapente...");
		this.buttonAjouterControleTechnique= new JButton("Ajouter un contrôle technique\n à un parapente...");
		this.buttonAjouterLocation= new JButton("Ajouter une location\n à un pilote...");
		this.buttonAjouterVol= new JButton("Ajouter un vol\n à une location...");
		
		this.buttonSupprimer= new JButton("Supprimer");
		this.buttonAfficher= new JButton("Afficher");
		this.buttonModifier= new JButton("Modifier...");
	
		this.buttonHistorique= new JButton("Historique");
		
		this.buttonAjouterPilote.setMaximumSize(new Dimension(340,45));
		this.buttonAjouterParapente.setMaximumSize(new Dimension(340,45));
		this.buttonAjouterControleTechnique.setMaximumSize(new Dimension(340,45));
		this.buttonAjouterVol.setMaximumSize(new Dimension(340,45));
		this.buttonAjouterLocation.setMaximumSize(new Dimension(340,45));

		this.buttonAfficher.setMaximumSize(new Dimension(340,45));
		this.buttonModifier.setMaximumSize(new Dimension(340,45));
		this.buttonSupprimer.setMaximumSize(new Dimension(340,45));

		this.buttonHistorique.setMaximumSize(new Dimension(340,45));

		//construction de panelBoutons
		this.panelBoutons.add(Box.createVerticalGlue());
		this.panelBoutons.add(this.buttonAfficher);
		this.panelBoutons.add(Box.createRigidArea(new Dimension(40,10)));
		
		this.panelBoutons.add(this.buttonAjouterPilote);
		this.panelBoutons.add(Box.createRigidArea(new Dimension(40,5)));
		this.panelBoutons.add(this.buttonAjouterParapente);
		this.panelBoutons.add(Box.createRigidArea(new Dimension(40,5)));
		this.panelBoutons.add(this.buttonAjouterLocation);
		this.panelBoutons.add(Box.createRigidArea(new Dimension(40,5)));
		this.panelBoutons.add(this.buttonAjouterVol);
		this.panelBoutons.add(Box.createRigidArea(new Dimension(40,5)));
		this.panelBoutons.add(this.buttonAjouterControleTechnique);
		this.panelBoutons.add(Box.createRigidArea(new Dimension(40,10)));
		
		this.panelBoutons.add(this.buttonModifier);
		this.panelBoutons.add(Box.createRigidArea(new Dimension(40,10)));
		this.panelBoutons.add(this.buttonSupprimer);
		this.panelBoutons.add(Box.createRigidArea(new Dimension(40,10)));
		this.panelBoutons.add(this.buttonHistorique);
		this.panelBoutons.add(Box.createVerticalGlue());

		this.panelBoutons.setPreferredSize(new Dimension(width*1/6, height));
		this.buttonHistorique.setBackground(CustomColor.lightRed);
		
		//construction de panelMessage
		this.panelMessages= new JPanel();
		this.panelMessages.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.message= new JLabel("");
		this.panelMessages.add(this.message);
			
		//construction
		this.add(this.panelPrincipal, BorderLayout.CENTER);
		this.add(this.panelBoutons, BorderLayout.WEST);
		this.add(this.containerPanelRecherche, BorderLayout.NORTH);
		this.add(this.panelMessages, BorderLayout.SOUTH);
		
		
		//ECOUTEURS
		this.buttonAfficher.addActionListener(new Boutons());
		this.buttonAjouterControleTechnique.addActionListener(new Boutons());
		this.buttonAjouterLocation.addActionListener(new Boutons());
		this.buttonAjouterParapente.addActionListener(new Boutons());
		this.buttonAjouterPilote.addActionListener(new Boutons());
		this.buttonAjouterVol.addActionListener(new Boutons());
		this.buttonModifier.addActionListener(new Boutons());
		this.buttonSupprimer.addActionListener(new Boutons());
		this.buttonHistorique.addActionListener(new Boutons());

		this.tDefaultTableModelControleTechnique.addTableModelListener(new Tableau());
		this.tDefaultTableModelPilote.addTableModelListener(new Tableau());
		this.tDefaultTableModelParapente.addTableModelListener(new Tableau());
		this.tDefaultTableModelVol.addTableModelListener(new Tableau());
		this.tDefaultTableModelLocation.addTableModelListener(new Tableau());
		this.champRecherche.addKeyListener(new Clavier());
		this.lesOnglets.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	            int i=  lesOnglets.getSelectedIndex();
	            if(i==0){
	            	labelRechercher.setText("Rechercher un pilote");
	            	labelResultatRecherche.setText("");
	            }
	            
	            if(i==1){
	            	labelRechercher.setText("Rechercher une location");
	            	labelResultatRecherche.setText("");
	            }
	            
	            if(i==2){
	            	labelRechercher.setText("Rechercher un vol");
	            	labelResultatRecherche.setText("");

	            }
	            
	            if(i==3){
	            	labelRechercher.setText("Rechercher un parapente");
	            	labelResultatRecherche.setText("");

	            }
            	
	            if(i==4){
	            	labelRechercher.setText("Rechercher un contrôle technique");
	            	labelResultatRecherche.setText("");

	            }

		
	        }
	    });
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public class Clavier implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}

		@Override
		public void keyTyped(KeyEvent e) {	
			if(e.getSource()==champRecherche ){
				
				int i=lesOnglets.getSelectedIndex();

				if(i==0){
					filtrer(tDefaultTableModelPilote);
				}
				
				if(i==1){
					filtrer(tDefaultTableModelLocation);

				}
				
				if(i==2){
					filtrer(tDefaultTableModelVol);
				}				

				
				if(i==3){
					filtrer(tDefaultTableModelParapente);

				}
				
			
				if(i==4){
					filtrer(tDefaultTableModelControleTechnique);

				}
			}		
		}
		
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public class Tableau implements TableModelListener{

		@Override
		public void tableChanged(TableModelEvent e) {
			setBoutons();
		}

	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public class Boutons implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if(e.getSource()== buttonAjouterPilote){
				Principale.ouvrirAjouterPilote();
			}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if(e.getSource()== buttonAjouterParapente){
				Principale.ouvrirAjouterParapente();
			}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////	
			if(e.getSource()== buttonAjouterVol){
				int i=0	;
				boolean trouve=false;
				
				if(selectionModelLocation.isSelectionEmpty()){
					JOptionPane.showMessageDialog( null, "Aucune location n'a été sélectionnée! Veuillez en sélectionner une le tableau des locations.","Erreur: ajout de vol impossible", JOptionPane.ERROR_MESSAGE);
					lesOnglets.setSelectedIndex(1);
				}
				else{
					if(tDefaultTableModelLocation.getRowCount()<=0){
						JOptionPane.showMessageDialog( null, "Aucune location n'est présente dans la base! Veuillez en ajouter une.","Erreur: ajout de vol impossible", JOptionPane.ERROR_MESSAGE);

					}
					else{
						while(trouve==false && i< tDefaultTableModelPilote.getRowCount()){
							if(selectionModelLocation.isSelectedIndex(i)){
								Principale.ouvrirAjouterVol((String) tDefaultTableModelLocation.getValueAt(i, 0));
								trouve=true;
							}
							else{
								i++;
							}
						}
					}	
				}
			}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if(e.getSource()== buttonAjouterLocation){
				int i=0	;
				boolean trouve=false;
				
				if(selectionModelPilote.isSelectionEmpty()){
					JOptionPane.showMessageDialog( null, "Aucun pilote n'a été sélectionné! Veuillez en sélectionner un le tableau des pilotes.","Erreur: ajout de location impossible", JOptionPane.ERROR_MESSAGE);
					lesOnglets.setSelectedIndex(0);
				}
				else{
					if(tDefaultTableModelParapente.getRowCount()<=0){
						JOptionPane.showMessageDialog( null, "Aucun parapente n'est présent dans la base! Veuillez en ajouter un.","Erreur: ajout de location impossible", JOptionPane.ERROR_MESSAGE);

					}
					else{
						while(trouve==false && i< tDefaultTableModelPilote.getRowCount()){
							if(selectionModelPilote.isSelectedIndex(i)){
								Principale.ouvrirAjouterLocation((String) tDefaultTableModelPilote.getValueAt(i, 0));
								trouve=true;
							}
							else{
								i++;
							}
						}
					}
					
					
				}
				
			}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if(e.getSource()== buttonAjouterControleTechnique){
				int i= 0;

				boolean trouve=false;
				
				if(selectionModelParapente.isSelectionEmpty()){
					JOptionPane.showMessageDialog( null, "Aucun parapente n'a été sélectionné! Veuillez en sélectionner un le tableau des parapentes.","Erreur: ajout de contrôle technique impossible", JOptionPane.ERROR_MESSAGE);
					lesOnglets.setSelectedIndex(0);
				}
				else{
					if(tDefaultTableModelParapente.getRowCount()<=0){
						JOptionPane.showMessageDialog( null, "Aucun parapente n'est présent dans la base! Veuillez en ajouter un.","Erreur: ajout de contrôle technique impossible", JOptionPane.ERROR_MESSAGE);

					}
					else{
						while(trouve==false && i< tDefaultTableModelParapente.getRowCount()){
							if(selectionModelParapente.isSelectedIndex(i)){
								Principale.ouvrirAjouterControleTechnique((String)(tDefaultTableModelParapente.getValueAt(i, 0)));
								trouve=true;
							}
							else{
								i++;
							}
						}
					}
				}
			}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////	
			if(e.getSource()== buttonAfficher){
				int index= lesOnglets.getSelectedIndex();
				int i=0;
				switch(index){
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					case 0:
						i=afficher(selectionModelPilote, tDefaultTableModelPilote);					
						
						if(i!=-1){							
							Principale.ouvrirAfficherPilote((String)tDefaultTableModelPilote.getValueAt(i, 0).toString());
						}
						
						else{
							//Principale.ouvrirAfficherTousPilote();
							JOptionPane.showMessageDialog( null, "Aucun élément n'a été sélectionné! Veuillez en sélectionner un dans un des tableaux.","Erreur: affichage impossible", JOptionPane.ERROR_MESSAGE);

						}
						
					break;
					
					/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					case 1: 
						i=afficher(selectionModelLocation, tDefaultTableModelLocation);					
						if(i!=-1){							
							Principale.ouvrirAfficherLocation((String)tDefaultTableModelLocation.getValueAt(i, 0).toString());
						}	
						else{
							//Principale.ouvrirAfficherTousLocation();
							JOptionPane.showMessageDialog( null, "Aucun élément n'a été sélectionné! Veuillez en sélectionner un dans un des tableaux.","Erreur: affichage impossible", JOptionPane.ERROR_MESSAGE);

						}
					break;
					
					/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					case 2: 
						i=afficher(selectionModelVol, tDefaultTableModelVol);					
						if(i!=-1){							
							Principale.ouvrirAfficherVol((String)tDefaultTableModelVol.getValueAt(i, 0).toString());
						}
						else{
							//Principale.ouvrirAfficherTousVol();
							JOptionPane.showMessageDialog( null, "Aucun élément n'a été sélectionné! Veuillez en sélectionner un dans un des tableaux.","Erreur: affichage impossible", JOptionPane.ERROR_MESSAGE);

						}
					break;
					
					/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					
					case 3:	
						i=afficher(selectionModelParapente, tDefaultTableModelParapente);					
						if(i!=-1){							
							Principale.ouvrirAfficherParapente((String)tDefaultTableModelParapente.getValueAt(i, 0).toString());
						}	
						else{
							JOptionPane.showMessageDialog( null, "Aucun élément n'a été sélectionné! Veuillez en sélectionner un dans un des tableaux.","Erreur: affichage impossible", JOptionPane.ERROR_MESSAGE);

						}
					break;
					/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

					case 4:	
						i=afficher(selectionModelControleTechnique, tDefaultTableModelControleTechnique);					
						if(i!=-1){							
							Principale.ouvrirAfficherControleTechnique((String)tDefaultTableModelControleTechnique.getValueAt(i, 0).toString());
						}	
						else{
							//Principale.ouvrirAfficherTousControleTechnique();
							JOptionPane.showMessageDialog( null, "Aucun élément n'a été sélectionné! Veuillez en sélectionner un dans un des tableaux.","Erreur: affichage impossible", JOptionPane.ERROR_MESSAGE);

						}
					break;
					
					/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

					default: 
						setMessage("Une erreur est survenue. Veuillez relancer la base s'il vous plaît", Color.RED);
					break;
					/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				}
			}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if(e.getSource()== buttonSupprimer){
				int index= lesOnglets.getSelectedIndex();
				int i;
				switch(index){
					case 0:
						i=supprimer(selectionModelPilote, tDefaultTableModelPilote);
						
						if(i!=-1){
							Principale.suppressionPilote((String)tDefaultTableModelPilote.getValueAt(i, 0));
							
						}
						
					break;
					
					case 1: 
						i=supprimer(selectionModelLocation, tDefaultTableModelLocation);
						
						if(i!=-1){
							Principale.suppressionLocation((String)tDefaultTableModelLocation.getValueAt(i, 0));
						}
					break;
					
					case 2: 
						i=supprimer(selectionModelVol, tDefaultTableModelVol);
						
						if(i!=-1){
							Principale.suppressionVol((String)tDefaultTableModelVol.getValueAt(i, 0));
						}
					break;
					
					case 3:	
						i=supprimer(selectionModelParapente, tDefaultTableModelParapente);
						
						if(i!=-1){
							Principale.suppressionParapente((String)tDefaultTableModelParapente.getValueAt(i, 0));
						}
					break;
					
					case 4:	
						i=supprimer(selectionModelControleTechnique, tDefaultTableModelControleTechnique);
						if(i!=-1){
							Principale.suppressionControleTechnique((String)tDefaultTableModelControleTechnique.getValueAt(i, 0));

						}
					break;
					
					default: 
						setMessage("Une erreur est survenue. Veuillez relancer la base s'il vous plaît", Color.RED);
					break;
				}
			}
			
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if(e.getSource()==buttonHistorique){

				if((buttonHistorique.getBackground()==CustomColor.lightGreen) || (buttonHistorique.getBackground()==CustomColor.LIGHTGREEN)){
					System.out.println("clicToRed\n");
					buttonHistorique.setBackground(CustomColor.lightRed);
					panelPrincipal.setRightComponent(scrollPane);
					panelPrincipal.setDividerLocation(width*4/7);

				}
				else{
					panelPrincipal.remove(scrollPane);
					buttonHistorique.setBackground(CustomColor.lightGreen);
					
				}
			}
			
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			if(e.getSource()== buttonModifier){
				int index= lesOnglets.getSelectedIndex();
				int i;
				switch(index){
					case 0:
						i=modifier(selectionModelPilote, tDefaultTableModelPilote);
						
						if(i!=-1){
							
							Principale.ouvrirModifierPilote((String)tDefaultTableModelPilote.getValueAt(i, 0), i);
							System.out.println((String)tDefaultTableModelPilote.getValueAt(i, 0));
						}
						
					break;
					
					case 1: 
						i=modifier(selectionModelLocation, tDefaultTableModelLocation);
						if(i!=-1){
							int k=0;
							if(tDefaultTableModelParapente.getRowCount()<=0){
								JOptionPane.showMessageDialog( null, "Aucun parapente n'est présent dans la base! Veuillez en ajouter un.","Erreur: modification de location impossible", JOptionPane.ERROR_MESSAGE);

							}
							else{
								Vector<String> lesParapentesDispo = new Vector<String>();
								
								while(k< tDefaultTableModelParapente.getRowCount()){
									lesParapentesDispo.add((String) tDefaultTableModelParapente.getValueAt(k, 0));
									System.out.println(lesParapentesDispo.get(k)+"\n");
									k++;
								}
							
							Principale.ouvrirModifierLocation(tDefaultTableModelLocation.getValueAt(i, 0)+" "+tDefaultTableModelLocation.getValueAt(i, 1)+" "+tDefaultTableModelLocation.getValueAt(i, 2)+" "+tDefaultTableModelLocation.getValueAt(i, 3)+" "+tDefaultTableModelLocation.getValueAt(i, 4)+" "+tDefaultTableModelLocation.getValueAt(i, 5)+" "+tDefaultTableModelLocation.getValueAt(i, 6)+" "+tDefaultTableModelLocation.getValueAt(i, 7)+" "+tDefaultTableModelLocation.getValueAt(i, 8), i, lesParapentesDispo);
						
							}
						}
					break;
					
					case 2: 
						i=modifier(selectionModelVol, tDefaultTableModelVol);
						if(i!=-1){
							Principale.ouvrirModifierVol(tDefaultTableModelVol.getValueAt(i, 0)+" "+tDefaultTableModelVol.getValueAt(i, 1)+" "+tDefaultTableModelVol.getValueAt(i, 2)+" "+tDefaultTableModelVol.getValueAt(i, 3)+" "+tDefaultTableModelVol.getValueAt(i, 4)+" "+tDefaultTableModelVol.getValueAt(i, 5)+" "+tDefaultTableModelVol.getValueAt(i, 6)+" "+tDefaultTableModelVol.getValueAt(i, 7), i);
						}
					break;
					
					case 3:	
						i=modifier(selectionModelParapente, tDefaultTableModelParapente);
						if(i!=-1){
							Principale.ouvrirModifierParapente(i);
						}
					break;
					
					case 4:	
						i=modifier(selectionModelControleTechnique, tDefaultTableModelControleTechnique);
						if(i!=-1){
							Principale.ouvrirModifierControleTechnique(tDefaultTableModelControleTechnique.getValueAt(i, 0)+" "+tDefaultTableModelControleTechnique.getValueAt(i, 1)+" "+tDefaultTableModelControleTechnique.getValueAt(i, 2)+" "+tDefaultTableModelControleTechnique.getValueAt(i, 3), i);

						}
					break;
					
					default: 
						setMessage("Une erreur est survenue. Veuillez relancer la base s'il vous plaît", Color.RED);
					break;
				}
			}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public class Fenetre implements WindowListener{
	
		@Override
		public void windowActivated(WindowEvent e) {
		
		}
		
		@Override
		public void windowClosed(WindowEvent e) {
		
		}
		
		@Override
		public void windowClosing(WindowEvent e) {
			Principale.closeBase();
		}
		
		
		@Override
		public void windowDeiconified(WindowEvent e) {
		
		}
		
		@Override
		public void windowIconified(WindowEvent e) {
		
		}
		
		@Override
		public void windowOpened(WindowEvent e) {
		
		}
		
		@Override
		public void windowDeactivated(WindowEvent e) {
		
		}
	
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void initialiseOnglets(){
		
       	JScrollPane tmpScrollPane1= new JScrollPane(this.tableauPilote);
       	lesOnglets.add("Pilotes", tmpScrollPane1);
       	
       	JScrollPane tmpScrollPane2= new JScrollPane(this.tableauLocation);
       	lesOnglets.add("Locations", tmpScrollPane2);
       	
       	JScrollPane tmpScrollPane3= new JScrollPane(this.tableauVol);
       	lesOnglets.add("Vols", tmpScrollPane3);
       	
       	JScrollPane tmpScrollPane4= new JScrollPane(this.tableauParapente);
       	lesOnglets.add("Parapentes", tmpScrollPane4); 
       	
       	JScrollPane tmpScrollPane5= new JScrollPane(this.tableauControleTechnique);
       	lesOnglets.add("Contrôles technique", tmpScrollPane5);
		
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void ajoutElementPilote(String[] t){
		this.tDefaultTableModelPilote.addRow(t);
		this.setMessage("Le pilote "+t[2]+" "+t[1].toUpperCase()+" a été ajouté à la base de données", Color.BLUE);
		this.lesOnglets.setSelectedIndex(0);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void ajoutElementPilote(Vector<Object> t){
		tDefaultTableModelPilote.addRow(t);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void ajoutElementVol(String[] t){
		this.tDefaultTableModelVol.addRow(t);
		this.setMessage("Le vol "+t[5]+" (parcours "+t[7].toUpperCase()+") a été ajouté à la location "+t[0]+" du pilote "+t[2]+" "+t[1].toUpperCase(), Color.BLUE);
		this.lesOnglets.setSelectedIndex(2);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void ajoutElementVol(Vector<Object> t){
		tDefaultTableModelVol.addRow(t);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void ajoutElementParapente(String[] t){
		this.tDefaultTableModelParapente.addRow(t);
		this.setMessage("Le parapente "+t[0]+" a été ajouté à la base de données", Color.blue);
		this.lesOnglets.setSelectedIndex(3);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void ajoutElementParapente(Vector<Object> t){
		tDefaultTableModelParapente.addRow(t);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void ajoutElementLocation(String[] t){
		this.tDefaultTableModelLocation.addRow(t);
		this.setMessage("La location "+t[0]+" a été ajouté au pilote "+t[2]+" "+t[1].toUpperCase(), Color.blue);
		this.lesOnglets.setSelectedIndex(1);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void ajoutElementLocation(Vector<Object> t){
		tDefaultTableModelLocation.addRow(t);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void ajoutElementControleTechnique(String[] t){
		this.tDefaultTableModelControleTechnique.addRow(t);
		this.setMessage("Le contrôle technique "+t[0]+" d'un coût total de "+t[3]+" euros a été ajouté au parapente "+t[1], Color.blue);
		this.lesOnglets.setSelectedIndex(4);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void ajoutElementControleTechnique(Vector<Object> t){
		tDefaultTableModelControleTechnique.addRow(t);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void ajoutElementHistorique(String t){
		try{			 
		   doc.insertString(26, t, null );
		}
			
		catch(Exception e) {
			System.out.println(e);
		}
	}	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void modificationElementPilote(String[] t, int i){
		this.tDefaultTableModelPilote.setValueAt(t[0], i, 0);
		this.tDefaultTableModelPilote.setValueAt(t[1], i, 1);
		this.tDefaultTableModelPilote.setValueAt(t[2], i, 2);
		this.tDefaultTableModelPilote.setValueAt(t[3], i, 3);
		this.tDefaultTableModelPilote.setValueAt(t[4], i, 4);
		this.setMessage("Les informations du pilote ont été modifiées dans la base de données", Color.BLUE);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void modificationElementParapente(String[] t, int i){
		this.tDefaultTableModelParapente.setValueAt(t[0], i, 0);
		this.tDefaultTableModelParapente.setValueAt(t[1], i, 1);
		this.tDefaultTableModelParapente.setValueAt(t[2], i, 2);
		this.tDefaultTableModelParapente.setValueAt(t[3], i, 3);
		this.tDefaultTableModelParapente.setValueAt(t[4], i, 4);
		this.setMessage("Les informations du parapente ont été modifiées dans la base de données", Color.BLUE);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void modificationElementLocation(String[] t, int i){
		this.tDefaultTableModelLocation.setValueAt(t[0], i, 0);
		this.tDefaultTableModelLocation.setValueAt(t[1], i, 1);
		this.tDefaultTableModelLocation.setValueAt(t[2], i, 2);
		this.tDefaultTableModelLocation.setValueAt(t[3], i, 3);
		this.tDefaultTableModelLocation.setValueAt(t[4], i, 4);
		this.setMessage("Les informations de la location modifiées dans la base de données", Color.BLUE);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void modificationElementVol(String[] t, int i){
		this.tDefaultTableModelVol.setValueAt(t[0], i, 0);
		this.tDefaultTableModelVol.setValueAt(t[1], i, 1);
		this.tDefaultTableModelVol.setValueAt(t[2], i, 2);
		this.tDefaultTableModelVol.setValueAt(t[3], i, 3);
		this.tDefaultTableModelVol.setValueAt(t[4], i, 4);
		this.setMessage("Les informations du vol ont été modifiées dans la base de données", Color.BLUE);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void modificationElementControleTechnique(String[] t, int i){
		this.tDefaultTableModelControleTechnique.setValueAt(t[0], i, 0);
		this.tDefaultTableModelControleTechnique.setValueAt(t[1], i, 1);
		this.tDefaultTableModelControleTechnique.setValueAt(t[2], i, 2);
		this.tDefaultTableModelControleTechnique.setValueAt(t[3], i, 3);
		this.tDefaultTableModelControleTechnique.setValueAt(t[4], i, 4);
		this.tDefaultTableModelControleTechnique.setValueAt(t[5], i, 5);
		this.tDefaultTableModelControleTechnique.setValueAt(t[6], i, 6);
		this.tDefaultTableModelControleTechnique.setValueAt(t[7], i, 7);
		this.setMessage("Les informations du contrôle technique ont été modifiées dans la base de données", Color.BLUE);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public int modifier(ListSelectionModel selectionModel, DefaultTableModel  tDefaultTableModel){
		int i=0	;
		boolean trouve=false;
	
		if(selectionModel.isSelectionEmpty()){
			JOptionPane.showMessageDialog( null, "Aucun élément n'a été sélectionné! Veuillez en sélectionner un dans un des tableaux.","Erreur: modification impossible", JOptionPane.ERROR_MESSAGE);
		}
		else{
			while(trouve==false && i< tDefaultTableModel.getRowCount()){
				if(selectionModel.isSelectedIndex(i)){
					trouve=true;
				}
				else{
					i++;
				}
			}
			
		}
		
		if(trouve==false){
			i=-1;
		}
		
		return i;
		
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public int afficher(ListSelectionModel selectionModel, DefaultTableModel  tDefaultTableModel){
		int i=0	;
		boolean trouve=false;
		
		if(!selectionModel.isSelectionEmpty()){
			while(trouve==false && i< tDefaultTableModel.getRowCount()){
				if(selectionModel.isSelectedIndex(i)){
					trouve=true;
				}
				
				else{
					i++;
				}
			}	
		
		}
		
		if(trouve==false){
			i=-1;
		}
		return i;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
public int supprimer(ListSelectionModel selectionModel, DefaultTableModel  tDefaultTableModel){
	int i=0	;
	boolean trouve=false;
	
	if(selectionModel.isSelectionEmpty()){
		JOptionPane.showMessageDialog( null, "Aucun élément n'a été sélectionné! Veuillez en sélectionner un dans un des tableaux.","Erreur: suppression impossible", JOptionPane.ERROR_MESSAGE);
	}
	else{
		while(trouve==false && i< tDefaultTableModel.getRowCount()){
			if(selectionModel.isSelectedIndex(i)){
				trouve=true;
			}
			else{
				i++;
			}
		}
	}
	
	if(trouve==false){
		i=-1;
	}
	
	return i;

}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setMessage(String unMessage, Color c){
		Font f = new Font("Arial", Font.BOLD, 22);
		this.message.setFont(f);
		this.message.setForeground(c);
		this.message.setText(unMessage);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setBoutons(){
		if(tDefaultTableModelPilote.getRowCount()<=0 || tDefaultTableModelParapente.getRowCount()<0){
			this.buttonAjouterLocation.setEnabled(false);
		}
		
		else{
			this.buttonAjouterLocation.setEnabled(true);
		}
		
		if(tDefaultTableModelParapente.getRowCount()<=0){
			this.buttonAjouterControleTechnique.setEnabled(false);
		}
		else{
			this.buttonAjouterControleTechnique.setEnabled(true);

		}
		
		if(tDefaultTableModelLocation.getRowCount()<=0){
			this.buttonAjouterVol.setEnabled(false);
		}
		
		else{
			this.buttonAjouterVol.setEnabled(true);

		}
	
		if(tDefaultTableModelLocation.getRowCount()<=0 && tDefaultTableModelVol.getRowCount()<=0 && tDefaultTableModelParapente.getRowCount()<=0 && tDefaultTableModelPilote.getRowCount()<=0 && tDefaultTableModelControleTechnique.getRowCount()<=0){
			this.buttonSupprimer.setEnabled(false);
			this.buttonAfficher.setEnabled(false);
			this.buttonModifier.setEnabled(false);
		}
		
		else{
			this.buttonSupprimer.setEnabled(true);
			this.buttonAfficher.setEnabled(true);
			this.buttonModifier.setEnabled(true);
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void filtrer(DefaultTableModel t){
		String text = this.champRecherche.getText();
		TableRowSorter<TableModel> sorter =  new TableRowSorter<TableModel>(t);
       int i;
       int nb=0;
       	Font f = new Font("Arial", Font.BOLD, 12);
		String message=" résultat(s) pour la recherche "+text;
       	this.labelResultatRecherche.setFont(f);
       
		if(t==tDefaultTableModelPilote){
			
			//compte le nombre de résultats
			for(i=0; i<tDefaultTableModelPilote.getRowCount(); i++){
				if(this.tableauPilote.convertRowIndexToView(i)!=-1){
					nb++;
				}
			}
			this.tableauPilote.setRowSorter(sorter);
			message= nb+message;
			
			if(nb!=0){
				this.labelResultatRecherche.setForeground(Color.blue);

			}
			else{
				this.labelResultatRecherche.setForeground(Color.red);
			}
			
			if(this.champRecherche.getText().isEmpty()){
				this.labelResultatRecherche.setText("");
			}
			
			else{
				this.labelResultatRecherche.setText(message);
			}

		}
		
		else if(t==tDefaultTableModelParapente){
			for(i=0; i<tDefaultTableModelParapente.getRowCount(); i++){
				if(this.tableauParapente.convertRowIndexToView(i)!=-1){
					nb++;
				}
			}
			this.tableauParapente.setRowSorter(sorter);
			message= nb+message;
			
			if(nb!=0){
				this.labelResultatRecherche.setForeground(Color.blue);

			}
			else{
				this.labelResultatRecherche.setForeground(Color.red);
			}
			
			if(this.champRecherche.getText().isEmpty()){
				this.labelResultatRecherche.setText("");
			}
			
			else{
				this.labelResultatRecherche.setText(message);
			}
		
		}
        
		else if(t==tDefaultTableModelLocation){
			for(i=0; i<tDefaultTableModelLocation.getRowCount(); i++){
				if(this.tableauLocation.convertRowIndexToView(i)!=-1){
					nb++;
				}
			}
			this.tableauLocation.setRowSorter(sorter);
			message= nb+message;
			
			if(nb!=0){
				this.labelResultatRecherche.setForeground(Color.blue);

			}
			else{
				this.labelResultatRecherche.setForeground(Color.red);
			}
			
			if(this.champRecherche.getText().isEmpty()){
				this.labelResultatRecherche.setText("");
			}
			
			else{
				this.labelResultatRecherche.setText(message);
			}
		}
		
		else if(t==tDefaultTableModelVol){
			for(i=0; i<tDefaultTableModelVol.getRowCount(); i++){
				if(this.tableauVol.convertRowIndexToView(i)!=-1){
					nb++;
				}
			}
			this.tableauVol.setRowSorter(sorter);
			message= nb+message;
			
			if(nb!=0){
				this.labelResultatRecherche.setForeground(Color.blue);

			}
			else{
				this.labelResultatRecherche.setForeground(Color.red);
			}
			
			if(this.champRecherche.getText().isEmpty()){
				this.labelResultatRecherche.setText("");
			}
			
			else{
				this.labelResultatRecherche.setText(message);
			}
		}
		
		else if(t==tDefaultTableModelControleTechnique){
			for(i=0; i<tDefaultTableModelControleTechnique.getRowCount(); i++){
				if(this.tableauControleTechnique.convertRowIndexToView(i)!=-1){
					nb++;
				}
			}
			this.tableauControleTechnique.setRowSorter(sorter);
			message= nb+message;
			
			if(nb!=0){
				this.labelResultatRecherche.setForeground(Color.blue);

			}
			else{
				this.labelResultatRecherche.setForeground(Color.red);
			}
			
			if(this.champRecherche.getText().isEmpty()){
				this.labelResultatRecherche.setText("");
			}
			
			else{
				this.labelResultatRecherche.setText(message);
			}
		}
		
        if (text.length() == 0) {
          sorter.setRowFilter(null);
        } 
        
        else {
         
        	try {
            sorter.setRowFilter(
                RowFilter.regexFilter(text));
          } 
          
          catch (PatternSyntaxException pse) {
            System.err.println("Bad regex pattern");
          }
        }
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void retirePilote(String licence){
		
		int i=0	;
		boolean trouve=false;
		
		//TABLEAU DES PILOTE
			while(trouve==false && i< tDefaultTableModelPilote.getRowCount()){
				if((String)tDefaultTableModelPilote.getValueAt(i, 0)==licence){
					tDefaultTableModelPilote.removeRow(i);
					setMessage("Le pilote, ses locations et ses vols ont été supprimés de la base de donnée", Color.blue);
					trouve=true;
				}
				else{
					i++;
				}
			}
		
		if(trouve==false){
			setMessage("Erreur dans la suppression du pilote", Color.red);
		}
		
		i=0;
		//TABLEAU DES LOCATIONS
		lesOnglets.setSelectedIndex(1);
		while( i< tDefaultTableModelLocation.getRowCount()){
			if((String)tDefaultTableModelLocation.getValueAt(i, 3)==licence){
				tDefaultTableModelLocation.removeRow(2);
				setMessage("Le pilote, ses locations et ses vols ont été supprimés de la base de donnée", Color.blue);
			}
			else{
				i++;
			}
		}
		i=0;
		lesOnglets.setSelectedIndex(2);

		//TABLEAU DES VOLS
		while( i< tDefaultTableModelVol.getRowCount()){
			if((String)tDefaultTableModelVol.getValueAt(i, 6)==licence){
				tDefaultTableModelVol.removeRow( 2);
				setMessage("Le pilote, ses locations et ses vols ont été supprimés de la base de donnée", Color.blue);
			}
			
			else{
				i++;
			}
		}
		
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void retireParapente(String idParapente){
		int i=0;
		boolean trouve=false;
		
		//TABLEAU DES PARAPENTES
		while(trouve==false && i< tDefaultTableModelParapente.getRowCount()){
			if((String)tDefaultTableModelParapente.getValueAt(i, 0)==idParapente){
				tDefaultTableModelParapente.removeRow(i);
				setMessage("Le parapente, ses locations, ses vols et ses contrôles techniques ont été supprimés de la base de donnée", Color.blue);
				trouve=true;
			}
			else{
				i++;
			}
		}
	
	if(trouve==false){
		setMessage("Erreur dans la suppression du parapente", Color.red);
	}
	i=0;	
	lesOnglets.setSelectedIndex(4);
	//TABLEAU DES CONTROLES TECHNIQUES
	while( i< tDefaultTableModelControleTechnique.getRowCount()){
		
		if((String)tDefaultTableModelControleTechnique.getValueAt(i, 1)== idParapente){
			tDefaultTableModelControleTechnique.removeRow( i);
			setMessage("Le parapente, ses locations, ses vols et ses contrôles techniques ont été supprimés de la base de donnée", Color.blue);		}
		
		else{
			i++;
		}
	}
		i=0;
		lesOnglets.setSelectedIndex(1);
		//TABLEAU DES LOCATIONS
		while(i< tDefaultTableModelLocation.getRowCount()){
			if((String)tDefaultTableModelLocation.getValueAt(i, 4)== idParapente){
				tDefaultTableModelLocation.removeRow(i);
				setMessage("Le parapente, ses locations, ses vols et ses contrôles techniques ont été supprimés de la base de donnée", Color.blue);
			}
			else{
				i++;
			}
		}
		
		i=0;
		lesOnglets.setSelectedIndex(2);
		//TABLEAU DES VOLS
		while(i< tDefaultTableModelVol.getRowCount()){
			if((String)tDefaultTableModelVol.getValueAt(i, 7)==idParapente){
				tDefaultTableModelVol.removeRow(i);
				setMessage("Le parapente, ses locations, ses vols et ses contrôles techniques ont été supprimés de la base de données", Color.blue);
			}
			
			else{
				i++;
			}
		}
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void retireLocation(String idLocation){
		boolean trouve=false;
		int i=0;
		
		//TABLEAU DES LOCATIONS
		while(trouve==false && i< tDefaultTableModelLocation.getRowCount()){
			if((String)tDefaultTableModelLocation.getValueAt(i, 0)== idLocation){
				tDefaultTableModelLocation.removeRow(i);
				setMessage("La locations et ses vols ont été supprimés de la base de données", Color.blue);
				trouve=true;
			}
			else{
				i++;
			}
		}
	
		i=0;

		//TABLEAU DES VOLS
		while( i< tDefaultTableModelVol.getRowCount()){
			if((String)tDefaultTableModelVol.getValueAt(i, 3)==idLocation){
				tDefaultTableModelVol.removeRow(i);
				setMessage("La locations et ses vols ont été supprimés de la base de données", Color.blue);
			}
					
			else{
				i++;
			}
		}	
		
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public  void retireControleTechnique(String idControleTechnique){
		boolean trouve= false;
		int i=0;
		while(trouve==false && i< tDefaultTableModelControleTechnique.getRowCount()){
			
			if((String)tDefaultTableModelControleTechnique.getValueAt(i, 0)== idControleTechnique){
				tDefaultTableModelControleTechnique.removeRow(i);
				tableauControleTechnique.setModel(tDefaultTableModelControleTechnique);

				setMessage("Le contrôle technique a été supprimé de la base de données", Color.blue);
				trouve=true;
			}
			
			else{
				i++;
			}
		}
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void retireVol(String idVol){
		boolean trouve= false;
		int i=0;
		while(trouve==false && i< tDefaultTableModelVol.getRowCount()){
			
			if((String)tDefaultTableModelVol.getValueAt(i, 0)== idVol){
				tDefaultTableModelVol.removeRow(i);
				tableauVol.setModel(tDefaultTableModelVol);
				setMessage("Le vol a été supprimé de la base de données", Color.blue);
				trouve=true;
			}
			
			else{
				i++;
			}
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void main(String [] args){
		Interface i= new Interface();
		i.setVisible(true);	
	}
}
