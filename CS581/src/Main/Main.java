package Main;

import java.util.ArrayList;

import Storage.AdjMatrixGraph;
import Common.Query;

public class Main {
	public static void main(String[] args) {

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
		AdjMatrixGraph G = new AdjMatrixGraph(V);
		int node_id = 7001;
		for (int i = 0; i < V; ++i) {
			int node1 = node_id + i;
			// node i has no adjacent node. Continue to update the next node's
			// adjNodes
			if (qry.GetAdjNodes(node1).size() == 0) {
				continue;
			}
			for (int j = 0; j < V; ++j) {

				// record the weight of from the current node to the adjacent
				// one
				ArrayList<Integer> adjNodes = qry.GetAdjNodes(node1);
				for (Integer node2 : adjNodes) {
					G.addEdge(i, node2 - node_id, Float.valueOf(qry
							.getShortestBlockStringField("distance", node1,
									node2)));
				}
			}
		}
		G.PrintWeightMatrix();
	}
}