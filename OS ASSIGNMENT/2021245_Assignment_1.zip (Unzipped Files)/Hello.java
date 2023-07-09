import java.util.*;
import java.time.LocalDateTime;    
import java.time.format.DateTimeFormatter;  
class Student{
	private String name;
	private int rollno;
	private float cgpa;
	private String branch;
	public static int studentcounter;
	private String status="unplaced";
	private String offer="reject";
	private String application="not applied";
	ArrayList<Company>stravailablecompany=new ArrayList<>();
	ArrayList<Company>strappliedcompany=new ArrayList<>();
	ArrayList<Company>strplacedcompany=new ArrayList<>();
	ArrayList<Company>strofferedcompany=new ArrayList<>();
	ArrayList<Company>strnotappliedcompany=new ArrayList<>();
	float ctc=0;
	public static int no_of_placed_students=0;
	public static int no_of_blocked_students=0;
	Student(String name,int rollno,float cgpa,String branch){
		this.name=name;
		this.rollno=rollno;
		this.cgpa=cgpa;
		this.branch=branch;
	}
	public String getApplication() {
		return this.application;
	}
	public void setApplication(String application ) {
		this.application=application;
	}
	public String getName() {
		return this.name;
	}
	
	public String getOffer() {
		return this.offer;
	}
	public void setOffer(String offer) {
		this.offer=offer;
	}
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status=status;
	}
	public int getRollno() {
		return this.rollno;
	}
	public float getCgpa() {
		return this.cgpa;
	}
	public void setCgpa(float cgpa) {
		 this.cgpa=cgpa;
	}
	
	public String getBranch() {
		return this.branch;
	}
	public void Register_For_Placement_Drive() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		System.out.println(dtf.format(now));  
		studentcounter++;
	}
	
	public void Register_For_Company(Company cp) {
		if(this.cgpa>=cp.getCgpacriteria() && cp.getOfferedpackage()>=3*this.ctc) {
			System.out.println("Successfully Registered at"+cp);
			this.setApplication("applied");
			this.strappliedcompany.add(cp);
			
		}
	}
	public void blocked_students() {
		int flag=0;
		for(int i=0;i<Institue_Placement_cell.std.size();i++) {
			Student std=Institue_Placement_cell.std.get(i);
			if(std.getStatus().equals("unplaced")) {
			for(int j=0;j<std.stravailablecompany.size();j++) {
				if(std.accept_reject_offer(stravailablecompany.get(i)).equals("accept")) {
					flag=1;
				}
			}
			if(flag==0) {
				std.setStatus("blocked");
				no_of_blocked_students++;
			}
			}
		}
	}
	
	public void Get_All_Available_Companies() {
		
		if(this.getStatus().equals("blocked") || this.getOffer().equals("accept")) {
			System.out.println("unavailable");
		}
		else {
		for(int i=0;i<Institue_Placement_cell.cpy.size();i++) {
		   Company cmpy=Institue_Placement_cell.cpy.get(i);
		   if(this.getCgpa()>=cmpy.getCgpacriteria() && 3*cmpy.getOfferedpackage()>=this.ctc) {
			   
			   this.stravailablecompany.add(cmpy);
		   }
		}
		   if(this.stravailablecompany.size()!=0) {
			System.out.println("List of all available companies");
		     for(int i=0;i<this.stravailablecompany.size();i++) {
				Company cmpy=Institue_Placement_cell.cpy.get(i);
				System.out.println("company name"+cmpy.getName());
				System.out.println("company role offering"+cmpy.getRole());
				System.out.println("company package"+cmpy.getOfferedpackage());
				System.out.println("company cgpa criteria"+cmpy.getCgpacriteria());
			}
		}
	  }
	}

	public void Get_Current_Status() {
		System.out.print(getStatus());
		if(this.getStatus().equals("placed")) {
			for(int i=0;i<this.strplacedcompany.size();i++) {
				Company cp=strplacedcompany.get(i);
				System.out.print("Name of the company"+cp.getName());
				System.out.println("ctc offered by the company"+cp.getOfferedpackage());
				System.out.println("role of the student"+cp.getRole());
				System.out.println("cgpa criteria of the company"+cp.getCgpacriteria());
			}
		}
	}
	public void Update_Cpga(Institue_Placement_cell plc) {
		plc.upcgpa(this,this.cgpa);
	}
	public String accept_reject_offer(Company cp) {
       System.out.println("do you want to accept the offer at "+cp.getName());
       Scanner sc=new Scanner(System.in);
       String offer=sc.next();
       if(offer.equals("accept")) {
    	   setStatus("Placed");
    	   no_of_placed_students++;
    	   setOffer("accept");
    	   strplacedcompany.add(cp);
    	   String st="accept";
    	   ctc=cp.getOfferedpackage();
    	   return st;
    	   
       }
       setOffer("reject");
       setStatus("unplaced");
       return "reject";
   }
	public void appliedcompanies(Student st) {
		for(int k=0;k<st.stravailablecompany.size();k++) {
			Company cpy=stravailablecompany.get(k);
			if(st.apply_offer(cpy).equals("apply")) {
				st.strappliedcompany.add(cpy);
			}
			else {
				st.strnotappliedcompany.add(cpy);
			}
		}
	}
	public String apply_offer(Company cp) {
		System.out.println("do you want to apply to this company(apply/not to apply)");
		Scanner sc=new Scanner(System.in);
		String apply=sc.next();
		if(apply.equals("apply")) {
			return "apply";
		}
		return "not to apply";
	}
	
}
class Institue_Placement_cell{
	static ArrayList<Student>std=new ArrayList<>();
	static ArrayList<Company>cpy=new ArrayList<>();
	Student st;
	public void upcgpa(Student student,float cgpa) {
		st=student;
		st.setCgpa(cgpa);
	}

	public void Open_Student_Registrations() {
		System.out.println("Fill in the details:-");
		System.out.println("1) Set the Opening time for Student registrations");
		System.out.println("2) Set the Closing time for Student registrations");
		Scanner sc=new Scanner(System.in);
		String str=sc.nextLine();
		String str1=sc.nextLine();
		System.out.println(str);
		System.out.println(str1);
	}
	public void Open_Company_Registrations() {
		System.out.println("Fill in the details:-");
		System.out.println("1) Set the Opening time for company registrations");
		System.out.println("2) Set the Closing time for company registrations");
		Scanner sc=new Scanner(System.in);
		String str=sc.nextLine();
		String str1=sc.nextLine();
		System.out.println(str);
		System.out.println(str1);
		
	}
   public void Get_No_Of_Student_Registrations() {
	      System.out.println(Student.studentcounter);
    }
    public void Get_No_of_Company_Regaistrations() {
    	  System.out.println(Company.companycounter);
    }
    public void Get_Number_of_Placed_UnPlaced_Blocked_Students() {
    	System.out.println("no of placed students"+Student.no_of_placed_students);
    	System.out.println("no of blocked students"+Student.no_of_blocked_students);
    	int no_of_unplaced_students=Student.studentcounter-(Student.no_of_blocked_students+Student.no_of_placed_students);
    	System.out.println("no of unplaced students"+no_of_unplaced_students);
    }
    public void Get_Student_Details(String name ,int rollno) {
    	
    	for(int i=0;i<Institue_Placement_cell.std.size();i++) {
    		Student st=Institue_Placement_cell.std.get(i);
    		if(st.getName().equals(name) && st.getRollno()==rollno) {
    			st.getStatus();
    			for(int j=0;j<st.strappliedcompany.size();j++) {
    				System.out.println("Applied comapnies"+st.strappliedcompany.get(j));
    			}
    			for(int k=0;k<st.strnotappliedcompany.size();k++) {
    				System.out.println(st.strnotappliedcompany.get(k));
    			}
    			for(int m=0;m<st.strofferedcompany.size();m++) {
    				System.out.println(st.strofferedcompany.get(m));
    			}
    		}
    	}
    }
    public void Get_Company_Details(String name) {
    	for(int i=0;i<Institue_Placement_cell.cpy.size();i++) {
    		Company cp=Institue_Placement_cell.cpy.get(i);
    		if(cp.getName().equals(name)) {
    			System.out.println(cp.getCgpacriteria());
    			System.out.println(cp.getRole());
    			System.out.println(cp.getOfferedpackage());
    			System.out.println(cp.getName());
    			for(int j=0;j<cp.strselectedstudents.size();j++) {
    				Student st=cp.strselectedstudents.get(i);
    				System.out.println("Name of student selected"+st.getName());
    				System.out.println("roll no of student selected"+st.getRollno());
    			}
    		}
    	}
    	
    }
    public void Get_Average_Package() {
    	float sum=0;
    	int m=0;
    	for(int i=0;i<cpy.size();i++) {
    	Company cp=cpy.get(i);
    	for(int j=0;j<cp.strselectedstudents.size();j++) {
    		m=m+cp.strselectedstudents.size();
    		Student st=cp.strselectedstudents.get(j);
    		for(int k=0;k<st.strplacedcompany.size();k++) {
    			Company c=st.strplacedcompany.get(k);
    			sum=sum+c.getOfferedpackage();
    		}
    	}
    	}
    	float avg=sum/m;
    	System.out.println("the average package offered to the students of the institute"+avg);
    }
    public void Get_Company_Process_Results(String name) {
    	for(int i=0;i<cpy.size();i++) {
    		Company cp=cpy.get(i);
    		if(cp.getName().equals(name)) {
    			cp.Get_Selected_students();
    		}
    	}
    }
  }
class Company{
	private String name;
	private String role;
	private float offeredpackage;
	public static int companycounter;
	private float cgpacriteria;
	ArrayList<Student>strofferedstudents=new ArrayList<>();
	ArrayList<Student>strselectedstudents=new ArrayList<>();
	Company(String name,String role,float ctc,float cgpa){
		this.name=name;
		this.role=role;
		this.offeredpackage=ctc;
		this.cgpacriteria=cgpa;
	}
	public String getName() {
		return this.name;
	}
	public String getRole() {
		return this.role;
	}
	public float getOfferedpackage() {
		return this.offeredpackage;
	}
	public float getCgpacriteria() {
		return this.cgpacriteria;
	}
	public void setRole(String role) {
		this.role=role;
	}
	public void setOfferedpackage(float offeredpackage) {
		this.offeredpackage=offeredpackage;
	}
	public void setCgpacriteria(float cgpacriteria) {
		this.cgpacriteria=cgpacriteria;
	}
	
	public void Register_to_Institute_Drive() {
		
		companycounter++;
	}
	public void Get_Offered_Students(Company cp){
		for(int i=0;i<Institue_Placement_cell.std.size();i++) {
			Student st=Institue_Placement_cell.std.get(i);
			if(st.getCgpa()>=cp.getCgpacriteria() && (st.getStatus().equals("unplaced"))){
				cp.strofferedstudents.add(st);
				st.strofferedcompany.add(cp);
			}
		}
}
	public void Get_Selected_students() {
		for(int i=0;i<this.strofferedstudents.size();i++) {
			Student st=this.strofferedstudents.get(i);
			if(st.getStatus().equals("unplaced") && st.getOffer().equals("accept")) {
				System.out.println("Name: "+st.getName());
				System.out.println("Rollno: "+st.getRollno());
				System.out.println("CGPA: "+st.getCgpa());
				System.out.println("Branch: "+st.getBranch());
				this.strselectedstudents.add(st);
			}
		}
	}
	public void update_role_package_cgpa_criteria() {
		Scanner sc=new Scanner(System.in);
		System.out.println("enter the updated role");
	    String role=sc.nextLine();
	    float offeredpackage=sc.nextFloat();
	    float cgpacriteria =sc.nextFloat();
		setRole(role);
		setOfferedpackage(offeredpackage);
		setCgpacriteria(cgpacriteria);
		}}
public class Hello
{
	public static void main(String[] args) {
		

        while(true) {
			   System.out.println("Welcome to FutureBuilder:");
			   System.out.println("1) Enter the Application");
			   System.out.println("2) Exit the Application");
		       Scanner sc=new Scanner(System.in);
		       
		       int opt=sc.nextInt();
		       if(opt==1) {
		    	   while(true) {
		    	   System.out.println("Choose The mode you want to Enter in:-");
		    	   System.out.println("1) Enter as Student Mode");
		    	   System.out.println("2) Enter as Company Mode");
		    	   System.out.println("3) Enter as Placement Cell Mode");
		    	   System.out.println("4) Return To Main Application");
		    	   int opt1=sc.nextInt();
		    	   if(opt1==1) {
		    		   while(true){
		    		   System.out.println("Choose the Student Query to perform-");
		    		   System.out.println("1) Enter as a Student(Give Student Name, and Roll No.)");
		    		   System.out.println("2) Add students");
		    		   System.out.println("3) Back");
		    		   int opt2=sc.nextInt();
		    		   if(opt2==1) {
		    			   String studentname=sc.next();
		    			   int studentrollno=sc.nextInt();
		    			   while(true){
		    				   System.out.println("welcome "+studentname);
		    				   System.out.println("1) Register For Placement Drive");
		    				   System.out.println("2) Register For Company");
		    				   System.out.println("3) Get All available companies");
		    				   System.out.println("4) Get Current Status");
		    				   System.out.println("5) Update CGPA");
		    				   System.out.println("6) Accept offer");
		    				   System.out.println("7) Reject offer");
		    				   System.out.println("8) Back");
		    				   int opt21=sc.nextInt();
		    				   if(opt21==1) {
		    					   for(int i=0;i<Institue_Placement_cell.std.size();i++) {
		    						    Student std=Institue_Placement_cell.std.get(i);
		    						    if(std.getName().equals(studentname) && std.getRollno()==studentrollno) {
		    						    	std.Register_For_Placement_Drive();
		    						    	System.out.println(std.getName()+"Registered for the Placement Drive at IIITD!!!!");
		    						    	System.out.println("Your details are:");
		    						    	System.out.println("Name: "+std.getName());
		    						    	System.out.println("Rollno: "+std.getRollno());
		    						    	System.out.println("CGPA: "+std.getCgpa());
		    						    	System.out.println("Branch: "+std.getBranch());
		    						    }
		    						}
		    				   }
		    				   else if(opt21==2) {
		    					   
		    						   for(int j=0;j<Institue_Placement_cell.std.size();j++) {
		    							   Student std=Institue_Placement_cell.std.get(j);
		    							   if(std.getName().equals(studentname) && std.getRollno()==studentrollno) {
		    								   System.out.println("enter the company you want to apply to");
		    								   String name=sc.next();
		    								   for(int k=0;k<Institue_Placement_cell.cpy.size();k++) {
		    									   Company cp=Institue_Placement_cell.cpy.get(k);
		    									   if(cp.getName().equals(name)) {
		    										   std.Register_For_Company(cp);
		    										   
		    									   }
		    								   }
		    								   
		    							   }
		    						   }
		    					   }
		    				   
		    				   else if(opt21==3) {
		    					   
		    					   for(int i=0;i<Institue_Placement_cell.std.size();i++) {
		    						    Student st=Institue_Placement_cell.std.get(i);
		    						    if(st.getName().equals(studentname) && st.getRollno()==studentrollno) {
		    						    	st.Get_All_Available_Companies();
		    						    }
		    					    }
		    				   }
		    				   else if(opt21==4) {
		    					   for(int i=0;i<Institue_Placement_cell.std.size();i++) {
		    						    Student std=Institue_Placement_cell.std.get(i);
		    						    if(std.getName().equals(studentname) && std.getRollno()==studentrollno) {
		    						    std.Get_Current_Status();}
		    					   }
		    				   }
		    				   else if(opt21==5) {
		    					   for(int i=0;i<Institue_Placement_cell.std.size();i++) {
		    						    Student std=Institue_Placement_cell.std.get(i);
		    						    Institue_Placement_cell iiitd=new Institue_Placement_cell();
		    						    if(std.getName().equals(studentname) && std.getRollno()==studentrollno) {
		    						    std.Update_Cpga(iiitd);}
		    					   }
		    				   }
		    				   else if(opt21==6) {
		    					   for(int i=0;i<Institue_Placement_cell.std.size();i++) {
		    						    Student std=Institue_Placement_cell.std.get(i);
		    						    if(std.getName().equals(studentname) && std.getRollno()==studentrollno) {
		    						    for(int j=0;j<std.stravailablecompany.size();j++) {
		    						    	std.accept_reject_offer(std.stravailablecompany.get(i));
		    						    		
		    						    }
		    						   }
		    					   }
		    				   }
		    				   else if(opt21==7) {
		    					   for(int i=0;i<Institue_Placement_cell.std.size();i++) {
		    						    Student std=Institue_Placement_cell.std.get(i);
		    						    if(std.getName().equals(studentname) && std.getRollno()==studentrollno) {
		    						    	for(int j=0;j<std.stravailablecompany.size();j++) {
			    						    	std.accept_reject_offer(std.stravailablecompany.get(i));
			    						    		
			    						    }
		    						    }
		    					   }
		    				   }
		    				   else if(opt21==8) {
		    					   break;
		    				   }
		    				   
		    				   
		    			   }
		    			  
		    		   }
		    		   else if(opt2==2) {
		    			   System.out.println("no of students to add");
		    			   int no_of_students=sc.nextInt();
		    			   System.out.println("Please add students Name, Roll No, CGPA, Branch(in order):");
		    			   
		    			   for(int i=0;i<no_of_students;i++) {
		    				   System.out.println("enter the name");
		    				   String name=sc.next();
		    				   System.out.println("enter the rollno");
		    				   int rollno=sc.nextInt();
		    				   System.out.println("Enter the cgpa");
		    				   float cgpa=sc.nextFloat();
		    				   System.out.println("enter the branch");
		    				   String branch=sc.next();
		    				   Student st=new Student(name,rollno,cgpa,branch);
		    				   Institue_Placement_cell.std.add(st);
		    				}
		    		     }
		    		   else if(opt2==3) {
		    		       break;
		    		   }
		    		 }
		    	   }
		    	   else if(opt1==2) {
		    		   while(true) {
		    	       System.out.println("Choose the Company Query to perform-");
		    		   System.out.println("1) Add Company and Details");
		    		   System.out.println("2) Choose Company");
		    		   System.out.println("3) Get Available Companies");
		    		   System.out.println("4) Back");
		    		   Scanner st=new Scanner(System.in);
		    		   int opt3=sc.nextInt();
		    		   if(opt3==1) {
		    			   System.out.println("enter the company name");
		    			   String companyname=sc.next();
		    			   System.out.println("enter the company role");
		    			   String companyrole=sc.next();
		    			   System.out.println("enter the company ctc");
		    			   float companyctc=st.nextFloat();
		    			   System.out.println("enter the company cgpa");
		    			   float companycgpa=st.nextFloat();
		    			   Company cmpy=new Company(companyname,companyrole,companyctc,companycgpa);
		    			   Institue_Placement_cell.cpy.add(cmpy);
		    			   System.out.println("Name: "+cmpy.getName());
		    				System.out.println("Role offered: "+cmpy.getRole());
		    				System.out.println("Offered package: "+cmpy.getOfferedpackage());
		    				System.out.println("Cgpa Requirement: "+cmpy.getCgpacriteria());
		    			}
		    		   else if(opt3==2) {
		    			   System.out.println("Choose To enter into mode of Available Companies:-");
		    			   String companyname=sc.next();
		    			   System.out.println("welcome"+companyname);
		    			   while(true) {
		    				   System.out.println("1) Update Role");
		    				   System.out.println("2) Update Package");
		    				   System.out.println("3) Update CGPA criteria");
		    				   System.out.println("4) Register To Institute Drive");
		    				   System.out.println("5) Back");
		    				   int opt31=sc.nextInt();
		    				   if(opt31==1) {
		    					   System.out.println("Enter the updated role");
		    					   String updatedrole=sc.next();
		    					   for(int i=0;i<Institue_Placement_cell.cpy.size();i++) {
		    						   Company cmpy=Institue_Placement_cell.cpy.get(i);
		    						   if(cmpy.getRole().equals(updatedrole)) {
		    							   cmpy.setRole(updatedrole);
		    						   }
		    					   }
		    				   }
		    				   else if(opt31==2) {
		    					   System.out.println("Enter the updated package");
		    					   float updatedpackage=sc.nextFloat();
		    					   for(int i=0;i<Institue_Placement_cell.cpy.size();i++) {
		    						   Company cmpy=Institue_Placement_cell.cpy.get(i);
		    						   if(cmpy.getOfferedpackage()==updatedpackage) {
		    							   cmpy.setOfferedpackage(updatedpackage);
		    						   }
		    					   }
		    					   
		    				   }
		    				   else if(opt31==3) {
		    					   System.out.println("Enter the updated cgpa criteria");
		    					   float updatedcgpacriteria=sc.nextFloat();
		    					   for(int i=0;i<Institue_Placement_cell.cpy.size();i++) {
		    						   Company cmpy=Institue_Placement_cell.cpy.get(i);
		    						   if(cmpy.getCgpacriteria()==updatedcgpacriteria) {
		    							   cmpy.setCgpacriteria(updatedcgpacriteria);
		    						   }
		    					   }
		    				   }
		    				   else if(opt31==4) {
		    					   System.out.println("enter the company name");
		    					   String cmpyname=sc.next();
		    					   System.out.println("enter the role offered at the company");
				    			   String cmpyrole=sc.next();
				    			   System.out.println("enter the ctc offered by the company");
				    			   float cmpyctc=st.nextFloat();
				    			   System.out.println("enter the cgpa requirement of the company");
				    			   float cmpycgpa=st.nextFloat();
				    			   Company cmpy=new Company(cmpyname,cmpyrole,cmpyctc,cmpycgpa);
		    					   cmpy.Register_to_Institute_Drive();
		    					   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		    					   LocalDateTime now = LocalDateTime.now();  
		    					   System.out.println(dtf.format(now));  
		    					}
		    				   else {
		    					   break;
		    				   }
		    			    
		    			   }
		    			   
		    		   }
		    		   else if(opt3==3) {
		    			   for(int i=0;i<Institue_Placement_cell.cpy.size();i++) {
    						   Company cmpy=Institue_Placement_cell.cpy.get(i);
    						   System.out.println(cmpy);
		    			   }
		    			  }
		    		   else if(opt3==4) {
		    			   break;
		    		   }
		    		  }
		    	   }
		    	   else if(opt1==3) {
		    		   while(true){
		    			   System.out.println("Welcome to IIITD Placement Cell");
		    			   System.out.println("1) Open Student Registrations");
		    			   System.out.println("2) Open Company Registrations");
		    			   System.out.println("3) Get Number of Student Registrations");
		    			   System.out.println("4) Get Number of Company Registrations");
		    			   System.out.println("5) Get Number of Offered/Unoffered/Blocked Students");
		    			   System.out.println("6) Get Student Details");
		    			   System.out.println("7) Get Company Details");
		    			   System.out.println("8) Get Average Package");
		    			   System.out.println("9) Get Company Process Results");
		    			   System.out.println("10) Back");
		    			   Institue_Placement_cell iiitd=new Institue_Placement_cell();
		    			   int opt4=sc.nextInt();
		    			   if(opt4==1) {
		    				  iiitd.Open_Student_Registrations(); 
		    			   }
		    			   else if(opt4==2) {
		    				   iiitd.Open_Company_Registrations();
		    			   }
		    			   else if(opt4==3) {
		    				   iiitd.Get_No_Of_Student_Registrations();
		    			   }
		    			  
		    			   else if(opt4==4) {
		    				   iiitd.Get_No_of_Company_Regaistrations();
		    			   }
		    			   else if(opt4==5) {
		    				   iiitd.Get_Number_of_Placed_UnPlaced_Blocked_Students();
		    			   }
		    			   else if(opt4==6) {
		    				   System.out.print("enter the name");
		    				   String name=sc.next();
		    				   System.out.println("enter the rollno");
		    				   int rollno=sc.nextInt();
		    				   iiitd.Get_Student_Details(name,rollno);
		    			   }
		    			   else if(opt4==7) {
		    				   System.out.println("enter the name");
		    				   String name=sc.next();
		    				   iiitd.Get_Company_Details(name);
		    			   }
		    			   else if(opt4==8) {
		    				   iiitd.Get_Average_Package();
		    			   }
		    			   else if(opt4==9) {
		    				   System.out.println("enter the company name to get its results");
		    				   String name=sc.next();
		    				   iiitd.Get_Company_Process_Results(name);
		    			   }
		    			   else {
		    				  break;
		    		       }
		    		   }
		    	   }
		    	   
		    	   else if(opt1==4) {
		    		   break;
		    	   }
			     }}
		      else if(opt==2) {
			   System.out.println("Thanks For Using FutureBuilder!!!!!!");
			   break;
		   }
		 
    }
	   }
}