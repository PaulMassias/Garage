package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

	private final String immatriculation;
	private final List<Stationnement> myStationnements = new LinkedList<>();

	public Voiture(String i) {
		if (null == i) {
			throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
		}

		immatriculation = i;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	/**
	 * Fait rentrer la voiture au garage 
         * Précondition : la voiture ne doit pas être déjà dans un garage
	 *
	 * @param g le garage où la voiture va stationner
	 * @throws java.lang.Exception Si déjà dans un garage
	 */
	public void entreAuGarage(Garage g) throws Exception {
                if(myStationnements.isEmpty()){
                    Stationnement s = new Stationnement(this, g);
                    myStationnements.add(s);
                }
                else{
                    int taille =myStationnements.size();
                    Stationnement st= myStationnements.get(taille-1);
                        if(st.getFin()==null){
                            throw new java.lang.Exception("La voiture est déja dans un garage");
                        }
                        else{
                            Stationnement s1 = new Stationnement(this, g);
                            myStationnements.add(s1);
                        }
                    }
                }

                
                
                


	/**
	 * Fait sortir la voiture du garage 
         * Précondition : la voiture doit être dans un garage
	 *
	 * @throws java.lang.Exception si la voiture n'est pas dans un garage
	 */
	public void sortDuGarage() throws Exception {
            int taille =myStationnements.size();
            Stationnement st= myStationnements.get(taille-1);
            if(st.getFin()!=null){
                throw new java.lang.Exception("La voiture est déjà sortie");
            }
            else{
                st.terminer();
            }
	}

        
        
	/**
	 * @return l'ensemble des garages visités par cette voiture
	 */
	public Set<Garage> garagesVisites() {
            Set<Garage> listeGarage = new HashSet();
            if(myStationnements.isEmpty()){
                return listeGarage;
            }
            else{
            
                for(Stationnement s : myStationnements){
                    listeGarage.add(s.getGarage());
                }
                return listeGarage;
            }
	}

	/**
	 * @return vrai si la voiture est dans un garage, faux sinon
	 */
	public boolean estDansUnGarage() {
            if(myStationnements.isEmpty()){
                return false;
            }
            else{
                int taille =myStationnements.size();
                Stationnement st= myStationnements.get(taille-1);
                if(st.getFin()==null){
                    return true;
                }
                return false;
            }
        }
           


	/**
	 * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste des dates d'entrée / sortie dans ce
	 * garage
	 * <br>Exemple :
	 * <pre>
	 * Garage Castres:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 *		Stationnement{ entree=28/01/2019, en cours }
	 *  Garage Albi:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 * </pre>
	 *
	 * @param out l'endroit où imprimer (ex: System.out)
	 */
	public void imprimeStationnements(PrintStream out) {
            Set<Garage> Gset=this.garagesVisites();
            for(Garage g : Gset){
               out.println(g.toString());
               for(Stationnement s : myStationnements){
                   if(s.getGarage()== g)
                   out.println(s.toString());
               }   
            }
	}

}
