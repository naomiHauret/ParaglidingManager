package gui;
import gui.AfficherControleTechnique;
import gui.AfficherLocation;
import gui.AfficherParapente;
import gui.AfficherPilote;
import gui.AfficherVol;
import gui.AjouterControleTechnique;
import gui.AjouterLocation;
import gui.AjouterParapente;
import gui.AjouterPilote;
import gui.AjouterVol;
import gui.Interface;
import gui.ModifierControleTechnique;
import gui.ModifierLocation;
import gui.ModifierParapente;
import gui.ModifierPilote;
import gui.ModifierVol;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Principale {
	//Fenêtre principale
	static Interface it;
	
	//Fenêtres secondaires
	//-ajout
	static AjouterPilote ajoutPilote;
	static AjouterParapente ajoutParapente;
	static AjouterVol ajoutVol;
	static AjouterLocation ajoutLocation;
	static AjouterControleTechnique ajoutControleTechnique;
	
	//-affichage
	static AfficheTousPilote afficheTousPilote;
	static AfficheTousParapente afficheTousParapente;
	static AfficheTousVol afficheTousVol;
	static AfficheTousLocation afficheTousLocation;
	static AfficheTousControleTechnique afficheTousControleTechnique;
	
	static AfficherPilote affichePilote;
	static AfficherParapente afficheParapente;
	static AfficherVol afficheVol;
	static AfficherLocation afficheLocation;
	static AfficherControleTechnique afficheControleTechnique;
	
	//-modification
	static ModifierPilote modifiePilote;
	static ModifierParapente modifieParapente;
	static ModifierVol modifieVol;
	static ModifierLocation modifieLocation;
	static ModifierControleTechnique modifieControleTechnique;
	
	//-base de données
	static Connection connex;
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//ouvrir la fenêtre principale
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * ouvre la fenêtre principale ---> FAIT
	 */
	public static void ouvrirFenetrePrincipale(){
		it= new Interface();
		it.setVisible(true);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//ouvrir les fenêtres d'ajout
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * ouvre la fenêtre d'ajout de pilote ---> FAIT
	 */
	public static void ouvrirAjouterPilote(){
		ajoutPilote= new AjouterPilote();
		ajoutPilote.setVisible(true);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * ouvre la fenêtre d'ajout de parapente ---> FAIT
	 */
	public static void ouvrirAjouterParapente(){
		ajoutParapente= new AjouterParapente();
		ajoutParapente.setVisible(true);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 *
	 * ouvre la fenêtre de location (initialise les champs noms, prénoms, numéro de licence du pilote + l'id de la location de la location sélectionnée et les désactive)  --> FAIT
	 * le vector parapentesDisponibles initialise la combobox de cette fenêtre avec les parapentes disponibles dans la bas
	 */
	public static void ouvrirAjouterVol(String idLocation){
		try{
			ajoutVol= new AjouterVol();
			boolean volA2=false;
			ajoutVol.setIdentifiantLocation(idLocation);
			
			
			String query= "SELECT location.idLocation, personne.nomPersonne, personne.prenomPersonne, location.licencePilote, location.idParapente, location.nbHeuresLocation, location.dateReservationLocation, location.locationPour2 "
		    		   + " FROM location INNER JOIN pilote"
		    		   + " ON location.licencePilote = pilote.licencePilote"
		    		   + " INNER JOIN personne"
		    		   + " ON pilote.idPilote= personne.idPersonne"
		    		   + " WHERE location.idLocation = ?";
			PreparedStatement preparedStatement = connex.prepareStatement(query);
			preparedStatement.setInt(1, Integer.valueOf(idLocation));
			ResultSet rs=  preparedStatement.executeQuery();
			

			while(rs.next()){
				ajoutVol.setNomPilote(rs.getString("nomPersonne"));
				ajoutVol.setPrenomPilote(rs.getString("prenomPersonne"));
				ajoutVol.setNumeroLicence(rs.getString("licencePilote"));
				ajoutVol.setPresencePassager(rs.getBoolean("locationPour2"));
				volA2=rs.getBoolean("locationPour2");
			}
			
			if(volA2){
				query= "SELECT personne.nomPersonne, personne.prenomPersonne, personne.poidsPersonne, personne.taillePersonne "
			    		   + " FROM location INNER JOIN personne"
			    		   + " ON location.idPassager = personne.idPersonne"
			    		   + " WHERE location.idLocation = ?";
				
				 preparedStatement = connex.prepareStatement(query);
					preparedStatement.setInt(1, Integer.valueOf(idLocation));
				 rs=  preparedStatement.executeQuery();
				 
				 while(rs.next()){
					 ajoutVol.setNomInvite(rs.getString("nomPersonne"));
					 ajoutVol.setPrenomInvite(rs.getString("prenomPersonne"));
					 ajoutVol.setPoidsInvite(String.valueOf(rs.getInt("poidsPersonne")));
					 ajoutVol.setTailleInvite(String.valueOf(rs.getInt("taillePersonne")));
				 }

			}
			
			ajoutVol.setVisible(true);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public static void ouvrirAjouterLocation(String licence){
		/**
		 * ouvre la fenêtre de location (initialise les champs noms, prénoms, numéro de licence du pilote sélectionné et les désactive)
		 * le vector parapentesDisponibles initialise la combobox de cette fenêtre avec les parapentes disponibles dans la bas
		 */
		try{		
			Vector<String> parapentesDisponibles = new Vector<String>();
			String query= "SELECT idParapente FROM parapente";
			PreparedStatement  preparedStatement = connex.prepareStatement(query);
			ResultSet rs=  preparedStatement.executeQuery();
			
			while(rs.next()){
				parapentesDisponibles.add(String.valueOf(rs.getInt("idParapente")));
			}
			
			query= "SELECT nomPersonne, prenomPersonne, idPilote, licencePilote FROM personne INNER JOIN pilote ON pilote.idPilote= personne.idPersonne WHERE licencePilote=?";
			preparedStatement = connex.prepareStatement(query);
			preparedStatement.setString(1, licence);
			rs=  preparedStatement.executeQuery();
			
			ajoutLocation= new AjouterLocation(parapentesDisponibles);

			while(rs.next()){
				ajoutLocation.setNomPilote(rs.getString("nomPersonne"));
				ajoutLocation.setPrenomPilote(rs.getString("prenomPersonne"));
				ajoutLocation.setNumeroLicence(licence);

			}
			
			ajoutLocation.setVisible(true);

		}
		
		catch(SQLException e){
			System.out.println(e);
		}
		
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * ouvre la fenêtre d'ajout de contrôle technique (initialise le champ de l'identifiant du parapente concerné et le désactive) ---> FAIT
	 */
	public static void ouvrirAjouterControleTechnique(String idParapente){
		try{
			ajoutControleTechnique= new AjouterControleTechnique();
			ajoutControleTechnique.setParapente(idParapente);
			ajoutControleTechnique.setVisible(true);
		}
		
		catch(Exception e){
			System.out.println(e);
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void ouvrirAfficherTousPilote(){		
		
		afficheTousPilote= new AfficheTousPilote();
		try{

			//#####################################################################################################################################
			//NOMBRE DE PILOTES
			String text = "Nombre de pilote: ";
			String query= "SELECT COUNT(*) as nbPilote "
					+"FROM pilote";
			
			PreparedStatement preparedStatement = connex.prepareStatement(query);
			ResultSet rs=  preparedStatement.executeQuery();
			ResultSetMetaData resultMeta = rs.getMetaData();

			while(rs.next()){
					text=text+String.valueOf(rs.getInt("nbPilote"))+"\n\n";
			}
			//#####################################################################################################################################
			//NOMBRE DE PILOTE DE CATEGORIE A
			text = text+"Nombre de pilote de catégorie A: ";
			query= "SELECT COUNT(*) as nbPilote "
					+"FROM pilote "
					+"WHERE pilote.categoriePilote= ?";
			
			preparedStatement = connex.prepareStatement(query);
			preparedStatement.setString(1, "A");
			rs=  preparedStatement.executeQuery();
			resultMeta = rs.getMetaData();


			while(rs.next()){
				text=text+String.valueOf(rs.getInt("nbPilote"))+"\n\n";
			}
			//#####################################################################################################################################
			//NOMBRE DE PILOTE DE CATEGORIE B
			text = text+"Nombre de pilote de catégorie B: ";
			query= "SELECT COUNT(*) as nbPilote "
					+"FROM pilote "
					+"WHERE pilote.categoriePilote= ?";
			
			preparedStatement = connex.prepareStatement(query);
			preparedStatement.setString(1, "B");
			rs=  preparedStatement.executeQuery();
			resultMeta = rs.getMetaData();


			while(rs.next()){
				text=text+String.valueOf(rs.getInt("nbPilote"))+"\n\n";
			}
			//#####################################################################################################################################
			//NOMBRE DE PILOTE DE CATEGORIE C
			text = text+"Nombre de pilote de catégorie C: ";
			query= "SELECT COUNT(*) as nbPilote "
					+"FROM pilote "
					+"WHERE pilote.categoriePilote= ?";
			
			preparedStatement = connex.prepareStatement(query);
			preparedStatement.setString(1, "C");
			rs=  preparedStatement.executeQuery();
			resultMeta = rs.getMetaData();


			while(rs.next()){
				text=text+String.valueOf(rs.getInt("nbPilote"))+"\n\n";
			}
			//#####################################################################################################################################
			//NOMBRE DE PILOTE DE CATEGORIE D
			text = text+"Nombre de pilote de catégorie D: ";
			query= "SELECT COUNT(*) as nbPilote "
					+"FROM pilote "
					+"WHERE pilote.categoriePilote= ?";
			
			preparedStatement = connex.prepareStatement(query);
			preparedStatement.setString(1, "D");
			rs=  preparedStatement.executeQuery();
			resultMeta = rs.getMetaData();


			while(rs.next()){
				text=text+String.valueOf(rs.getInt("nbPilote"))+"\n\n";

			}
			//#####################################################################################################################################
			//DEPENSE MOYENNE POUR UNE LOCATION
			text= text+"Dépense moyenne pour une location: ";
					
			query="SELECT AVG(prixTTCLocation) as prix "
				+"FROM pilote INNER JOIN location"
				+ "ON pilote.licencePilote = location.licencePilote" 
			;
			
			preparedStatement = connex.prepareStatement(query);
			rs=  preparedStatement.executeQuery();
			resultMeta = rs.getMetaData();


			while(rs.next()){
				text=text+String.valueOf(rs.getInt("prix"))+"\n\n";

			}
			//#####################################################################################################################################
			//NOMBRE DE LOCATION MOYENNE PAR PILOTE
			/*
			
			//#####################################################################################################################################
			//LISTE DES LOCATIONS DU PILOTE
			//idLocation serial NOT NULL, dateReservationLocation text NOT NULL, nbHeuresLocation integer NOT NULL, prixTTCLocation integer NOT NULL, locationPour2 boolean NOT NULL,prixHTLocation integer NOT NULL, idParapente integer NOT NULL, licencePilote text NOT NULL, idPassager integer
			text = text+"Liste des locations: \n";
			if(nbLocations >0){
					
				query= "SELECT location.idLocation, location.idParapente, location.dateReservationLocation, location.nbHeuresLocation, location.prixTTCLocation, location.locationPour2 "
						+ " FROM pilote INNER JOIN location"
						+ " ON pilote.licencePilote = location.licencePilote"
						+ " WHERE pilote.licencePilote = ?";
				
				preparedStatement = connex.prepareStatement(query);
				preparedStatement.setString(1, licence);
				rs=  preparedStatement.executeQuery();
				resultMeta = rs.getMetaData();
				
				
				while(rs.next()){
					String nombrePersonnes;
					if(rs.getBoolean("locationPour2")){
						nombrePersonnes=" pour 2 personnes:";
					}
					else{
						nombrePersonnes=" pour une personne:";
					}
					text= text+"Location "+String.valueOf(rs.getInt("idLocation"))+nombrePersonnes+", le "+rs.getString("dateReservationLocation")+", le parapente "+String.valueOf(rs.getInt("idParapente"))+" a été loué  pour "+String.valueOf(rs.getInt("nbHeuresLocation"))+" heure(s) pour un coût total de "+String.valueOf(rs.getInt("prixTTCLocation"))+" euros\n";
				}
			}
			
			else{
				text=text+"Pas de location \n\n";
			}
			//#####################################################################################################################################
			//COUT DE LA LOCATION LA PLUS ELEVE
			text = text+"Location avec le prix le plus élevé: \n";
			if(nbLocations >0){
					
				query= "SELECT MAX(prixTTCLocation) AS prix, location.idLocation, location.idParapente, location.dateReservationLocation, location.nbHeuresLocation, location.prixTTCLocation, location.locationPour2 "
						+ " FROM pilote INNER JOIN location"
						+ " ON pilote.licencePilote = location.licencePilote"
						+ " WHERE pilote.licencePilote = ?"
						+ " GROUP BY location.idLocation, location.idParapente, location.dateReservationLocation, location.nbHeuresLocation, location.prixTTCLocation, location.locationPour2 "
						+ " ORDER BY prix DESC";

				preparedStatement = connex.prepareStatement(query);
				preparedStatement.setString(1, licence);
				rs=  preparedStatement.executeQuery();
				resultMeta = rs.getMetaData();
				
				
				while(rs.next()){
					String nombrePersonnes;
					if(rs.getBoolean("locationPour2")){
						nombrePersonnes=" pour 2 personnes:";
					}
					else{
						nombrePersonnes=" pour une personne:";
					}
					text= text+"Location "+String.valueOf(rs.getInt("idLocation"))+nombrePersonnes+", le "+rs.getString("dateReservationLocation")+", le parapente "+String.valueOf(rs.getInt("idParapente"))+" a été loué  pour "+String.valueOf(rs.getInt("nbHeuresLocation"))+" heure(s) pour un coût total de "+String.valueOf(rs.getInt("prixTTCLocation"))+" euros\n\n";
				}
			}
			
			else{
				text=text+"Pas de location \n\n";
			}
			
			//#####################################################################################################################################
			//SOMME DES LOCATIONS
			text = text+"Total des locations en euros pour ce pilote: ";
			if(nbLocations >0){
					
				query= "SELECT SUM(prixTTCLocation) AS prix "
						+ " FROM pilote INNER JOIN location"
						+ " ON pilote.licencePilote = location.licencePilote"
						+ " WHERE pilote.licencePilote = ?";
				
				preparedStatement = connex.prepareStatement(query);
				preparedStatement.setString(1, licence);
				rs=  preparedStatement.executeQuery();
				resultMeta = rs.getMetaData();
				
				
				while(rs.next()){
					text= text+String.valueOf(rs.getInt("prix"))+" euros\n\n";
				}
			}
			
			else{
				text=text+"Pas de location \n\n";
			}
			//#####################################################################################################################################
			//MOYENNE DES LOCATIONS
			text = text+"Moyenne des locations en euros pour ce pilote: ";
			if(nbLocations >0){
				query= "SELECT AVG(prixTTCLocation) AS prix "
						+ " FROM pilote INNER JOIN location"
						+ " ON pilote.licencePilote = location.licencePilote"
						+ " WHERE pilote.licencePilote = ?";
				
				preparedStatement = connex.prepareStatement(query);
				preparedStatement.setString(1, licence);
				rs=  preparedStatement.executeQuery();
				resultMeta = rs.getMetaData();
				
				
				while(rs.next()){
					text= text+String.valueOf(rs.getInt("prix"))+" euros\n\n";
				}
			}
			
			else{
				text=text+"Pas de location \n\n";
			}
			
			//#####################################################################################################################################
			//NOMBRE DE VOLS DU PILOTE
			int nbVols=0;
			text = text+"Nombre de vols: ";
		    
			query= "SELECT COUNT(*) AS nbvol"
					+ " FROM pilote INNER JOIN location"
					+ " ON pilote.licencePilote = location.licencePilote"
					+" INNER JOIN vol"
					+" ON vol.idLocation = location.idLocation "
					+ " WHERE pilote.licencePilote = ?";
			
			preparedStatement = connex.prepareStatement(query);
			preparedStatement.setString(1, licence);
			rs=  preparedStatement.executeQuery();
			resultMeta = rs.getMetaData();

			while(rs.next()){
				text= text+String.valueOf(rs.getInt("nbVol"))+"\n\n";
				nbVols=rs.getInt("nbVol");
			}
			//#####################################################################################################################################
			//LISTES DE VOLS DU PILOTE
			text = text+"Liste des vols: \n";
			if(nbVols >0){
				
				query= "SELECT vol.idVol, vol.idLocation, vol.nomParcoursVol, vol.dateVol"
						+ " FROM vol INNER JOIN location"
						+ " ON vol.idLocation = location.idLocation"
						+ " INNER JOIN pilote"
						+ " ON location.licencePilote = pilote.licencePilote"
						+ " WHERE pilote.licencePilote = ?";
				
				preparedStatement = connex.prepareStatement(query);
				preparedStatement.setString(1, licence);
				rs=  preparedStatement.executeQuery();
				resultMeta = rs.getMetaData();
				
				
				while(rs.next()){
					text= text+"Vol "+String.valueOf(rs.getInt("idVol"))+", location "+String.valueOf(rs.getInt("idLocation"))+": vol effectué le "+rs.getString("dateVol")+" sur le parcours "+rs.getString("nomParcoursVol")+"\n";
				}
			}
			
			else{
				text=text+"Pas de vol \n\n";
			}
			//#####################################################################################################################################
			//LISTE DES PARAPENTES
			text= text+"Liste des parapentes pilotés:\n";
			if(nbLocations<=0){
				text=text+"Pas de parapentes déjà pilotés\n";
			}
			
			else{
				query= "SELECT location.idParapente "
						+ " FROM pilote INNER JOIN location"
						+ " ON pilote.licencePilote = location.licencePilote"
						+ " WHERE pilote.licencePilote = ?"
						+ " GROUP BY idParapente";
				

				preparedStatement = connex.prepareStatement(query);
				preparedStatement.setString(1, licence);
				rs=  preparedStatement.executeQuery();
				resultMeta = rs.getMetaData();

				while(rs.next()){
					text=text+String.valueOf(rs.getInt("idParapente"))+" ;";
				}
			}*/
			text=text+"\n\n";
			
			AfficheTousPilote.ajoutElement(text);
		    afficheTousPilote.setVisible(true);

		}
		
		catch(Exception e){
			System.out.println(e);
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void ouvrirAfficherTousParapente(){
		
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void ouvrirAfficherTousLocation(){
		
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void ouvrirAfficherTousVol(){
		
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void ouvrirAfficherTousControleTechnique(){
		
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//ouvrir les fenêtres d'affichage
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void ouvrirAfficherPilote(String licence){
		affichePilote= new AfficherPilote();
		
		try{
			//#####################################################################################################################################
			//INFOS DU PILOTE
			String text = "Information du pilote ";
			String query= "SELECT personne.nomPersonne, personne.prenomPersonne, personne.poidsPersonne, personne.taillePersonne, pilote.licencePilote, pilote.dateNaissancePilote, pilote.categoriePilote, pilote.adressePilote, pilote.telephonePilote "
		    		   + " FROM pilote INNER JOIN personne"
		    		   + " ON pilote.idPilote = personne.idPersonne"
		    		   + " WHERE pilote.licencePilote = ?";
			
			PreparedStatement preparedStatement = connex.prepareStatement(query);
			preparedStatement.setString(1, licence);
			ResultSet rs=  preparedStatement.executeQuery();
			ResultSetMetaData resultMeta = rs.getMetaData();


			while(rs.next()){
				text= text+rs.getString("prenomPersonne")+" "+rs.getString("nomPersonne").toUpperCase()+"\n"
						+"Numéro de licence: "+licence+"\n"
						+"Date de naissance: "+rs.getString("dateNaissancePilote")+"\n"
						+"Poids: "+String.valueOf(rs.getInt("poidsPersonne"))+" kg\n"
						+"Taille: "+String.valueOf(rs.getInt("taillePersonne"))+" cm\n"
						+"Catégorie: "+rs.getString("categoriePilote")+"\n"
						+"Adresse: "+rs.getString("adressePilote")+"\n"
						+"Numéro de téléphone: "+rs.getString("telephonePilote")+"\n\n"
						;
			}
			//#####################################################################################################################################
			//NOMBRE DE LOCATIONS DU PILOTE
			int nbLocations=0;
			
			text = text+"Nombre de locations: ";
		    
			query= "SELECT COUNT(*) AS nbLocation"
					+ " FROM pilote INNER JOIN location"
					+ " ON pilote.licencePilote = location.licencePilote"
					+ " WHERE pilote.licencePilote = ?";
			
			preparedStatement = connex.prepareStatement(query);
			preparedStatement.setString(1, licence);
			rs=  preparedStatement.executeQuery();
			resultMeta = rs.getMetaData();
			
			while(rs.next()){
				text= text+String.valueOf(rs.getInt("nbLocation"))+"\n\n";
				nbLocations=rs.getInt("nbLocation");
			}
			//#####################################################################################################################################
			//LISTE DES LOCATIONS DU PILOTE
			//idLocation serial NOT NULL, dateReservationLocation text NOT NULL, nbHeuresLocation integer NOT NULL, prixTTCLocation integer NOT NULL, locationPour2 boolean NOT NULL,prixHTLocation integer NOT NULL, idParapente integer NOT NULL, licencePilote text NOT NULL, idPassager integer
			text = text+"Liste des locations: \n";
			if(nbLocations >0){
					
				query= "SELECT location.idLocation, location.idParapente, location.dateReservationLocation, location.nbHeuresLocation, location.prixTTCLocation, location.locationPour2 "
						+ " FROM pilote INNER JOIN location"
						+ " ON pilote.licencePilote = location.licencePilote"
						+ " WHERE pilote.licencePilote = ?";
				
				preparedStatement = connex.prepareStatement(query);
				preparedStatement.setString(1, licence);
				rs=  preparedStatement.executeQuery();
				resultMeta = rs.getMetaData();
				
				
				while(rs.next()){
					String nombrePersonnes;
					if(rs.getBoolean("locationPour2")){
						nombrePersonnes=" pour 2 personnes:";
					}
					else{
						nombrePersonnes=" pour une personne:";
					}
					text= text+"Location "+String.valueOf(rs.getInt("idLocation"))+nombrePersonnes+", le "+rs.getString("dateReservationLocation")+", le parapente "+String.valueOf(rs.getInt("idParapente"))+" a été loué  pour "+String.valueOf(rs.getInt("nbHeuresLocation"))+" heure(s) pour un coût total de "+String.valueOf(rs.getInt("prixTTCLocation"))+" euros\n";
				}
			}
			
			else{
				text=text+"Pas de location \n\n";
			}
			//#####################################################################################################################################
			//COUT DE LA LOCATION LA PLUS ELEVE
			text = text+"Location avec le prix le plus élevé: \n";
			if(nbLocations >0){
					
				query= "SELECT MAX(prixTTCLocation) AS prix, location.idLocation, location.idParapente, location.dateReservationLocation, location.nbHeuresLocation, location.prixTTCLocation, location.locationPour2 "
						+ " FROM pilote INNER JOIN location"
						+ " ON pilote.licencePilote = location.licencePilote"
						+ " WHERE pilote.licencePilote = ?"
						+ " GROUP BY location.idLocation, location.idParapente, location.dateReservationLocation, location.nbHeuresLocation, location.prixTTCLocation, location.locationPour2 "
						+ " ORDER BY prix DESC";

				preparedStatement = connex.prepareStatement(query);
				preparedStatement.setString(1, licence);
				rs=  preparedStatement.executeQuery();
				resultMeta = rs.getMetaData();
				
				
				while(rs.next()){
					String nombrePersonnes;
					if(rs.getBoolean("locationPour2")){
						nombrePersonnes=" pour 2 personnes:";
					}
					else{
						nombrePersonnes=" pour une personne:";
					}
					text= text+"Location "+String.valueOf(rs.getInt("idLocation"))+nombrePersonnes+", le "+rs.getString("dateReservationLocation")+", le parapente "+String.valueOf(rs.getInt("idParapente"))+" a été loué  pour "+String.valueOf(rs.getInt("nbHeuresLocation"))+" heure(s) pour un coût total de "+String.valueOf(rs.getInt("prixTTCLocation"))+" euros\n\n";
				}
			}
			
			else{
				text=text+"Pas de location \n\n";
			}
			
			//#####################################################################################################################################
			//SOMME DES LOCATIONS
			text = text+"Total des locations en euros pour ce pilote: ";
			if(nbLocations >0){
					
				query= "SELECT SUM(prixTTCLocation) AS prix "
						+ " FROM pilote INNER JOIN location"
						+ " ON pilote.licencePilote = location.licencePilote"
						+ " WHERE pilote.licencePilote = ?";
				
				preparedStatement = connex.prepareStatement(query);
				preparedStatement.setString(1, licence);
				rs=  preparedStatement.executeQuery();
				resultMeta = rs.getMetaData();
				
				
				while(rs.next()){
					text= text+String.valueOf(rs.getInt("prix"))+" euros\n\n";
				}
			}
			
			else{
				text=text+"Pas de location \n\n";
			}
			//#####################################################################################################################################
			//MOYENNE DES LOCATIONS
			text = text+"Moyenne des locations en euros pour ce pilote: ";
			if(nbLocations >0){
				query= "SELECT AVG(prixTTCLocation) AS prix "
						+ " FROM pilote INNER JOIN location"
						+ " ON pilote.licencePilote = location.licencePilote"
						+ " WHERE pilote.licencePilote = ?";
				
				preparedStatement = connex.prepareStatement(query);
				preparedStatement.setString(1, licence);
				rs=  preparedStatement.executeQuery();
				resultMeta = rs.getMetaData();
				
				
				while(rs.next()){
					text= text+String.valueOf(rs.getInt("prix"))+" euros\n\n";
				}
			}
			
			else{
				text=text+"Pas de location \n\n";
			}
			
			//#####################################################################################################################################
			//NOMBRE DE VOLS DU PILOTE
			int nbVols=0;
			text = text+"Nombre de vols: ";
		    
			query= "SELECT COUNT(*) AS nbvol"
					+ " FROM pilote INNER JOIN location"
					+ " ON pilote.licencePilote = location.licencePilote"
					+" INNER JOIN vol"
					+" ON vol.idLocation = location.idLocation "
					+ " WHERE pilote.licencePilote = ?";
			
			preparedStatement = connex.prepareStatement(query);
			preparedStatement.setString(1, licence);
			rs=  preparedStatement.executeQuery();
			resultMeta = rs.getMetaData();

			while(rs.next()){
				text= text+String.valueOf(rs.getInt("nbVol"))+"\n\n";
				nbVols=rs.getInt("nbVol");
			}
			//#####################################################################################################################################
			//LISTES DE VOLS DU PILOTE
			text = text+"Liste des vols: \n";
			if(nbVols >0){
				
				query= "SELECT vol.idVol, vol.idLocation, vol.nomParcoursVol, vol.dateVol"
						+ " FROM vol INNER JOIN location"
						+ " ON vol.idLocation = location.idLocation"
						+ " INNER JOIN pilote"
						+ " ON location.licencePilote = pilote.licencePilote"
						+ " WHERE pilote.licencePilote = ?";
				
				preparedStatement = connex.prepareStatement(query);
				preparedStatement.setString(1, licence);
				rs=  preparedStatement.executeQuery();
				resultMeta = rs.getMetaData();
				
				
				while(rs.next()){
					text= text+"Vol "+String.valueOf(rs.getInt("idVol"))+", location "+String.valueOf(rs.getInt("idLocation"))+": vol effectué le "+rs.getString("dateVol")+" sur le parcours "+rs.getString("nomParcoursVol")+"\n";
				}
			}
			
			else{
				text=text+"Pas de vol \n\n";
			}
			//#####################################################################################################################################
			//LISTE DES PARAPENTES
			text= text+"Liste des parapentes pilotés:\n";
			if(nbLocations<=0){
				text=text+"Pas de parapentes déjà pilotés\n";
			}
			
			else{
				query= "SELECT location.idParapente "
						+ " FROM pilote INNER JOIN location"
						+ " ON pilote.licencePilote = location.licencePilote"
						+ " WHERE pilote.licencePilote = ?"
						+ " GROUP BY idParapente";
				

				preparedStatement = connex.prepareStatement(query);
				preparedStatement.setString(1, licence);
				rs=  preparedStatement.executeQuery();
				resultMeta = rs.getMetaData();

				while(rs.next()){
					text=text+String.valueOf(rs.getInt("idParapente"))+" ;";
				}
			}
			text=text+"\n\n";
			
			AfficherPilote.ajoutElement(text);
		    affichePilote.setVisible(true);

		}
		
		catch(Exception e){
			System.out.println(e);
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void ouvrirAfficherParapente(String idParapente){	
		afficheParapente= new AfficherParapente();
		try{
			//#####################################################################################################################################
			//INFOS DU PARAPENTE
			String text = "Informations du parapente ";
			String query= "SELECT * "
		    		   + " FROM parapente"
		    		   + " WHERE parapente.idParapente = ?";
			
			PreparedStatement preparedStatement = connex.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(idParapente));
			ResultSet rs=  preparedStatement.executeQuery();
			ResultSetMetaData resultMeta = rs.getMetaData();

			String parapenteBiplace="non\n";
			
			while(rs.next()){
				if(rs.getBoolean("estBiplace")){
					parapenteBiplace="oui\n";
				}
				text= text+idParapente+"\n"
						+"Nom du modèle: "+rs.getString("modeleParapente")+"\n"
						+"Nom de la marque: "+rs.getString("marqueParapente")+"\n"
						+"Parapente biplace: "+parapenteBiplace
						+"Poids: "+String.valueOf(rs.getInt("poidsParapente"))+" kg\n"
						+"Date du dernier contrôle technique: "+rs.getString("dateDernierControleTechniqueParapente")
						+"\n\n"
						;
				
			}
			//#####################################################################################################################################
			//NOMBRE DE LOCATIONS DU PARAPENTE
			int nbLocations=0;
			
			text = text+"Nombre de locations: ";
		    
			query= "SELECT COUNT(*) AS nbLocation"
					+ " FROM parapente INNER JOIN location"
					+ " ON parapente.idParapente = location.idParapente"
					+ " WHERE parapente.idParapente = ?";
			
			preparedStatement = connex.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(idParapente));
			rs=  preparedStatement.executeQuery();
			resultMeta = rs.getMetaData();
			
			while(rs.next()){
				text= text+String.valueOf(rs.getInt("nbLocation"))+"\n\n";
				nbLocations=rs.getInt("nbLocation");
			}
			//#####################################################################################################################################
			//LISTE DES LOCATIONS DU PARAPENTE
			
			text = text+"Liste des locations: \n";
			if(nbLocations >0){
					
				query= "SELECT location.idLocation, location.dateReservationLocation, location.nbHeuresLocation, location.prixTTCLocation, location.locationPour2, personne.nomPersonne, personne.prenomPersonne, location.licencePilote "
						+ " FROM parapente INNER JOIN location"
						+ " ON parapente.idParapente = location.idParapente"
						+ " INNER JOIN pilote"
						+ " ON location.licencePilote = pilote.licencePilote"
						+ " INNER JOIN personne "
						+ " ON pilote.idPilote = personne.idPersonne "
						+ " WHERE parapente.idParapente = ?";
				
				preparedStatement = connex.prepareStatement(query);
				preparedStatement.setInt(1, Integer.parseInt(idParapente));
				rs=  preparedStatement.executeQuery();
				resultMeta = rs.getMetaData();
				
				
				while(rs.next()){
					String nombrePersonnes;
					if(rs.getBoolean("locationPour2")){
						nombrePersonnes=" pour 2 personnes:";
					}
					else{
						nombrePersonnes=" pour une personne:";
					}
					text= text+"Location "+String.valueOf(rs.getInt("idLocation"))+nombrePersonnes+", le "+rs.getString("dateReservationLocation")+", le pilote "+rs.getString("prenomPersonne")+" "+rs.getString("nomPersonne").toUpperCase()+" (numéro de licence: "+rs.getString("licencePilote")+") a effectué une location pour "+String.valueOf(rs.getInt("nbHeuresLocation"))+" heure(s) pour un coût total de "+String.valueOf(rs.getInt("prixTTCLocation"))+" euros\n\n";
				}
			}
			
			else{
				text=text+"Pas de location \n\n";
			}
			//#####################################################################################################################################
			//LOCATION LA PLUS COÛTEUSE 
			text = text+"Location avec le prix le plus élevé: \n";
			if(nbLocations >0){
					
				query= "SELECT MAX(prixTTCLocation) AS prix, location.idLocation, location.dateReservationLocation, location.nbHeuresLocation, location.locationPour2, personne.nomPersonne, personne.prenomPersonne, location.licencePilote "
						+ " FROM parapente INNER JOIN location"
						+ " ON parapente.idParapente = location.idParapente"
						+ " INNER JOIN pilote"
						+ " ON location.licencePilote = pilote.licencePilote"
						+ " INNER JOIN personne "
						+ " ON pilote.idPilote = personne.idPersonne "
						+ " WHERE parapente.idParapente = ?"
						+ " GROUP BY location.idLocation, location.dateReservationLocation, location.nbHeuresLocation, location.locationPour2, personne.nomPersonne, personne.prenomPersonne, location.licencePilote "
						+ " ORDER BY prix DESC";
				
				preparedStatement = connex.prepareStatement(query);
				preparedStatement.setInt(1, Integer.parseInt(idParapente));
				rs=  preparedStatement.executeQuery();
				resultMeta = rs.getMetaData();
				
				
				if(rs.next()){
					String nombrePersonnes;
					if(rs.getBoolean("locationPour2")){
						nombrePersonnes=" pour 2 personnes:";
					}
					else{
						nombrePersonnes=" pour une personne:";
					}
					text= text+"Location "+String.valueOf(rs.getInt("idLocation"))+nombrePersonnes+", le "+rs.getString("dateReservationLocation")+", le pilote "+rs.getString("prenomPersonne")+" "+rs.getString("nomPersonne").toUpperCase()+" (numéro de licence: "+rs.getString("licencePilote")+") a effectué une location pour "+String.valueOf(rs.getInt("nbHeuresLocation"))+" heure(s) pour un coût total de "+String.valueOf(rs.getInt("prix"))+" euros\n\n";
				}
			}
			
			else{
				text=text+"Pas de location \n\n";
			}
			//#####################################################################################################################################
			//SOMME DES LOCATIONS
			text = text+"Total des locations en euros pour ce parapente: \n";
			if(nbLocations >0){
					
				query= "SELECT SUM(prixTTCLocation) AS prix "
						+ " FROM parapente INNER JOIN location"
						+ " ON parapente.idParapente = location.idParapente"
						+ " WHERE parapente.idParapente = ?";
				
				preparedStatement = connex.prepareStatement(query);
				preparedStatement.setInt(1, Integer.parseInt(idParapente));
				rs=  preparedStatement.executeQuery();
				resultMeta = rs.getMetaData();
				
				
				while(rs.next()){
					text= text+String.valueOf(rs.getInt("prix"))+" euros\n\n";
				}
			}
			
			else{
				text=text+"Pas de location \n\n";
			}
			
			//#####################################################################################################################################
			//NOMBRE DE VOLS DU PARAPENTE
			int nbVols=0;
			text = text+"Nombre de vols: ";
		    
			query= "SELECT COUNT(*) AS nbvol"
					+ " FROM parapente INNER JOIN location"
					+ " ON parapente.idParapente = location.idParapente"
					+" INNER JOIN vol"
					+" ON vol.idLocation = location.idLocation "
					+ " WHERE parapente.idParapente = ?";
			
			preparedStatement = connex.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(idParapente));
			rs=  preparedStatement.executeQuery();
			resultMeta = rs.getMetaData();

			while(rs.next()){
				text= text+String.valueOf(rs.getInt("nbVol"))+"\n\n";
				nbVols=rs.getInt("nbVol");
			}
			//#####################################################################################################################################
			//LISTES DE VOLS DU PARAPENTE
			text = text+"Liste des vols: \n";
			if(nbVols >0){
				
				query= "SELECT vol.idVol, vol.idLocation, vol.nomParcoursVol, vol.dateVol"
						+ " FROM vol INNER JOIN location"
						+ " ON vol.idLocation = location.idLocation"
						+ " INNER JOIN parapente"
						+ " ON location.idParapente = parapente.idParapente"
						+ " WHERE parapente.idParapente = ?";
				
				preparedStatement = connex.prepareStatement(query);
				preparedStatement.setInt(1, Integer.parseInt(idParapente));
				rs=  preparedStatement.executeQuery();
				resultMeta = rs.getMetaData();
				
				
				while(rs.next()){
					text= text+"Vol "+String.valueOf(rs.getInt("idVol"))+", location "+String.valueOf(rs.getInt("idLocation"))+": vol effectué le "+rs.getString("dateVol")+" sur le parcours "+rs.getString("nomParcoursVol")+"\n";
				}
			}
			
			else{
				text=text+"Pas de vol \n\n";
			}
			//#####################################################################################################################################
			//NOMBRE DE CONTROLES TECHNIQUES
			int nbControles=0;
			text = text+"\nNombre de contrôles techniques subis depuis l'ajout dans la base: ";
		    
			query= "SELECT COUNT(*) AS nbControles"
					+ " FROM parapente INNER JOIN controletechnique"
					+ " ON parapente.idParapente = controletechnique.idParapente"
					+ " WHERE parapente.idParapente = ?";
			
			preparedStatement = connex.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(idParapente));
			rs=  preparedStatement.executeQuery();
			resultMeta = rs.getMetaData();
			
			while(rs.next()){
				text= text+String.valueOf(rs.getInt("nbControles"))+"\n\n";
				nbControles=rs.getInt("nbControles");
			}
			//#####################################################################################################################################
			//LISTE DES CONTROLES TECHNIQUES
			text= text+"Liste des contrôles techniques subis depuis l'ajout dans la base: \n";
			if(nbControles >0){
				//idControleTechnique serial, prixControleTechnique int, dateControleTechnique text NOT NULL,piecesChangeesControleTechnique text, estFaitAvantVol boolean NOT NULL, estFaitApresVol boolean NOT NULL, idParapente intege
				String controleFait="(avant un vol)";

				query= "SELECT *"
						+ " FROM controletechnique"
						+ " WHERE controletechnique.idParapente = ?";
				
				preparedStatement = connex.prepareStatement(query);
				preparedStatement.setInt(1, Integer.parseInt(idParapente));
				rs=  preparedStatement.executeQuery();
				resultMeta = rs.getMetaData();
				
				
				while(rs.next()){
					
					if(rs.getBoolean("estFaitAvantVol")){
						controleFait="(avant un vol)";
					}
					
					else if(rs.getBoolean("estFaitApresVol")){
						controleFait="(après un vol)";
					}
					else{
						controleFait="(contrôle de routine)";
					}
					text= text+"Contrôle technique "+String.valueOf(rs.getInt("idControleTechnique"))+" effectué le "+rs.getString("dateControleTechnique")+controleFait+"; nom et nombre de pièces changées: "+rs.getString("piecesChangeesControleTechnique")+"; Coût total: "+String.valueOf(rs.getInt("prixControleTechnique"))+" euros\n" ;
				}
				
				text=text+"\n";
			}
			
			else{
				text=text+"Pas de contrôle technique\n\n";
			}
			//#####################################################################################################################################
			//LISTE DES PIECES CHANGEES
			text= text+"\nListe des pièces changées lors des contrôles techniques depuis l'ajout dans la base: \n";

			if(nbControles >0){
				//idControleTechnique serial, prixControleTechnique int, dateControleTechnique text NOT NULL,piecesChangeesControleTechnique text, estFaitAvantVol boolean NOT NULL, estFaitApresVol boolean NOT NULL, idParapente intege
				query= "SELECT idControleTechnique, dateControleTechnique, piecesChangeesControleTechnique"
						+ " FROM controletechnique"
						+ " WHERE controletechnique.idParapente = ?";
				
				preparedStatement = connex.prepareStatement(query);
				preparedStatement.setInt(1, Integer.parseInt(idParapente));
				rs=  preparedStatement.executeQuery();
				resultMeta = rs.getMetaData();
				
				
				while(rs.next()){
					text= text+"Contrôle technique "+String.valueOf(rs.getInt("idControleTechnique"))+" effectué le "+rs.getString("dateControleTechnique")+"; nom et nombre de pièces changées: "+rs.getString("piecesChangeesControleTechnique")+"\n" ;
				}
				
				text=text+"\n";
			}
			
			else{
				text=text+"Pas de contrôle technique\n\n";
			}
			//#####################################################################################################################################
			//CONTROLE TECHNIQUE LE PLUS COUTEUX
			text= text+"\nContrôle technique le plus coûteux:\n";
			if(nbControles >0){
				
			query= "SELECT idControleTechnique, dateControleTechnique, MAX(prixControleTechnique) AS prix, piecesChangeesControleTechnique"
					+ " FROM controletechnique"
					+ " WHERE controletechnique.idParapente = ?"
					+ " GROUP BY idControleTechnique, dateControleTechnique, piecesChangeesControleTechnique"
					+" ORDER BY prix DESC";
			
			preparedStatement = connex.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(idParapente));
			rs=  preparedStatement.executeQuery();
			resultMeta = rs.getMetaData();
			
			
			if(rs.next()){
				text= text+"Contrôle technique "+String.valueOf(rs.getInt("idControleTechnique"))+" effectué le "+rs.getString("dateControleTechnique")+"; nom et nombre de pièces changées: "+rs.getString("piecesChangeesControleTechnique")+"; Coût total: "+String.valueOf(rs.getInt("prix"))+" euros\n\n" ;
			}
			
			text=text+"\n";
		}
		
		else{
			text=text+"Pas de contrôle technique\n\n";
		}
			//#####################################################################################################################################
			//SOMME DES CONTRÔLES TECHNIQUES
			text= text+"Total en euros du coût des contrôles techniques pour ce parapente: ";
			if(nbControles >0){
				//idControleTechnique serial, prixControleTechnique int, dateControleTechnique text NOT NULL,piecesChangeesControleTechnique text, estFaitAvantVol boolean NOT NULL, estFaitApresVol boolean NOT NULL, idParapente intege
				query= "SELECT SUM(prixControleTechnique) as prix"
						+ " FROM controletechnique"
						+ " WHERE controletechnique.idParapente = ?";
				
				preparedStatement = connex.prepareStatement(query);
				preparedStatement.setInt(1, Integer.parseInt(idParapente));
				rs=  preparedStatement.executeQuery();
				resultMeta = rs.getMetaData();
				
				
				while(rs.next()){
					text= text+String.valueOf(rs.getInt("prix"))+" euros\n";
				}
				
				text=text+"\n";
			}
			
			else{
				text=text+"0 euro: Pas de contrôle technique\n\n";
			}
			//#####################################################################################################################################
			//MOYENNE DES COÛTS DES CONTRÔLES TECHNIQUES
			text= text+"Moyenne du coût des contrôles techniques pour ce parapente: ";
			if(nbControles >0){
				//idControleTechnique serial, prixControleTechnique int, dateControleTechnique text NOT NULL,piecesChangeesControleTechnique text, estFaitAvantVol boolean NOT NULL, estFaitApresVol boolean NOT NULL, idParapente intege
				query= "SELECT AVG(prixControleTechnique) as prix"
						+ " FROM controletechnique"
						+ " WHERE controletechnique.idParapente = ?";
				
				preparedStatement = connex.prepareStatement(query);
				preparedStatement.setInt(1, Integer.parseInt(idParapente));
				rs=  preparedStatement.executeQuery();
				resultMeta = rs.getMetaData();
				
				
				while(rs.next()){
					text= text+String.valueOf(rs.getInt("prix"))+" euros\n";
				}
				
				text=text+"\n";
			}
			
			else{
				text=text+"0 euro: Pas de contrôle technique\n\n";
			}
			
			//#####################################################################################################################################
			//LISTE DES PILOTES
			text= text+"Liste des pilotes:\n";
			if(nbLocations<=0){
				text=text+"Pas de pilotes\n";
			}
			
			else{
				query= "SELECT DISTINCT location.licencePilote, personne.nomPersonne, personne.prenomPersonne "
						+ " FROM parapente INNER JOIN location"
						+ " ON parapente.idParapente = location.idParapente"
						+ " INNER JOIN pilote "
						+ " ON pilote.licencePilote = location.licencePilote"
						+ " INNER JOIN personne "
						+ " ON personne.idPersonne = pilote.idPilote "
						+ " WHERE parapente.idParapente = ?";				

				preparedStatement = connex.prepareStatement(query);
				preparedStatement.setInt(1, Integer.parseInt(idParapente));
				rs=  preparedStatement.executeQuery();
				resultMeta = rs.getMetaData();

				while(rs.next()){
					text=text+rs.getString("prenomPersonne")+" "+rs.getString("nomPersonne").toUpperCase()+" (numéro de licence "+rs.getString("licencePilote")+"); ";
				}
			}
			text=text+"\n\n";
		
			afficheParapente.ajoutElement(text);
			afficheParapente.setVisible(true);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void ouvrirAfficherVol(String idVol){
		try{
			afficheVol= new AfficherVol();
			
			
			//#####################################################################################################################################
			//INFOS DU VOL
			String text = "Informations du vol\n ";
			String query= "SELECT vol.idVol, vol.nomParcoursVol, vol.dateVol, vol.idLocation, location.licencePilote, location.dateReservationLocation, location.nbHeuresLocation, location.prixTTCLocation, location.locationPour2, personne.nomPersonne, personne.prenomPersonne, personne.taillePersonne, personne.poidsPersonne, location.idParapente, pilote.dateNaissancePilote,  pilote.telephonePilote, pilote.adressePilote, pilote.categoriePilote  "
		    		   + " FROM vol INNER JOIN location "
		    		   + " ON vol.idLocation = location.idLocation "
		    		   + " INNER JOIN pilote"
		    		   + " ON location.licencePilote = pilote.licencePilote "
		    		   + " INNER JOIN personne "
		    		   + " ON personne.idPersonne = pilote.idPilote"
		    		   + " WHERE vol.idVol = ?";
			
			PreparedStatement preparedStatement = connex.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(idVol));
			ResultSet rs=  preparedStatement.executeQuery();
			ResultSetMetaData resultMeta = rs.getMetaData();
			
			String nombrePersonnes = null;

			while(rs.next()){
				
				if(rs.getBoolean("locationPour2")){
					nombrePersonnes=" pour 2 personnes:";
				}
				else{
					nombrePersonnes=" pour une personne:";
				}
				
				text= text+"Identifiant du vol: "+idVol+"\n"
						+"Nom du parcours: "+rs.getString("nomParcoursVol")+" \n"
						+"Date du vol: "+rs.getString("dateVol")+" \n\n"
						+"Informations de la location concernée:\n"
						+"Identifiant de la location: "+String.valueOf(rs.getInt("idLocation"))+"\n"
						+"Identifiant du parapente loué: "+rs.getString("idParapente")+"\n"
						+"Date de la location: "+rs.getString("dateReservationLocation")+"\n"
						+"Durée de la location: "+String.valueOf(rs.getInt("nbHeuresLocation"))+" heure(s)\n"
						+"Location "+nombrePersonnes+"\n"
						+"Prix TTC de la location: "+String.valueOf(rs.getInt("prixTTCLocation"))+" euros\n\n"
						+"Informations du pilote\n"
						+"Nom: "+rs.getString("nomPersonne").toUpperCase()+"\n"
						+"Prénom: "+rs.getString("prenomPersonne")+"\n"
						+ "Date de naissance: "
						+"Numéro de licence: "+rs.getString("licencePilote")+"\n"
						+"Catégorie: "+rs.getString("categoriePilote")+"\n"
						+"Taille: "+String.valueOf(rs.getInt("taillePersonne"))+" cm\n"
						+"Poids: "+String.valueOf(rs.getInt("poidsPersonne"))+" kg\n"
						+"Numéro de téléphone: "+rs.getString("telephonePilote")+"\n"
						+"Adresse: "+rs.getString("adressePilote")+"\n\n"
						;
			}
			//#####################################################################################################################################
			//INFOS DU PASSAGER
			if(nombrePersonnes ==" pour 2 personnes:"){
				query= "SELECT personne.nomPersonne, personne.prenomPersonne, personne.taillePersonne, personne.poidsPersonne "
			    		   + " FROM vol INNER JOIN location "
			    		   + " ON vol.idLocation = location.idLocation "
			    		   + " INNER JOIN personne"
			    		   + " ON location.idPassager = personne.idPersonne"
			    		   + " WHERE vol.idVol = ?";
				
				preparedStatement = connex.prepareStatement(query);
				preparedStatement.setInt(1, Integer.parseInt(idVol));
				rs=  preparedStatement.executeQuery();
				resultMeta = rs.getMetaData();
				
				while(rs.next()){
					text= text+"Informations du passager:\n"
							+"Nom: "+rs.getString("nomPersonne").toUpperCase()+"\n"
							+"Prénom: "+rs.getString("prenomPersonne")+"\n"
							+"Taille: "+String.valueOf(rs.getInt("taillePersonne"))+" cm\n"
							+"Poids: "+String.valueOf(rs.getInt("poidsPersonne"))+" kg\n"
							;	
				}
			}
			 

			AfficherVol.ajoutElement(text);

			afficheVol.setVisible(true);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void ouvrirAfficherLocation(String idLocation){
		try{
	
		afficheLocation= new AfficherLocation();

		//#####################################################################################################################################
		//INFOS DE LA LOCATION
		String text = "Informations de la location\n ";
		String query= "SELECT location.idLocation, location.licencePilote, location.dateReservationLocation, location.nbHeuresLocation, location.prixHTLocation, location.prixTTCLocation, location.locationPour2, personne.nomPersonne, personne.prenomPersonne, personne.taillePersonne, personne.poidsPersonne, location.idParapente, pilote.dateNaissancePilote,  pilote.telephonePilote, pilote.adressePilote, pilote.categoriePilote  "
	    		   + " FROM location INNER JOIN pilote "
	    		   + " ON location.licencePilote = pilote.licencePilote "
	    		   + " INNER JOIN personne "
	    		   + " ON personne.idPersonne = pilote.idPilote"
	    		   + " WHERE location.idLocation = ?";
		
		PreparedStatement preparedStatement = connex.prepareStatement(query);
		preparedStatement.setInt(1, Integer.parseInt(idLocation));
		ResultSet rs=  preparedStatement.executeQuery();
		ResultSetMetaData resultMeta = rs.getMetaData();
		
		String nombrePersonnes = null;

		while(rs.next()){
			
			if(rs.getBoolean("locationPour2")){
				nombrePersonnes=" pour 2 personnes:";
			}
			else{
				nombrePersonnes=" pour une personne:";
			}
			
			text= text+"Informations de la location:\n"
					+"Identifiant de la location: "+String.valueOf(rs.getInt("idLocation"))+"\n"
					+"Identifiant du parapente loué: "+rs.getString("idParapente")+"\n"
					+"Date de la location: "+rs.getString("dateReservationLocation")+"\n"
					+"Durée de la location: "+String.valueOf(rs.getInt("nbHeuresLocation"))+" heure(s)\n"
					+"Location "+nombrePersonnes+"\n"
					+"Prix TTC de la location: "+String.valueOf(rs.getInt("prixTTCLocation"))+" euros\n\n"
					+"Informations du pilote\n"
					+"Nom: "+rs.getString("nomPersonne").toUpperCase()+"\n"
					+"Prénom: "+rs.getString("prenomPersonne")+"\n"
					+ "Date de naissance: "
					+"Numéro de licence: "+rs.getString("licencePilote")+"\n"
					+"Catégorie: "+rs.getString("categoriePilote")+"\n"
					+"Taille: "+String.valueOf(rs.getInt("taillePersonne"))+" cm\n"
					+"Poids: "+String.valueOf(rs.getInt("poidsPersonne"))+" kg\n"
					+"Numéro de téléphone: "+rs.getString("telephonePilote")+"\n"
					+"Adresse: "+rs.getString("adressePilote")+"\n\n"
					;
		}
		//#####################################################################################################################################
		//INFOS DU PASSAGER
		
		if(nombrePersonnes ==" pour 2 personnes:"){
			query= "SELECT personne.nomPersonne, personne.prenomPersonne, personne.taillePersonne, personne.poidsPersonne "
		    		   + " FROM vol INNER JOIN location "
		    		   + " ON vol.idLocation = location.idLocation "
		    		   + " INNER JOIN personne"
		    		   + " ON location.idPassager = personne.idPersonne"
		    		   + " WHERE vol.idVol = ?";
			
			preparedStatement = connex.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(idLocation));
			rs=  preparedStatement.executeQuery();
			resultMeta = rs.getMetaData();
			
			while(rs.next()){
				text= text+"Informations du passager:\n"
						+"Nom: "+rs.getString("nomPersonne").toUpperCase()+"\n"
						+"Prénom: "+rs.getString("prenomPersonne")+"\n"
						+"Taille: "+String.valueOf(rs.getInt("taillePersonne"))+" cm\n"
						+"Poids: "+String.valueOf(rs.getInt("poidsPersonne"))+" kg\n"
						;	
			}
			//#####################################################################################################################################
			//NOMBRE DE VOLS
			int nbVols=0;
			text = text+"Nombre de vols de la location: ";
		    
			query= "SELECT COUNT(*) AS nbvol"
					+ " FROM location INNER JOIN vol"
					+ " ON location.idLocation = vol.idLocation"
					+ " WHERE location.idLocation = ?";
			
			preparedStatement = connex.prepareStatement(query);
			preparedStatement.setInt(1,  Integer.parseInt(idLocation));
			rs=  preparedStatement.executeQuery();
			resultMeta = rs.getMetaData();

			while(rs.next()){
				text= text+String.valueOf(rs.getInt("nbVol"))+"\n\n";
				nbVols=rs.getInt("nbVol");
			}
			//#####################################################################################################################################
			//LISTES DE VOLS DE LA LOCATION
			text = text+"Liste des vols de la location: \n";
			if(nbVols >0){
				
				query= "SELECT vol.idVol, vol.nomParcoursVol, vol.dateVol"
						+ " FROM vol INNER JOIN location"
						+ " ON vol.idLocation = location.idLocation"
						+ " WHERE location.idLocation = ?";
				
				preparedStatement = connex.prepareStatement(query);
				preparedStatement.setInt(1, Integer.parseInt(idLocation));
				rs=  preparedStatement.executeQuery();
				resultMeta = rs.getMetaData();
				
				
				while(rs.next()){
					text= text+"Vol "+String.valueOf(rs.getInt("idVol"))+": vol effectué le "+rs.getString("dateVol")+" sur le parcours "+rs.getString("nomParcoursVol")+"\n";
				}
			}
			
			else{
				text=text+"Pas de vol \n\n";
			}
		}
		 
		AfficherLocation.ajoutElement(text);
		afficheLocation.setVisible(true);
		
		}
		
		catch(Exception e){
			System.out.println(e);
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void ouvrirAfficherControleTechnique(String idControleTechnique){
		try{
			afficheControleTechnique= new AfficherControleTechnique();
			
			//#####################################################################################################################################
				//INFOS DU CONTRÔLE TECHNIQUE (et du parapente concerné)
		
			String controleFait="(avant un vol)";
				String parapenteBiplace="non\n";

			String text = "Informations du contrôle technique\n ";
			String query= "SELECT controletechnique.idControleTechnique, controletechnique.idParapente, controletechnique.prixControleTechnique, parapente.estBiplace, controletechnique.dateControleTechnique, controletechnique.piecesChangeesControleTechnique, controletechnique.estFaitAvantVol, controletechnique.estFaitApresVol, parapente.poidsParapente, parapente.marqueParapente, parapente.modeleParapente  "
		    		   + " FROM controletechnique INNER JOIN parapente "
		    		   + " ON parapente.idParapente= controletechnique.idParapente "
		    		   + " WHERE controletechnique.idControleTechnique = ?";
			
			PreparedStatement preparedStatement = connex.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(idControleTechnique));
			ResultSet rs=  preparedStatement.executeQuery();
			ResultSetMetaData resultMeta = rs.getMetaData();
			
	
			while(rs.next()){
				
				if(rs.getBoolean("estFaitAvantVol")){
					controleFait="(avant un vol)";
				}
				
				else if(rs.getBoolean("estFaitApresVol")){
					controleFait="(après un vol)";
				}
				else{
					controleFait="(contrôle de routine)";
				}
				
				
				if(rs.getBoolean("estBiplace")){
					parapenteBiplace="oui\n";
				}
				
				text= text+"Identifiant du contrôle technique: "+String.valueOf(rs.getInt("idControleTechnique"))+"\n"
				+"Date du contrôle technique: "+rs.getString("dateControleTechnique")+controleFait+"\n\n"
				+ "Informations du parapente contrôlé:\n "
				+ "Identifiant: "+String.valueOf(rs.getInt("idParapente"))+"\n"
				+"Nom du modèle du parapente: "+rs.getString("modeleParapente")+"\n"
				+"Nom de la marque du parapente: "+rs.getString("marqueParapente")+"\n"
				+"Parapente biplace: "+parapenteBiplace+"\n"
				+ "Résultat du contrôle technique: \n"
				+"Nom et nombre de pièces changées: "+rs.getString("piecesChangeesControleTechnique")+"\n"
				+"Coût total: "+String.valueOf(rs.getInt("prixControleTechnique"))+" euros\n" ;
				
			}
			
			text=text+"\n";
			
			
			AfficherControleTechnique.ajoutElement(text);
			afficheControleTechnique.setVisible(true);

		}
		
		catch(Exception e){
			System.out.println(e);
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//ouvrir les fenêtres de modification
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//TODO
	public static void ouvrirModifierPilote(String licence, int index){
		try{
			
			modifiePilote= new ModifierPilote();

			String query = "SELECT nomPersonne, prenomPersonne, poidsPersonne, taillePersonne, licencePilote, dateNaissancePilote,  telephonePilote, adressePilote, categoriePilote  FROM personne INNER JOIN pilote ON personne.idPersonne= pilote.idPilote WHERE licencePilote = '"+licence+"';";   
			Statement state = connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet res = state.executeQuery(query);
		     res.first();
		     
		     //On met à jour les champs
		    modifiePilote.setNomPilote(res.getString("nomPersonne"));
		    modifiePilote.setPrenomPilote(res.getString("prenomPersonne"));
		    modifiePilote.setDateNaissancePilote(res.getString("dateNaissancePilote"));
		    modifiePilote.setTelephonePilote(res.getString("telephonePilote"));
		    modifiePilote.setAdressePilote(res.getString("adressePilote"));
		    modifiePilote.setCategoriePilote(res.getString("categoriePilote"));
	     	modifiePilote.setPoidsPilote(String.valueOf(res.getInt("poidsPersonne")));
	     	modifiePilote.setTaillePilote(String.valueOf(res.getInt("taillePersonne")));
		 	modifiePilote.setLicencePilote(res.getString("licencePilote"));
		 	modifiePilote.setIndex(index);
		 	modifiePilote.setVisible(true);
	     
		}
	    
		catch(Exception e){
			JOptionPane.showMessageDialog( null, "Une erreur est survenue. Impossible d'ouvrir la fenêtre de modification.","Erreur: modification impossible", JOptionPane.ERROR_MESSAGE);
			System.out.println(e);

		}

	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//TODO
	public static void ouvrirModifierParapente( int index){
		modifieParapente= new ModifierParapente();
		/*
		modifieParapente.setIdentifiantParapente();
		modifieParapente.setModeleParapente(p.getNomModele());
		modifieParapente.setMarqueParapente(p.getNomMarque());
		modifieParapente.setPoidsParapente(String.valueOf(p.getPoids()));
		modifieParapente.setEstBiplace(p.getEstBiplace());
		modifieParapente.setDateDernierControle(p.getDateDernierControle());
		modifieParapente.setIndex(index);
		*/
		modifieParapente.setVisible(true);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//TODO
	public static void ouvrirModifierVol(String element, int index){
		modifieVol= new ModifierVol();
		String[] cells = new String[8];
		cells= element.split(" ");
		modifieVol.setTextFields(cells[0], cells[1], cells[2], cells[3], cells[4], cells[5], cells[6], cells[7]);
		modifieVol.setIndex(index);
		modifieVol.setVisible(true);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//TODO
	public static void ouvrirModifierLocation(String element, int index, Vector<String> parapentesDisponibles){
		/*
		modifieLocation= new ModifierLocation(parapentesDisponibles);
		Location l= lesLocations.get(index);
		Passager p= l.getPassager();
		String[] cells = new String[9];
		cells= element.split(" ");
		modifieLocation.setTextFields(cells[0], cells[1], cells[2], cells[3], cells[4], cells[5], cells[6], cells[7], cells[8]);
		//String identifiantLocation, String nomPilote, String prenomPilote, String numeroLicence, String identifiantParapente, String dureeLocation, String dateReservation, String presencePassager, String prixTTC
		modifieLocation.setPrixHT(String.valueOf(l.getPrixHT()));

		modifieLocation.setPresencePassager(l.getVolA2());
		if(l.getVolA2()){
			modifieLocation.setNomInvite(p.getNom());
			modifieLocation.setPrenomInvite(p.getPrenom());
			modifieLocation.setPoidsInvite(String.valueOf(p.getPoids()));
			modifieLocation.setTailleInvite(String.valueOf(p.getTaille()));	
		}
		else{
			modifieLocation.desactiveChampsInvite();
			modifieLocation.setNomInvite(null);
			modifieLocation.setPrenomInvite(null);
			modifieLocation.setPoidsInvite(null);
			modifieLocation.setTailleInvite(null);	
		}
		modifieLocation.setIndex(index);*/
		modifieLocation.setVisible(true);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//TODO
	public static void ouvrirModifierControleTechnique(String element, int index){
		modifieControleTechnique= new ModifierControleTechnique();
		/*
		String[] cells = new String[4];
		cells= element.split(" ");
		modifieControleTechnique.setTextFields(cells[0], cells[1], cells[2], cells[3]);
		modifieControleTechnique.setIndex(index);
		*/
		modifieControleTechnique.setVisible(true);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	* ajoute un pilote dans l'interface
	*/
		public static void demandeAjoutPilote(String numLicence, String nom, String prenom, String dateNaissance, String categorie){
		String[] donnees={numLicence, nom, prenom, dateNaissance, categorie };
		it.ajoutElementPilote(donnees);	
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	* ajoute un parapente dans l'interface
	*/
	public static void demandeAjoutParapente( String id, String modele, String marque, String biplace, String dateDernierControle){
		String[] donnees={id, modele, marque, biplace, dateDernierControle};
		it.ajoutElementParapente(donnees);	
	}	 
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	* ajoute un vol dans l'interface
	*/
	public static void demandeAjoutVol(String identifiantLocation, String nomPilote, String prenomPilote, String licencePilote, String identifiantParapente, String identifiantVol, String dateVol, String nomParcours){
		String[] donnees={identifiantLocation, nomPilote, prenomPilote, licencePilote, identifiantParapente, identifiantVol, dateVol, nomParcours};
		it.ajoutElementVol(donnees);	
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	* ajoute une location dans l'interface
	*/
	public static void demandeAjoutLocation(String identifiantLocation, String nomPilote, String prenomPilote, String licencePilote, String identifiantParapente, String dureeLocation, String dateReservation, String presencePassager, String prixTTC){
		String[] donnees={identifiantLocation, nomPilote, prenomPilote, licencePilote, identifiantParapente,  dureeLocation, dateReservation, presencePassager, prixTTC};
		it.ajoutElementLocation(donnees);	
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	* ajoute un contrôle technique dans l'interface
	*/
	public static void demandeAjoutControleTechnique(String identifiantControleTechnique, String identifiantParapente, String date, String cout){
		String[] donnees={identifiantControleTechnique,identifiantParapente, date, cout};
		it.ajoutElementControleTechnique(donnees);	
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	* modifie un pilote dans l'interface
	*/
	public static void demandeModificationPilote(String numLicence, String nom, String prenom, String dateNaissance, String categorie, int index){
		String[] donnees={numLicence, nom, prenom, dateNaissance, categorie };
		it.modificationElementPilote(donnees, index);	
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	* modifie un parapente dans l'interface
	*/
	public static void demandeModificationParapente(String identifiant, String modele, String marque, String biplace, String dateDernierControle, int index){
		String[] donnees={identifiant, modele, marque, biplace, dateDernierControle};
		it.modificationElementParapente(donnees, index);	
	}	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	* modifie un vol dans l'interface
	*/
	public static void demandeModificationVol(String identifiantLocation, String nomPilote, String prenomPilote, String licencePilote, String identifiantParapente, String identifiantVol, String dateVol, String nomParcours, int index){
		String[] donnees={identifiantLocation, nomPilote, prenomPilote, licencePilote, identifiantParapente, identifiantVol, dateVol, nomParcours};
		it.modificationElementVol(donnees, index);	
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	* modifie une location dans l'interface
	*/
	public static void demandeModificationLocation(String identifiantLocation, String nomPilote, String prenomPilote, String licencePilote, String identifiantParapente, String dureeLocation, String dateReservation, String b, String prixTTC, int index){
		String[] donnees={identifiantLocation, nomPilote, prenomPilote, licencePilote, identifiantParapente,  dureeLocation, dateReservation, b, prixTTC};
		it.modificationElementLocation(donnees, index);	
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * modifie un contrôle technique dans l'interface
	 */
	public static void demandeModificationControleTechnique(String identifiantControle, String identifiantParapente, String date, String cout, int index){
		String[] donnees={identifiantControle, identifiantParapente, date, cout};
		it.modificationElementControleTechnique(donnees, index);	
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 * ajoute un pilote à la base et à l'interface
	 */

	public static void ajouterPilote(String nom, String prenom, String dateNaissance,String licence, String categorie, String poids, String taille, String adresse, String numeroTelephone){
		int idPilote = 0;
		try{
			
			String insertTableSQL = "INSERT INTO personne"
					+ "(nomPersonne, prenomPersonne, poidsPersonne, taillePersonne) VALUES"
					+ "(?,?,?,?)";
			
			PreparedStatement preparedStatement = connex.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, nom);
			preparedStatement.setString(2, prenom);
			preparedStatement.setInt(3, Integer.parseInt(poids));
			preparedStatement.setInt(4, Integer.parseInt(taille));
			 preparedStatement.executeUpdate();
			
			 ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
		     if (generatedKeys.next()) {
		    	 idPilote= generatedKeys.getInt("idPersonne");
		          System.out.println(idPilote);
		     }   
		      
			 
			String requete= "SELECT * FROM personne";
			preparedStatement= connex.prepareStatement(requete);
			ResultSet res= preparedStatement.executeQuery();
			
		      
			insertTableSQL = "INSERT INTO pilote"
					+ "(idPilote, licencePilote, dateNaissancePilote,  telephonePilote, adressePilote, categoriePilote) VALUES"
					+ "(?,?,?,?,?,?)";
		      
			preparedStatement = connex.prepareStatement(insertTableSQL);
			preparedStatement.setInt(1, idPilote );
			preparedStatement.setString(2, licence);
			preparedStatement.setString(3, dateNaissance);
			preparedStatement.setString(4, numeroTelephone);
			preparedStatement.setString(5, adresse);
			preparedStatement.setString(6, categorie);
			preparedStatement.executeUpdate();
			 
			demandeAjoutPilote(licence, nom, prenom, dateNaissance, categorie);
			SimpleDateFormat formater = null;
			formater = new SimpleDateFormat("'Le' EEEE dd MMMM yyyy 'à' HH:mm:ss", Locale.FRENCH); 
			String dateToStr = formater.format(new Date());
			String operation=": AJOUT DE PILOTE\n"+dateToStr+"\n"
					+"Ajout du pilote "+prenom+" "+nom.toUpperCase()+", né(e) le "+dateNaissance+"\n"
					+"Numéro de licence: "+licence+"\n"
					+"Poids: "+poids+" kg\n"
					+"Taille: "+taille+" cm\n"
					+"Catégorie: "+categorie+"\n"
					+"Adresse: "+adresse+"\n"
					+"Numéro de téléphone: "+numeroTelephone+"\n\n";
			
			ajouteEntreeHistorique(operation);
		}
		
		catch(SQLException e){
			if(e.getSQLState().startsWith("23")){
				JOptionPane.showMessageDialog( null, "Erreur: il existe déjà un pilote possédant le numéro de licence "+licence+". L'ajout du pilote n'a pas eu lieu.","Erreur: ajout impossible", JOptionPane.ERROR_MESSAGE);

			}
			else{
				JOptionPane.showMessageDialog( null, "Une erreur est survenue. L'ajout du pilote n'a pas eu lieu.","Erreur: ajout impossible", JOptionPane.ERROR_MESSAGE);

			}
		}
		
		
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO
 * modifie un pilote dans la base et dans l'interface
 */
	public static void modifierPilote(int index, String nom, String prenom, String dateNaissance,String licence, String categorie, String poids, String taille, String adresse, String numeroTelephone){
	      
		Statement state;
	      try {
			state = connex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			 
			//On va chercher une ligne dans la base de donn�es

			String query ="SELECT nomPersonne, prenomPersonne, poidsPersonne, taillePersonne, licencePilote, dateNaissancePilote,  telephonePilote, adressePilote, categoriePilote  FROM personne INNER JOIN pilote ON personne.idPersonne= pilote.idPilote"
					+ " WHERE licencePilote = \""+licence
					+"\";";   
			ResultSet res = state.executeQuery(query);
		     res.first();
		     int lePoids= Integer.parseInt(poids);
		     int laTaille= Integer.parseInt(taille);

		     //On met � jour les champs
		     res.updateString("nomPersonne", nom);
		     res.updateString("prenomPersonne", prenom);
		     res.updateString("dateNaissancePilote",dateNaissance);
		     res.updateString("telephonePilote", numeroTelephone);
		     res.updateString("adressePilote",adresse);
		     res.updateString("categoriePilote", categorie);
		     res.updateInt("poidsPersonne", lePoids);
		     res.updateInt("taillePersonne", laTaille);
		 
		    //On valide
		    res.updateRow();
		    demandeModificationPilote(licence,  nom, prenom,dateNaissance, categorie, index);
		}
		
		catch (SQLException e) {
			if(e.getSQLState().startsWith("23")){
				JOptionPane.showMessageDialog( null, "Erreur: il existe d�j� un pilote poss�dant le num�ro de licence "+licence+". La modification n'a pas eu lieu.","Erreur: modification impossible", JOptionPane.ERROR_MESSAGE);

			}
			else{
				JOptionPane.showMessageDialog( null, "Une erreur est survenue. La modification n'a pas eu lieu.","Erreur: modification impossible", JOptionPane.ERROR_MESSAGE);
				System.out.println(e);
			}
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO
 * supprime un pilote de la base et de l'interface
 */
	public static void supprimerPilote(int index){
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 *  ajoute un parapente à la base et à l'interface
	 *
	 */
	public static void ajouterParapente(String dateDernierControle,  String poids, String nomModele, String nomMarque, boolean estBiplace){
		
		try{
			int id=0;
			String insertTableSQL = "INSERT INTO parapente"
					+ "(marqueParapente, modeleParapente, poidsParapente, estBiplace, dateDernierControleTechniqueParapente) VALUES"
					+ "(?,?,?,?,?)";
			
			PreparedStatement preparedStatement = connex.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, nomMarque);
			preparedStatement.setString(2, nomModele);
			preparedStatement.setInt(3, Integer.parseInt(poids));
			preparedStatement.setBoolean(4, estBiplace);
			preparedStatement.setString(5, dateDernierControle);
			 preparedStatement.executeUpdate();
			 
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				id= generatedKeys.getInt("idParapente");
				System.out.println(id);
			}   
		
			String biplace;
			
			if(estBiplace){
				biplace="oui";
			}
			else{
				biplace="non";
			}
			
			Principale.demandeAjoutParapente( String.valueOf(id), nomModele, nomMarque, biplace, dateDernierControle);
			
			SimpleDateFormat formater = null;
			formater = new SimpleDateFormat("'Le' EEEE dd MMMM yyyy 'à' HH:mm:ss", Locale.FRENCH); 
			String dateToStr = formater.format(new Date());
			String operation=": AJOUT DE PARAPENTE\n"+dateToStr+"\n"
					+"Ajout du parapente "+String.valueOf(id)+"\n"
					+"Modèle: "+nomModele+"\n"
					+"Marque: "+nomMarque+"\n"
					+"Modèle biplace: "+biplace+"\n"
					+"Poids: "+poids+" kg\n"
					+"Date du dernier contrôle technique: "+dateDernierControle+"\n\n";
			
			ajouteEntreeHistorique(operation);
		}
		
		catch(Exception e){
			System.out.println(e);
		}
		

	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void modifierParapente(int index, String dateDernierControle, String identifiantParapente, String poids, String nomModele, String nomMarque, boolean estBiplace){
		/**
		 * TODO
		 * modifie un parapente de la base de donnée et de l'interface
		 */
		/*
		Parapente p= new Parapente(dateDernierControle, identifiantParapente, Integer.parseInt(poids), nomModele, nomMarque, estBiplace);
		lesParapentes.setElementAt(p, index);
		*/
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO
 * supprime un parapente de la base et de l'interface
 *
 */
	public static void supprimerParapente(int index){
		//lesParapentes.removeElementAt(index);
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 * ajoute une location avec un passager à la base et à l'interface
	 */
	public static void ajouterLocation(String numeroLicence, String  nomPilote, String prenomPilote, String idParapente, String dateReservation, String nbHeures,boolean presenceInvite, String nomPassager, String prenomPassager, String poidsPassager, String taillePassager, String prixHT, String prixTTC){
		try{
			
			int idPassager= 0;
			int idLocation= 0;
			
			String passagerPresent;
			
			if(presenceInvite){
				passagerPresent= "oui";
			}
			
			else{
				passagerPresent= "non";	
			}
			
			String insertTableSQL = "INSERT INTO personne"
					+ "(nomPersonne, prenomPersonne, poidsPersonne, taillePersonne) VALUES"
					+ "(?,?,?,?)";
			
			PreparedStatement preparedStatement = connex.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, nomPassager);
			preparedStatement.setString(2, prenomPassager);
			preparedStatement.setInt(3, Integer.parseInt(poidsPassager));
			preparedStatement.setInt(4,  Integer.parseInt(taillePassager));
			 preparedStatement.executeUpdate();
			
			 ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
		     if (generatedKeys.next()) {
		    	 idPassager= generatedKeys.getInt("idPersonne");
		          System.out.println(idPassager);
		     }   
		      
			insertTableSQL = "INSERT INTO passager"
					+ "(idPassager) VALUES"
					+ "(?)";
		      
			preparedStatement = connex.prepareStatement(insertTableSQL);
			preparedStatement.setInt(1, idPassager );
			preparedStatement.executeUpdate();
			
			insertTableSQL = "INSERT INTO location"
					+ "(dateReservationLocation, nbHeuresLocation, prixTTCLocation, locationPour2, prixHTLocation, idParapente, licencePilote, idPassager) VALUES"
					+ "(?,?,?,?,?,?,?,?)";
		      
			preparedStatement = connex.prepareStatement(insertTableSQL,  Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, dateReservation);
			preparedStatement.setInt(2, Integer.parseInt(nbHeures));
			preparedStatement.setInt(3, Integer.parseInt(prixTTC));
			preparedStatement.setBoolean(4, presenceInvite);
			preparedStatement.setInt(5,  Integer.parseInt(prixHT));
			preparedStatement.setInt(6,  Integer.parseInt(idParapente));
			preparedStatement.setString(7, numeroLicence);
			preparedStatement.setInt(8, idPassager );

			preparedStatement.executeUpdate();
			
			
			generatedKeys = preparedStatement.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				idLocation= generatedKeys.getInt("idLocation");
				System.out.println(idLocation);
			}   
			
			
			
			demandeAjoutLocation( String.valueOf(idLocation),  nomPilote, prenomPilote, numeroLicence,  idParapente, nbHeures,  dateReservation, passagerPresent, prixTTC);
			SimpleDateFormat formater = null;
			formater = new SimpleDateFormat("'Le' EEEE dd MMMM yyyy 'à' HH:mm:ss", Locale.FRENCH); 
			String dateToStr = formater.format(new Date());
			String operation=": AJOUT DE LOCATION AVEC PASSAGER\n"+dateToStr+"\n"
					+"Ajout de la location "+String.valueOf(idLocation)+" au pilote "+prenomPilote+" "+nomPilote.toUpperCase()+" (numéro de licence: "+numeroLicence+")\n"
					+"Date de la location: "+dateReservation+"\n"
					+"Parapente concerné: "+idParapente+"\n"
					+"Durée de la location: "+nbHeures+" heure(s)\n"
					+"Présence du passager "+prenomPassager+" "+nomPassager.toUpperCase()+" \n"
					+"Taille: "+taillePassager+" cm\n"
					+"Poids: "+poidsPassager+" kg\n"
					+"Coût hors taxe de la location: "+prixHT+" euros\n"
					+"Coût toutes taxes comprises de la location: "+prixTTC+" euros\n\n";
			
			ajouteEntreeHistorique(operation);
		}
		
		catch(Exception e){
			System.out.println(e);
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	* 
	* ajoute une location sans passager à la base et à l'interface
	*/	
	public static void ajouterLocation(String numeroLicence, String  nomPilote, String prenomPilote, String idParapente, String dateReservation, String nbHeures,boolean presenceInvite, String prixHT, String prixTTC){
	try{
			
			int idLocation= 0;
			
			String passagerPresent;
			
			if(presenceInvite){
				passagerPresent= "oui";
			}
			
			else{
				passagerPresent= "non";	
			}
			
		
			
			String insertTableSQL = "INSERT INTO location"
					+ "(dateReservationLocation, nbHeuresLocation, prixTTCLocation, locationPour2, prixHTLocation, idParapente, licencePilote) VALUES"
					+ "(?,?,?,?,?,?,?)";
		      
			PreparedStatement preparedStatement;
			
			preparedStatement = connex.prepareStatement(insertTableSQL,  Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, dateReservation);
			preparedStatement.setInt(2, Integer.parseInt(nbHeures));
			preparedStatement.setInt(3, Integer.parseInt(prixTTC));
			preparedStatement.setBoolean(4, presenceInvite);
			preparedStatement.setInt(5,  Integer.parseInt(prixHT));
			preparedStatement.setInt(6,  Integer.parseInt(idParapente));
			preparedStatement.setString(7, numeroLicence);

			preparedStatement.executeUpdate();
			
			
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				idLocation= generatedKeys.getInt("idLocation");
				System.out.println(idLocation);
			}   

			demandeAjoutLocation( String.valueOf(idLocation),  nomPilote, prenomPilote, numeroLicence,  idParapente, nbHeures,  dateReservation, passagerPresent, prixTTC);
			SimpleDateFormat formater = null;
			formater = new SimpleDateFormat("'Le' EEEE dd MMMM yyyy 'à' HH:mm:ss", Locale.FRENCH); 
			String dateToStr = formater.format(new Date());
			String operation=": AJOUT DE LOCATION SANS PASSAGER\n"+dateToStr+"\n"
					+"Ajout de la location "+String.valueOf(idLocation)+" au pilote "+prenomPilote+" "+nomPilote.toUpperCase()+" (numéro de licence: "+numeroLicence+")\n"
					+"Date de la location: "+dateReservation+"\n"
					+"Parapente concerné: "+idParapente+"\n"
					+"Durée de la location: "+nbHeures+" heure(s)\n"
					+"Pas de passager\n"
					+"Coût hors taxe de la location: "+prixHT+" euros\n"
					+"Coût toutes taxes comprises de la location: "+prixTTC+" euros\n\n";
			
			ajouteEntreeHistorique(operation);

		}
		
		catch(Exception e){
			System.out.println(e);
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void modifierLocation(int index, String numeroLicence, String identifiantParapente, String identifiantLocation,boolean deuxPersonnes, String nombreHeures, String prixHT, String prixTTC){
		/**
		 * TODO
		 * modifie la location dans la base et dans l'interface
		 */
		/*
		java.util.Iterator<Pilote> itPilote= lesPilotes.iterator();
		java.util.Iterator<Parapente> itParapente= lesParapentes.iterator();
		boolean trouve =false;
		
		Location l = new Location(identifiantLocation, deuxPersonnes, Integer.parseInt(nombreHeures), Integer.parseInt(prixHT), Integer.parseInt(prixTTC));
		
		while(itParapente.hasNext() && trouve == false){
			Parapente parapente= itParapente.next();
			/*if(parapente.getIdentifiantParapente()==identifiantParapente){
				l.setParapente(parapente);
				parapente.ajouterLocation(identifiantLocation, l);
				trouve=true;
			}
		}
		
		lesLocations.setElementAt(l, index);
		
		while(itPilote.hasNext() && trouve ==false){
			Pilote pilote= itPilote.next();
			if(pilote.getNumeroLicence()==numeroLicence){
				pilote.modifierLocation(identifiantLocation, l);
				trouve=true;
			}
		}
		
		trouve=false;
		
		while(itParapente.hasNext() && trouve == false){
			Parapente parapente= itParapente.next();
			/*if(parapente.getIdentifiantParapente()==identifiantParapente){
				parapente.modifierLocation(identifiantLocation, l);
				trouve=true;
			}
		}*/
	
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * TODO
	 * supprime la location de la base et de l'interface
	 * 
	 */
	public static void supprimerLocation(int index, String numeroLicence, String identifiantParapente, String identifiantLocation){
		/*java.util.Iterator<Pilote> itPilote= lesPilotes.iterator();
		java.util.Iterator<Parapente> itParapente= lesParapentes.iterator();
		boolean trouve=false;
		
		lesLocations.removeElementAt(index);
		
		while(itPilote.hasNext() && trouve==false){
			Pilote pilote= itPilote.next();
			if(pilote.getNumeroLicence()==numeroLicence){
				pilote.supprimerLocation(identifiantLocation);
				trouve=true;
			}
		}
		
		trouve=false;
		
		while(itParapente.hasNext() && trouve == false){
			Parapente parapente= itParapente.next();
			/*if(parapente.getIdentifiantParapente()==identifiantParapente){
				parapente.supprimerLocation(identifiantLocation);
				trouve=true;
			}
			
		}*/
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * ajoute un contrôle technique à la base et à l'interface
	 * 
	 **/
	public static void ajouterControleTechnique(String identifiantParapente, String date, String cout, String pieces, boolean avantVol, boolean apresVol){
		try{
			String estFaitAvantVol="non";
			String estFaitApresVol="non";

			int idControleTechnique = 0;
			
			//requête d'insertion
			String insertTableSQL = "INSERT INTO controletechnique"
					+ "(prixControleTechnique, dateControleTechnique,  piecesChangeesControleTechnique,  estFaitAvantVol, estFaitApresVol,  idParapente) VALUES"
					+ "(?,?,?,?,?,?)";
			
			PreparedStatement preparedStatement = connex.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setInt(1, Integer.parseInt(cout));
			preparedStatement.setString(2, date);
			preparedStatement.setString(3, pieces);
			preparedStatement.setBoolean(4, avantVol);
			preparedStatement.setBoolean(5, apresVol);
			preparedStatement.setInt(6, Integer.parseInt(identifiantParapente));

			 preparedStatement.executeUpdate();
			
			 //on récupère l'id du contrôle technique qu'on vient de créer
			 ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
		     if (generatedKeys.next()) {
		    	 idControleTechnique= generatedKeys.getInt("idControleTechnique");
		          System.out.println(idControleTechnique);
		     }   
		     
		     if(avantVol){
		    	 estFaitAvantVol="oui";
		     }
		     
		     if(apresVol){
		    	 estFaitApresVol="oui";
		     }
		     
			//ajout de la ligne
			demandeAjoutControleTechnique(String.valueOf(idControleTechnique), identifiantParapente, date, cout);
			SimpleDateFormat formater = null;
			formater = new SimpleDateFormat("'Le' EEEE dd MMMM yyyy 'à' HH:mm:ss", Locale.FRENCH); 
			String dateToStr = formater.format(new Date());
			String operation=": AJOUT DE CONTRÔLE TECHNIQUE\n"+dateToStr+"\n"
					+"Ajout du contrôle technique "+String.valueOf(idControleTechnique)+" au parapente "+identifiantParapente+", fait le "+date+"\n"
					+"Fait avant vol: "+estFaitAvantVol+"\n"
					+"Fait après vol: "+estFaitApresVol+"\n"
					+"Pièces changées: "+pieces+"\n"
					+"Coût: "+cout+" euros\n\n";
			
			ajouteEntreeHistorique(operation);
		}
		
		catch(Exception e){
			System.out.println(e); //si erreur: on l'affiche
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * TODO 
	 * Modifie le contrôle technique dans la base et dans l'interface
	 */
	
	public static void modifierControleTechnique(int index, String identifiantParapente, String identifiantControle, String lesPieces, String date, String cout, boolean avantVol, boolean apresVol){
		/*
		java.util.Iterator<Parapente> itParapente= lesParapentes.iterator();
		ControleTechnique c = new ControleTechnique(identifiantControle, lesPieces, date, Integer.parseInt(cout), avantVol, apresVol);
		boolean trouve=false;
		
		lesControlesTechniques.setElementAt(c, index);
		
		while(itParapente.hasNext() && trouve ==false){
			Parapente p= itParapente.next();
			/*if(p.getIdentifiantParapente()==identifiantParapente){
				p.modifierControleTechnique(identifiantControle, c);
				trouve=true;
			}
		}
		*/
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * TODO 
	 * Supprime le contrôle technique de la base et de l'interface
	 */
	public static void supprimerControleTechnique(int index, String identifiantParapente, String identifiantControle){
		/*
		java.util.Iterator<Parapente> itParapente= lesParapentes.iterator();
		boolean trouve=false;
		
		lesControlesTechniques.remove(index);
		
		while(itParapente.hasNext() && trouve ==false){
			Parapente p= itParapente.next();
			/*if(p.getIdentifiantParapente()==identifiantParapente){
					p.supprimerControleTechnique(identifiantControle);
					trouve=true;
			}
		}
	*/
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 *
	 * ajoute un vol dans la base et dans l'interface
	 * 
	 */
	public static void ajouterVol(String identifiantLocation,  String nomParcours, String date){
		try{
			int idVol=0;
			//idVol serial NOT NULL, idLocation integer NOT NULL, nomParcoursVol text NOT NULL, dateVol text NOT NULL, licencePilote text NOT NULL, idParapente integer NOT NULL, idPassager integer

			String insertTableSQL ="INSERT INTO vol"
					+"(idLocation, nomParcoursVol, dateVol)"
					+"VALUES(?,?,?)";
			
			PreparedStatement preparedStatement = connex.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setInt(1, Integer.parseInt(identifiantLocation));
			preparedStatement.setString(2, nomParcours);
			preparedStatement.setString(3, date);
			
			 preparedStatement.executeUpdate();
			 
			 ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
				
			if (generatedKeys.next()) {
				idVol= generatedKeys.getInt("idVol");
				System.out.println(idVol);
			}
			
			Statement stmt= connex.createStatement();
			
			//idLocation, nomPersonne, prenomPersonne, licencePilote, idParapente, idVol, dateVol, nomParcours
			ResultSet result = stmt.executeQuery(
		    		   "SELECT vol.idLocation, personne.nomPersonne, personne.prenomPersonne, location.licencePilote, location.idParapente, vol.idVol, vol.dateVol, vol.nomParcoursVol "
		    		   + " FROM vol INNER JOIN location"
		    		   + " ON vol.idLocation = location.idLocation"
		    		   + " INNER JOIN pilote"
		    		   + " ON location.licencePilote = pilote.licencePilote"
		    		   + " INNER JOIN personne"
		    		   + " ON pilote.idPilote= personne.idPersonne"); //la requête
		     
			if(result.next()){
				demandeAjoutVol( String.valueOf(idVol), date, nomParcours, identifiantLocation, result.getString("nomPersonne"), result.getString("prenomPersonne"), result.getString("licencePilote"), String.valueOf(result.getInt("idParapente"))  );
				
				SimpleDateFormat formater = null;
				formater = new SimpleDateFormat("'Le' EEEE dd MMMM yyyy 'à' HH:mm:ss", Locale.FRENCH); 
				String dateToStr = formater.format(new Date());
				String operation=": AJOUT DE VOL\n"+dateToStr+"\n"
						+"Ajout du vol "+String.valueOf(idVol)+" à la location "+identifiantLocation+" du pilote "+result.getString("prenomPersonne")+" "+result.getString("nomPersonne").toUpperCase()+" (numéro de licence: "+result.getString("licencePilote")+")\n"
						+"Parcours: "+nomParcours+"\n"
						+"Date du vol: "+date+"\n\n";
				
				ajouteEntreeHistorique(operation);
			}
				
		}
		
		catch(Exception e){
			System.out.println(e);
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * TODO
	 * modifie un vol dans la base et dans l'interface
	 * 
	 */
	public static void modifierVol(int index, String identifiantLocation, String identifiantVol, String nomParcours, String date){
	/*
		java.util.Iterator<Location> itLocation= lesLocations.iterator();
		boolean trouve= false;
		
		Vol v= new Vol(identifiantVol, date, nomParcours);
		lesVols.setElementAt(v, index);
		
		while(itLocation.hasNext() && trouve ==false){
			Location l= itLocation.next();
			if(l.getIdentifiantLocation()==identifiantLocation){
				l.modifierVol(identifiantVol, v);
				trouve=true;
			}
		}	
		*/
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * TODO 
	 * supprime un vol dans la base et dans l'interface
	 */
	
	public static void supprimerVol(int index, String identifiantLocation, String identifiantVol){
		/*java.util.Iterator<Location> itLocation= lesLocations.iterator();
		boolean trouve= false;
		
		lesVols.remove(index);
		
		while(itLocation.hasNext() && trouve ==false){
			Location l= itLocation.next();
			if(l.getIdentifiantLocation()==identifiantLocation){
			l.supprimerVol(identifiantVol);
			trouve=true;
			}
		}	
		*/
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 * connecte l'utilisateur à la base de donnée
	 * 
	 * @param host : nom du serveur
	 * @param baseName : nom de la base
	 * @param user: nom de l'utilisateur 
	 * @param psw: mot de passe
	 */
	public static void connexion(String host, String baseName, String user, String psw ){
		final String url= "jdbc:postgresql://"+host+"/"+user;
		try{
			connex=DriverManager.getConnection(url, user, psw);
			it.setMessage("Bienvenue sur votre base de données!", Color.DARK_GRAY);
		}
		catch(Exception e){
			System.out.println(e);
			it.setMessage("Erreur: impossible de se connecter à la base", Color.RED);
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * 
 * ferme la base
 * 
 */
	public static void closeBase(){
		try {
			connex.close();
			System.out.println("Base fermée");
		} 
		
		catch (SQLException e) {
			System.out.println(e); //si erreur: on l'affiche
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * 
 * check dans la table si la table @nomTable existe
 * @return true si oui, false sinon
 */
	public static boolean tableExists(String nomTable){
		
		boolean res=true;	
		
		try {
			DatabaseMetaData dbm = connex.getMetaData();
			ResultSet tables;
			tables = dbm.getTables(null, null, nomTable, null);
			
			// la table existe pas
			if (!tables.next()) {
				res=false;
			}
		}
		
		catch (SQLException e) {
			e.printStackTrace(); //si erreur: on l'affiche
		}
		
		return res;

	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 *  check dans la base si la table du nom <nom> existe et la crée si elle n'existe pas
	 **/
	public static void creeTable(){
		
		try {
			
			Statement stmt= connex.createStatement();
			//pour supprimer les tables si y'a une couille
			/*
 			stmt.executeUpdate("DROP TABLE entreeHistorique;");
			stmt.executeUpdate("DROP TABLE vol;");
			stmt.executeUpdate("DROP TABLE location;");
			stmt.executeUpdate("DROP TABLE pilote;");
			stmt.executeUpdate("DROP TABLE controletechnique;");
			stmt.executeUpdate("DROP TABLE parapente;");
			stmt.executeUpdate("DROP TABLE passager;");
			stmt.executeUpdate("DROP TABLE personne;");
			*/
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
			//personne ---> fait
			if(!tableExists("personne")){
				stmt.executeUpdate("CREATE TABLE personne(nomPersonne text NOT NULL, prenomPersonne text NOT NULL, poidsPersonne integer NOT NULL, taillePersonne integer NOT NULL, idPersonne serial NOT NULL, CONSTRAINT personne_pkey PRIMARY KEY (idPersonne)) WITH (OIDS=FALSE);");
				System.out.println("table personne créée \n");
			}
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
			//passager ---> fait (avec la location)
			if(!tableExists("passager")){
				stmt.executeUpdate("CREATE TABLE passager(idPassager serial NOT NULL, CONSTRAINT passager_pkey PRIMARY KEY (idPassager), CONSTRAINT passager_idPassager_fkey FOREIGN KEY (idPassager) REFERENCES personne (idPersonne) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE)WITH (OIDS=FALSE);");
				System.out.println("table passager créée \n");
			}
			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
			//parapente ---> fait
			if(!tableExists("parapente")){
				stmt.executeUpdate("CREATE TABLE parapente (idParapente serial NOT NULL, marqueParapente text NOT NULL, modeleParapente text NOT NULL, poidsParapente integer NOT NULL, estBiplace boolean NOT NULL, dateDernierControleTechniqueParapente text NOT NULL, CONSTRAINT parapente_pkey PRIMARY KEY (idParapente)) WITH ( OIDS=FALSE);");
				System.out.println("table parapente créée \n");
			}
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
			//controletechnique ---> fait
			if(!tableExists("controletechnique")){
				stmt.executeUpdate("CREATE TABLE controletechnique(idControleTechnique serial NOT NULL, prixControleTechnique integer NOT NULL DEFAULT 0, dateControleTechnique text NOT NULL,piecesChangeesControleTechnique text, estFaitAvantVol boolean NOT NULL, estFaitApresVol boolean NOT NULL, idParapente integer, CONSTRAINT controletechnique_pkey PRIMARY KEY (idControleTechnique), CONSTRAINT controletechnique_id_fkey FOREIGN KEY (idParapente) REFERENCES parapente(idParapente) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE) WITH ( OIDS=FALSE);");
				System.out.println("table controletechnique créée \n");
			}
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
			//pilote ---> fait
			if(!tableExists("pilote")){
				stmt.executeUpdate("CREATE TABLE pilote( idPilote integer NOT NULL, licencePilote text NOT NULL, dateNaissancePilote text NOT NULL, telephonePilote text NOT NULL, adressePilote text NOT NULL, categoriePilote character(1) NOT NULL, CONSTRAINT pilote_pkey PRIMARY KEY (licencePilote), CONSTRAINT pilote_idpilote_fkey FOREIGN KEY (idPilote) REFERENCES personne(idPersonne) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE) WITH (OIDS=FALSE);");
			}	
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
			//location ---> fait
			if(!tableExists("location")){
				stmt.executeUpdate("CREATE TABLE location(idLocation serial NOT NULL, dateReservationLocation text NOT NULL, nbHeuresLocation integer NOT NULL, prixTTCLocation integer NOT NULL, locationPour2 boolean NOT NULL,prixHTLocation integer NOT NULL, idParapente integer NOT NULL, licencePilote text NOT NULL, idPassager integer, CONSTRAINT location_pkey PRIMARY KEY (idLocation),CONSTRAINT location_idParapente_fkey FOREIGN KEY (idParapente) REFERENCES parapente (idParapente) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT location_idPassager_fkey FOREIGN KEY (idPassager) REFERENCES passager (idPassager) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT location_licencePilote_fkey FOREIGN KEY (licencePilote) REFERENCES pilote (licencePilote) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE)WITH ( OIDS=FALSE);");
				System.out.println("table location créée \n");

			}
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
			//vol ---> fait
			if(!tableExists("vol")){
				//stmt.executeUpdate("CREATE TABLE vol( idVol serial NOT NULL, idLocation integer NOT NULL, nomParcoursVol text NOT NULL, dateVol text NOT NULL, licencePilote text NOT NULL, idParapente integer NOT NULL, idPassager integer, CONSTRAINT vol_pkey PRIMARY KEY (idVol),  CONSTRAINT vol_idLocation_fkey FOREIGN KEY (idLocation) REFERENCES location (idLocation) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT vol_idParapente_fkey FOREIGN KEY (idParapente) REFERENCES parapente(idParapente) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT vol_idPassager_fkey FOREIGN KEY (idPassager) REFERENCES passager(idPassager) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT vol_licencePilote_fkey FOREIGN KEY (licencePilote) REFERENCES pilote (licencePilote) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE ) WITH ( OIDS=FALSE);");
				stmt.executeUpdate("CREATE TABLE vol( idVol serial NOT NULL, idLocation integer NOT NULL, nomParcoursVol text NOT NULL, dateVol text NOT NULL, CONSTRAINT vol_pkey PRIMARY KEY (idVol),  CONSTRAINT vol_idLocation_fkey FOREIGN KEY (idLocation) REFERENCES location (idLocation) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE) WITH ( OIDS=FALSE);");

				System.out.println("table vol créée \n");

			}
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
			if(!tableExists("entreehistorique")){
				stmt.executeUpdate("CREATE TABLE entreeHistorique( identifiant serial NOT NULL,  operation text NOT NULL, CONSTRAINT entreehistorique_pkey PRIMARY KEY (identifiant)) WITH (OIDS=FALSE);");
			}
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
			stmt.close();
		} 
		
		catch (SQLException e) {
			e.printStackTrace(); //si erreur on l'affiche
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void remplirTables(){
		try {
			Statement stmt= connex.createStatement();
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//tableau pilote
			
			//la requête
		      ResultSet result = stmt.executeQuery("SELECT nomPersonne, prenomPersonne,  licencePilote, dateNaissancePilote, categoriePilote  FROM personne INNER JOIN pilote ON personne.idPersonne= pilote.idPilote ORDER BY personne.idPersonne ASC");
		   
		      //les noms des colonnes 
		      ResultSetMetaData resultMeta = result.getMetaData();

		      while(result.next()){ //tant qu'on trouve un résultat qui match avec la requête
		    	 Vector<Object> data= new Vector<Object>(); //on crée un vector 
		         data.add(result.getString("licencePilote")); 
		         data.add(result.getString("nomPersonne")); 
		         data.add(result.getString("prenomPersonne")); 
		         data.add(result.getString("dateNaissancePilote")); 
		         data.add(result.getString("categoriePilote")); 
		         Interface.ajoutElementPilote(data); //on ajoute à la jtable pilote le vector comme row
		      }   
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		      //tableau parapente
		      //la requête
		      result = stmt.executeQuery("SELECT * FROM parapente ORDER BY parapente.idParapente ASC");
		      
		      //les noms des colonnes 
		      resultMeta = result.getMetaData();

		      while(result.next()){ //tant qu'on trouve un résultat qui match avec la requête
		    	 Vector<Object> data= new Vector<Object>();  //on crée un vector 
		         data.add(String.valueOf(result.getInt("idParapente"))); 
		        data.add(result.getString("modeleParapente")); 		        
		         data.add(result.getString("marqueParapente")); 
		         if(result.getBoolean("estBiplace")==true){
			         data.add("oui"); 
		         }
		         else{
		        	 data.add("non");
		         }
		         data.add(result.getString("dateDernierControleTechniqueParapente")); 
		         Interface.ajoutElementParapente(data); //on ajoute à la jtable parapente le vector comme row
		      }   
		      
		  	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		      //tableau controletechnique
		      //la requête
		      result = stmt.executeQuery("SELECT idControleTechnique, idParapente, dateControleTechnique, prixControleTechnique FROM controletechnique");
		    
		      //les noms des colonnes 
		      resultMeta = result.getMetaData();

		      while(result.next()){   //tant qu'on trouve un résultat qui match avec la requête
		    	 Vector<Object> data= new Vector<Object>(); //on crée un vector 
		         data.add(String.valueOf(result.getInt("idControleTechnique"))); 
		         data.add(String.valueOf(result.getInt("idParapente"))); 
		         data.add(result.getString("dateControleTechnique")); 		        
		         data.add(String.valueOf(result.getInt("prixControleTechnique"))); 

		         Interface.ajoutElementControleTechnique(data); //on ajoute à la jtable controletechnique le vector comme row
		      }  

		  	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		      
		      // tableau location
		        
		       result = stmt.executeQuery(
		    		   "SELECT location.idLocation, nomPersonne, prenomPersonne, location.licencePilote, location.idParapente, location.nbHeuresLocation, location.dateReservationLocation, location.locationPour2, location.prixTTCLocation"
		    		   + " FROM location INNER JOIN pilote"
		    		   + " ON location.licencePilote = pilote.licencePilote"
		    		   + " INNER JOIN personne"
		    		   + " ON pilote.idPilote= personne.idPersonne "
		    		   + " ORDER BY location.idLocation ASC"); //la requête
		     
		       resultMeta = result.getMetaData(); //nom des colonnes
		       

		       while(result.next()){   //tant qu'on trouve un résultat qui match avec la requête
		       		//les champs du tableau location: id location, nom pilote, prénom pilote, numéro licence pilote, id parapente, durée loc en heure, date réservation, présence d'un passager, prix ttc
			    	 Vector<Object> data= new Vector<Object>(); //on crée un vector 
			         data.add(String.valueOf(result.getInt("idLocation")));
			         data.add(result.getString("nomPersonne"));
			         data.add(result.getString("prenomPersonne"));
			         data.add(result.getString("licencePilote"));
			         data.add(String.valueOf(result.getInt("idParapente")));
			         data.add(String.valueOf(result.getInt("nbHeuresLocation")));
			         data.add(result.getString("dateReservationLocation"));
			         
			         if(result.getBoolean("locationPour2")){
			        	 data.add("oui");
			         }
			         
			         else{
			        	 data.add("non");
			         }
			         
			         data.add(String.valueOf(result.getInt("prixTTCLocation")));

			         
			         Interface.ajoutElementLocation(data); //on ajoute à la jtable controletechnique le vector comme row
		      	}  
		       
		       
			  	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		      	//tableau vol		     
				//demandeAjoutVol(identifiantLocation, result.getString("nomPersonne"), result.getString("prenomPersonne"), result.getString("licencePilote"), String.valueOf(result.getInt("idParapente")), String.valueOf(idVol), date, nomParcours  );
			
			    result = stmt.executeQuery("SELECT vol.idLocation, personne.nomPersonne, personne.prenomPersonne, location.licencePilote, location.idParapente, vol.idVol, vol.dateVol, vol.nomParcoursVol "
			    		   + " FROM vol INNER JOIN location"
			    		   + " ON vol.idLocation = location.idLocation"
			    		   + " INNER JOIN pilote"
			    		   + " ON location.licencePilote = pilote.licencePilote"
			    		   + " INNER JOIN personne"
			    		   + " ON pilote.idPilote= personne.idPersonne"
			    		   + " ORDER BY vol.idVol ASC"); //la requête
		     
		       	resultMeta = result.getMetaData(); //nom des colonnes
		       
		       while(result.next()){   //tant qu'on trouve un résultat qui match avec la requête
		       		//les champs du tableau vol:id location, nom pilote, prenom pilote, num licence pilote, id parapente, id vol, date vol, nom parcours
			    	 Vector<Object> data= new Vector<Object>(); //on crée un vector 
			    	 data.add(String.valueOf(result.getInt("idVol")));
			         data.add(result.getString("dateVol"));
			         data.add(result.getString("nomParcoursVol"));
			         data.add(String.valueOf(result.getInt("idLocation")));
			         data.add(result.getString("nomPersonne"));
			         data.add(result.getString("prenomPersonne"));
			         data.add(result.getString("licencePilote"));
			         data.add(String.valueOf(result.getInt("idParapente")));

			         Interface.ajoutElementVol(data); //on ajoute à la jtable controletechnique le vector comme row
		      	}  
		      
		      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		     //historique
		       String texte;
		       result= stmt.executeQuery("SELECT identifiant, operation FROM entreehistorique ORDER BY identifiant ASC");
		       resultMeta= result.getMetaData();
		       
		       while(result.next()){
		    	   texte="OPERATION #"+String.valueOf(result.getInt("identifiant"))+""
		    			   +result.getString("operation");
		    	   
		    	   Interface.ajoutElementHistorique("\n"+texte);

		       }
			   ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			   ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
		       
		      result.close();
		      stmt.close();
		      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
		} catch (SQLException e) {
			e.printStackTrace(); //si erreur on l'affiche
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void ajouteEntreeHistorique(String texte){
		try{
			int idEntree=0;
			String insertTableSQL = "INSERT INTO entreehistorique"
					+ "(operation) VALUES"
					+ "(?)";
			
			PreparedStatement preparedStatement = connex.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, texte);

			 preparedStatement.executeUpdate();
			
			 ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
		     if (generatedKeys.next()) {
		    	 idEntree= generatedKeys.getInt("identifiant");
		          System.out.println(idEntree);
		     }
		     texte="OPERATION #"+String.valueOf(idEntree)+""
		    		 +texte;
		     Interface.ajoutElementHistorique("\n"+texte);
		     
		     
		}
		
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void suppressionPilote(String licence){
		try{
			String query="SELECT personne.nomPersonne, personne.prenomPersonne, pilote.licencePilote "
								+" FROM pilote INNER JOIN personne"
								+" ON pilote.idPilote = personne.idPersonne "
								+" WHERE pilote.licencePilote= ?"; 
			
			PreparedStatement preparedStatement = connex.prepareStatement(query);
			preparedStatement.setString(1, licence);			 
			SimpleDateFormat formater = null;
			formater = new SimpleDateFormat("'Le' EEEE dd MMMM yyyy 'à' HH:mm:ss", Locale.FRENCH); 
			String dateToStr = formater.format(new Date());
			
			ResultSet result1 = preparedStatement.executeQuery();
					
			String operation=": SUPPRESSION DE PILOTE, VOL ET LOCATION\n"+dateToStr+"\n";
			while(result1.next()){
	
						operation= operation+"Suppression du pilote "+result1.getString("prenomPersonne")+" "+result1.getString("nomPersonne").toUpperCase()+"\n"
						+"Numéro de licence: "+licence+",\n"
						+"\n\n";
			}
			
			
			query= "SELECT location.idLocation "
					+" FROM pilote INNER JOIN location"
					+" ON pilote.licencePilote = location.licencePilote "
					+" WHERE pilote.licencePilote= ?";
			
			preparedStatement = connex.prepareStatement(query);
			preparedStatement.setString(1, licence);			 

			ResultSet result2 = preparedStatement.executeQuery();

			operation= operation+"Suppression des locations: ";
			
			while(result2.next()){
				
				operation= operation+String.valueOf(result2.getInt("idLocation"))+" ;";
			}
			operation = operation+"\n\n";
			
			query= "SELECT vol.idVol "
					+" FROM pilote INNER JOIN location"
					+" ON pilote.licencePilote = location.licencePilote "
					+ " INNER JOIN vol "
					+ " ON vol.idLocation = location.idLocation"
					+" WHERE pilote.licencePilote= ?";
			
			preparedStatement = connex.prepareStatement(query);
			preparedStatement.setString(1, licence);			 

			ResultSet result3 = preparedStatement.executeQuery();
			
			operation= operation+"Suppression des vols: ";
			
			while(result3.next()){
				
				operation= operation+String.valueOf(result3.getInt("idVol"))+" ;";
			}
			operation = operation+"\n\n";
			
			ajouteEntreeHistorique(operation);
				
				query="DELETE FROM pilote WHERE licencePilote= ?";
				preparedStatement = connex.prepareStatement(query);
				preparedStatement.setString(1, licence);
				 preparedStatement.executeUpdate();
				 it.retirePilote(licence);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void suppressionParapente(String idParapente){
		try{
			String query="SELECT * "
			+" FROM parapente "
			+" WHERE parapente.idParapente= ?"; 
			
			String estBiplace=" monoplace ";
				
			PreparedStatement preparedStatement = connex.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(idParapente));			 
			SimpleDateFormat formater = null;
			formater = new SimpleDateFormat("'Le' EEEE dd MMMM yyyy 'à' HH:mm:ss", Locale.FRENCH); 
			String dateToStr = formater.format(new Date());
			
			ResultSet result1 = preparedStatement.executeQuery();
			
			String operation=": SUPPRESSION DE PARAPENTE, LOCATION, VOL ET CONTRÔLE TECHNIQUE\n"+dateToStr+"\n";
			
			while(result1.next()){
				
				if(result1.getBoolean("estBiplace")){
					estBiplace="biplace";
				}
				operation= operation+"Suppression du parapente "+idParapente+", marque "+result1.getString("marqueParapente")+", modèle "+result1.getString("modeleParapente")+"("+estBiplace+"), de poids "+String.valueOf(result1.getInt("poidsParapente"))+"\n"
				+"\n\n";
			}
			
			
			query= "SELECT location.idLocation "
			+" FROM parapente INNER JOIN location"
			+" ON parapente.idParapente = location.idParapente "
			+" WHERE parapente.idParapente= ?";
			
			preparedStatement = connex.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(idParapente));			 
			
			ResultSet result2 = preparedStatement.executeQuery();
			
			operation= operation+"Suppression des locations: ";
			
			while(result2.next()){
				operation= operation+String.valueOf(result2.getInt("idLocation"))+" ;";
			}
			
			operation = operation+"\n\n";
			
			query= "SELECT vol.idVol "
			+" FROM parapente INNER JOIN location"
			+" ON parapente.idParapente = location.idParapente "
			+ " INNER JOIN vol "
			+ " ON vol.idLocation = location.idLocation"
			+" WHERE parapente.idParapente= ?";
			
			preparedStatement = connex.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(idParapente));			 
			
			ResultSet result3 = preparedStatement.executeQuery();
			
			operation= operation+"Suppression des vols: ";
			
			while(result3.next()){
			
				operation= operation+String.valueOf(result3.getInt("idVol"))+" ;";
			}
			operation = operation+"\n\n";
			
			
			query= "SELECT controletechnique.idControleTechnique "
					+" FROM parapente INNER JOIN controletechnique"
					+" ON parapente.idParapente = controletechnique.idParapente "
					+" WHERE parapente.idParapente= ?";
					
					preparedStatement = connex.prepareStatement(query);
					preparedStatement.setInt(1, Integer.parseInt(idParapente));			 
					
					ResultSet result4 = preparedStatement.executeQuery();
					
					operation= operation+"Suppression des contrôles techniques: ";
					
					while(result2.next()){
						operation= operation+String.valueOf(result4.getInt("idContrôleTechnique"))+" ;";
					}
					
			operation = operation+"\n\n";
			
			ajouteEntreeHistorique(operation);
			
			query="DELETE FROM parapente WHERE idParapente= ?";
			preparedStatement = connex.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(idParapente));			 
			preparedStatement.executeUpdate();
			it.retireParapente(idParapente);
		}
		catch(Exception e){
		System.out.println(e);
		}
	}	
		
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		public static void suppressionVol(String idVol){
			try{
				String query="SELECT * FROM vol WHERE idVol= ?";
				PreparedStatement preparedStatement = connex.prepareStatement(query);
				preparedStatement.setInt(1, Integer.parseInt(idVol));
				
				ResultSet rs=preparedStatement.executeQuery();
				SimpleDateFormat formater = null;
				formater = new SimpleDateFormat("'Le' EEEE dd MMMM yyyy 'à' HH:mm:ss", Locale.FRENCH); 
				String dateToStr = formater.format(new Date());
								
				String operation=": SUPPRESSION DE VOL\n"+dateToStr+"\n";
				while(rs.next()){
					operation=operation+"Identifiant du vol: "+idVol+"\n"
					+"Identifiant de la location: "+rs.getString("idLocation")+"\n"
					+"Date du vol: "+rs.getString("dateVol")+"\n"
					+"Nom du parcours: "+rs.getString("nomParcoursVol")+"\n\n";
				}
				
				query="DELETE FROM vol WHERE idVol= ?";
				preparedStatement = connex.prepareStatement(query);
				preparedStatement.setInt(1, Integer.parseInt(idVol));			 
				preparedStatement.executeUpdate()
				;
				ajouteEntreeHistorique(operation);
				it.retireVol(idVol);
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
		
		
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		public static void suppressionControleTechnique(String idControleTechnique){
			try{
				String query="SELECT * FROM controletechnique WHERE idControleTechnique= ?";
				PreparedStatement preparedStatement = connex.prepareStatement(query);
				preparedStatement.setInt(1, Integer.parseInt(idControleTechnique));
				
				ResultSet rs=preparedStatement.executeQuery();
				SimpleDateFormat formater = null;
				formater = new SimpleDateFormat("'Le' EEEE dd MMMM yyyy 'à' HH:mm:ss", Locale.FRENCH); 
				String dateToStr = formater.format(new Date());
				
				String estFait="Contrôle de routine";
				
				String operation=": SUPPRESSION DE CONTRÔLE TECHNIQUE\n"+dateToStr+"\n";
				while(rs.next()){
					if(rs.getBoolean("estFaitAvantVol")){
						estFait="Contrôle fait avant vol";
					}
					else if(rs.getBoolean("estFaitApresVol")){
						estFait="Contrôle fait après vol";
					}
					
					operation=operation+"Identifiant du contrôle technique: "+idControleTechnique+"\n"
					+"Identifiant du parapente: "+String.valueOf(rs.getString("idParapente"))+"\n"
					+"Date du contrôle technique: "+rs.getString("dateControleTechnique")+"\n"
					+estFait+"\n"
					+"Nom et nombres de pièces changées: "+rs.getString("piecesChangeesControleTechnique")
					+"Coût du contrôle: "+String.valueOf(rs.getInt("prixControleTechnique"))+" euros \n";
				}
				
				query="DELETE FROM controletechnique WHERE idControleTechnique= ?";
				preparedStatement = connex.prepareStatement(query);
				preparedStatement.setInt(1, Integer.parseInt(idControleTechnique));			 
				preparedStatement.executeUpdate()
				;
				ajouteEntreeHistorique(operation);
				it.retireControleTechnique(idControleTechnique);
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void suppressionLocation(String idLocation){
		try{
			String query="SELECT location.idLocation, location.dateReservationLocation, location.nbHeuresLocation, location.prixTTCLocation, location.locationPour2, location.prixHTLocation , location.idParapente, location.licencePilote, location.idPassager FROM location WHERE idLocation= ?";
			PreparedStatement preparedStatement = connex.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(idLocation));
			
			SimpleDateFormat formater = null;
			formater = new SimpleDateFormat("'Le' EEEE dd MMMM yyyy 'à' HH:mm:ss", Locale.FRENCH); 
			String dateToStr = formater.format(new Date());
			
			ResultSet result1 = preparedStatement.executeQuery();
			String pour2="non";
			String prixHT="";
			String prixTTC="";
			String operation=": SUPPRESSION DE LOCATION\n"+dateToStr+"\nInformation de la location:\n";
			while(result1.next()){
				
				if(result1.getBoolean("locationPour2")){
					pour2="oui";
				}
				
				operation=operation+"Identifiant de la location: "+idLocation
						+"Numéro de licence du pilote: "+result1.getString("licencePilote")
						+"Date de la location: "+result1.getString("dateReservationLocation")+"\n"
						+"Parapente réservé: "+String.valueOf(result1.getInt("idParapente"))+"\n"
						+"Durée de la location: "+String.valueOf(result1.getInt("nbHeuresLocation"))+"\n"
						+"Location pour 2: "+pour2+"\n\n"
						;
						
				prixHT=String.valueOf(result1.getInt("prixHTLocation")+" euros\n");
				prixTTC=String.valueOf(result1.getInt("prixHTLocation")+" euros\n");
				
			}
			
			if(pour2=="oui"){
				query="SELECT location.idLocation, personne.nomPersonne, personne.prenomPersonne, personne.poidsPersonne, personne.taillePersonne "
						+ " FROM location INNER JOIN personne"
						+ " ON location.idPassager = personne.idPersonne"
						+ " WHERE idLocation= ?";
				
				preparedStatement = connex.prepareStatement(query);
				preparedStatement.setInt(1, Integer.parseInt(idLocation));
				ResultSet result2 = preparedStatement.executeQuery();

				operation=operation+"Nom du passager: "+result2.getString("nomPersonne").toUpperCase()+"\n"
						+"Prénom du passager: "+result2.getString("prenomPersonne")+"\n"
						+"Poids du passager: "+String.valueOf(result2.getInt("poidsPassager"))+" kg\n"
						+"Taille du passager: "+String.valueOf(result2.getInt("taillePassager"))+ "cm\n\n";

			}
			
			operation=operation+"Prix hors taxe: "+prixHT
					+"Prix toutes taxes comprises: "+prixTTC+"\n"
					+"Suppression des vols: ";
			
			
			query="SELECT vol.idVol, location.idLocation "
					+ " FROM location INNER JOIN vol"
					+ " ON location.idLocation = vol.idLocation"
					+ " WHERE location.idLocation= ?";
			
			preparedStatement = connex.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(idLocation));
			ResultSet result3 = preparedStatement.executeQuery();
			
			while(result3.next()){
							operation= operation+String.valueOf(result3.getInt("idVol"))+" ;";
						}
			
			operation=operation+"\n\n";
			
			query="DELETE FROM location WHERE location.idLocation= ?";
			preparedStatement = connex.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(idLocation));			 
			preparedStatement.executeUpdate()
			;
			
			ajouteEntreeHistorique(operation);
			it.retireLocation(idLocation);
		}
		
		catch(Exception e){
			System.out.println(e);

		}
	}	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 *  PROGRAMME PRINCIPAL
	 **/
	public static void main(String[] args){
		
		String host= "localhost:5432"; //nom de l'hôte 
		String nomBase= "GSI"; //nom de la base où tu veux taffer
		String user="postgres"; //nom d'utilisateur
		String psw= "lanaodu56"; //mot de passe
		
		it= new Interface();
		it.construireFenetre(); 
		
		//connexion
		connexion(host, nomBase, user, psw); //connexion à la base
		creeTable(); //crée les différentes tables dans la base si elles n'existent pas
		
		it.initialiseOnglets(); //créé les onglets avec des tableaux dedans
		
		remplirTables(); //rempli les différents tableaux avec les valeurs des données contenues dans la base
		it.setBoutons(); //initialise l'état des boutons
		it.setVisible(true); //on rend visible la fenêtre de l'interface
	}
	
}

