package Main;

import java.util.ArrayList;

import Common.Query;
import GraphAlgorithm.Graph;
import Storage.AdjMatrixGraph;

public class Main {
	public static void main(String[] args) {
//        syso
		// dbTier db = new dbTier();
		//
		//
		// String sql = "select * from sfpark_node";
		// ResultSet rs = db.ExecuteNonScalarQuery(sql);
		// sql = "insert into sfpark_node values((SELECT max(node_id)+1 FROM "
		// + "(select node_id from sfpark_node) as temp)"
		// + ",'ss','dd','ff');";
		// // ArrayList<String> sqls = new ArrayList<String>();
		// // sqls.add(sql);
		// // sqls.add(sql);
		// // db.TransCommit(sqls);
		//
		// db.ExecuteActionQuery(sql);
		// db.Close();
		// db.ExecuteActionQuery(sql);
		// Node nd= new Node();

		Query qry = new Query();

		// ArrayList<Integer> test=qry.GetAdjNodes(7001);
		ArrayList<ArrayList<Integer>> test = qry.GetIntersByBlockID(612311);
		for (ArrayList<Integer> row : test) {
			for (int col : row)
				System.out.println(col);
		}

		System.out.println(qry.getShortestBlockStringField("longitude_1", 7001,
				7005));

		System.out.println(qry.getShortestBlockStringField("block_id", 7001,
				7005));

		System.out.println(qry.GetIntersByBlockID(612311));

		int V = qry.GetNodeNumber();
		int E = qry.GetEdgeNumber();
		Graph G = new Graph(V);
		G.UpdateFromDB();
		G.PrintWeightMatrix();
	}
}