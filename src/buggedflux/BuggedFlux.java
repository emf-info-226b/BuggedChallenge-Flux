package buggedflux;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Classe principale de l'application "BuggedFlux".
 *
 * @author <a href="mailto:friedlip@edufr.ch">Paul Friedli</a>
 * @since 23 août 2012
 * @version 1.0
 */
public class BuggedFlux {

    public final static int TAILLE_MIN = 512;
    public final static int TAILLE_MAX = 1024;

    /**
     * LA méthode main() de l'application, là où tout commence mais... tout se finit-il bien là ?
     *
     * @param args les arguments pour le programme passés sur la ligne de commande
     */
    public static void main( String[] args ) {
        // Pour se souvenir de quand on a commencé
        long startTime = System.currentTimeMillis();

        // Production de données
        byte[][] tableau1 = productionDeDonnees();

        // Ecriture des données sur disque
        if ( ecrireSurDisque( tableau1, "data.dat" ) ) {
            System.out.println( "L'écriture a réussi ! Le tableau fait " + tableau1.length + " x " + tableau1[0].length + " = " + tableau1.length * tableau1[0].length + " bytes" );
            // Lecture des données du disque
            byte[][] tableau2 = lireDuDisque( "data.dat" );
            if ( tableau2 != null ) {
                System.out.println( "La lecture a réussi !" );
                // Comparaison pour vérifier que tout est en ordre
                boolean identique = true;
                if ( ( tableau1.length == tableau2.length ) && ( tableau1[0].length == tableau2[0].length ) ) {
                    for ( int i = 0; i < tableau1.length; i++ ) {
                        for ( int j = 0; j < tableau1[0].length; j++ ) {
                            if ( tableau1[i][j] != tableau2[i][j] ) {
                                identique = false;
                            }
                        }
                        
                    }
                } else {
                    identique = false;
                }

                if ( identique ) {
                    System.out.println( "Le contenu est identique !!!" );
                } else {
                    System.out.println( "Le contenu n'est pas identique !!!" );
                }

                // Afficher durée totale de l'opération
                long ms = System.currentTimeMillis() - startTime;
                System.out.println( "L'ensemble a pris " + ms + " ms." );

            } else {
                System.out.println( "La lecture a échoué !" );
            }
        } else {
            System.out.println( "L'écriture a échoué !" );
        }
    }

    private static byte[][] productionDeDonnees() {

        // Taille aléatoire du tableau entre [TAILLE_MIN..TAILLE_MAX] x [TAILLE_MIN..TAILLE_MAX]
        int largeur = TAILLE_MIN + ( int ) ( Math.random() * ( TAILLE_MAX - TAILLE_MIN ) );
        int hauteur = TAILLE_MIN + ( int ) ( Math.random() * ( TAILLE_MAX - TAILLE_MIN ) );

        // Création du tableau
        byte[][] data = new byte[ largeur ][ hauteur ];

        // Remplissage du tableau
        for ( int i = 0; i < data.length; i++ ) {
            for ( int j = 0; j < data[0].length; j++ ) {
                data[i][j] = ( byte ) ( Math.random() * 256 );
            }
        }

        return data;
    }

    private static boolean ecrireSurDisque( byte[][] data, String filename ) {

        boolean result = false;

        DataOutputStream out = null;

        try {
            out = new DataOutputStream(  new FileOutputStream( filename ) );

            int dim1 = data.length;
            int dim2 = data[0].length;

            out.writeInt( dim2 );
            out.writeInt( dim1 );

            for ( int i = 0; i < data.length; i++ ) {
                for ( int j = 0; j < data[0].length; j++ ) {
                    out.writeByte( data[i][j] );
                }
            }

            result = true;  // Si on est ici c'est que vraiment tout s'est bien passé !
        } catch ( Exception e ) {
        }

        return result;
    }

    private static byte[][] lireDuDisque( String filename ) {
        byte[][] resultat = null;
        DataInputStream in = null;

        try {
            in = new DataInputStream( new FileInputStream( filename ) );

            int dim1 = in.readInt();
            int dim2 = in.readInt();

            resultat = new byte[ dim1 ][ dim2 ];
            for ( int i = 0; i < resultat.length; i++ ) {
                for ( int j = 0; j < resultat[i].length; j++ ) {
                    resultat[i][j] = in.readByte();
                }
            }
            
        } catch ( Exception e ) {
            resultat = null;
        }

        return resultat;
    }

}
