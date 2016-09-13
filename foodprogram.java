import javax.swing.*;
import java.awt.*; 
import java.awt.event. *; //adding event action listener
import java.text.*;
import java.util.Date; //importing date utility
import java.io.*; 
import java.util.Scanner;

public class foodprogram extends JFrame implements ActionListener{ 
  //Creating GUI components
  String[] foodnamearray = new String[100], unitarray = new String[100], amountarray = new String[100], yeardatearray = new String[100] ;
  double[] pricearray = new double[100];
  int[] searcharray = new int[100];
  int foodcount = 0, count3, searchlistsize, holdprice2;
  int index=0;
  boolean state1, state2, searching, datename=false, dateformat=true, datevalid=true, pricevalid=true, amountvalid=true;
  String holdprice, temp1, temp3, temp4, temp5, searchCriteria;
  double holdprice3, temp2;
  
  SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
  Date date = null;
  
  Checkbox Checkpounds;
  Checkbox Checkkilograms;
  
  JButton Button1 = new JButton("Add Food"); //Creating Buttons for the GUI
  JButton Button2= new JButton("Remove Food");
  JButton Button3 = new JButton("Shopping List");
  JButton Button4 = new JButton("Edit Data");
  JButton Button5 = new JButton("Search");
  JButton Button6 = new JButton("Sort by Date");
  JButton Button7 = new JButton("Sort by Name");
  JButton Button8 = new JButton("Save");
  JButton Button9 = new JButton("");
  JButton Button10 = new JButton("Done");
  JButton Button11 = new JButton ("Pounds");
  JButton Button12 = new JButton ("Amount");
  JButton Button13 = new JButton ("Kilograms");
  JButton Button14 = new JButton ("Next");
  JButton Button15 = new JButton ("Confirm");
  JButton Button16 = new JButton ("Cancel Search");
  
  JTextField foodname = new JTextField(7);
  JTextField price = new JTextField(7);
  JTextField unit = new JTextField(7);
  JTextField amount = new JTextField(7);
  JTextField yeardate = new JTextField(7);
  JTextField budgetfield = new JTextField(7);
  JTextField searchfield = new JTextField(10);
  
  JLabel foodname2 = new JLabel("Food Name: ", JLabel.LEFT); //Creating the labels for the program
  JLabel price2 = new JLabel("Price:                                                  $", JLabel.LEFT);
  JLabel unit2 = new JLabel("Units: ", JLabel.LEFT);
  JLabel amount2 = new JLabel("Amount: ", JLabel.LEFT);
  JLabel expirydate = new JLabel("Expiry Date (yyyy/mm/dd): ", JLabel.LEFT);
  JLabel budget = new JLabel("What is the Budget?   $",JLabel.LEFT);
  JLabel foodname3 = new JLabel("Food Name: ", JLabel.LEFT); //Creating the labels for the program
  JLabel price3 = new JLabel("Price: $", JLabel.LEFT);
  JLabel unit3 = new JLabel("Units: ", JLabel.LEFT);
  JLabel amount3 = new JLabel("Amount: ", JLabel.LEFT);
  JLabel expirydate3 = new JLabel("Expiry Date (yyyy/mm/dd): ", JLabel.LEFT);
  JLabel budget3 = new JLabel("What is the Budget?   $",JLabel.LEFT);
  //JLabel buying = new JLabel("How m
  
  
  JTabbedPane tab = new JTabbedPane(); //Declare tab
  
  // JTextField titleprogram = new JTextField(7); //Creating TextFields for the user to type their information in
  
  // JLabel titleprogram = new JLabel("Cafeteria Organizer", JLabel.CENTER); //Creating the labels for the program
  
  JPanel pan0= new JPanel();
  JPanel pan1= new JPanel(); // Adding Panels for GUI
  JPanel pan2= new JPanel();
  JPanel pan3= new JPanel();
  JPanel pan4= new JPanel();
  JPanel pan5= new JPanel();
  JPanel pan6= new JPanel();
  JPanel pan7= new JPanel();
  JPanel pan8= new JPanel();
  JPanel pan9= new JPanel();
  JPanel pan10= new JPanel();
  JPanel pan11= new JPanel();
  JPanel pan12= new JPanel();
  JPanel pan13= new JPanel();
  JPanel pan14= new JPanel();
  JPanel pan15= new JPanel();
  JPanel pan16= new JPanel();
  JPanel pan17= new JPanel();
  JPanel pan18= new JPanel();
  JPanel pan19= new JPanel();
  JPanel pan20= new JPanel();
  
  CheckboxGroup check1 = new CheckboxGroup();
  JCheckBox checkBox1 = new JCheckBox("Pounds"); //Add checkboxes
  JCheckBox checkBox2 = new JCheckBox("Kilograms");
  
  DefaultListModel<String> listModel; //Making a new list model for the list in the GUI
  JList<String> listbox; //Creating a new list
  
  JFrame frame1 = new JFrame("FrameDemo"); //frame for the GUI
  
  JDialog dialog =new JDialog(frame1,"Food Details",true); //create a new dialog 
  JDialog dialog2 =new JDialog(frame1,"Editing Food Details",true); //create a new dialog 
  JDialog dialog3 =new JDialog(frame1,"Shopping List",true); //create a new dialog 
  JDialog dialog4 =new JDialog(frame1,"How much to buy?",true); //create a new dialog 
  JDialog dialog5 =new JDialog(frame1,"add food",true); //create a new dialog 
  JDialog dialog6 =new JDialog(frame1,"Search",true); //create a new dialog 
  /*private void addListeners() {
   yeardate.addFocusListener(new FocusListener() {
   
   @Override
   public void focusGained(FocusEvent fe) {
   }
   
   @Override
   public void focusLost(FocusEvent fe) {
   if (yeardate.getText().length() >=1) {
   JOptionPane.showMessageDialog(null, "You entered valid data");
   yeardate.setText("");
   }else {
   JOptionPane.showMessageDialog(null, "You entered invalid data");
   yeardate.grabFocus();//make the textField in focus again
   }
   }
   });
   }
   */
  //Constructor for GUI
  public foodprogram() {
    frame1.add(Button1, BorderLayout.CENTER); //adding a layout for the main frame
    
    
    setLayout(new BorderLayout()); 
    listModel = new DefaultListModel<String>(); 
    listbox = new JList<String> (listModel); //making a new list using the default list model 
    
    listbox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //set the selection mode for the list to be only single selection
    JScrollPane listscroll = new JScrollPane(listbox); // creating a scrolling wheel for the list if a lot of contacts were saved
    listscroll.setPreferredSize(new Dimension(250,300));
    //listbox.setFixedCellHeight(15); // dimensions
    //listbox.setVisibleRowCount(-1);
    pan1.add(listscroll, BorderLayout.CENTER); //adding scroll list to panel 2
    
    listbox.addMouseListener(new MouseAdapter() { //creating a action listener for mouse clicks on the list
      public void mouseClicked(MouseEvent evt) {
        JList<String> listbox = (JList<String>)evt.getSource();
        if (evt.getClickCount() == 2) { //checks if list was doubleclicked on
          int index = listbox.getSelectedIndex(); //finds index of contact
          
          JDialog dialog =new JDialog(frame1,"Food Information",true); //creating new popup dialog for double clicking on a contact
          dialog.setSize(new Dimension(400, 300));//dimensions
          dialog.setModal(true); //stops executing until dialog box is closed
          foodname3.setText("Food Name: "+foodnamearray[index]); //setting correct labels for the information
          price3.setText("Price: $"+pricearray[index]);
          unit3.setText("Units: "+unitarray[index]);
          amount3.setText("Amount: "+amountarray[index]);
          expirydate3.setText("Expiry Date: "+yeardatearray[index]);
          
          pan17.add(foodname3);
          pan17.add(price3);
          
          pan17.add(unit3);
          pan17.add(amount3);
          pan17.add(expirydate3);
          dialog.add(pan17);
          dialog.setModal(true);
          dialog.setVisible(true); //display dialog
        }
      }
    });
    setTitle("Food****"); //Title for the GUI address book program
    setSize(360, 495);//dimensions
    setResizable(true); //doesn't let user resize the program
    
    BoxLayout layout2=new BoxLayout(pan2,BoxLayout.Y_AXIS);//layouts
    BoxLayout layout4=new BoxLayout(pan3,BoxLayout.Y_AXIS);
    BoxLayout layout6=new BoxLayout(pan1,BoxLayout.Y_AXIS);
    BoxLayout layout7=new BoxLayout(pan9,BoxLayout.X_AXIS);
    BoxLayout layout8=new BoxLayout(pan10,BoxLayout.Y_AXIS);
    BoxLayout layout9=new BoxLayout(pan14,BoxLayout.Y_AXIS);
    BoxLayout layout10=new BoxLayout(pan17,BoxLayout.Y_AXIS);
    BoxLayout layout11=new BoxLayout(pan12,BoxLayout.Y_AXIS);
    FlowLayout layout42=new FlowLayout();
    GridLayout layout3=new GridLayout(0,2);
    GridLayout layout34 = new GridLayout(1,3);
    GridLayout layout24 = new GridLayout(3,1);
    
    
    setLayout(layout42); //frame layout
    pan1.setLayout(layout6); //setting layouts for each of the panels
    pan2.setLayout(layout3);
    pan3.setLayout(layout4);
    pan8.setLayout(layout3);
    pan9.setLayout(layout7);
    pan10.setLayout(layout8);
    pan14.setLayout(layout42);
    pan13.setLayout(layout42);
    pan17.setLayout(layout10);
    pan12.setLayout(layout11);
    pan19.setLayout(layout42);
    pan0.add(Box.createRigidArea(new Dimension(0, 50)));
    pan0.setPreferredSize(new Dimension(250,300));
    
    
    // pan1.add(titleprogram);
    pan2.add(Button1);
    Button1.addActionListener(this);
    pan2.add(Button2);
    Button2.addActionListener(this);
    pan2.add(Button3);
    Button3.addActionListener(this);
    pan2.add(Button4);
    Button4.addActionListener(this);
    pan2.add(Button5);
    Button5.addActionListener(this);
    pan2.add(Button6);
    Button6.addActionListener(this);
    pan3.add(Button7);
    Button7.addActionListener(this);
    Button10.addActionListener(this);
    Button14.addActionListener(this);
    
    pan8.add(foodname2);
    pan8.add(foodname);
    pan8.add(price2);
    pan8.add(price);
    
    
    
    pan8.add(amount2);
    pan8.add(amount);
    pan8.add(expirydate);
    pan8.add(yeardate);
    
    pan9.add(unit2);
    pan11.add(Button10);
    pan18.add(Button8);
    Button8.addActionListener(this);
    Checkpounds =new Checkbox("Pounds", check1, false);
    pan9.add(Checkpounds);
    Checkkilograms = new Checkbox ("Kilograms", check1,false);
    pan9.add(Checkkilograms);
    
    pan14.add(budget);
    pan14.add(budgetfield);
    pan15.add(Button14);
    
    pan19.add(searchfield);
    pan19.add(Button15);
    Button15.addActionListener(this);
    
    add(pan0);
    pan0.add(pan1);
    pan0.add(pan2);
    //pan2.add(pan3);
    
    add(tab); //add tab to frame
    
    tab.addTab("Food List", pan0);
    tab.setPreferredSize(new Dimension(340,450));
    
    setVisible(true); //display everything
  }
  
  protected JComponent makeTextPanel (String text){ //new text panel
    JPanel panel = new JPanel(false);
    JLabel filler = new JLabel(text);
    filler.setHorizontalAlignment(JLabel.CENTER);
    panel.setLayout(new GridLayout(1,1));
    panel.add(filler);
    return panel;
  }
  
  
  
  //Action listener, runs when a button is pressed
  public void actionPerformed(ActionEvent event) {
    String command = event.getActionCommand();//finds which buttom that was pressed
    
    if (command.equals ("Sort by Date")){
      pan2.remove(Button6);
      pan2.add(pan3);
      pan2.repaint();
      pan2.revalidate();
      datesort();
      datename=true;
    }
    
    if (command.equals ("Sort by Name")){
      pan2.remove(pan3);
      pan2.add(Button6);
      pan2.repaint();
      pan2.revalidate();
      namesort();
      datename=false;
    }
    
    if (command.equals ("Add Food")){
      dialog5.setSize(new Dimension(400, 300)); //dimensions
      dialog5.setModal(true);
      
      foodname.setText("");
      price.setText("");
      amount.setText("");
      unit.setText("");
      yeardate.setText("");
      
      
      dialog5.add(pan10); //add panel 8 on dialog (contact information)
      pan10.add(pan8);
      pan10.add(pan9);
      pan10.add(pan11);
      
      
      
      dialog5.setModal(true);
      dialog5.setVisible(true);
      
    }
    if (command.equals ("Edit Data")){
      index=listbox.getSelectedIndex();
      if (index==-1){
        System.out.println("Selecty a thing");
      }else{
        dialog2.setSize(new Dimension(400, 300)); //dimensions
        dialog2.setModal(true);
        
        dialog2.add(pan12);
        pan12.add(pan8);
        pan12.add(pan9);
        pan12.add(pan18);
        
        foodname.setText(foodnamearray[index]);
        String hold2 = String.valueOf(pricearray[index]);
        price.setText(hold2);
        amount.setText(amountarray[index]);
        yeardate.setText(yeardatearray[index]);
        dialog2.setModal(true);
        dialog2.setVisible(true);
      }
    }
    if (command.equals ("Shopping List")){
      loadmyfile();
      dialog3.setSize(new Dimension(280,140)); //dimensions
      dialog3.setModal(true);
      
      dialog3.add(pan13);
      
      pan13.add(pan14);
      pan13.add(pan15);
      dialog3.setModal(true);
      dialog3.setVisible(true);
    }
    if (command.equals ("Next")){
      
      dialog4.setSize(new Dimension(280,140)); //dimensions
      dialog4.setModal(true);
      
      dialog4.add(pan16);
      
      
      dialog4.setModal(true);
      dialog4.setVisible(true);
    }
    
    if (command.equals ("Done")){
      foodnamearray[foodcount]=("");
      pricearray[foodcount]=(0.0);
      
      foodnamearray[foodcount] = foodname.getText(); //fill array
      holdprice = price.getText();
      
      if (!holdprice.equals("")){
        try{
          holdprice3 = Double.parseDouble(holdprice);
          pricevalid=true;
        }catch(NumberFormatException e){
          pricevalid=false;
        }
        if(pricevalid==true){
          pricearray[foodcount] = roundTwoDecimals(holdprice3);
        }
      }
      
      amountarray[foodcount] = amount.getText();
      
      if (!amountarray[foodcount].equals("")){
        try{
          holdprice2 = Integer.parseInt(amountarray[foodcount]); //check if amount is an integer
          amountvalid=true; //set amountvalid to true if it is an integer
        }catch(NumberFormatException e){
          amountvalid=false; //set amountvalid to false if it isnt an integer
        }
      }
      
      yeardatearray[foodcount] = yeardate.getText();
      if (check1.getSelectedCheckbox() == Checkpounds){
        unitarray[foodcount] = ("Pounds");
      }else if (check1.getSelectedCheckbox() == Checkkilograms){
        unitarray[foodcount] = ("Kilograms");
      }else{
        unitarray[foodcount] = ("");
      }
      
      if (!yeardatearray[foodcount].equals("")){
        try{
          date = df.parse(yeardatearray[foodcount]); //check if date was formatted correctly (yyyy/mm/dd)
          dateformat=true;
        }catch (ParseException e){
          dateformat=false;
        }
        if (dateformat==true){ 
          if (!df.format(date).equals(yeardatearray[foodcount])){ //if date is formatted correctly, check if date is a valid date (month 1-12, day 1-31/30/29)
            datevalid=false;
          }else{
            datevalid=true;
          }
        }
      }
      
      if (!foodnamearray[foodcount].equals("")&&!holdprice.equals("")&&!unitarray[foodcount].equals("")&&!amountarray[foodcount].equals("")&&!yeardatearray[foodcount].equals("")&&dateformat==true&&datevalid==true&&pricevalid==true&&amountvalid==true){
        listModel.addElement(foodnamearray[foodcount]);
        foodcount++;                       //makes it so the next input will be put in the next slot of the array
        dialog5.setVisible(false);
        System.out.println("Saved!");
        
        foodname.setText(null);
        price.setText(null);
        amount.setText(null);
        unit.setText(null);
        yeardate.setText(null);
        if(datename==true){
          datesort();
        }else if (datename==false){
          namesort();
        }
      }else{
        if (foodnamearray[foodcount].equals("")||holdprice.equals("")||unitarray[foodcount].equals("")||amountarray[foodcount].equals("")||yeardatearray[foodcount].equals("")){
          System.out.println("Some fields were not filled out"); //output if some fields were left blank
        }
        if (dateformat==false){
          System.out.println("The date is formatted incorrectly"); //output if date is formatted wrong           
        }else if (datevalid==false){
          System.out.println("The date is not valid"); //output if date is invalid
        }
        if (pricevalid==false){
          System.out.println("The price is not valid"); //output if price is invalid
        }
        
        if(amountvalid==false){
          System.out.println("The amount is not valid"); //output if amount is invalid
        }
      }
    }
    
    if(command.equals ("Remove Food")){ //remove the selected contact from the list
      index=listbox.getSelectedIndex();
      if (index==-1){
        System.out.println("Please select a food");
      }else{
        listModel.removeElementAt(index);
        System.out.println(index);
        for (int i=index; i<foodcount; i++){ //set i to the index of the selected contact and loop until it reaches the list's current size
          foodnamearray[i]=foodnamearray[i+1]; //overwrite current value (deleting it) while also moving everything after the value one spot over
          pricearray[i]=pricearray[i+1];
          unitarray[i]=unitarray[i+1];
          amountarray[i]=amountarray[i+1];
          yeardatearray[i]=yeardatearray[i+1];
        }
        foodcount--; //subtract one from the number of contacts
      }
    }
    if(command.equals ("Save")){
      foodnamearray[index] = foodname.getText(); //fill array
      holdprice = price.getText();
      holdprice3 = Double.parseDouble(holdprice);
      pricearray[index] = roundTwoDecimals(holdprice3);
      amountarray[index] = amount.getText();
      yeardatearray[index] = yeardate.getText();
      if (check1.getSelectedCheckbox() == Checkpounds){
        System.out.println("Pounds");
        unitarray[index] = ("Pounds");
      }else if (check1.getSelectedCheckbox() == Checkkilograms){
        System.out.println("Kilograms");
        unitarray[index] = ("Kilograms");
      }
      if (!foodnamearray[index].equals("")||pricearray[index]!=0.0||!unitarray[index].equals("")||!amountarray[index].equals("")||!yeardatearray[index].equals("")){
        listModel.set(index, foodnamearray[index]);
        dialog2.setVisible(false);
        
        foodname.setText(null);
        price.setText(null);
        amount.setText(null);
        unit.setText(null);
        yeardate.setText(null);
        
        if(datename==true){
          datesort();
        }else if (datename==false){
          namesort();
        }
      } 
    }
    
    if(command.equals ("Search")){
      dialog6.setSize(new Dimension(280,140));
      dialog6.setModal(true);
      dialog6.add(pan19);
      dialog6.setModal(true);
      dialog6.setVisible(true);
    }
    
    if(command.equals("Confirm")){
      searchCriteria=searchfield.getText(); //get what user is searching for
      searching=true;
      System.out.println(searchfield.getText());
      
      for(int i=0;i<foodcount;i++){
        if (foodnamearray[i].equals(searchCriteria)){ //check if the searched name is equal to a name in the contact list
          searcharray[count3]=i; //set search array to i 
          count3++;
        }
      }
      
      listModel.removeAllElements(); //remove all elements then add back only the elements that match the searched term
      for(int i=0;i<count3;i++){
        listModel.addElement(foodnamearray[searcharray[i]]);
      }
      
      searchlistsize=count3; //store the size of the searched list in a variable
      count3=0; //reset count3
      
      dialog6.setVisible(false);
      
      
      Button5.setText("Cancel Search");
      
      
      //repaint();
      revalidate();
    }
    
    if(command.equals("Cancel Search")){
      
      Button5.setText("Search");
      repaint();
      revalidate();
      
      listModel.removeAllElements(); //clear the list
      
      for(int i=0;i<foodcount;i++){
        listModel.addElement(foodnamearray[i]); //add back all the elements
      }
      searching=false; //set the searching variable to 0
    }
    
  }
  public void namesort(){
    String a, b;
    for (int i=0;i<foodcount-1;i++){ 
      for (int k=0;k<foodcount-1;k++){
        a=foodnamearray[k].toLowerCase();
        b=foodnamearray[k+1].toLowerCase();
        if ((a).compareTo(b)>0){ //comparing the foodnames in the array
          temp1=foodnamearray[k];
          foodnamearray[k]=foodnamearray[k+1]; //move the contact position up
          listModel.set(k,foodnamearray[k+1]); //swapping the names on the list in order
          foodnamearray[k+1]=temp1; //swapping the contacts to place them in order
          listModel.set(k+1,temp1); //swapping the names on the list in order
          
          temp2=pricearray[k];//more sorting of information in the array
          pricearray[k]=pricearray[k+1];
          pricearray[k+1]=temp2;
          temp3=amountarray[k];
          amountarray[k]=amountarray[k+1];
          amountarray[k+1]=temp3;
          temp4=unitarray[k];
          unitarray[k]=unitarray[k+1];
          unitarray[k+1]=temp4;
          temp5=yeardatearray[k];
          yeardatearray[k]=yeardatearray[k+1];
          yeardatearray[k+1]=temp5;
        }
      }
    }
  }
  
  public void datesort(){
    String a, b;
    for (int i=0;i<foodcount-1;i++){ 
      for (int k=0;k<foodcount-1;k++){
        a=yeardatearray[k];
        b=yeardatearray[k+1];
        if ((a).compareTo(b)>0){ //comparing the dates in the array
          temp1=foodnamearray[k];
          foodnamearray[k]=foodnamearray[k+1]; //move the contact position up
          listModel.set(k,foodnamearray[k+1]); //swapping the names on the list in order
          foodnamearray[k+1]=temp1; //swapping the contacts to place them in order
          listModel.set(k+1,temp1); //swapping the names on the list in order
          
          temp2=pricearray[k];//more sorting of information in the array
          pricearray[k]=pricearray[k+1];
          pricearray[k+1]=temp2;
          temp3=amountarray[k];
          amountarray[k]=amountarray[k+1];
          amountarray[k+1]=temp3;
          temp4=unitarray[k];
          unitarray[k]=unitarray[k+1];
          unitarray[k+1]=temp4;
          temp5=yeardatearray[k];
          yeardatearray[k]=yeardatearray[k+1];
          yeardatearray[k+1]=temp5;
        }
      }
    }
  }
  
  public void loadmyfile(){
    try{
      
      File myFile = new java.io.File("Shopping_List.txt");
      PrintWriter output = new PrintWriter (myFile);
      
      output.println("");
      output.println("Food Name: " + foodnamearray[index]);
      output.println("Price: " + pricearray[index]);
      output.println("Amount: " + amountarray[index]);
      output.println("Units: " + unitarray[index]);
      output.println("Expiry Date:" + yeardatearray[index]);
      output.println("");
      output.close();
      
      Scanner input = new Scanner(myFile);   
      
      //Read the first line of the file and output it to the console (this is the title)
      System.out.println(input.nextLine());
      
      // Loop through the contents of the file
      while (input.hasNext()) {   
        
        //Store the Data from the file and display it the the console in a meaningful way
        foodnamearray[index] = input.next(); 
        pricearray[index] = input.nextDouble();
        amountarray[index] = input.next();
        unitarray[index] = input.next();
        yeardatearray[index] = input.next();
        // System.out.print(foodnamearray);;
      }
      
      //We have read all the data in the file. Close it.
      input.close();     
    } catch (Exception E){};
  }
  
  
  //Main Method
  public static void main(String[] args) {
    foodprogram frame1 = new foodprogram(); 
    frame1.setVisible(true); //making frame visible
  }
  
  public double roundTwoDecimals (double d){
    double roundoff = (double) Math.round(d*100.00)/100.00;
    return roundoff;
  }
}



