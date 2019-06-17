package project;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import java.text.DecimalFormat;


public class BMIGUI
{
   private final int WIDTH = 275;
   private final int HEIGHT = 100;
   
   DecimalFormat fmt = new DecimalFormat ("0.00");

   private JFrame frame;
   private JPanel panel;
   private JLabel heightLabel, weightLabel, BMILabel, resultLabel, infoLabel, S_NoLabel, NameLabel;
   private JTextField height, weight,S_No,Name;
   private JButton calculate,back;

//  Sets up the GUI
   
 public BMIGUI()
   {
      frame = new JFrame ("BMI Calculator");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

      //creates labels for the height and weight textFields 
String str = "<html>Body mass index (BMI) is a measure of body fat based on your weight in relation to your height, and applies to most adult men and women aged 20 and over.<p>For children aged 2 and over, BMI percentile is the best assessment of body fat.<p>BMI does not measure body fat directly.<p>However, research indicates that BMI correlates to direct measures of body fat such as underwater weighing and dual-energy X-ray absorptiometry (DXA),<p>and is considered an inexpensive and easy-to-perform alternative for these<p> Health criterias :<p>If the BMI is below 18.5, then you are underweight !! go and gain health.<p>If the BMI is between 19 and 25, then your health is normal.<p>If the BMI is above 25, then you have to worry darling, you are overweight/obese..XD<p></html>";

      infoLabel = new JLabel (str,SwingConstants.CENTER);
	 //Displays and prints normal text and information regarding BMI.

      S_NoLabel = new JLabel ("<html>Enter your serial no. :<p></html>",SwingConstants.CENTER);

      //Creates a label "Enter your serial no. :"

      NameLabel = new JLabel ("<html>Enter your full name :<p></html>",SwingConstants.CENTER);

      //Creates a label "Enter your name :"

      heightLabel = new JLabel ("<html>Enter your height in meters: <p></html>",SwingConstants.CENTER);

      //Creates a label "Enter your height in metres :"

      weightLabel = new JLabel ("<html>Enter your weight in kgs: <p></html>",SwingConstants.CENTER);

      //creates a "Your BMI is" label

      BMILabel = new JLabel ("<html>Your BMI is <p></html>",SwingConstants.CENTER);

      //creates a result label to hold the BMI value

      resultLabel = new JLabel ("");

      //creates a JTextField to hold the person's Serial no

      S_No = new JTextField (5);

      //creates a JTextField to hold the person's full name

      Name = new JTextField (25);

      //creates a JTextField to hold the person's height in inches

      height = new JTextField (5);

      //creates a JTextField to hold the person's weight in pounds

      weight = new JTextField (5);
    
      //creates a button to press to calculate BMI

      calculate = new JButton ("Calculate BMI");
      back = new JButton ("Back");

      //creates a BMIListener and make it listen for the button to be pressed

      calculate.addActionListener (new BMIListener());

      //set up the JPanel to go on the JFrame 

      panel = new JPanel();
      panel.setPreferredSize (new Dimension(WIDTH, HEIGHT));
      panel.setBackground (Color.white);

      //add the height label and height textField to the panel and sets a background label
//panel.add (back);
      panel.add (infoLabel);

      //adds the infoLabel to the panel

      //add the serial no label and its textField to the panel

      panel.add (S_NoLabel);
      panel.add (S_No);

      //add the name label and its textField to the panel

      panel.add (NameLabel);
      panel.add (Name);


      panel.add (heightLabel);
      panel.add (height);

      //add the weight label and weight textField to the panel

      panel.add (weightLabel);
      panel.add (weight);

      //add the button to the panel

      panel.add (calculate);

      //add the BMI label to the panel

      panel.add (BMILabel);

      //add the label that holds the result to the panel

      panel.add (resultLabel);
      

      //add the panel to the frame 

      frame.getContentPane().add(panel);
    }

       //Put result in result label.  Use Double.toString to convert double to string.     
   //  Displays the primary application frame.

   public void display()
   {
      frame.pack();
      frame.setVisible(true);
      frame.setSize(900,500);
      S_NoLabel.setBounds(200,400,60,55);
      NameLabel.setBounds(200,400,60,55);
      heightLabel.setBounds(200,400,50,55);
      weightLabel.setBounds(300,460,70,55);
      height.setBounds(250,400,50,55);
      weight.setBounds(280,460,70,55);
   }

   //  Represents an action listener for the calculate button.

   private class BMIListener implements ActionListener
   {
      //  Compute the BMI when the button is pressed

      public void actionPerformed (ActionEvent event)
      {
         String heightText, weightText,S_NoText,NameText;
         double heightVal, weightVal, bmi;

     //get the text from the height, weight and SerialNp textFields

     heightText = height.getText();
     weightText = weight.getText();
     S_NoText = S_No.getText();
     NameText = Name.getText();

     //Use Integer.parseInt to convert the text to integer values

     heightVal = Double.parseDouble(heightText);
     weightVal = Double.parseDouble(weightText);

     //Calculate the bmi = 703 * weight in pounds/(height in inches)*(height in inches)

     bmi = weightVal / (heightVal*heightVal);

     
     resultLabel.setText( fmt.format(bmi));
            try {
                Statement stmt = Start.conn.createStatement();
                stmt.execute("use project;");
                String str = "insert into bmidata values("+S_No.getText()+",'"+Name.getText()+"',"+height.getText()+","+weight.getText()+","+bmi+");";
                stmt.execute(str);
            } catch (SQLException ex) {
                System.out.print(ex);
            }

      }
   }
}