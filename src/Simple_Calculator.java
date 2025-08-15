import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Simple_Calculator extends JFrame {
    private JPanel MainPanel; //I should give name for Jpanel to call in setContentPane
    private JTextField textField1;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton button4;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton xButton;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton button5;
    private JButton a0Button;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton cButton;
    private JButton loginFormButton;

    String numberContainer1 = "0";
    String operatorsContainer = "0";
    boolean decimal = false;


    public Simple_Calculator(){
        setContentPane(MainPanel); //this is how to call Jpanel
        setTitle("Simple Calculator Apps");
        setDefaultCloseOperation(EXIT_ON_CLOSE); //WHEN I CLICK CLOSE, THE PROGRAM WILL BE STOP AND NOT RUNNING IN BACKGROUND PROCESSING
        setSize(400,550);
        setLocationRelativeTo(null);
        setVisible(true);

        a0Button.addActionListener(new ActionListener() { // button 0
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().equals("0") || !operatorsContainer.isEmpty() && decimal == false){textField1.setText("");}
                textField1.setText(textField1.getText() + "0");
            }
        });

        a1Button.addActionListener(new ActionListener() { // button 1
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().equals("0") || !operatorsContainer.isEmpty() && decimal == false){textField1.setText("");}
                textField1.setText(textField1.getText() + "1");
            }
        });

        a2Button.addActionListener(new ActionListener() { // button 2
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().equals("0") || !operatorsContainer.isEmpty() && decimal == false){textField1.setText("");}
                textField1.setText(textField1.getText() + "2");
            }
        });

        a3Button.addActionListener(new ActionListener() { // button 3
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().equals("0") || !operatorsContainer.isEmpty() && decimal == false){textField1.setText("");}
                textField1.setText(textField1.getText() + "3");
            }
        });

        a4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().equals("0") || !operatorsContainer.isEmpty() && decimal == false){textField1.setText("");}
                textField1.setText(textField1.getText() + "4");
            }
        });

        a5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().equals("0") || !operatorsContainer.isEmpty() && decimal == false){textField1.setText("");}
                textField1.setText(textField1.getText() + "5");
            }
        });

        a6Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().equals("0") || !operatorsContainer.isEmpty() && decimal == false){textField1.setText("");}
                textField1.setText(textField1.getText() + "6");
            }
        });

        a7Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().equals("0") || !operatorsContainer.isEmpty() && decimal == false){textField1.setText("");}
                textField1.setText(textField1.getText() + "7");
            }
        });

        a8Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().equals("0") || !operatorsContainer.isEmpty() && decimal == false){textField1.setText("");}
                textField1.setText(textField1.getText() + "8");
            }
        });

        a9Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().equals("0") || !operatorsContainer.isEmpty() && decimal == false){textField1.setText("");}
                textField1.setText(textField1.getText() + "9");
            }
        });
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!decimal && numberContainer1.isEmpty() || !numberContainer1.equals(textField1.getText())){
                textField1.setText(textField1.getText() + ".");
                decimal = true;
                }
                else
                {
                    textField1.setText("0.");
                    decimal = true;
                }
            }
        });

        loginFormButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // this is how to hide form
                new login(); // open new form
            }
        });
        cButton.addActionListener(new ActionListener() { //ERASE ALL
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("0");
                numberContainer1 = "0";
                operatorsContainer = "";
                decimal = false;
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField1.getText().equals("0") && numberContainer1.equals("0")) {
                    operatorsContainer = "/";
                    numberContainer1 = textField1.getText();
                    textField1.setText("0");
                    decimal = false;
                }
                else
                {
                    button8.doClick();
                    operatorsContainer = "+";
                    numberContainer1 = textField1.getText();
                    decimal = false;
                }
            }
        });
        xButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField1.getText().equals("0") && numberContainer1.equals("0")) {
                    operatorsContainer = "x";
                    numberContainer1 = textField1.getText();
                    textField1.setText("0");
                    decimal = false;
                }
                else
                {
                    button8.doClick();
                    operatorsContainer = "+";
                    numberContainer1 = textField1.getText();
                    decimal = false;
                }
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField1.getText().equals("0") && numberContainer1.equals("0")) {
                    operatorsContainer = "-";
                    numberContainer1 = textField1.getText();
                    textField1.setText("0");
                    decimal = false;
                }
                else
                {
                    button8.doClick();
                    operatorsContainer = "+";
                    numberContainer1 = textField1.getText();
                    decimal = false;
                }
            }
        });
        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField1.getText().equals("0") && numberContainer1.equals("0")) {
                    operatorsContainer = "+";
                    numberContainer1 = textField1.getText();
                    textField1.setText("0");
                    decimal = false;
                }
                else
                {
                    button8.doClick();
                    operatorsContainer = "+";
                    numberContainer1 = textField1.getText();
                    decimal = false;
                }
            }
        });

        button8.addActionListener(new ActionListener() { //EQUAL BUTTON
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!numberContainer1.equals("0") && !textField1.getText().equals("0"))
                {
                    double number1 = Double.parseDouble(numberContainer1);
                    double number2 = Double.parseDouble(textField1.getText());
                    double answer = 0;

                    // convert 4.0 to 4
                    // and 4 to 4
                    DecimalFormat df = new DecimalFormat("0.#");

                    answer = switch (operatorsContainer) {
                        case "/" -> number1 / number2;
                        case "x" -> number1 * number2;
                        case "-" -> number1 - number2;
                        case "+" -> number1 + number2;
                        default -> answer;
                    };

                    textField1.setText(String.valueOf(df.format(answer)));
                    numberContainer1 = "0";
                    operatorsContainer = "";
                    decimal = false;

                }
            }
        });
    }

    public void alert(String data){JOptionPane.showMessageDialog(Simple_Calculator.this, data);} //ALERT LIKE IN JAVA
}
