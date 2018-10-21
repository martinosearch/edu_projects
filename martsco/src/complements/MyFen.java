/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package complements;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author webdev
 */
public class MyFen extends JFrame{

    private JPanel contennaire = new JPanel();
    private JLabel ecran = new JLabel();
    private JLabel[] opEcran = new JLabel[3];
    private Dimension dim = new Dimension(52,33);
    private Dimension dim2 = new Dimension(112,71);
    private Dimension dim3 = new Dimension(109,33);
    private Double chiffre1 = new Double(0);
    private Double memo = new Double(0);
    private Boolean clicOperateur = true, update = false;
    private String operateur = "";

    String[] tab_String = {"MC", "MR", "MS", "M+", "M-", "Sup", "CE", "C", "7", "8", "9", "4", "5", "6", "1", "2", "3", "0", ".", "+", "-", "*", "/", "%", "±", "="};
    //JCoolBouton[] tab_Bouton = new JCoolBouton[tab_String.length];
    JButton[] tab_Bouton = new JButton[tab_String.length];


    public MyFen(){
        this.setSize(316, 360);
        this.setTitle("MartSCO");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE              );
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.addKeyListener(new KeyPadListener());

        Objets();
        
        this.setContentPane(contennaire);
        this.setVisible(true);
    }
    
    private void Objets(){
        JPanel operateur = new JPanel();
        operateur.setPreferredSize(new Dimension(116, 230));      
        
        JPanel chiffre = new JPanel();
        chiffre.setPreferredSize(new Dimension(174, 230));
        EcranPannel panEcran = new EcranPannel("");
        panEcran.setPreferredSize(new Dimension(294, 68));
        
        for(int i=0; i<3; i++){                 //AFFICHEUR DES OPERATION AU DESSU DE L'ECRAN DE CALCULE
            Font Police1 = new Font("Consolas", Font.BOLD, 12);
            opEcran[i] = new JLabel("");
            opEcran[i].setFont(Police1);
            opEcran[i].setHorizontalAlignment(JLabel.CENTER);
            opEcran[i].setPreferredSize(new Dimension(80,16));
            //opEcran[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panEcran.add(opEcran[i]);
        }
        
        Font Police = new Font("LCD", Font.BOLD, 28);   //28
        ecran = new JLabel("0");
        ecran.setFont(Police);
        ecran.setHorizontalAlignment(JLabel.RIGHT);
        ecran.setPreferredSize(new Dimension(280,38));

        
        for(int i=0; i < tab_String.length; i++){
            tab_Bouton[i] = new JCoolBouton(tab_String[i]);
            tab_Bouton[i].setPreferredSize(dim);
            
            switch(i){
                case 0:
                    tab_Bouton[i].addActionListener(new MemoClearListener());
                    tab_Bouton[i].addKeyListener(new KeyPadListener());
                    chiffre.add(tab_Bouton[i]);
                break;
                case 1:
                    tab_Bouton[i].addActionListener(new MemoReadListener());
                    tab_Bouton[i].addKeyListener(new KeyPadListener());
                    chiffre.add(tab_Bouton[i]);
                break;
                case 2:
                    tab_Bouton[i].addActionListener(new MemoSaveListener());
                    tab_Bouton[i].addKeyListener(new KeyPadListener());
                    chiffre.add(tab_Bouton[i]);
                break;
                case 3:
                    tab_Bouton[i].addActionListener(new MemoPlusListener());
                    tab_Bouton[i].addKeyListener(new KeyPadListener());
                    operateur.add(tab_Bouton[i]);
                break;
                case 4:
                    tab_Bouton[i].addActionListener(new MemoMoinsListener());
                    tab_Bouton[i].addKeyListener(new KeyPadListener());
                    operateur.add(tab_Bouton[i]);
                break;
                case 5:
                    tab_Bouton[i].addActionListener(new BackListener());
                    tab_Bouton[i].addKeyListener(new KeyPadListener());
                    chiffre.add(tab_Bouton[i]);
                break;
                case 6:
                    tab_Bouton[i].addActionListener(new CEResetListener());
                    tab_Bouton[i].addKeyListener(new KeyPadListener());
                    chiffre.add(tab_Bouton[i]);
                break;
                case 7:
                    tab_Bouton[i].addActionListener(new ResetListener());
                    tab_Bouton[i].addKeyListener(new KeyPadListener());
                    chiffre.add(tab_Bouton[i]);
                break;
                case 19:
                    tab_Bouton[i].addActionListener(new PlusListener());
                    tab_Bouton[i].addKeyListener(new KeyPadListener());
                    operateur.add(tab_Bouton[i]);
                break;
                case 20:
                    tab_Bouton[i].addActionListener(new MoinsListener());
                    tab_Bouton[i].addKeyListener(new KeyPadListener());
                    operateur.add(tab_Bouton[i]);
                break;
                case 21:
                    tab_Bouton[i].addActionListener(new MultiListener());
                    tab_Bouton[i].addKeyListener(new KeyPadListener());
                    operateur.add(tab_Bouton[i]);
                break;
                case 22:
                    tab_Bouton[i].addActionListener(new DivListener());
                    tab_Bouton[i].addKeyListener(new KeyPadListener());
                    operateur.add(tab_Bouton[i]);
                break;
                case 23:
                    tab_Bouton[i].addActionListener(new PourcentListener());
                    tab_Bouton[i].addKeyListener(new KeyPadListener());
                    operateur.add(tab_Bouton[i]);
                break;
                case 24:
                    tab_Bouton[i].addActionListener(new PlusMoinstListener());
                    tab_Bouton[i].addKeyListener(new KeyPadListener());
                    operateur.add(tab_Bouton[i]);
                break;
                case 25:
                    tab_Bouton[i].addActionListener(new EgalListener());
                    tab_Bouton[i].addKeyListener(new KeyPadListener());
                    tab_Bouton[i].setPreferredSize(dim2);
                    operateur.add(tab_Bouton[i]);
                break;
                default:
                    if(i==17) tab_Bouton[i].setPreferredSize(dim3);
                    chiffre.add(tab_Bouton[i]);
                    tab_Bouton[i].addActionListener(new ChiffreListener());
                    tab_Bouton[i].addKeyListener(new KeyPadListener());
                break;
            }
        }
      
        //chiffre.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //operateur.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //ecran.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //mybouton.setPreferredSize(dim4);
        panEcran.add(ecran);
        contennaire.setBackground(Color.white);
        contennaire.add(panEcran, BorderLayout.NORTH);
        chiffre.setBackground(Color.white);
        contennaire.add(chiffre, BorderLayout.CENTER);
        operateur.setBackground(Color.white);
        contennaire.add(operateur, BorderLayout.SOUTH);
    }
/*
 * //////////////////////////////////////////////////////////////////////////////////////
 * //////////////////////////////////////////////////////////////////////////////////////
 * ////////////////////////////  LES METHODES QUI CALCULE   /////////////////////////////
 * //////////////////////////////////////////////////////////////////////////////////////
 * //////////////////////////////////////////////////////////////////////////////////////
*/
    private void Calcul(){
        if(operateur.equals("+")){
            chiffre1 = chiffre1 + Double.valueOf(ecran.getText()).doubleValue();
            ecran.setText(String.valueOf(chiffre1));
            DecimVerif();   // CETTE FONCTION VERIFIE ET SUPPRIME LE "." SI CA PLUSIEUR FOIS
        }
        if(operateur.equals("-")){
            chiffre1 = chiffre1 - Double.valueOf(ecran.getText()).doubleValue();
            ecran.setText(String.valueOf(chiffre1));
            DecimVerif();   // CETTE FONCTION VERIFIE ET SUPPRIME LE "." SI CA PLUSIEUR FOIS
        }
        if(operateur.equals("*")){
            chiffre1 = chiffre1 * Double.valueOf(ecran.getText()).doubleValue();
            ecran.setText(String.valueOf(chiffre1));
            DecimVerif();   // CETTE FONCTION VERIFIE ET SUPPRIME LE "." SI CA PLUSIEUR FOIS
        }
        if(operateur.equals("/")){
            try{
                chiffre1 = chiffre1 / Double.valueOf(ecran.getText()).doubleValue();
                ecran.setText(String.valueOf(chiffre1));
            }catch(ArithmeticException e){ 
                ecran.setText("0");
            }
            DecimVerif();   // CETTE FONCTION VERIFIE ET SUPPRIME LE "." SI CA PLUSIEUR FOIS
        }

    }
private void DecimVerif(){
    String unEcran1, unEcran2, unEcran3 = new String();
    
    try{
        unEcran1 = String.valueOf(ecran.getText());
        unEcran2 = unEcran1.substring(0, unEcran1.indexOf("."));
        unEcran3 = unEcran1.substring((unEcran1.indexOf(".")+1), unEcran1.length());

        if(unEcran3.length() < 2){
            if(String.valueOf(unEcran3).equals("0")){
                ecran.setText(unEcran2);
            }
        }
    }catch(StringIndexOutOfBoundsException e){
        ecran.setText("0");
    }
    
}
/*
 * //////////////////////////////////////////////////////////////////////////////////////
 * //////////////////////////////////////////////////////////////////////////////////////
 * //////////////  LES METHODES QUI ECOUTES LES ACTIONS SUR LES BOUTONS   ///////////////
 * //////////////////////////////////////////////////////////////////////////////////////
 * //////////////////////////////////////////////////////////////////////////////////////
*/
    class ChiffreListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if((ecran.getText().length()) < 15){
            String str = ((JButton)e.getSource()).getText();  // RECUPERATION DES ETIQUETTE DES BOUTONS

            if(update){
                update = false;
                if(str.equals(".")){
                    str = ecran.getText() + str;
                    ecran.setText(str);
                }else{
                    ecran.setText(str);
                }
            }else{
                if(!ecran.getText().equals("0")){
                    if(str.equals(".")){
                        if(Integer.valueOf(ecran.getText().indexOf(".")).equals(-1)){
                        str = ecran.getText() + str;
                        ecran.setText(str);
                        }
                    }else{
                        str = ecran.getText() + str;
                        ecran.setText(str);
                    }
                }else{
                    if(str.equals(".")){
                        if(Integer.valueOf(ecran.getText().indexOf(".")).equals(-1)){
                        str = ecran.getText() + str;
                        ecran.setText(str);
                        }
                    }else{
                        ecran.setText(str);
                    }
                }
            }
        }
        }
        
    }
    class EgalListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            Calcul();
            chiffre1 = 0.0;
            update = true;
            opEcran[0].setText("");
        }
    }
    class PlusListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(chiffre1 == 0){
                chiffre1 += Double.valueOf(ecran.getText());
            }else if(chiffre1 != 0 && Integer.valueOf(ecran.getText()) >= 0){
                Calcul();
            }else{
                Calcul();
            }
            operateur = "+";
            opEcran[0].setText(operateur);
            clicOperateur = true;
            update = true;
        }
    }
    class MoinsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(chiffre1 == 0){
                chiffre1 += Double.valueOf(ecran.getText());
            }else if(chiffre1 != 0 && Integer.valueOf(ecran.getText()) >= 0){
                Calcul();
            }else{
                Calcul();
            }
            operateur = "-";
            opEcran[0].setText(operateur);
            update = true;
        }
    }

    class MultiListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(chiffre1 == 0){
                chiffre1 += Double.valueOf(ecran.getText());
            }else if(chiffre1 != 0 && Integer.valueOf(ecran.getText()) >= 0){
                Calcul();
            }else{
                Calcul();
            }
            operateur = "*";
            opEcran[0].setText(operateur);
            update = true;
        }
    }
    class DivListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(chiffre1 == 0){
                chiffre1 += Double.valueOf(ecran.getText());
            }else if(chiffre1 != 0 && Integer.valueOf(ecran.getText()) >= 0){
                Calcul();
            }else{
                Calcul();
            }
            operateur = "/";
            opEcran[0].setText(operateur);
            update = true;
        }
    }

    private class CEResetListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ecran.setText("0");
        }
    }

    class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            update = true;
            chiffre1 = 0.0;
            operateur = "";
            ecran.setText("0");
            opEcran[0].setText(operateur);
        }
    }
    class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String unEcran = "";
            
                chiffre1 = 0.0;
                for(int i=0; i <ecran.getText().length(); i++){
                    unEcran = String.valueOf(ecran.getText().substring(0, i));
                    if(Integer.valueOf(i).equals(0))
                        unEcran = String.valueOf("0");
                }
                ecran.setText(unEcran);
                update = false;
                operateur = ""; 
        }
    }
    
    class MemoClearListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            memo = 0.0;
            opEcran[1].setText("");
        }
    }
    
    class MemoReadListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
                ecran.setText(String.valueOf(memo));
                update = true;
                Calcul();
        }
    }
    
    class MemoSaveListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!ecran.getText().equals("0")) {
                opEcran[1].setText("M");
                memo = Double.valueOf(ecran.getText());
            }
        }
    }
    
    class MemoPlusListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!ecran.getText().equals("0")) {
                opEcran[1].setText("M");
                memo = memo + Double.valueOf(ecran.getText());
            }
        }
    }
    
    class MemoMoinsListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!ecran.getText().equals("0")) {
                opEcran[1].setText("M");
                memo = memo - Double.valueOf(ecran.getText());
            }
        }
    }

    class PourcentListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            int compteur = 0;
            compteur = ecran.getText().length();
            ecran.setText(String.valueOf(compteur));
        }
    }    
    
    class PlusMoinstListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if((Double.valueOf(ecran.getText()))<0){
                String recupe = ecran.getText().substring(1, ecran.getText().length());
                ecran.setText(recupe);
        }else{
                ecran.setText("-"+ecran.getText());
        }
        }
    }
    private class KeyPadListener implements KeyListener {       // INTERFACE QUI ECOUTE LE CLAVIER

        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            //char myChar = (char)KeyPadCode;                   // CONVERSION DES CHIFFRE EN LETTRE
            //String str = String.valueOf(myChar);
            int KeyPadLocation = e.getKeyLocation();
            int KeyPadCode = e.getKeyCode();
            String str = String.valueOf(e.getKeyChar());
            boolean interup = false;
            str=str.replaceAll(",", ".");
            
            for(int k = 0; k < 10; k++){            // BOUCLE QUI VERIFI SI LES CHIFFRES OU UN POINT (.) SONT SAISIE AU CLAVIER
                if(String.valueOf(k).equals(str)){
                    interup = true;
                }else if(str.equals(".")){
                    interup = true;
                }
            }
            
            ///// TESTE SI UN OPERATEUR ARITHMETIQUE EST APPUYER
            if(KeyPadCode==61 && str.equals("+") || str.equals("=") || KeyPadCode==54 && str.equals("-")){
                interup = true;
            }else if(KeyPadCode==151 && str.equals("*") || KeyPadCode==27 || KeyPadCode==10 || KeyPadCode==513 && str.equals("/")){
                interup = true;
            }else if(KeyPadCode==8 || KeyPadCode==106 || KeyPadCode==107 || KeyPadCode==109 || KeyPadCode==111){
                interup = true;
            }
            
            if(interup == true){
                switch(KeyPadLocation){
                    case 1:
                    if(KeyPadCode >47 && KeyPadCode<58 && !str.equals("-") || KeyPadCode==44 || KeyPadCode==59){
                        if((ecran.getText().length()) < 15){

                            if(update){
                                update = false;
                                if(str.equals(".")){
                                    str = ecran.getText() + str;
                                    ecran.setText(str);
                                }else{
                                    ecran.setText(str);
                                }
                            }else{
                                if(!ecran.getText().equals("0")){
                                    if(str.equals(".")){
                                        if(Integer.valueOf(ecran.getText().indexOf(".")).equals(-1)){
                                        str = ecran.getText() + str;
                                        ecran.setText(str);
                                        }
                                    }else{
                                        str = ecran.getText() + str;
                                        ecran.setText(str);
                                    }
                                }else{
                                    if(str.equals(".")){
                                        if(Integer.valueOf(ecran.getText().indexOf(".")).equals(-1)){
                                        str = ecran.getText() + str;
                                        ecran.setText(str);
                                        }
                                    }else{
                                        ecran.setText(str);
                                    }
                                }
                            }

                        }
                    }else if(KeyPadCode==54 && str.equals("-")){                            // SI C LA TOUCHE (-)
                        if(chiffre1 == 0){
                            chiffre1 += Double.valueOf(ecran.getText());
                        }else if(chiffre1 != 0 && Integer.valueOf(ecran.getText()) >= 0){
                            Calcul();
                        }else{
                            Calcul();
                        }
                        operateur = "-";
                        opEcran[0].setText(operateur);
                        update = true;
                    }else if(KeyPadCode==61 && str.equals("+")){                            // SI C LA TOUCHE (+)
                        if(chiffre1 == 0){
                            chiffre1 += Double.valueOf(ecran.getText());
                        }else if(chiffre1 != 0 && Integer.valueOf(ecran.getText()) >= 0){
                            Calcul();
                        }else{
                            Calcul();
                        }
                        operateur = "+";
                        opEcran[0].setText(operateur);
                        update = true;
                    }else if(KeyPadCode==61 && str.equals("=")){                            // SI C LA TOUCHE (=)
                        Calcul();
                        update = true;
                        chiffre1 = 0.0;
                        opEcran[0].setText("");
                    }else if(KeyPadCode==151 && str.equals("*")){                           // SI C LA TOUCHE (*)
                        if(chiffre1 == 0){
                            chiffre1 += Double.valueOf(ecran.getText());
                        }else if(chiffre1 != 0 && Integer.valueOf(ecran.getText()) >= 0){
                            Calcul();
                        }else{
                            Calcul();
                        }
                        operateur = "*";
                        opEcran[0].setText(operateur);
                        clicOperateur = true;
                        update = true;
                    }else if(KeyPadCode==27){                                               //// SI C LA TOUCHE ECHAP
                        update = true;
                        chiffre1 = 0.0;
                        operateur = "";
                        ecran.setText("0");
                        opEcran[0].setText(operateur);
                    }else if(KeyPadCode==10){                                               // SI C UNE AUTRES TOUCHE (=)
                        Calcul();
                        chiffre1 = 0.0;
                        update = true;
                        opEcran[0].setText("");
                    }else if(KeyPadCode==513 && str.equals("/")){                           // SI C LA TOUCHE (/)
                        if(chiffre1 == 0){
                            chiffre1 += Double.valueOf(ecran.getText());
                        }else if(chiffre1 != 0 && Integer.valueOf(ecran.getText()) >= 0){
                            Calcul();
                        }else{
                            Calcul();
                        }
                        operateur = "/";
                        opEcran[0].setText(operateur);
                        update = true;
                    }else if(KeyPadCode==8){                                        // SI C LA TOUCHE BACKSPACE
                        String unEcran = "";

                        chiffre1 = 0.0;
                        for(int i=0; i <ecran.getText().length(); i++){
                            unEcran = String.valueOf(ecran.getText().substring(0, i));
                            if(Integer.valueOf(i).equals(0))
                                unEcran = String.valueOf("0");
                        }
                        ecran.setText(unEcran);
                        update = false;
                        operateur = ""; 

                    }
                  break;
                  case 4:
                    if(KeyPadCode >=96 && KeyPadCode<=105 || KeyPadCode == 110){
                        if((ecran.getText().length()) < 15){

                            if(update){
                                update = false;
                                if(str.equals(".")){
                                    str = ecran.getText() + str;
                                    ecran.setText(str);
                                }else{
                                    ecran.setText(str);
                                }
                            }else{
                                if(!ecran.getText().equals("0")){
                                    if(str.equals(".")){
                                        if(Integer.valueOf(ecran.getText().indexOf(".")).equals(-1)){
                                        str = ecran.getText() + str;
                                        ecran.setText(str);
                                        }
                                    }else{
                                        str = ecran.getText() + str;
                                        ecran.setText(str);
                                    }
                                }else{
                                    if(str.equals(".")){
                                        if(Integer.valueOf(ecran.getText().indexOf(".")).equals(-1)){
                                        str = ecran.getText() + str;
                                        ecran.setText(str);
                                        }
                                    }else{
                                        ecran.setText(str);
                                    }
                                }
                            }

                        }
                    }else if(KeyPadCode==107 && str.equals("+")){                            // SI C LA TOUCHE (+)
                        if(chiffre1 == 0){
                            chiffre1 += Double.valueOf(ecran.getText());
                        }else if(chiffre1 != 0 && Integer.valueOf(ecran.getText()) >= 0){
                            Calcul();
                        }else{
                            Calcul();
                        }
                        operateur = "+";
                        opEcran[0].setText(operateur);
                        update = true;
                    }else if(KeyPadCode==109 && str.equals("-")){                            // SI C LA TOUCHE (-)
                        if(chiffre1 == 0){
                            chiffre1 += Double.valueOf(ecran.getText());
                        }else if(chiffre1 != 0 && Integer.valueOf(ecran.getText()) >= 0){
                            Calcul();
                        }else{
                            Calcul();
                        }
                        operateur = "-";
                        opEcran[0].setText(operateur);
                        update = true;

                    }else if(KeyPadCode==106 && str.equals("*")){                           // SI C LA TOUCHE (*)
                        if(chiffre1 == 0){
                            chiffre1 += Double.valueOf(ecran.getText());
                        }else if(chiffre1 != 0 && Integer.valueOf(ecran.getText()) >= 0){
                            Calcul();
                        }else{
                            Calcul();
                        }
                        operateur = "*";
                        opEcran[0].setText(operateur);
                        clicOperateur = true;
                        update = true;
                    }else if(KeyPadCode==111 && str.equals("/")){                           // SI C LA TOUCHE (/)
                        if(chiffre1 == 0){
                            chiffre1 += Double.valueOf(ecran.getText());
                        }else if(chiffre1 != 0 && Integer.valueOf(ecran.getText()) >= 0){
                            Calcul();
                        }else{
                            Calcul();
                        }
                        operateur = "/";
                        opEcran[0].setText(operateur);
                        update = true;
                    }else if(KeyPadCode==10){                                               // SI C UNE AUTRES TOUCHE (=)
                        Calcul();
                        chiffre1 = 0.0;
                        update = true;
                        opEcran[0].setText("");
                    }
                  break;
                }/*else{
                
                }*/
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
