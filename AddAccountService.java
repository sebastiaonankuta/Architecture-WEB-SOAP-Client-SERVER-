package upec.soap;  
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.jws.WebService;

import com.mysql.jdbc.Statement;

//@WebService(serviceName= "AccountService")
public class  AddAccountService{
	private String sql; 
	private Connection conne;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public void addAccount(int accountNumber,float solde,String creationDate ) throws ClassNotFoundException, SQLException {
		
		try{
			sql = "insert into account values ('"+accountNumber+"','"+solde+"','"+creationDate+"')";
			conne = GestionBDD.Connection_DataBase();
			stmt  = conne.prepareStatement(sql);
			stmt.executeUpdate();
			System.out.print("Données ajoutées OK\n");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();  
		}finally {
			try {
				conne.close();
				stmt.close();
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}

	public void deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException {
		int temporel_accountNumber;
		temporel_accountNumber=accountNumber ;
		try{
			sql = "delete from account  where accountNumber =" +temporel_accountNumber;
			//System.out.println(sql);
			conne = GestionBDD.Connection_DataBase();
			stmt  = conne.prepareStatement(sql);
			stmt.executeUpdate();
			System.out.print("Données supprimées OK\n");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();  
		}finally {
			try {
				conne.close();
				stmt.close();
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}

	
	public void updateAccount(int accountNumber,float solde,String creationDate) throws ClassNotFoundException, SQLException {
		try{
			//sql = "UPDATE Account SET solde =?,creationDate =? WHERE  accountNumber=?";
			sql = "update account set solde =" +solde+",creationDate ="+creationDate+" where  accountNumber="+accountNumber;
			//System.out.println(sql);
			conne = GestionBDD.Connection_DataBase();
			stmt  = conne.prepareStatement(sql);
			stmt.executeUpdate();
			System.out.print("Données updateD OK\n");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();  
		}finally {
			try {
				conne.close();
				stmt.close();
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	
	public  ArrayList<Account> getAllAccount( ) throws ClassNotFoundException, SQLException{
		 ArrayList<Account> accounts = new ArrayList<>();
		 sql   = "select * from account";
		 conne = GestionBDD.Connection_DataBase();
		 stmt  = conne.prepareStatement(sql);
		 rs     = stmt.executeQuery();	
		 System.out.print("Données recuperées OK\n");
		 while( rs .next()){
			   System.out.println( rs .getInt("accountNumber"));
		       System.out.println( rs .getFloat("solde"));
		       System.out.println( rs .getDate("creationDate"));      
		       }
		 
			while (rs.next()) {
				Account A =null;
				A = new Account(rs.getInt("accountNumber"),rs.getFloat("solde"),rs.getString("creationDate"));
				A.setAcountNumber(rs.getInt("accountNumber"));
				A.setSolde(rs.getFloat("solde"));
				A.setCreationDate(rs.getString("creationDate"));
				accounts.add(A);
			}
			
			return accounts;
			
	}
	
	/*public void getAllAccount(int accountNumber,float solde,String creationDate) throws ClassNotFoundException, SQLException {
		
		
		try{
			//sql = "UPDATE Account SET solde =?,creationDate =? WHERE  accountNumber=?";
			sql = "select* from account";
			System.out.println(sql);
			conne = GestionBDD.Connection_DataBase();
			stmt  = conne.prepareStatement(sql);
			stmt.executeUpdate();
			//System.out.print("Données update OK");
		}
		catch (SQLException e) { 
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();  
		}finally {
			try {
				conne.close();
				stmt.close();
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	*/
	

}
