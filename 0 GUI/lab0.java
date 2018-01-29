import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.applet.*;

public  class divset extends  Applet implements ActionListener{
int a;
int c;
int s;
Label  u1,u2,u3,u4,u5,u6,u8;
Label fruit,inventory,user;
Label i1,i2,q1,q2;
Label l1;
Label l2;
Label l3;
Label l4;
Label l5;
Label l6;
TextField t1,t2,t3,t4,t5,t8;
TextField ut1,ut2,ut3,ut4,ut5,ut8;
Button but;

public void init(){  
	
  BoxLayout mainLayout = new BoxLayout(this, BoxLayout.Y_AXIS); 
     
  Panel title =new Panel();
  Panel t=new Panel();
  Panel p=new Panel();
  Panel p1=new Panel();
  Panel p2=new Panel();
  Panel p3=new Panel();
  Panel p4=new Panel();
  Panel p5=new Panel();
  Panel result=new Panel();
  
  fruit=new Label("IIITD Fruit Stall");
  
  inventory=new Label("Inventory");
  user=new Label("User");
 
  i1=new Label("Item");
  q1=new Label("Quantity");
  i2=new Label("Item");
  q2=new Label("Quantity");
  
  l1=new Label("Apple");
  t1=new TextField(5);
  t1.setText("10");
  u1=new Label("Apple");
  ut1=new TextField(5);
  ut1.setText("");
 
  l2=new Label("Mango");
  t2=new TextField(5);
  t2.setText("6");
  u2=new Label("Mango");
  ut2=new TextField(5);
  ut2.setText("");
 
  l3=new Label("Orange");
  t3=new TextField(5);
  t3.setText("8");
  u3=new Label("Orange");
  ut3=new TextField(5);
  ut3.setText("");
  
  l4=new Label("Pineapple");
  t4=new TextField(5);
  t4.setText("4");
  u4=new Label("Pineapple");
  ut4=new TextField(5);
  ut4.setText("");

  l5=new Label("Banana");
  t5=new TextField(5);
  t5.setText("2");
  u5=new Label("Banana");
  ut5=new TextField(5);
  ut5.setText("");
    
  l6=new Label("Total");
  t8=new TextField(5); 
  t8.setText("");
  u8=new Label("Total");
  ut8=new TextField(5); 
  t8.setText("");
  
  super.setLayout(mainLayout);
  
  but=new Button("Submit");
  add(but); 
  but.addActionListener(this);
   
  title.add(fruit);
  t.add(inventory);
  t.add(user);
  
  p.add(i1);
  p.add(q1);
  p.add(i2);
  p.add(q2);
   
  p1.add(l1);
  p1.add(t1);
  t1.addActionListener(this);
  p1.add(u1);
  p1.add(ut1);
  ut1.addActionListener(this);
   
  p2.add(l2);
  p2.add(t2);
  t2.addActionListener(this);
  p2.add(u2);
  p2.add(ut2);
  ut2.addActionListener(this);
  
  p3.add(l3);
  p3.add(t3);
  t3.addActionListener(this);
  p3.add(u3);
  p3.add(ut3);
  ut3.addActionListener(this);
  
  p4.add(l4);
  p4.add(t4);
  t4.addActionListener(this);
  p4.add(u4);
  p4.add(ut4);
  ut4.addActionListener(this);
  
  p5.add(l5);
  p5.add(t5);
  t5.addActionListener(this);
  p5.add(u5);
  p5.add(ut5);
  ut5.addActionListener(this);
   
  result.add(l6);
  result.add(t8);
  t8.addActionListener(this);
  result.add(u8);
  result.add(ut8);
  ut8.addActionListener(this);
  
  super.add(title);
  super.add(t);
  super.add(p);
  super.add(p1);
  super.add(p2);
  super.add(p3);
  super.add(p4);
  super.add(p5);
  super.add(result);
  
  }
  
  public void actionPerformed (ActionEvent e){
	  if (but==e.getSource()){
		  a=(Integer.parseInt(t2.getText())+Integer.parseInt(t4.getText())+Integer.parseInt(t5.getText())+ Integer.parseInt(t3.getText())+Integer.parseInt(t1.getText()));
    	  t8.setText(""+a);
    	  
		  {  
			 if (Integer.parseInt(t3.getText()) <= Integer.parseInt(ut3.getText()))
            	  ut3.setText("0");
             if (Integer.parseInt(ut4.getText()) > Integer.parseInt(t4.getText()))
            	  ut4.setText("0");
             if (Integer.parseInt(ut1.getText()) > Integer.parseInt(t1.getText()))
            	  ut1.setText("0");
             if (Integer.parseInt(t5.getText()) <= Integer.parseInt(ut5.getText()))
            	 ut5.setText("0");
             if (Integer.parseInt(ut2.getText()) > Integer.parseInt(t2.getText()))
            	 ut2.setText("0");
          c=Integer.parseInt(ut1.getText()) +Integer.parseInt(ut2.getText())+ Integer.parseInt(ut3.getText()) + Integer.parseInt(ut4.getText())+Integer.parseInt(ut5.getText());
          ut8.setText(""+c);
		  }
       	  
	 }
       s=(a-c);
       t8.setText(""+s);
       t5.setText(Integer.parseInt(t5.getText())-Integer.parseInt(ut5.getText())+"");
       t4.setText(Integer.parseInt(t4.getText())-Integer.parseInt(ut4.getText())+"");
       t3.setText(Integer.parseInt(t3.getText())-Integer.parseInt(ut3.getText())+"");
       t2.setText(Integer.parseInt(t2.getText())-Integer.parseInt(ut2.getText())+"");
       t1.setText(Integer.parseInt(t1.getText())-Integer.parseInt(ut1.getText())+"");
       
    }  


}