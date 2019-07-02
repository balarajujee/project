package com.aia.g400.service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.aia.g400.model.Invoice;

public class InvoiceService  extends Thread {
	
	private Thread t;
	
	public void run() {
//	
		ArrayList<Invoice> invoiceDetalist= getInvoicDetails();
		//System.out.println("No of Files :");
		String name = null;
		Iterator<Invoice> it=invoiceDetalist.iterator();
		while(it.hasNext()) {
			
			Invoice in=it.next();
			 name=in.getCompanyName();
			 System.out.println("==================================="+name);
				
		}
		//String name = null;
		/*for(int i=0;i<noFiles; i++) {
			invoicelist=invoiceDetalist.get(i);
			for(Invoice idata:invoicelist) {
				 name=idata.getCompanyName();
				 System.out.println("==================================="+name);
					
			}
			
		}*/
		
	}
	
	 public static ArrayList<Invoice> getInvoicDetails(){
		 
		 
		 String FILENAME = "F:\\Test_Read\\invoice.txt";
		 BufferedReader br = null;
 		 FileReader fr = null;
 		//HashMap<Integer, ArrayList<Invoice>> invoiceDetalist= new HashMap<Integer,ArrayList<Invoice>>(); 
 		  ArrayList<Invoice> list=null;
 		try {
       
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);
			if(br == null || br.equals("")){
				   System.out.println("No Invoice Flat file ");
			   }else{
				   String sCurrentLine;
				   int  cuurline = -1, pdfCount=0;
				 
				   list=new ArrayList<Invoice>();
				   while((sCurrentLine=br.readLine())!=null) {
					 //System.out.println("getInvoiceDetails() : sCurrentLine "+sCurrentLine);
					   list=new ArrayList<Invoice>();
					   Invoice invoice=new Invoice();
					   
					   if(sCurrentLine.contains("****")) {
						   invoice=new Invoice();
						   System.out.println("=========================== ");
							  
						      pdfCount++;  
						}
					String[] data= sCurrentLine.split("\\|");
					for(int i=0; i<data.length; i++) {
						
						if(data[0].equalsIgnoreCase("0001") && data[1].equalsIgnoreCase("1H")&& data[2].equalsIgnoreCase("01") &&i==3 ) {
							invoice.setCompanyName(data[i] != null && data[i].length()>0 ?data[i].trim() :"");
							System.out.println("company Name "+data[i] != null && data[i].length()>0 ?data[i].trim() :"");
						}
						if(data[0].equalsIgnoreCase("0001") && data[1].equalsIgnoreCase("1H")&& data[2].equalsIgnoreCase("01") &&i==4 ) {
							invoice.setAddressLine1(data[i] != null && data[i].length()>0 ?data[i].trim() :"");
							System.out.println("Address Line1 "+data[i] != null && data[i].length()>0 ?data[i].trim() :"");
						}
					}
					list.add(invoice);
					//invoiceDetalist.put(pdfCount, list);
					  
				   }
				   // System.out.println("List Data : "+list);
				   
				   System.out.println("List Data : "+pdfCount);
				   
			   }
			
			
		} catch (FileNotFoundException e) {
			 System.out.println("[Invoice.getInvoicedetails] Exception: " + e.toString());
           e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		  return list;
	 }
	
	 
	  public void startBatch() {
			System.out.println("Starting thread ");
			
			if (t == null) {
				t = new Thread(this);
				t.start();
			}
		}
	 public static void main(String args[]) {
		 InvoiceService invoice=new InvoiceService();
		 invoice.startBatch();
		 System.out.println("startedd.....");
	 }
}
