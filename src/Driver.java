
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.Instance;
import weka.core.Instances;


import javax.swing.*;

public class Driver extends JFrame  implements ActionListener {

	/**
	 * @param args
	 * @throws exception
	 * 
	 */
	public String [] a= new String[3];
	public String prediction;
	private  static String filename;
	
	JTextField F1=new JTextField(30);
	JTextField F2=new JTextField(30);
	JTextField F3=new JTextField(30);	
	JTextField F4=new JTextField(30);
	JTextField F5=new JTextField(30);
	JTextField F6=new JTextField(30);
	JTextField F7=new JTextField(30);
	JTextField F8=new JTextField(30);
	JButton load=new JButton("Load");
	JButton loadf=new JButton("Load from file and select one of the given algorithms");
	JLabel j1=new JLabel("Select");
	JLabel pre=new JLabel("Predicted Value");
	
	
	JButton pj=new JButton("J48 Prediction");
	JButton pv=new JButton("SVM Prediction");
	JButton pn=new JButton("ANN Prediction");
	JButton ca=new JButton("Comparatie Analysis");
	public String [] input =new String[8];
	
	
	public String[] jdetails;
	public String[] vdetails;
	public String[] ndetails;
	
	public Driver(){
		
		super("AI Project Salah Uddin and Mehtaba");
		setSize(700,500);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		JButton button=new JButton("J48 Model Details");
		this.setLayout(null);
		add(button).setBounds(10, 15, 200,40);
	
		button.addActionListener(this);
		
		JButton button1=new JButton("SVM Model details");
		add(button1).setBounds(230, 15, 200,40);
		button1.addActionListener(this);
		JButton button2=new JButton("ANN Details");
		add(button2).setBounds(450, 15, 200,40);
		button2.addActionListener(this);
		add(F1).setBounds(100, 300, 50, 20);
		add(F2).setBounds(155, 300, 50, 20);
		add(F3).setBounds(210, 300, 50, 20);
		add(F4).setBounds(265, 300, 50, 20);
		add(F5).setBounds(320, 300, 50, 20);
		add(F6).setBounds(375, 300, 50, 20);
		add(F7).setBounds(430, 300, 50, 20);
		add(F8).setBounds(485, 300, 50, 20);
		add(load).setBounds(540, 300, 100, 20);
		add(pj).setBounds(15, 350, 200, 30);
		pj.addActionListener(this);
		
		add(pv).setBounds(230, 350, 200, 30);
		pv.addActionListener(this);
		add(pn).setBounds(450, 350, 200, 30);
		pn.addActionListener(this);
		load.addActionListener(this);
		add(loadf).setBounds(150, 250, 400, 20);
		add(j1).setBounds(555, 250, 140,20);
		loadf.addActionListener(this);
		add(pre).setBounds(300, 150, 100,30);
		add(ca).setBounds(30, 180, 190, 30);
		ca.addActionListener(this);
	}
	
	
	public void actionPerformed(ActionEvent e)  {
		// TODO Auto-generated method stub
		String name=e.getActionCommand();
		if(name=="J48 Model Details"){
		
			try {
			jdetails=J48details();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			JOptionPane.showMessageDialog(null, jdetails[0]+jdetails[1]);
			
		}else if(name=="SVM Model details"){
			try {
				vdetails=Vectordetails();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				JOptionPane.showMessageDialog(null, vdetails[0]+vdetails[1]);
			
		}else if(name=="ANN Details"){
			
			try {
				ndetails=Nueraldetails();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				JOptionPane.showMessageDialog(null, ndetails[0]+ndetails[1]);
		}
		else if(name=="Load"){
			
			input[0]=F1.getText();
			input[1]=F2.getText();
			input[2]=F3.getText();
			input[3]=F4.getText();
			input[4]=F5.getText();
			input[5]=F6.getText();
			input[6]=F7.getText();
			input[7]=F8.getText();
		
			 FileWriter writer;
			try {
				writer = new FileWriter("testing.csv");
				
				writer.append("Parents");
				writer.append(',');
				writer.append("has_nurs");
				writer.append(',');
				writer.append("form");
				writer.append(',');
				writer.append("childern");
				writer.append(',');
				writer.append("housing");
				writer.append(',');
				writer.append("finance");					
				writer.append(',');
				writer.append("Social");	
				writer.append(',');
				writer.append("health");	
				
				
				 writer.append('\n');
				writer.append(input[0]);
				 writer.append(',');
				writer.append(input[1]);
				 writer.append(',');
				writer.append(input[2]);
				 writer.append(',');
				writer.append(input[3]);
				 writer.append(',');
				writer.append(input[4]);
				 writer.append(',');
				writer.append(input[5]);
				 writer.append(',');
				writer.append(input[6]);
				 writer.append(',');
				writer.append(input[7]);
				
				writer.flush();
				writer.close();
				
				
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
	
			 
		
		}else if(name=="Load from file and select one of the given algorithms"){
			
		filename="test2.arff";
			
			j1.setText(filename+ " file selected");
			
			
		}
		
		
		else if(name=="J48 Prediction"){
			try {
			prediction=	Modellj48();
				pre.setText(prediction);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
	else if(name=="SVM Prediction"){
			
			
		try {
			prediction=	SMO();
			pre.setText(prediction);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	else if(name=="ANN Prediction"){
		
		try {
			prediction=	Nueral();
			pre.setText(prediction);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
		
		
else if(name=="Comparatie Analysis"){
		
	try {
		a=Analysis();
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		JOptionPane.showMessageDialog(null, a[0]+"\n "+a[1]+"\n "+a[2]);
		
	}
		
		
	}
	
	
	
	
	
	public static void main(String[] args) throws Exception {
		new Driver().setVisible(true);
		// TODO Auto-generated method stub
		//Modellj48();
		//SMO();
	
	}

	
	
	
	public static void arff() throws Exception{
		
		 CSVLoader loader = new CSVLoader();
		    loader.setSource(new File("testing.csv"));
		    Instances data = loader.getDataSet();
		 
		    // save ARFF
		    ArffSaver saver = new ArffSaver();
		    saver.setInstances(data);
		    saver.setFile(new File("java.arff"));
		    saver.setDestination(new File("java.arff"));
		    saver.writeBatch();
		
	}
	
	
	
	
	
	
	public static String Modellj48() throws Exception{
		
		
		BufferedReader br = new BufferedReader(new FileReader(
				filename));
		Instances testingSet = new Instances(br);
		testingSet.setClassIndex(testingSet.numAttributes() - 1);
		Instance inst = testingSet.instance(5);
		
		Modelj48 obj = new Modelj48("nusery.arff");
		
		obj.learnModel();
		String result = obj.classifyInstance(inst);
		System.out.println(result);
		return result;
		
	}
	
	public static String  SMO() throws Exception{
	
		BufferedReader br = new BufferedReader(new FileReader(
				"test2.arff"));
		Instances testingSet = new Instances(br);
		testingSet.setClassIndex(testingSet.numAttributes() - 1);
		Instance inst = testingSet.instance(5);
		
		VectorMachineModel obj = new VectorMachineModel("nusery.arff");
		
		obj.learnModel();
		String result = obj.classifyInstance(inst);
		System.out.println(result);
		return result;
	}
	public static String Nueral() throws Exception{
		
		BufferedReader br = new BufferedReader(new FileReader(
				"test2.arff"));
		Instances testingSet = new Instances(br);
		testingSet.setClassIndex(testingSet.numAttributes() - 1);
		Instance inst = testingSet.instance(9);
		
		NueralModel obj = new NueralModel("nusery.arff");
		
		obj.learnModel();
		String result = obj.classifyInstance(inst);
		System.out.println(result);
		return result;
	}
	public  String[] J48details() throws Exception{
		String [] u=new String[2];
		BufferedReader br = new BufferedReader(new FileReader(
				"test2.arff"));
		Instances testingSet = new Instances(br);
		testingSet.setClassIndex(testingSet.numAttributes() - 1);
		Instance inst = testingSet.instance(3);
		
		Modelj48 obj = new Modelj48("nusery.arff");
		u=obj.j48data();
		
		
		
		return u;
	}
	public  String[] Vectordetails() throws Exception{
		String [] u=new String[2];
		BufferedReader br = new BufferedReader(new FileReader(
				"test2.arff"));
		Instances testingSet = new Instances(br);
		testingSet.setClassIndex(testingSet.numAttributes() - 1);
		Instance inst = testingSet.instance(3);
		
		VectorMachineModel obj = new VectorMachineModel("nusery.arff");
		u=obj.Vectordata();
		
		
		
		return u;
	}
	public  String[] Nueraldetails() throws Exception{
		String [] u=new String[2];
		BufferedReader br = new BufferedReader(new FileReader(
				"test2.arff"));
		Instances testingSet = new Instances(br);
		testingSet.setClassIndex(testingSet.numAttributes() - 1);
		Instance inst = testingSet.instance(3);
		
		NueralModel obj = new NueralModel("nusery.arff");
		u=obj.Nueraldata();
		
		return u;
	}
	public String[] Analysis() throws Exception{
		String [] u=new String[2];
		String [] u1=new String[2];
		String [] u2=new String[2];
		u=Vectordetails();
		u1= J48details();
		u2=Nueraldetails() ;
		
		String [] k=new String[3];
		k[0]=u[1];
		k[1]=u1[1];
		k[2]=u2[1];
		return k;
		
		
	}
	
}

