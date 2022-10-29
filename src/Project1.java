import java.io.IOException;
import java.io.*;  
import java.io.FileWriter;
import java.io.FileReader;
import java.util.NoSuchElementException;
import java.util.Scanner; 
import java.util.Arrays;

public class Project1{
	public static void main(String[] args) {
		
		File fi = new File(args[0]);
		File fo = new File(args[1]);
	    PrintStream writer;
		//reading from input.txt line by line
	    try {
	    	writer = new PrintStream(fo);

	    }  
	    catch(FileNotFoundException e){  
	    	e.printStackTrace();  
	    	return;
	    } 
	    
	    Scanner scan;
	    try {
	    	scan = new Scanner(fi);
	    }catch(FileNotFoundException e){  
	    	e.printStackTrace();  
	    	return;
	    }
	    
	    
	    FactoryImpl my_factory = new FactoryImpl();
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			String[] my_array = line.split(" ");
			//System.out.println(Arrays.toString(my_array));
			
			//addFirst method
			if (my_array[0].equals("AF")) {
				int id = Integer.parseInt(my_array[1]);
				int value = Integer.parseInt(my_array[2]);
				Product my_product = new Product(id,value);
				my_factory.addFirst(my_product);
			}
			
			//addLast method
			if (my_array[0].equals("AL")) {
				int id = Integer.parseInt(my_array[1]);
				int value = Integer.parseInt(my_array[2]);
				Product my_product = new Product(id,value);
				my_factory.addLast(my_product);
			}
			
			//add method
			if (my_array[0].equals("A")) {
				int index = Integer.parseInt(my_array[1]);
				int id = Integer.parseInt(my_array[2]);
				int value = Integer.parseInt(my_array[3]);
				Product my_product = new Product(id,value);
				try {
					my_factory.add(index,my_product);
				}
				catch(IndexOutOfBoundsException e) {
					writer.println("Index out of bounds.");
				}
			}
			
			//removeFirst method
			if (my_array[0].equals("RF")) {
				try{
					writer.println(my_factory.removeFirst());
				}
				catch(NoSuchElementException e){
					writer.println("Factory is empty.");
				}
			}
			
			//removeLast method
			if (my_array[0].equals("RL")) {
				try{
					writer.println(my_factory.removeLast());
					//writer.println(my_factory.toString());
				}
				catch(NoSuchElementException e){
					writer.println("Factory is empty.");
				}
			}
			
			//find method
			if (my_array[0].equals("F")) {
				int id = Integer.parseInt(my_array[1]);
				try{
					writer.println(my_factory.find(id));
				}
				catch(NoSuchElementException e){
					writer.println("Product not found.");
				}
			}
			
			//get method
			if (my_array[0].equals("G")) {
				int index = Integer.parseInt(my_array[1]);
				try{
					writer.println(my_factory.get(index));
				}
				catch(IndexOutOfBoundsException e){
					writer.println("Index out of bounds.");
				}
			}
			//removeIndex method
			if (my_array[0].equals("RI")) {
				int index = Integer.parseInt(my_array[1]);
				try{
					writer.println(my_factory.removeIndex(index));
					//writer.println(my_factory.removeIndex(index).toString());
				}
				catch(IndexOutOfBoundsException e){
					writer.println("Index out of bounds.");
				}
			}
			
			//removeProduct method
			if (my_array[0].equals("RP")) {
				int value = Integer.parseInt(my_array[1]);
				try {
					writer.println(my_factory.removeProduct(value));
					//writer.println(my_factory.removeProduct(value).toString());
				}
				catch(NoSuchElementException e){
					writer.println("Product not found.");
					
				}	
			}
			
			//filterDuplicates method
			if (my_array[0].equals("FD")) {
				writer.println(my_factory.filterDuplicates());
				//writer.println(my_factory.toString());
			}
			
			//update method
			if (my_array[0].equals("U")) {
				int id = Integer.parseInt(my_array[1]);
				int value = Integer.parseInt(my_array[2]);
				Product my_product = new Product(id,value);
				try {
					writer.println(my_factory.update(id, value));
					//writer.println(my_factory.update(id,value).toString());
				}
				catch(NoSuchElementException e){
					writer.println("Product not found.");
				}	
			}
			
			//reverse method
			if (my_array[0].equals("R")) {
				my_factory.reverse();
				writer.println(my_factory.printList());
				//writer.println(my_factory.toString());
				
			}
			//printList method
			if (my_array[0].equals("P")) {
				writer.println(my_factory.printList());
				//writer.println(my_factory.toString());
			}
		}
		scan.close();     
		writer.close();	
	}
}