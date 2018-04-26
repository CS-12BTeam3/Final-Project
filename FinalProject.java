/* 4 widgets: JOptionPane, Jslider (how satisfied are you and average that), JRadioButton (lbs or kg), Color Chooser
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

/**
 * Welcome to my PA! This is a gui with 2 widgets
 *it as a calculator for weightlifters to determine how many lbs they lifted that day and stores the results in a hashmap that you can use to look up a person. It also contains a color chooser to change the background of the calculator, a JSlider to indicate how satisfied you are, and a converter from pounds to kilograms and vice-versa.
 * it also has an option pane that lists the directions for how to use the app.
 * 
 */
public class FinalProject extends JPanel {
	public static Map<String,String> weights = new HashMap<String,String>();

    ArrayList<Point> points = new ArrayList<Point>();
		int lastx=0;
		int lasty=0;

    static double [] satisfaction = new double[2];


      public static void main(String[] args) throws FileNotFoundException{
        JFrame window = new JFrame("PA05");
    		JPanel content = new JPanel();
    		content.setLayout(new BorderLayout());
    		JLabel button1 = new JLabel("Hello there weightlifter! Use this app to calculate the total amount of weight you lifted today you buff beast!");
        JLabel label1 = new JLabel("Please adjust the slider to indicate how satisfied you are with this app, then hit save!");



    		JLabel button2 = new JLabel("# Reps per set");
        JLabel button12 = new JLabel("Enter your name:");
    		JTextField textField4 = new JTextField("Kyle");


    		JLabel button4 = new JLabel("# Sets completed");


    		JLabel button6 = new JLabel("Weight per rep (include the bar!)");
    		JLabel button7 = new JLabel("1000 lbs. wow!");
    		JOptionPane button13 = new JOptionPane();
    	  button13.showMessageDialog(content,"Hi! Welcome to team 4’s final project. \nThis is a weightlifting calculator and tracker.\nYou enter your name and the amount of weight you lifted \n(we will calculate it based on sets, reps, and weight per rep),\nand it stores and continuously adds your weight over time!!\nYou can also change the background color of the center\nusing the Save Button.");



        Scanner weightsFile = new Scanner(new File("weights.txt"));
          while(weightsFile.hasNextLine()) {
          	String name = weightsFile.next();
          	String weight = weightsFile.next();
          	weights.put(name, weight);
        	}




    		JButton button9 = new JButton("<html><h1>Add weight<html><h1>");
    		JButton button10 = new JButton("<html><h1>Lookup<html><h1>");

    		JTextField textField1 = new JTextField("2");
    		JTextField textField2 = new JTextField("10");
    		JTextField textField3 = new JTextField("50");


    		JLabel title = new JLabel("<html><h1>Welcome to team 4’s final project! :)<html><h1>");

    		JLabel name = new JLabel("Your name:");

    		JTextField enter = new JTextField("...enter your name in this textfield...");



    		JPanel top = new JPanel();
    		top.setLayout(new FlowLayout());

    		top.add(title);

    		content.add(top, BorderLayout.PAGE_START);

    		JPanel center = new JPanel();
    		center.setLayout(new GridLayout(0,2));
    		center.add(button12);
    		center.add(textField4);
    		center.add(button2);
    		center.add(textField1);
    		center.add(button4);
    		center.add(textField2);
    		center.add(button6);
    		center.add(textField3);
    		center.setBackground(new Color(255,255,0));
    		center.add(button13);
    		JRadioButton lb = new JRadioButton("Pounds", true);
    		JRadioButton kg = new JRadioButton("Kilograms", false);
    		ButtonGroup measurement = new ButtonGroup();
    		measurement.add(lb);
    		measurement.add(kg);
    		JPanel radioButtons = new JPanel();
    		radioButtons.setLayout(new GridLayout(0,2));
    		radioButtons.add(lb);
    		radioButtons.add(kg);
    		center.add(radioButtons);


  		  content.add(center, BorderLayout.CENTER);
        JPanel left = new JPanel();
      	left.setLayout(new GridLayout(6,1));
      	left.add(button1);
        left.add(label1);
        JSlider slider = new JSlider(JSlider.HORIZONTAL,0, 10, 5);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
      	left.add(slider);
        JLabel label2 = new JLabel("Average satisfaction: "+satisfaction[1]);
        left.add(label2);
      	JColorChooser tcc = new JColorChooser();
      	left.add(tcc);
        JButton save = new JButton("Save");
        left.add(save);
  	   	content.add(left,BorderLayout.LINE_START);





    		JPanel right = new JPanel();
    		right.setLayout(new GridLayout(0,1));
    		right.add(button9);
    		right.add(button10);
    		right.add(button7);
    		content.add(right, BorderLayout.LINE_END);
    		JPanel bottom = new JPanel();
    		bottom.setLayout(new GridLayout(0,2));
  		  content.add(bottom, BorderLayout.PAGE_END);




  		    button9.addActionListener(new ActionListener(){
        		public void actionPerformed(ActionEvent event){
          		String name = textField4.getText();
          		String t1 = textField1.getText();
          		String t2 = textField2.getText();
          		String t3 = button4.getText();
          		String t4 = textField3.getText();
          		Double x = Double.parseDouble(t1);
          		Double y = Double.parseDouble(t2);
          		Double w = Double.parseDouble(t4);
          		Double z = 0.0;
          		z = (w*x*y);
          		 if(kg.isSelected()){
          			z*=2.2;
          		  }
          		  if (weights.get(name) != null) {
          			 double z2 = Double.parseDouble(weights.get(name));
          			 z = z + z2;
          		  }
          		String stringz = "" + z;

          		weights.put(name,stringz);
              weights.put(name,stringz);
          	   try {
              		PrintWriter writer = new PrintWriter(new FileOutputStream(new File("weights.txt"), true));
              		writer.print("\n" + name + " " + z);
              		writer.close();
               }  catch(Exception e){
          		      System.out.println("Problem writing to file: " +e);
          	      }

       			      if(kg.isSelected()){
       				       button7.setText("" + (z/2.2) + " kgs. Wow!");
       			      }
                  else{
          			  button7.setText(" " + z + " lbs. Wow!");
          		    }

        		  }

      	   });
      	button10.addActionListener(new ActionListener(){
        		public void actionPerformed(ActionEvent event){
          		String name = textField4.getText();
          		String lifted = weights.get(name);
          		if(kg.isSelected()){
          			double total = Double.parseDouble(lifted);
          			total/=2.2;
          			lifted = ""+total;
          		}
          		if(lifted == null){
          			lifted = "Hasn't lifted yet. Schmuck.";
        			}else if(kg.isSelected()){
        				lifted += " kgs, wow!";
        			}else{
        				lifted += " lbs, wow!";
        			}
        			button7.setText(lifted);
        			textField1.setText("");
        			textField2.setText("");
        			textField3.setText("");
        		}
      	});

save.addActionListener(new ActionListener()
{
      		public void actionPerformed(ActionEvent event)
{
Color newColor = tcc.getColor();
            center.setBackground(newColor);
satisfaction[0]++;            satisfaction[1]=(((satisfaction[1]*(satisfaction[0]-1))+(int)slider.getValue())/satisfaction[0]);
label2.setText("Average satisfaction: "+satisfaction[1]);




      		}
    	});





		window.setContentPane(content);
		window.pack();
		//window.setSize(250,300);
		window.setLocation(100,100);
		window.setVisible(true);

    }
}