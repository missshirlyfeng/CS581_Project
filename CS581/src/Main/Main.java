package Main;

import java.sql.ResultSet;
import java.util.ArrayList;

import DBControl.dbTier;

public class Main {
	public static void main(String[] args) {

		dbTier db = new dbTier();
		
		int testgit=0;

		String sql = "select * from sfpark_node";
		ResultSet rs = db.ExecuteNonScalarQuery(sql);
		sql = "insert into sfpark_node values((SELECT max(node_id)+1 FROM "
				+ "(select node_id from sfpark_node) as temp)"
				+ ",'ss','dd','ff');";
		// ArrayList<String> sqls = new ArrayList<String>();
		// sqls.add(sql);
		// sqls.add(sql);
		// db.TransCommit(sqls);

		db.ExecuteActionQuery(sql);
		db.Close();
		db.ExecuteActionQuery(sql);
	}
}