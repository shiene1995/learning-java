import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Simple_Calculator extends JFrame {
    private JPanel MainPanel; //I should give name for Jpanel to call in setContentPane
    private JTextField textField;
    private JButton sevenButton;
    private JButton eightButton;
    private JButton nineButton;
    private JButton devideButton;
    private JButton fourButton;
    private JButton fiveButton;
    private JButton sixButton;
    private JButton multiplyButton;
    private JButton oneButton;
    private JButton twoButton;
    private JButton threeButton;
    private JButton substractionButton;
    private JButton zeroButton;
    private JButton decimalButton;
    private JButton equalButton;
    private JButton additionButton;
    private JButton clearButton;
    private JButton loginFormButton;

    String numberContainer1 = "0";
    String operatorsContainer = "0";
    boolean operatorsStatus = false;
    boolean decimal = false;

    public Simple_Calculator(){
        setContentPane(MainPanel); //this is how to call Jpanel
        setTitle("Simple Calculator Apps");
        //setDefaultCloseOperation(EXIT_ON_CLOSE); //WHEN I CLICK CLOSE, THE PROGRAM WILL BE STOP AND NOT RUNNING IN BACKGROUND PROCESSING
        setResizable(false);
        setSize(400,550);
        setLocationRelativeTo(null);
        setVisible(true);
        ImageIcon image = new ImageIcon("img/icon.png");
        setIconImage(image.getImage());

        zeroButton.addActionListener(new ActionListener() { // button 0
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInput();
                textField.setText(textField.getText() + "0");
            }
        });

        oneButton.addActionListener(new ActionListener() { // button 1
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInput();
                textField.setText(textField.getText() + "1");
            }
        });

        twoButton.addActionListener(new ActionListener() { // button 2
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInput();
                textField.setText(textField.getText() + "2");
            }
        });

        threeButton.addActionListener(new ActionListener() { // button 3
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInput();
                textField.setText(textField.getText() + "3");
            }
        });

        fourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInput();
                textField.setText(textField.getText() + "4");
            }
        });

        fiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInput();
                textField.setText(textField.getText() + "5");
            }
        });

        sixButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInput();
                textField.setText(textField.getText() + "6");
            }
        });

        sevenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInput();
                textField.setText(textField.getText() + "7");
            }
        });

        eightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInput();
                textField.setText(textField.getText() + "8");
            }
        });

        nineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInput();
                textField.setText(textField.getText() + "9");
            }
        });
        decimalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!decimal && !textField.equals("0")){
                textField.setText(textField.getText() + ".");
                decimal = true;
                }
                else if (textField.equals("0"))
                {
                    textField.setText("0.");
                    decimal = true;
                }

            }
        });

        loginFormButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // this is how to hide form
                //new Login(); // open new form
            }
        });
        clearButton.addActionListener(new ActionListener() { //ERASE ALL
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("0");
                numberContainer1 = "0";
                operatorsContainer = "0";
                decimal = false;
                operatorsStatus = false;
            }
        });
        devideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOperatorsContainer("/");
            }
        });
        multiplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOperatorsContainer("x");
            }
        });
        substractionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOperatorsContainer("-");
            }
        });
        additionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOperatorsContainer("+");
            }
        });

        equalButton.addActionListener(new ActionListener() { //EQUAL BUTTON
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!numberContainer1.equals("0") && !textField.getText().equals("0"))
                {
                    double number1 = Double.parseDouble(numberContainer1);
                    double number2 = Double.parseDouble(textField.getText());
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

                    textField.setText(String.valueOf(df.format(answer)));
                    numberContainer1 = "0";
                    operatorsContainer = "0";
                    decimal = false;
                    operatorsStatus = false;

                }
            }
        });
    }

    public void alert(String data){JOptionPane.showMessageDialog(Simple_Calculator.this, data);} //ALERT LIKE IN JAVA
    public void clearInput(){
        if (textField.getText().equals("0")){textField.setText("");}
        else if (!operatorsContainer.equals("0") && !operatorsStatus){textField.setText("");operatorsStatus = true;}
    }
    public void setOperatorsContainer(String data){
        if (!textField.getText().equals("0") && numberContainer1.equals("0")) {
            operatorsContainer = data;
            numberContainer1 = textField.getText();
            //textField.setText("0");
            decimal = false;
        }
        else
        {
            equalButton.doClick();
            operatorsContainer = data;
            numberContainer1 = textField.getText();
            decimal = false;
        }
    }
}
