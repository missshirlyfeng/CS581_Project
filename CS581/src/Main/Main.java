package Main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Common.Query;

public class Main {
	public static void main(String[] args) {

//		dbTier db = new dbTier();
//		
//
//		String sql = "select * from sfpark_node";
//		ResultSet rs = db.ExecuteNonScalarQuery(sql);
//		sql = "insert into sfpark_node values((SELECT max(node_id)+1 FROM "
//				+ "(select node_id from sfpark_node) as temp)"
//				+ ",'ss','dd','ff');";
//		// ArrayList<String> sqls = new ArrayList<String>();
//		// sqls.add(sql);
//		// sqls.add(sql);
//		// db.TransCommit(sqls);
//
//		db.ExecuteActionQuery(sql);
//		db.Close();
//		db.ExecuteActionQuery(sql);
		//Node nd= new Node();
		
		Query qry= new Query();
		
		ArrayList<Integer> test=qry.GetAdjNodes(7001);
	
		for(int i:test)
		{
			System.out.println(i);
		}
		
		
		System.out.println(qry.getShortestBlockStringField("longitude_1", 7001, 7005));
		
		System.out.println(qry.getShortestBlockStringField("block_id", 7001, 7005));
	}
}