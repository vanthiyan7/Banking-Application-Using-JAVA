import java.io.*;
class Account
{
protected int accnumber;
protected double balance;

public Account()
{
accnumber = 0;
balance = 0.0;
}

Account(int accnumber,double balance)
{
this.accnumber = accnumber;
this.balance = balance;
}
}

class SBAccount extends Account
{
SBAccount()
{
super();
}

SBAccount(int accnumber, double init_balance)
{
super(accnumber,init_balance);
System.out.println("Your SB Account is opened with the following credential:");
System.out.println("Your SB Account No :"+accnumber);
System.out.println("Your Initial balance :"+init_balance);

}

void deposit(double amount)
{
if(amount > 0)
{
System.out.println("Current balance :"+balance);
System.out.println("Amount to be deposited :"+amount);
balance = balance + amount;
System.out.println("New balance :"+balance);
}
else
System.out.println("Invalid amount");
}

void withdraw(double amount)
{
if(balance - amount > 1000)
{
System.out.println("Current balance :"+balance);
System.out.println("Amount to be withdraw :"+amount);
balance = balance - amount;
System.out.println("New balance :"+balance);
}
else
System.out.println("Insufficient balance");
}

void calc_interest()
{
System.out.println("Current balance :"+balance);
double interest = 4*balance/1200;
System.out.println("Interest :"+interest);
balance = balance + interest;
System.out.println("New balance :"+balance);
}
}

class FDAccount extends Account
{
int period;

FDAccount()
{
super();
period = 0;
}

FDAccount(int accnumber,int period,double balance)
{
super(accnumber,balance);
this.period = period;
System.out.println("Your FD Account is opened with the following credential:");
System.out.println("Your FD Account No :"+accnumber);
System.out.println("Your Deposited amount :"+balance);
System.out.println("Period :"+period);
}

double calc_interest()
{
return 8.25*balance*period/1200;
}

void close()
{
double interest = calc_interest();
System.out.println("Deposited amount :"+balance);
System.out.println("Period :"+period);
System.out.println("Interest :"+interest);
balance = balance + interest;
System.out.println("Maturity amount :"+balance);
System.out.println(" * FD Account Closed * ");
}
}

class Customer
{
int cust_id;
String name;
String address;
SBAccount sbacc;
FDAccount fdacc;
static int sbno,fdno;

static
{
sbno = 123000;
fdno = 678000;
}

Customer()
{
cust_id = 0;
name = null;
address = null;
}

Customer(int cust_id,String name,String address)
{
this.cust_id = cust_id;
this.name = name;
this.address = address;
System.out.println("Customer ID  :"+cust_id);
System.out.println("Keep your Customer ID CONFIDENTIALLY as it is needed for all your transactions.");
}

void createAccount(int type)throws IOException
{
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
if(type == 1)
{
System.out.println("Enter initial amount for opening new SB account :");
double amt = Double.parseDouble(br.readLine());
sbacc = new SBAccount(++sbno,amt);
}

else if(type == 2)
{
System.out.println("Enter initial amount and period for opening new FD account :");
double amt = Double.parseDouble(br.readLine());
int period = Integer.parseInt(br.readLine());
fdacc = new FDAccount(++fdno,period,amt);
}

else
System.out.println("Invalid choice");
}

void transaction(int type)throws IOException
{
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
if(type == 1)
{
System.out.println("Enter amount to withdraw :");
double amt = Double.parseDouble(br.readLine());
sbacc.withdraw(amt);
}

else if(type == 2)
{
System.out.println("Enter amount to deposit :");
double amt = Double.parseDouble(br.readLine());
sbacc.deposit(amt);
}

else if(type == 3)
{
sbacc.calc_interest();
}

else if(type == 4)
{

fdacc.close();
}

else
System.out.println("Invalid choice");
}
}

class BankDemo
{
public static void main(String arg[])throws IOException
{
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
int ch1,ch2,ch3,cid;
int i=0;

Customer c[] = new Customer[5];

do//main menu
{
System.out.println("**********************************");
System.out.println("     Welcome to SIMREN BANK       ");
System.out.println("**********************************");
System.out.println("SB Account ----- 1");
System.out.println("FD Account ----- 2");
System.out.println("Exit ----- 3");
System.out.println("Enter your choice :");
ch1 = Integer.parseInt(br.readLine());

switch(ch1)//main menu
{
case 1://main menu
do
{
System.out.println("New SB Account open ----- 1");
System.out.println("SB Account deposit ----- 2");
System.out.println("SB Account withdraw ----- 3");
System.out.println("SB Account calculate interest ----- 4");
System.out.println("Exit ----- 5");
System.out.println("Enter your choice :");
ch2 = Integer.parseInt(br.readLine());
switch(ch2)//sb menu
{
case 1://sb menu
System.out.println("Enter name and address :");
String name = br.readLine();
String address = br.readLine();
c[i] = new Customer(i,name,address);
c[i].createAccount(1);
i++;
break;

case 2://sb menu
try
{
System.out.println("Enter Customer ID :");
cid = Integer.parseInt(br.readLine());
c[cid].transaction(2);
}
catch(Exception e)
{
System.out.println("Customer ID not found. Enter a valid Customer ID.");
}
break;

case 3://sb menu
try
{
System.out.println("Enter Customer ID :");
cid = Integer.parseInt(br.readLine());
c[cid].transaction(1);
}
catch(Exception e)
{
System.out.println("Customer ID not found. Enter a valid Customer ID.");

}
break;

case 4://sb menu
try
{
System.out.println("Enter Customer ID :");
cid = Integer.parseInt(br.readLine());
c[cid].transaction(3);
}
catch(Exception e)
{
System.out.println("Customer ID not found. Enter a valid Customer ID.");
}
break;

case 5://sb menu
break;

default://sb menu
System.out.println("Invalid choice for SB Account!");
}
}while(ch2 != 5);
break;

case 2://main menu
do
{
System.out.println("New FD Account open ----- 1");
System.out.println("FD Account close ----- 2");
System.out.println("Exit ----- 3");
System.out.println("Enter your choice :");
ch3 = Integer.parseInt(br.readLine());
switch(ch3)//fd menu
{
case 1://fd menu
System.out.println("Enter name and address :");
String name = br.readLine();
String address = br.readLine();
c[i] = new Customer(i,name,address);
c[i].createAccount(2);
i++;
break;

case 2://fd menu
try
{
System.out.println("Enter Customer ID :");
cid = Integer.parseInt(br.readLine());
c[cid].transaction(4);
}
catch(Exception e)
{
System.out.println("Customer ID not found. Enter a valid Customer ID");
}
break;

case 3://fd menu
break;

default://fd menu
System.out.println("Invalid choice for FD Account!");
}
}while(ch3 != 3);
break;

case 3://main menu
System.out.println("THANK YOU FOR VISITING OUR BANK");
break;

default://main menu
System.out.println("Invalid choice");
}
}while(ch1 != 3);//Main menu
}
}
