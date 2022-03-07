import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculadoraView extends JFrame implements ActionListener{

   JTextField operacion;
   JLabel res;
   JButton[] num = new JButton[17];
   JPanel panel1, panel2, panelP;
   
   public CalculadoraView(){
      initComponents();
   }
   
   private void initComponents(){
      panelP = new JPanel();
      panel1 = new JPanel();
      panel2 = new JPanel();
      
      res = new JLabel("0", JLabel.RIGHT);
      operacion = new JTextField("", 30);
      operacion.setEditable(false);
      operacion.setHorizontalAlignment(SwingConstants.RIGHT);
      
      panelP.add(panel1, BorderLayout.CENTER);
      panelP.add(panel2);
      
      panel1.setLayout(new GridLayout(2,1));
      panel1.add(res);
      panel1.add(operacion);
      
      panel2.setLayout(new GridLayout(5, 4, 10, 10));
      
      int x = 0;
      for(int i = 0; i < num.length; i++){
      
         if(i == 3) num[i] = new JButton("+");
         else if(i == 7) num[i] = new JButton("-");
         else if(i == 11) num[i] = new JButton("*");
         else if(x < 10){
            num[i] = new JButton(String.valueOf(x+1));   
            x++;
         }
         if(i == 12) num[i] = new JButton(String.valueOf(0));
         if(i == 13) num[i] = new JButton(".");
         if(i == 14) num[i] = new JButton("Clean");
         if(i == 15) num[i] = new JButton("/");
         if(i == 16) num[i] = new JButton("=");
         panel2.add(num[i]);
         num[i].addActionListener(this);
      }
   
      setSize(400, 500);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setTitle("Calculadora");
      setContentPane(panelP);
      setVisible(true);
   }
   
   public void actionPerformed(ActionEvent e){
      JButton b = (JButton)e.getSource();
      String auxB = b.getText();
      String auxOp = operacion.getText();
      
      if(auxB == "Clean") clean();
      else{
         if(verificar(auxB, auxOp)){
            if(auxB == "=") calcular(auxOp);
            else {
               if(auxB == "."){
                  if(!auxOp.contains(".")){
                     operacion.setText(auxOp + auxB);
                  }
               } else operacion.setText(auxOp + auxB);
            }
         }
      }
   }
   
   private void calcular(String op){
      JerarquiaV1 opera = new JerarquiaV1();
      res.setText(String.valueOf(opera.Descomponer(op)));
      
   }
   
   private boolean verificar(String auxB, String auxOp){
      if(auxB == "+" || auxB == "-" || auxB == "*" || auxB == "/" || auxB == "="){
         char y = auxOp.charAt(auxOp.length()-1);
         if(y == '+' || y == '-' || y == '*' || y == '/' || y == '.') return false;
      }
      return true;
   } 
   
   private void clean(){
      operacion.setText("");
      res.setText("0");
   }
}