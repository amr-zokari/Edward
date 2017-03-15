
package chatbot;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.regex.*;
/**
 *
 * @author amrzokari
 */
public class ChatBot extends JFrame {

    private JTextArea convo = new JTextArea();
    private JTextField user = new JTextField();
    
    //use this to  
    private int control = 0;  // 0: system, 1: user
    
    
    public static void main(String[] args) {
        new ChatBot();
    }
    
    
    public ChatBot(){
        
        
        this.setTitle("Chat Bot");
        this.setSize(500, 580);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        
        convo.setSize(480, 480);
        convo.setLocation(10,10);
        convo.setEditable(false);
        this.add(convo);
        
        user.setSize(486, 50);
        user.setLocation(6, 500);
        this.add(user);
        
        
        //diplay the welcome message the moment the program starts
        welcomeMessage();
        
        //wait for a user's action and procced accordingly
        user.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
               if (control == 1) {
                convo.append("You: " + user.getText() + "\n");
                handleUserInput(user.getText());
                control = 0;
               }
               else 
                control = 1;
            }
        });
            
    }
    
    
    public  void handleUserInput(String str){
                
        str = str.toLowerCase();
               
                    
        String botInfoRegex = ".*\\b(who made you)|(who are you)|(what are you)|(what is your name)|(tell me about yourself)|(tell me about urself)|(who created you)|(why are you here)";   
        String welcomeAndHelpRegex = ".*\\b(help)|(can't find)|(welcome message)\\b.*";       
        String greetingRegex1 = ".*\\b(hello)|(hi)|(hey)|(heyy)\\b.*";
        String greetingRegex2 = ".*\\b(how are you)|(how you doing)|(how is it going)|(how is your day)|(whats up)\\b.*";
        String timeAndDateRegex = ".*\\b(time)|(clock)|(oclock)|(date)|(month)\\b.*";
        String calculatorRegex = ".*\\b(calculator)|(calculate)|(add)|(subtract)|(multiply)|(divide)\\b.*";
        String openWebRegex = ".*\\b(open)|(search)|(what is)|(who is)|(where is)|(what are)|(who are)|(where are)\\b.*";
                
        Pattern greetingPattern1 = Pattern.compile(greetingRegex1);
        Matcher m1 = greetingPattern1.matcher(str);
        
        Pattern greetingPattern2 = Pattern.compile(greetingRegex2);
        Matcher m2 = greetingPattern2.matcher(str);
                
        Pattern p3 = Pattern.compile(calculatorRegex);
        Matcher m3 = p3.matcher(str);
                
        Pattern welcomeAndHelpPattern = Pattern.compile(welcomeAndHelpRegex);
        Matcher m4 = welcomeAndHelpPattern.matcher(str);
                
        Pattern botInfoPattern = Pattern.compile(botInfoRegex);
        Matcher m5 = botInfoPattern.matcher(str);
                
        Pattern openWebPatter = Pattern.compile(openWebRegex);
        Matcher m6 = openWebPatter.matcher(str);
        
        Pattern p7 = Pattern.compile(timeAndDateRegex);
        Matcher m7 = p7.matcher(str);
                

        boolean greeting1 = m1.find();
        boolean greeting2 = m2.find();
        boolean calculator = m3.find();
        boolean welcomeAndHelp = m4.find();
        boolean botInfo = m5.find();
        boolean openWeb = m6.find();
        boolean timeAndDate = m7.find();
        

        if (greeting1)
            greetingMethod(1);
        else if (greeting2)
            greetingMethod(2);    
        else if (timeAndDate)
            timeAndDateMethod();
        else if (calculator)
            calculatorMethod();
        else if (welcomeAndHelp)
            welcomeAndHelpMethod();
        else if (botInfo)
            botInfoMethod();
        else if (openWeb)
            openWebMethod(user.getText());
        else 
            errorMethod();
        
        
        user.setText("");
     
    }
    
    public void botInfoMethod(){
        convo.append("Bot: I am Edward, a chatbot, created by Amr Zokari.\nBot: And I am here to help you!\n");
    }
    
    public void errorMethod(){
        String[] errorMessages = {"I dont quite understand what you mean.", "Excuse me, do you mind rephrasing that", "Please elaborate",
            "I dont understand what you mean, please elaborate"};
        int arrSize = errorMessages.length;
        int num = (int)(Math.random() * arrSize);
        
        convo.append("Bot: " + errorMessages[num] + "\n");   
    }
    
    public void welcomeMessage(){
        GregorianCalendar time = new GregorianCalendar();  
        int hour = time.get(Calendar.HOUR_OF_DAY);
        int min = time.get(Calendar.MINUTE);
        int day = time.get(Calendar.DAY_OF_MONTH);
        int month = time.get(Calendar.MONTH) + 1;
        int year = time.get(Calendar.YEAR);
          
        String minStr = min + "";
            if (min == 0 || min == 1 || min == 2 || min == 3 || min == 4 || min == 5 || min == 6 || min == 7 || min == 8 || min == 9 )
              minStr = "0" + min; 

        convo.append("Current time is \t" + hour + ":" + minStr);
        convo.append("\nToday's date is \t" + month + "/" + day + "/" + year);

        if (hour < 12) 
            convo.append("\n\nBot: Good Morning!");
        else if (hour < 17 && !(hour == 12))
            convo.append("\n\nBot: Good Afternoon!");
        else 
            convo.append("\n\nBot: Good Evening!");
          
        convo.append("\nBot: I am Edward, what can I help you with today\n");

    }
    
    public void welcomeAndHelpMethod(){
        JOptionPane.showMessageDialog(null, "Hello, \n\nMy name is Edward. And I am chatBot created by Amr Zokari.\nThe following is a list of shortcuts commands that you can use to get my help:\n\n 1) help: to display the help message.\n 2) time: to display the current time and date.\n 3) calculate: to help you calculate numbers.\n 4) search: to help you search the web.\n\n\nNote: I am still under development, hence I am still not fully functional.");
        
    }
    
    public void greetingMethod(int type){
       
        if (type == 1){
            String[] greetingResponse = {"Hey there!", "Hello", "Hi", "Hey"};
        
            int arrSize = greetingResponse.length;
            int num = (int) (Math.random() * arrSize);
            convo.append("Bot: " + greetingResponse[num] + "\n");
        }
        
        if (type == 2){
            String[] greetingResponse = {"I am really good, how are you?", "I am quite good, yourself?", "Nothing much, yourself?", "I am doing great, thank you, how are you?"};
        
            int arrSize = greetingResponse.length;
            int num = (int) (Math.random() * arrSize);
            convo.append("Bot: " + greetingResponse[num] + "\n");
            
            
            user.addActionListener(new ActionListener(){
                
                public void actionPerformed(ActionEvent e){
                    
                    String str = user.getText().toLowerCase();
                    String responseRegex = ".*\\b(great)|(good)|(fine)|(alright)|(not much)|(fantastic)\\b.*";
            
                    Pattern responsePattern = Pattern.compile(responseRegex);
                    Matcher m = responsePattern.matcher(str);
                    
                    convo.append("You: " + user.getText() + "\n");
                    
                    if (m.find()){
                        control = 0;
                        
                        convo.append("Bot: Awesome!\n");
                        user.setText("");
                        user.removeActionListener(this);
                    }
                    else {
                       handleUserInput(user.getText());
                        user.setText("");
                        //user.removeActionListener(this); 
                    }
                        
                    
                    
                    
                }
            });
            
        }
        
        
    }
    
    public void calculatorMethod(){
        convo.append("Bot: Enter what you would like to calculate (with a space in between) \n");
        
        user.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
               convo.append("You: " + user.getText() + "\n");
               
               
               String[] expression = user.getText().split(" ");
               int num1 = Integer.parseInt(expression[0]);
               int num2 = Integer.parseInt(expression[2]);
               String result;
               
               if (expression[1].equals("+"))
                   result = "Bot: Result is " + (num1 + num2);
               else if (expression[1].equals("-"))
                   result = "Bot: Result is " + (num1 - num2);
               else if (expression[1].equals("*"))
                   result = "Bot: Result is " + (num1 * num2);
               else if (expression[1].equals("/"))
                   result = "Bot: Result is " + (num1 / num2);
               else
                   result = "Bot: Could not calculate";
              
               
               convo.append(result + "\n");
               control = 0;
               user.setText("");
               user.removeActionListener(this);
              
            }
        });
     
    }
    
    public void timeAndDateMethod(){
        DateFormat dateformat = new SimpleDateFormat("'It is' E MM/dd/yyyy 'at' HH:mm a");
        Date date = new Date();
        convo.append("Bot: " + dateformat.format(date) + "\n");
        user.setText("");
    }
    
    public void openWebMethod(String question){
  
        int reply = JOptionPane.showConfirmDialog(null,  "Would you like me to search the web for \"" + question + "\"?");
        if (reply == JOptionPane.YES_OPTION) {
       
            try {
                question = question.replace(' ', '+');
                String URL = "https://google.com/#q=";
                URL = URL + question;
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(URL));


            } catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage());

            }
            
        }
          
    }    
}
