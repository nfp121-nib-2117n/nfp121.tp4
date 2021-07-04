package question3;

import question3.tp3.PileI;
import question3.tp3.PilePleineException;
import question3.tp3.PileVideException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Décrivez votre classe Controleur ici.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Controleur extends JPanel implements ActionListener, MouseListener {

    private JButton push, add, sub, mul, div, clear;
    private PileModele<Integer> pile;
    private JTextField donnee;

    public Controleur(PileModele<Integer> pile) {
        super();
        this.pile = pile;
        this.donnee = new JTextField(8);

        this.push = new JButton("push");
        this.add = new JButton("+");
        this.sub = new JButton("-");
        this.mul = new JButton("*");
        this.div = new JButton("/");
        this.clear = new JButton("[]");

        setLayout(new GridLayout(2, 1));
        add(donnee);
        donnee.addActionListener(this);
        
        // Add mouse listener to field
        donnee.addMouseListener(this);
        
        JPanel boutons = new JPanel();
        boutons.setLayout(new FlowLayout());
        boutons.add(push);  push.addActionListener(this);
        boutons.add(add);   add.addActionListener(this);
        boutons.add(sub);   sub.addActionListener(this);
        boutons.add(mul);   mul.addActionListener(this);
        boutons.add(div);   div.addActionListener(this);
        boutons.add(clear); clear.addActionListener(this);
        add(boutons);
        boutons.setBackground(Color.red);
        actualiserInterface();
    }

    public void actualiserInterface() {
        donnee.revalidate();
        push.revalidate();
        add.revalidate();
        sub.revalidate();
        mul.revalidate();
        div.revalidate();
        clear.revalidate();
    }

    private Integer operande() throws NumberFormatException {
        return Integer.parseInt(donnee.getText());
    }

    // MouseListener
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        
        // Clear text when user presses on textfield
        if (source == donnee) {
            donnee.setText("");
        }
    }
  
    // ActionListener
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        
        // Push
        if (source == push) {
            try {
                pile.empiler(operande());
            } catch (Exception exception) {
                donnee.setText((exception instanceof PilePleineException ? "La pile est pleine!" : "Inserer un nombre valide"));
            }
            return;
        }
        
        // Add
        if (source == add) {
                try {
                    pile.empiler(pile.depiler() + pile.depiler());
                } catch (Exception exception) {
                    donnee.setText((exception instanceof PilePleineException ? "La pile est pleine!" : "La pile est vide!"));
                }
                return;
        }
        
        // Substract
        if (source == sub) {
                try{
                    pile.empiler(pile.depiler() - pile.depiler());
                } catch(Exception exception) {
                    donnee.setText((exception instanceof PilePleineException ? "La pile est pleine!" : "La pile est vide!"));
                }  
                return;
        }
        
        // Multiply
        if (source == mul) {
                try{
                    pile.empiler(pile.depiler() * pile.depiler());
                } catch(Exception exception) {
                    donnee.setText((exception instanceof PilePleineException ? "La pile est pleine!" : "La pile est vide!"));
                } 
                return;
        }
        
        // Divide
        if (source == div) {
            try{
                int num1 = pile.depiler();
                int num2 = pile.depiler();
                if(num2 == 0) {
                    pile.empiler(num2);
                    pile.empiler(num1);
                    donnee.setText("Erreur Mathematique: interdiction de diviser par 0");
                } else {
                    pile.empiler(num1/ num2);   
                }
            } catch(Exception exception) {
                donnee.setText((exception instanceof PilePleineException ? "La pile est pleine!" : "La pile est vide!"));
            } 
            return;
        }
        
        // Clear
        while(!pile.estVide()){
           try{
               pile.depiler();
            } catch(PileVideException exception){
            }
        }
        donnee.setText("");
    }
    
    // Functions of MouseListener class
    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}
}
