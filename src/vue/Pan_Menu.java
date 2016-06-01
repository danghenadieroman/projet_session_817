package vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * ver 2.0
 *
 * @author 1395945
 */
public class Pan_Menu extends JPanel{

    //attributs
    private JButton btnAcceuil;
    private JButton btnListeUtilisateurs;
    private JButton btnCommancer;
    
    
    JPanel jpPanel;

    public Pan_Menu() {
        
        
        jpPanel = new JPanel(new BorderLayout());
        
        btnAcceuil = new JButton("Acceuil");
        btnCommancer = new JButton("Commancer");
        btnListeUtilisateurs = new JButton("Liste utilisateurs");
        
        //listeners
        btnListeUtilisateurs.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        
        //
        add(btnAcceuil, BorderLayout.CENTER);
        add(btnCommancer, BorderLayout.CENTER);
        add(btnListeUtilisateurs, BorderLayout.CENTER);
        
        //set panel
        
    }
    
    
    
    
    
    
    
    
    
    

}
