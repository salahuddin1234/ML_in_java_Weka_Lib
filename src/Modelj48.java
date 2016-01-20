import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SMO;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

public class Modelj48 {
	private File inputFile;
	public Instances dataset;
	private J48 mClassifier;

	
	
		public String[] j48data() throws Exception {
		
			
			BufferedReader reader = null;

			
		    File file = new File("nusery.arff");
		    reader = new BufferedReader(new FileReader(file));

		   Instances train=new Instances(reader);
		   train.setClassIndex(train.numAttributes() -1);
		   reader.close();

		   J48 j =new J48();
		   j.buildClassifier(train);
		   Evaluation eval=new Evaluation(train);
		   eval.crossValidateModel(j, train, 10, new Random(1));
		  String po=eval.toSummaryString("\nResults\n====\n",true);
		  double i = (eval.truePositiveRate(1)+eval.trueNegativeRate(1))/(eval.truePositiveRate(1)+eval.trueNegativeRate(1)+eval.falseNegativeRate(1)+eval.falsePositiveRate(1));
		  String pi="..F-Measure.."+eval.fMeasure(1)+".. Precision.. "+eval.precision(1)+".. Recall .."+ eval.recall(1) + "Accuray " + i;

		String [] pp= new String[2];
		pp[0]=po;
		pp[1]=pi;
				return pp;
			
		}

	public Modelj48(String filePath) throws IOException {
		System.out.println("Loading Data...");
		inputFile = new File(filePath);
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		dataset = new Instances(br);
		dataset.setClassIndex(dataset.numAttributes() - 1);
	}

	public void learnModel() throws Exception {
		
		mClassifier = new J48();
		mClassifier.buildClassifier(dataset);
	} 

	
	public String classifyInstance(Instance inst) throws Exception {
		
		inst.setDataset(dataset);
		double result = mClassifier.classifyInstance(inst);
		
	
		switch (String.valueOf(result)) {
		case "0.0":
			return "Priority";

		case "1.0":
			return "Not Recommended";


		case "3.0":
			return "Recomended";

		default:
			return null;
	}
	

	}
	private Instance getInstance(String[] data){
		Attribute[] at = new Attribute[8];
		at[0] = new Attribute("a", 0);
		at[1] = new Attribute("b", 1);
		at[2] = new Attribute("c", 2);
		at[3] = new Attribute("d", 3);
		at[4] = new Attribute("e", 4);
		at[5] = new Attribute("f", 5);
		at[6] = new Attribute("g", 6);
		at[7] = new Attribute("h", 7);
		at[8] = new Attribute("i", 8);

		
		Instance inst = new Instance(14);
		for(int i=0; i<at.length-1; i++){
			inst.setValue(at[i], data[i]);
		}
		
		return inst;
	}

}