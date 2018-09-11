package com.fh.util;

import java.util.ArrayList;
import java.util.List;

public class ChangeUtil {
	
//	public List arrayLists=new ArrayList();
	
	public static List doExchange(List arrayLists){
		
        int len=arrayLists.size();
        if (len<2){
//            this.arrayLists=arrayLists;
            return (List)arrayLists.get(0);
        }
        int len0;
        if (arrayLists.get(0) instanceof String[]){
            String[] arr0= (String[]) arrayLists.get(0);
            len0=arr0.length;
        }else {
            len0=((ArrayList<String>)arrayLists.get(0)).size();
        }

        String[] arr1= (String[]) arrayLists.get(1);
        int len1=arr1.length;

        int lenBoth=len0*len1;
        
        ArrayList<ArrayList<String>> tempArrayLists=new ArrayList<ArrayList<String>>(lenBoth);
        
        for (int i=0;i<len0;i++){
            for (int j=0;j<len1;j++){ 
                if (arrayLists.get(0) instanceof String[]){
                    String[] arr0= (String[]) arrayLists.get(0);
                    ArrayList<String> arr=new ArrayList<String>();
                    arr.add(arr0[i]);
                    arr.add(arr1[j]);
                    tempArrayLists.add(arr);
                }else {
                    ArrayList<ArrayList<String>> arrtemp= (ArrayList<ArrayList<String>>) arrayLists.get(0);
                    ArrayList<String> arr=new ArrayList<String>();
                    for (int k=0;k<arrtemp.get(i).size();k++){
                        arr.add(arrtemp.get(i).get(k));
                    }
                    arr.add(arr1[j]);
                    tempArrayLists.add(arr);
                }
            }
        }

        List newArrayLists=new ArrayList();
        for (int i=2;i<arrayLists.size();i++){
            newArrayLists.add(arrayLists.get(i));
        }
        newArrayLists.add(0,tempArrayLists);

        return doExchange(newArrayLists);
    }
	
	public static void main(String[] args) {
		List a=new ArrayList();
		String [] s1={"红","黄","蓝"};
		String [] s2={"大","中","小"};
		String [] s3={"高","地","矮"};
		String [] s4={"高1","地1","矮1"};
		String [] s5={"高2","地2","矮2"};
		String [] s6={"高3","地3","矮3"};
		String [] s7={"高4","地4","矮4"};
		a.add(s1);
		a.add(s2);
		a.add(s3);
		a.add(s4);
		a.add(s5);
		a.add(s6);
		a.add(s7);
		ChangeUtil c = new ChangeUtil();
		c.doExchange(a);
//		System.out.println(((List)(c.arrayLists.get(0))).size());
//		System.out.println(c.arrayLists);
	}

}
