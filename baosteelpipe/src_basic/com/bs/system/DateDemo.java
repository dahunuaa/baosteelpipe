package com.bs.system;

import java.text.SimpleDateFormat;
import java.util.Date;




public class DateDemo {
//   public String time;
   public static void main(String[] args) {	
   SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   String time = sm.format(new Date());
   System.out.println(time);
//   return time;
}

}
